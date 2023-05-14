package masters.aggregatorservice.service.impl;

import masters.aggregatorservice.entity.Language;
import masters.aggregatorservice.entity.Tool;
import masters.aggregatorservice.service.AverageLatencyService;
import masters.aggregatorservice.service.LanguageQueryService;
import masters.aggregatorservice.service.ToolQueryService;
import masters.aggregatorservice.service.dto.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// TODO: Possibly dynamic adding of tools during runtime
@Service
public class AverageLatencyServiceImpl implements AverageLatencyService {

    private static final int HISTORY_SIZE = 100;

    private static final double COEFFICIENT = 0.55;

    private final Map<String, Map<String, Triple<List<Double>, Lock, Lock>>> languageToolLatenciesLocksMap;

    @Autowired
    public AverageLatencyServiceImpl(LanguageQueryService languageQueryService, ToolQueryService toolQueryService) {
        final List<Language> languages = languageQueryService.findAll();

        final Map<String, Map<String, Triple<List<Double>, Lock, Lock>>> languageToolLatencyMap = new ConcurrentHashMap<>();
        for (Language language : languages) {
            final List<Tool> tools = toolQueryService.findByQuery(language, Collections.emptySet());

            final Map<String, Triple<List<Double>, Lock, Lock>> toolLatencyMap = new ConcurrentHashMap<>();
            for (Tool tool : tools) {
                toolLatencyMap.put(tool.getName(), createReadWriteLockLatencyListTriple());
            }

            languageToolLatencyMap.put(language.getName(), toolLatencyMap);
        }

        this.languageToolLatenciesLocksMap = languageToolLatencyMap;
    }

    @Override
    public void recordLatency(Language language, Tool tool, double latency) {
        final Map<String, Triple<List<Double>, Lock, Lock>> toolLatenciesLocksMap =
            languageToolLatenciesLocksMap.computeIfAbsent(language.getName(), s -> new ConcurrentHashMap<>());
        final Triple<List<Double>, Lock, Lock> latenciesLocksTriple =
            toolLatenciesLocksMap.computeIfAbsent(tool.getName(), s -> createReadWriteLockLatencyListTriple());

        final List<Double> latencyList = latenciesLocksTriple.getFirst();
        final Lock writeLock = latenciesLocksTriple.getSecond();

        try {
            writeLock.lock();

            latencyList.add(latency);
            if (latencyList.size() > HISTORY_SIZE) {
                latencyList.remove(0);
            }
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public Optional<Double> calculateLatency(String languageName, String toolName) {
        final Map<String, Triple<List<Double>, Lock, Lock>> toolLatenciesLocksMap =
            languageToolLatenciesLocksMap.getOrDefault(languageName, Collections.emptyMap());
        final Triple<List<Double>, Lock, Lock> latenciesLocksTriple = toolLatenciesLocksMap.get(toolName);

        if (Objects.isNull(latenciesLocksTriple)) {
            return Optional.empty();
        }

        final List<Double> latencyList = latenciesLocksTriple.getFirst();
        final Lock readLock = latenciesLocksTriple.getThird();

        try {
            readLock.lock();

            if (latencyList.isEmpty()) {
                return Optional.empty();
            }

            double exponentialMovingAverage = latencyList.get(0);
            for (int i = 1; i < latencyList.size(); i++) {
                exponentialMovingAverage = (latencyList.get(i) * COEFFICIENT) + (exponentialMovingAverage * (1 - COEFFICIENT));
            }

            return Optional.of(exponentialMovingAverage);
        } finally {
            readLock.unlock();
        }
    }

    private Triple<List<Double>, Lock, Lock> createReadWriteLockLatencyListTriple() {
        final ArrayList<Double> latencyList = new ArrayList<>();

        final ReadWriteLock lock = new ReentrantReadWriteLock();
        final Lock writeLock = lock.writeLock();
        final Lock readLock = lock.readLock();

        return new Triple<>(latencyList, writeLock, readLock);
    }

}

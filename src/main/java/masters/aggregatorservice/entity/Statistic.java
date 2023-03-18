package masters.aggregatorservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "rule_id", foreignKey = @ForeignKey(foreignKeyDefinition = "statistic_rule_violation_foreign_key"))
    private RuleViolation ruleViolation;

    @Column(nullable = false)
    private Long occurrence;

    public Statistic(RuleViolation ruleViolation, Long occurrence) {
        this.ruleViolation = ruleViolation;
        this.occurrence = occurrence;
    }
}

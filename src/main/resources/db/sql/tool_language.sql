INSERT INTO TOOL_LANGUAGE(tool_id, language_id)
VALUES ((SELECT t.id FROM tool t WHERE t.name = 'Cppcheck'), (SELECT l.id FROM language l WHERE l.name = 'c')),
       ((SELECT t.id FROM tool t WHERE t.name = 'Cppcheck'), (SELECT l.id FROM language l WHERE l.name = 'cpp')),
       ((SELECT t.id FROM tool t WHERE t.name = 'Infer'), (SELECT l.id FROM language l WHERE l.name = 'c')),
       ((SELECT t.id FROM tool t WHERE t.name = 'Infer'), (SELECT l.id FROM language l WHERE l.name = 'cpp')),
       ((SELECT t.id FROM tool t WHERE t.name = 'Infer'), (SELECT l.id FROM language l WHERE l.name = 'java')),
       ((SELECT t.id FROM tool t WHERE t.name = 'PMD'), (SELECT l.id FROM language l WHERE l.name = 'java')),
       ((SELECT t.id FROM tool t WHERE t.name = 'PMD'), (SELECT l.id FROM language l WHERE l.name = 'javascript')),
       ((SELECT t.id FROM tool t WHERE t.name = 'Checkstyle'), (SELECT l.id FROM language l WHERE l.name = 'java')),
       ((SELECT t.id FROM tool t WHERE t.name = 'Sonarqube'), (SELECT l.id FROM language l WHERE l.name = 'java')),
       ((SELECT t.id FROM tool t WHERE t.name = 'Sonarqube'), (SELECT l.id FROM language l WHERE l.name = 'c')),
       ((SELECT t.id FROM tool t WHERE t.name = 'Sonarqube'),
        (SELECT l.id FROM language l WHERE l.name = 'javascript')),
       ((SELECT t.id FROM tool t WHERE t.name = 'EsLint'), (SELECT l.id FROM language l WHERE l.name = 'javascript'));
-- INFER java
INSERT INTO rule_violation(rule_sarif_id, language_id, tool_id)
VALUES ('UNINITIALIZED_VALUE', (SELECT l.id FROM language l WHERE l.name = 'java'),  (SELECT t.id FROM tool t WHERE t.name = 'Infer')),
       ('NULL_DEREFERENCE', (SELECT l.id FROM language l WHERE l.name = 'java'),  (SELECT t.id FROM tool t WHERE t.name = 'Infer')),
       ('DEAD_STORE', (SELECT l.id FROM language l WHERE l.name = 'java'),  (SELECT t.id FROM tool t WHERE t.name = 'Infer')),
       ('RESOURCE_LEAK', (SELECT l.id FROM language l WHERE l.name = 'java'),  (SELECT t.id FROM tool t WHERE t.name = 'Infer')),
       ('DIVIDE_BY_ZERO', (SELECT l.id FROM language l WHERE l.name = 'java'),  (SELECT t.id FROM tool t WHERE t.name = 'Infer')),
       ('INTEGER_OVERFLOW_L1', (SELECT l.id FROM language l WHERE l.name = 'java'),  (SELECT t.id FROM tool t WHERE t.name = 'Infer')),
       ('INTEGER_OVERFLOW_L2', (SELECT l.id FROM language l WHERE l.name = 'java'),  (SELECT t.id FROM tool t WHERE t.name = 'Infer')),
       ('INTEGER_OVERFLOW_L5', (SELECT l.id FROM language l WHERE l.name = 'java'),  (SELECT t.id FROM tool t WHERE t.name = 'Infer')),
       ('INTEGER_OVERFLOW_U5', (SELECT l.id FROM language l WHERE l.name = 'java'),  (SELECT t.id FROM tool t WHERE t.name = 'Infer')),
       ('INEFFICIENT_KEYSET_ITERATOR', (SELECT l.id FROM language l WHERE l.name = 'java'),  (SELECT t.id FROM tool t WHERE t.name = 'Infer')),
       ('POINTER_TO_INTEGRAL_IMPLICIT_CAST', (SELECT l.id FROM language l WHERE l.name = 'java'),  (SELECT t.id FROM tool t WHERE t.name = 'Infer'));

       -- INFER c
INSERT INTO rule_violation(rule_sarif_id, language_id, tool_id)
VALUES ('UNINITIALIZED_VALUE', (SELECT l.id FROM language l WHERE l.name = 'c'),  (SELECT t.id FROM tool t WHERE t.name = 'Infer')),
       ('NULL_DEREFERENCE', (SELECT l.id FROM language l WHERE l.name = 'c'),  (SELECT t.id FROM tool t WHERE t.name = 'Infer')),
       ('DEAD_STORE', (SELECT l.id FROM language l WHERE l.name = 'c'),  (SELECT t.id FROM tool t WHERE t.name = 'Infer')),
       ('RESOURCE_LEAK', (SELECT l.id FROM language l WHERE l.name = 'c'),  (SELECT t.id FROM tool t WHERE t.name = 'Infer')),
       ('DIVIDE_BY_ZERO', (SELECT l.id FROM language l WHERE l.name = 'c'),  (SELECT t.id FROM tool t WHERE t.name = 'Infer')),
       ('INTEGER_OVERFLOW_L1', (SELECT l.id FROM language l WHERE l.name = 'c'),  (SELECT t.id FROM tool t WHERE t.name = 'Infer')),
       ('INTEGER_OVERFLOW_L2', (SELECT l.id FROM language l WHERE l.name = 'c'),  (SELECT t.id FROM tool t WHERE t.name = 'Infer')),
       ('INTEGER_OVERFLOW_L5', (SELECT l.id FROM language l WHERE l.name = 'c'),  (SELECT t.id FROM tool t WHERE t.name = 'Infer')),
       ('INTEGER_OVERFLOW_U5', (SELECT l.id FROM language l WHERE l.name = 'c'),  (SELECT t.id FROM tool t WHERE t.name = 'Infer')),
       ('INEFFICIENT_KEYSET_ITERATOR', (SELECT l.id FROM language l WHERE l.name = 'c'),  (SELECT t.id FROM tool t WHERE t.name = 'Infer')),
       ('POINTER_TO_INTEGRAL_IMPLICIT_CAST', (SELECT l.id FROM language l WHERE l.name = 'c'),  (SELECT t.id FROM tool t WHERE t.name = 'Infer'));
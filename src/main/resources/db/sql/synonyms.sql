-- CPPCHECK AND INFER
INSERT INTO synonyms(base_id, synonym_id)
VALUES ((SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'uninitvar'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Cppcheck')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'c')),
        (SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'UNINITIALIZED_VALUE'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Infer')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'c'))),

       ((SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'nullPointer'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Cppcheck')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'c')),
        (SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'NULL_DEREFERENCE'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Infer')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'c'))),

       ((SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'resourceLeak'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Cppcheck')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'c')),
        (SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'RESOURCE_LEAK'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Infer')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'c'))),

       ((SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'zerodiv'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Cppcheck')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'c')),
        (SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'DIVIDE_BY_ZERO'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Infer')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'c'))),

       ((SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'integerOverflow'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Cppcheck')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'c')),
        (SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'INTEGER_OVERFLOW_L1'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Infer')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'c'))),

       ((SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'integerOverflow'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Cppcheck')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'c')),
        (SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'INTEGER_OVERFLOW_L2'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Infer')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'c'))),

       ((SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'integerOverflow'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Cppcheck')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'c')),
        (SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'INTEGER_OVERFLOW_L5'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Infer')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'c'))),

       ((SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'integerOverflow'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Cppcheck')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'c')),
        (SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'INTEGER_OVERFLOW_U5'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Infer')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'c'))),

       ((SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'uninitStructMember'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Cppcheck')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'c')),
        (SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'UNINITIALIZED_VALUE'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Infer')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'c'))),

       ((SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'unusedVariable'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Cppcheck')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'c')),
        (SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'DEAD_STORE'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Infer')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'c'))),

       ((SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'unusedAllocatedMemory'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Cppcheck')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'c')),
        (SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'DEAD_STORE'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Infer')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'c')));

-- PMD-CHECKSTYLE SYNONYMS
INSERT INTO synonyms(base_id, synonym_id)
VALUES ((SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'UnusedLocalVariable'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'PMD')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'java')),
        (SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'unused.local.var'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Checkstyle')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'java'))),

       ((SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'MethodNamingConventions'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'PMD')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'java')),
        (SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'name.invalidPattern'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Checkstyle')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'java'))),

       ((SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'ControlStatementBraces'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'PMD')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'java')),
        (SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'needBraces'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Checkstyle')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'java'))),

       ((SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'UnnecessaryImport'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'PMD')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'java')),
        (SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'import.unused'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Checkstyle')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'java'))),

       ((SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'UnnecessaryImport'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'PMD')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'java')),
        (SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'import.duplicate'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Checkstyle')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'java'))),

       ((SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'ClassNamingConventions'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'PMD')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'java')),
        (SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'name.invalidPattern'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Checkstyle')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'java'))),

       ((SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'LogicInversion'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'PMD')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'java')),
        (SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'simplify.expression'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Checkstyle')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'java'))),

       ((SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'SimplifyConditional'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'PMD')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'java')),
        (SELECT id
         FROM rule_violation
         WHERE rule_sarif_id = 'simplify.expression'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Checkstyle')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'java')));

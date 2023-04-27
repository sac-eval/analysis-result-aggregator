INSERT INTO CUSTOM_MESSAGE(rule_id, message, message_type)
VALUES ((SELECT r.id
         FROM rule_violation r
         WHERE rule_sarif_id = 'nullPointer'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Cppcheck')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'c')),
        '<h4>This is created by accessing an address that is pointing to a protected area in memory!</h4><p>Look if you initialize it correctly!</p>',
        'HTML'),
       ((SELECT r.id
         FROM rule_violation r
         WHERE rule_sarif_id = 'nullPointer'
           AND r.tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Cppcheck')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'c')),
        '## This is created by accessing an address that is pointing to a protected area in memory!\n\n Look if you initialize it correctly!',
        'MARKDOWN'),
       ((SELECT r.id
         FROM rule_violation r
         WHERE rule_sarif_id = 'nullPointer'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Cppcheck')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'c')),
        'This is created by accessing an address that is pointing to a protected area in memory! Look if you initialize it correctly!',
        'TEXT'),
       ((SELECT r.id
         FROM rule_violation r
         WHERE rule_sarif_id = 'NULL_DEREFERENCE'
           AND tool_id = (SELECT t.id FROM tool t WHERE t.name = 'Infer')
           AND language_id = (SELECT l.id FROM language l WHERE l.name = 'c')),
        '<h4>Watch out! Accessing an address that is pointing to a protected area in memory!</h4><p>Care to initialize it correctly!</p>',
        'HTML');

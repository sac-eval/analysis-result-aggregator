-- CPPCHECK
INSERT INTO rule_violation(rule_id, tool)
VALUES ('uninitvar', 'Cppcheck'),
       ('syntaxError', 'Cppcheck'),
       ('internalAstError', 'Cppcheck'),
       ('legacyUninitvar', 'Cppcheck'),
       ('arrayIndexOutOfBounds', 'Cppcheck'),
       ('resourceLeak', 'Cppcheck'),
       ('returnDanglingLifetime', 'Cppcheck'),
       ('writeReadOnlyFile', 'Cppcheck'),
       ('IOWithoutPositioning', 'Cppcheck'),
       ('missingReturn', 'Cppcheck'),
       ('bufferAccessOutOfBounds', 'Cppcheck'),
       ('unknownEvaluationOrder', 'Cppcheck'),
       ('negativeIndex', 'Cppcheck'),
       ('invalidFunctionArgStr', 'Cppcheck'),
       ('integerOverflow', 'Cppcheck'),
       ('nullPointer', 'Cppcheck'),
       ('wrongPrintfScanfArgNum', 'Cppcheck'),
       ('leakReturnValNotUsed', 'Cppcheck'),
       ('zerodiv', 'Cppcheck'),
       ('readWriteOnlyFile', 'Cppcheck'),
       ('leakNoVarFunctionCall', 'Cppcheck'),
       ('autoVariables', 'Cppcheck'),
       ('danglingLifetime', 'Cppcheck'),
       ('unknownMacro', 'Cppcheck'),
       ('danglingTemporaryLifetime', 'Cppcheck'),
       ('uninitStructMember', 'Cppcheck'),
       ('comparePointers', 'Cppcheck'),
       ('ctuuninitvar', 'Cppcheck'),
       ('nullPointerArithmetic', 'Cppcheck');

-- INFER
INSERT INTO rule_violation(rule_id, tool)
VALUES ('UNINITIALIZED_VALUE', 'Infer'),
       ('NULL_DEREFERENCE', 'Infer'),
       ('DEAD_STORE', 'Infer'),
       ('RESOURCE_LEAK', 'Infer'),
       ('POINTER_TO_INTEGRAL_IMPLICIT_CAST', 'Infer');

-- CPPCHECK INFER SYNONYMS
INSERT INTO synonyms(id, synonym_id)
VALUES (SELECT id FROM rule_violation WHERE rule_id = 'uninitvar' AND tool = 'Cppcheck',
           SELECT id FROM rule_violation WHERE rule_id = 'UNINITIALIZED_VALUE' AND tool = 'Infer'),
       (SELECT id FROM rule_violation WHERE rule_id = 'UNINITIALIZED_VALUE' AND tool = 'Infer',
           SELECT id FROM rule_violation WHERE rule_id = 'uninitvar' AND tool = 'Cppcheck'),
       (SELECT id FROM rule_violation WHERE rule_id = 'nullPointer' AND tool = 'Cppcheck',
           SELECT id FROM rule_violation WHERE rule_id = 'NULL_DEREFERENCE' AND tool = 'Infer'),
       (SELECT id FROM rule_violation WHERE rule_id = 'NULL_DEREFERENCE' AND tool = 'Infer',
           SELECT id FROM rule_violation WHERE rule_id = 'nullPointer' AND tool = 'Cppcheck'),
       (SELECT id FROM rule_violation WHERE rule_id = 'resourceLeak' AND tool = 'Cppcheck',
           SELECT id FROM rule_violation WHERE rule_id = 'RESOURCE_LEAK' AND tool = 'Infer'),
       (SELECT id FROM rule_violation WHERE rule_id = 'RESOURCE_LEAK' AND tool = 'Infer',
           SELECT id FROM rule_violation WHERE rule_id = 'resourceLeak' AND tool = 'Cppcheck')

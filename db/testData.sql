insert into slightBlacklist(UUID, ruleType, ruleName, content) values 
('2145435435432543', 'MissData', 'FieldIncoherent', '[{"tableName":"Production","tableField":"productionNo","fieldValue":"1111111"}]');

insert into MissData_FieldIncoherent(UUID, ruleUUID, host, port, databaseName, tableName, fieldName) values 
('4363563423522534', '90000001', 'cd', '3306', 'iCRM_CustomerDB_4850_test1', 'Production', 'productionNo');
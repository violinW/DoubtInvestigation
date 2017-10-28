//规则类型表基础数据
delete from ruleType;
insert into ruleType(UUID, typeName, typeIntroduction) values 
('80000001', 'MissData', '数据丢失型规则类型'), 
('80000002', 'MissData', '数据丢失型规则类型'), 
('80000003', 'DataWrong', '数据错误型规则类型');

//规则表面基础数据
delete from rule;
insert into rule(UUID, typeUUID, ruleName, typeIntroduction) values 
('90000001','80000001', 'FieldIncoherent', '数据某字段连续性缺失')

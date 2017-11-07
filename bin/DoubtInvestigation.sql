-- -----------------------------------------------------
-- Table `DoubtInvestigation`.`slightBlacklist`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DoubtInvestigation`.`slightBlacklist`;
CREATE TABLE IF NOT EXISTS `DoubtInvestigation`.`slightBlacklist` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `UUID` VARCHAR(45) UNIQUE NOT NULL,
  `ruleType` VARCHAR(45) NOT NULL COMMENT '规则类型',
  `ruleName` VARCHAR(45) NOT NULL COMMENT '规则名称',
  `type` ENUM('single', 'mutliple') NOT NULL DEFAULT 'single' COMMENT '规则名称',
  `databaseName` VARCHAR(45) NOT NULL COMMENT '数据库名称',
  `tableName` VARCHAR(45) NOT NULL COMMENT '表名',
  `fieldName` VARCHAR(45) NOT NULL COMMENT '字段名',
  `fieldValue` VARCHAR(45) COMMENT '字段值(仅限于type为single时使用)',
  `startNo` VARCHAR(45) COMMENT '数据开始Id(仅限于type为mutiple时使用)',
  `endNo` VARCHAR(45) COMMENT '数据结束Id(仅限于type为mutiple时使用)',
  `content` TEXT NOT NULL COMMENT '具体内容',
  PRIMARY KEY (`id`, `UUID`))
ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT = '轻度黑名单';

-- -----------------------------------------------------
-- Table `DoubtInvestigation`.`seriousBlacklist`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DoubtInvestigation`.`seriousBlacklist`;
CREATE TABLE IF NOT EXISTS `DoubtInvestigation`.`seriousBlacklist` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `UUID` VARCHAR(45) UNIQUE NOT NULL,
  `ruleType` VARCHAR(45) NOT NULL COMMENT '规则类型',
  `ruleName` VARCHAR(45) NOT NULL COMMENT '规则名称',
  `type` ENUM('single', 'multiple') NOT NULL DEFAULT 'single' COMMENT '规则名称',
  `databaseName` VARCHAR(45) NOT NULL COMMENT '数据库名称',
  `tableName` VARCHAR(45) NOT NULL COMMENT '表名',
  `fieldName` VARCHAR(45) NOT NULL COMMENT '字段名',
  `fieldValue` VARCHAR(45) COMMENT '字段值(仅限于type为single时使用)',
  `startNo` VARCHAR(45) COMMENT '数据开始Id(仅限于type为mutiple时使用)',
  `endNo` VARCHAR(45) COMMENT '数据结束Id(仅限于type为mutiple时使用)',
  `content` TEXT NOT NULL COMMENT '具体内容',
  PRIMARY KEY (`id`, `UUID`))
ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT = '重度黑名单';

-- -----------------------------------------------------
-- Table `DoubtInvestigation`.`whitelist`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DoubtInvestigation`.`whitelist`;
CREATE TABLE IF NOT EXISTS `DoubtInvestigation`.`whitelist` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `UUID` VARCHAR(45) UNIQUE NOT NULL,
  `ruleType` VARCHAR(45) NOT NULL COMMENT '规则类型',
  `ruleName` VARCHAR(45) NOT NULL COMMENT '规则名称',
  `type` ENUM('single', 'multiple') NOT NULL DEFAULT 'single' COMMENT '规则名称',
  `databaseName` VARCHAR(45) NOT NULL COMMENT '数据库名称',
  `tableName` VARCHAR(45) NOT NULL COMMENT '表名',
  `fieldName` VARCHAR(45) NOT NULL COMMENT '字段名',
  `fieldValue` VARCHAR(45) NOT NULL COMMENT '字段值(仅限于type为single时使用)',
  `startNo` VARCHAR(45) NOT NULL COMMENT '数据开始Id(仅限于type为mutiple时使用)',
  `endNo` VARCHAR(45) NOT NULL COMMENT '数据结束Id(仅限于type为mutiple时使用)',
  `content` TEXT NOT NULL COMMENT '具体内容',
  PRIMARY KEY (`id`, `UUID`))
ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT = '白名单';

-- -----------------------------------------------------
-- Table `DoubtInvestigation`.`ruleTyle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DoubtInvestigation`.`ruleType`;
CREATE TABLE IF NOT EXISTS `DoubtInvestigation`.`ruleType` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `UUID` VARCHAR(45) UNIQUE NOT NULL,
  `typeName` VARCHAR(45) NOT NULL COMMENT '规则类型名称',
  `typeIntroduction` TEXT NOT NULL COMMENT '简介',
  PRIMARY KEY (`id`, `UUID`))
ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT = '规则类型表';

-- -----------------------------------------------------
-- Table `DoubtInvestigation`.`rule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DoubtInvestigation`.`rule`;
CREATE TABLE IF NOT EXISTS `DoubtInvestigation`.`rule` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `UUID` VARCHAR(45) UNIQUE NOT NULL,
  `typeUUID` VARCHAR(45) NOT NULL COMMENT '规则类型UUID',
  `ruleName` VARCHAR(45) NOT NULL COMMENT '规则名称',
  `ruleIntroduction` TEXT NOT NULL COMMENT '规则简介',
  PRIMARY KEY (`id`, `UUID`))
ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT = '规则类型表';

CREATE TABLE IF NOT EXISTS `ipcs`.`activity` (
  `activity_objid` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `location` VARCHAR(255) NULL,
  `description` VARCHAR(255) NULL,
  `host` INT NULL,
  `school_fk` INT NULL,
  `activity_type_fk` INT NULL,
  `start_time` TIMESTAMP NULL,
  `end_time` TIMESTAMP NULL,
  `date_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`activity_objid`))
ENGINE = InnoDB
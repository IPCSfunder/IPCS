CREATE TABLE IF NOT EXISTS `ipcs`.`person` (
  `person_objid` INT NOT NULL AUTO_INCREMENT,
  `account_name` VARCHAR(45) NULL,
  `password_hash` VARCHAR(45) NULL,
  `date_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`person_objid`),
  UNIQUE INDEX `person_objid_UNIQUE` (`person_objid` ASC),
  UNIQUE INDEX `account_name_UNIQUE` (`account_name` ASC)
  )
ENGINE = InnoDB
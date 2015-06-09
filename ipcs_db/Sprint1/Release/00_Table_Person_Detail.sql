CREATE TABLE IF NOT EXISTS `ipcs`.`person_detail` (
  `person_detail_objid` INT NOT NULL AUTO_INCREMENT,
  `last_name` VARCHAR(45) NULL,
  `first_name` VARCHAR(45) NULL,
  `age` INT NULL,
  `sex` VARCHAR(10) NULL,
  `nationality` VARCHAR(45) NULL,
  `dob` DATE NULL,
  `nric` VARCHAR(45) NULL,
  `nick_name` VARCHAR(45) NULL,
  `address` VARCHAR(255) NULL,
  `postcode` VARCHAR(45) NULL,
  `market_option` BOOLEAN NULL,
  `date_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`person_detail_objid`),
  UNIQUE INDEX `person_detail_objid_UNIQUE` (`person_detail_objid` ASC)
  )
ENGINE = InnoDB

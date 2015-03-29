CREATE TABLE IF NOT EXISTS `ipcs`.`contact` (
  `contact_objid` INT NOT NULL  AUTO_INCREMENT,
  `address` VARCHAR(255) NULL,
  `mobile_number` VARCHAR(45) NULL,
  `email_address` VARCHAR(45) NULL,
  `date_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`contact_objid`),
  UNIQUE INDEX `contact_objid_UNIQUE` (`contact_objid` ASC))
ENGINE = InnoDB

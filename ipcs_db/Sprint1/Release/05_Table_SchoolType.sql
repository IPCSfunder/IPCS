CREATE TABLE IF NOT EXISTS `ipcs`.`school_type` (
  `school_type_objid` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `date_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`school_type_objid`),
  UNIQUE INDEX `school_type_objid_UNIQUE` (`school_type_objid` ASC))
ENGINE = InnoDB
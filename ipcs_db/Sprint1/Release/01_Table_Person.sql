CREATE TABLE IF NOT EXISTS `ipcs`.`person` (
  `person_objid` INT NOT NULL AUTO_INCREMENT,
  `account_name` VARCHAR(45) NULL,
  `password_hash` VARCHAR(45) NULL,
  `person_detail_fk` INT null,
  `school_fk` INT null,
  `date_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`person_objid`),
  UNIQUE INDEX `person_objid_UNIQUE` (`person_objid` ASC),
  UNIQUE INDEX `account_name_UNIQUE` (`account_name` ASC),
  CONSTRAINT `fk_person_detail`
    FOREIGN KEY (`person_detail_fk`)
    REFERENCES `ipcs`.`person_detail` (`person_detail_objid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
  )
ENGINE = InnoDB
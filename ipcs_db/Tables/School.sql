

CREATE TABLE IF NOT EXISTS `ipcs`.`school` (
  `school_objid` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `school_type_fk` INT NOT NULL,
  `date_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`school_objid`),
  UNIQUE INDEX `school_objid_UNIQUE` (`school_objid` ASC),
  INDEX `fk__school_type1_idx` (`school_type_fk` ASC),
  CONSTRAINT `fk_school_school_type1`
    FOREIGN KEY (`school_type_fk`)
    REFERENCES `ipcs`.`school_type` (`school_type_objid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
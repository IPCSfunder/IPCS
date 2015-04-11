CREATE TABLE IF NOT EXISTS `ipcs`.`person_school` (
  `person_school_objid` INT NOT NULL AUTO_INCREMENT,
  `person_fk` INT NOT NULL,
  `school_fk` INT NOT NULL,
  `date_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 
  PRIMARY KEY (`person_school_objid`),
  INDEX `fk_person_has_school_school1_idx` (`school_fk` ASC),
  INDEX `fk_person_has_school_person1_idx` (`person_fk` ASC),
  UNIQUE INDEX `person_school_objid_UNIQUE` (`person_school_objid` ASC),
  CONSTRAINT `fk_person_has_school_person1`
    FOREIGN KEY (`person_fk`)
    REFERENCES `ipcs`.`person` (`person_objid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_has_school_school1`
    FOREIGN KEY (`school_fk`)
    REFERENCES `ipcs`.`school` (`school_objid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
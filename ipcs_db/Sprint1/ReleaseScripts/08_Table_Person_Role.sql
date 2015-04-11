CREATE TABLE IF NOT EXISTS `ipcs`.`person_role` (
  `person_role_objid` INT NOT NULL AUTO_INCREMENT,
  `person_fk` INT NULL,
  `role_fk` INT NULL,
  PRIMARY KEY (`person_role_objid`),
  INDEX `fk_person_role_role1_idx` (`role_fk` ASC),
  INDEX `fk_person_role_person1_idx` (`person_fk` ASC),
  CONSTRAINT `fk_person_role_role1`
    FOREIGN KEY (`role_fk`)
    REFERENCES `ipcs`.`role` (`role_objid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_role_person1`
    FOREIGN KEY (`person_fk`)
    REFERENCES `ipcs`.`person` (`person_objid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
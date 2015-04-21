CREATE TABLE IF NOT EXISTS `ipcs`.`schedule` (
  `schedule_objid` INT NOT NULL AUTO_INCREMENT,
  `activity_fk` INT NULL,
  `person_fk` INT NULL,
  PRIMARY KEY (`schedule_objid`),
  INDEX `fk_activity_fk_idx` (`activity_fk` ASC),
  INDEX `fk_person_fk_idx` (`person_fk` ASC),
  CONSTRAINT `fk_activity_ct`
    FOREIGN KEY (`activity_fk`)
    REFERENCES `ipcs`.`activity` (`activity_objid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_ct`
    FOREIGN KEY (`person_fk`)
    REFERENCES `ipcs`.`person` (`person_objid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
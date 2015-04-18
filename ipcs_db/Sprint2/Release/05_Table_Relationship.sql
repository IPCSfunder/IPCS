CREATE TABLE IF NOT EXISTS `ipcs`.`relationship` (
  `relationship_objid` INT NOT NULL AUTO_INCREMENT,
  `whose` INT NOT NULL,
  `relationship_type_fk` INT NOT NULL,
  `iswho` INT NOT NULL,
  `date_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`relationship_objid`),
  UNIQUE INDEX `relationship_objid_UNIQUE` (`relationship_objid` ASC),
  INDEX `fk__relatioship_type_idx` (`relationship_type_fk` ASC),
  CONSTRAINT `fk_relatioship_type1`
    FOREIGN KEY (`relationship_type_fk`)
    REFERENCES `ipcs`.`relationship_type` (`relationship_type_objid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_whose1`
    FOREIGN KEY (`whose`)
    REFERENCES `ipcs`.`person` (`person_objid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_iswho1`
    FOREIGN KEY (`iswho`)
    REFERENCES `ipcs`.`person` (`person_objid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    )
ENGINE = InnoDB
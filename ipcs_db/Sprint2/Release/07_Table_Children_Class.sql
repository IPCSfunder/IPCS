CREATE TABLE IF NOT EXISTS `ipcs`.`children_class` (
  `children_class_objid` INT NOT NULL AUTO_INCREMENT,
  `children_fk` INT NULL,
  `class_fk` INT NULL,
  PRIMARY KEY (`children_class_objid`),
  INDEX `fk_children_fk_idx` (`children_fk` ASC),
  INDEX `fk_class_fk_idx` (`class_fk` ASC),
  CONSTRAINT `fk_children`
    FOREIGN KEY (`children_fk`)
    REFERENCES `ipcs`.`person` (`person_objid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_class`
    FOREIGN KEY (`class_fk`)
    REFERENCES `ipcs`.`class` (`class_objid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
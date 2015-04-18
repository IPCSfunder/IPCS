CREATE TABLE IF NOT EXISTS `ipcs`.`class` (
  `class_objid` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `teacher` INT NULL,
  `date_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`class_objid`),
  UNIQUE INDEX `class_objid_UNIQUE` (`class_objid` ASC),
   CONSTRAINT `fk_teacher1`
    FOREIGN KEY (`teacher`)
    REFERENCES `ipcs`.`person` (`person_objid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
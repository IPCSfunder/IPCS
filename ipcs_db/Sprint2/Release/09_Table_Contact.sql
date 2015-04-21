CREATE TABLE IF NOT EXISTS `ipcs`.`contact` (
  `contact_objid` INT NOT NULL  AUTO_INCREMENT,
  `address` VARCHAR(255) NULL,
  `postcode` VARCHAR(45) NULL,
  `mobile_number` VARCHAR(45) NULL,
  `contact_name` VARCHAR(45) NULL,
  `relationship_type_fk` INT NULL,
  `primary_contact` TINYINT(1) NULL,
  `person_fk` INT NULL,
  `email_address` VARCHAR(45) NULL,
  `date_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`contact_objid`),
  UNIQUE INDEX `contact_objid_UNIQUE` (`contact_objid` ASC),
   CONSTRAINT `fk_relationship_type`
    FOREIGN KEY (`relationship_type_fk`)
    REFERENCES `ipcs`.`relationship_type` (`relationship_type_objid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
   CONSTRAINT `fk_person`
    FOREIGN KEY (`person_fk`)
    REFERENCES `ipcs`.`person` (`person_objid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB

CREATE TABLE IF NOT EXISTS `ipcs`.`relationship_type` (
  `relationship_type_objid` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `date_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`relationship_type_objid`),
  UNIQUE INDEX `relationship_type_objid_UNIQUE` (`relationship_type_objid` ASC))
ENGINE = InnoDB
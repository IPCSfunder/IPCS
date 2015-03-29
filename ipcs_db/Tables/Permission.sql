CREATE TABLE IF NOT EXISTS `ipcs`.`permission` (
  `permission_objid` INT NOT NULL  AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `date_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`permission_objid`),
  UNIQUE INDEX `permission_objid_UNIQUE` (`permission_objid` ASC))
ENGINE = InnoDB

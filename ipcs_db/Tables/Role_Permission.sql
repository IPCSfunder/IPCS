
CREATE TABLE IF NOT EXISTS `ipcs`.`role_permission` (
  `role_permission_objid` INT NOT NULL AUTO_INCREMENT,
  `role_fk` INT NULL,
  `permission_fk` INT NULL,
  PRIMARY KEY (`role_permission_objid`),
  INDEX `fk_permission_idx` (`permission_fk` ASC),
  INDEX `fk_role_idx` (`role_fk` ASC),
  CONSTRAINT `fk_role`
    FOREIGN KEY (`role_fk`)
    REFERENCES `ipcs`.`role` (`role_objid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_permission`
    FOREIGN KEY (`permission_fk`)
    REFERENCES `ipcs`.`permission` (`permission_objid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
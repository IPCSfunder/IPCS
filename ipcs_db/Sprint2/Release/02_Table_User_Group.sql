CREATE TABLE IF NOT EXISTS `ipcs`.`user_group` (
  `user_group_objid` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `organizer` INT,
  `group_member` INT NOT NULL,
  `date_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_group_objid`),
  CONSTRAINT `fk_person1`
    FOREIGN KEY (`orginizer`)
    REFERENCES `ipcs`.`person` (`person_objid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_person2`
    FOREIGN KEY (`group_member`)
    REFERENCES `ipcs`.`person` (`person_objid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    )
ENGINE = InnoDB
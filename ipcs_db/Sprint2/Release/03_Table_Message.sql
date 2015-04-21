CREATE TABLE IF NOT EXISTS `ipcs`.`message` (
  `message_objid` INT NOT NULL AUTO_INCREMENT,
  `header` VARCHAR(255) NULL,
  `content` VARCHAR(2500) NULL,
  `from_user` int NULL,
  `group_fk` int NULL,
  `attachment_address` VARCHAR(255) NULL,
  `sent_time` TIMESTAMP NULL,
  `message_type_fk` INT NOT NULL,
  `date_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`message_objid`),
  CONSTRAINT `fk_message_type1`
    FOREIGN KEY (`message_type_fk`)
    REFERENCES `ipcs`.`message_type` (`message_type_objid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_from_user`
    FOREIGN KEY (`from_user`)
    REFERENCES `ipcs`.`person` (`person_objid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_fk`
    FOREIGN KEY (`group_fk`)
    REFERENCES `ipcs`.`user_group` (`user_group_objid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
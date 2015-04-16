CREATE TABLE IF NOT EXISTS `ipcs`.`user_message` (
  `user_messages_objid` INT NOT NULL AUTO_INCREMENT,
  `to_user` INT,
  `from_user` INT NOT NULL,
  `message_fk` INT NOT NULL,
  `date_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_messages_objid`),
  CONSTRAINT `fk_user_messages1`
    FOREIGN KEY (`message_fk`)
    REFERENCES `ipcs`.`message` (`message_objid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_person1`
    FOREIGN KEY (`to_user`)
    REFERENCES `ipcs`.`person` (`person_objid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_person2`
    FOREIGN KEY (`from_user`)
    REFERENCES `ipcs`.`person` (`person_objid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    )
ENGINE = InnoDB
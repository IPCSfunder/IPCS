package com.ipcs.dao;

import com.ipcs.model.Contact;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ipcs.model.Person;

/**
 * @author Chen Chao
 *
 */
@Repository
public class ContactDao extends GenericHibernateDao<Contact, Long> {

}

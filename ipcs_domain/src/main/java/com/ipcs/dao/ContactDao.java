package com.ipcs.dao;

import org.springframework.stereotype.Service;

import com.ipcs.model.Person;

/**
 * @author Chen Chao
 *
 */
@Service
public class ContactDao extends GenericHibernateDao<Person, Long> {

}

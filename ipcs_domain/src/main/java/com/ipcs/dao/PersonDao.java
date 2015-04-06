package com.ipcs.dao;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ipcs.model.Person;

/**
 * @author Chen Chao
 *
 */

@Repository
public class PersonDao extends GenericHibernateDao<Person, Long> {

}

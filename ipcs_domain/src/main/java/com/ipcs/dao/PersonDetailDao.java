package com.ipcs.dao;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ipcs.model.PersonDetail;

/**
 * @author Chen Chao
 *
 */
@Repository
public class PersonDetailDao extends GenericHibernateDao<PersonDetail, Long> {

}

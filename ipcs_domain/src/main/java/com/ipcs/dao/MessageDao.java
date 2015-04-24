package com.ipcs.dao;

import com.ipcs.model.Message;
import org.springframework.stereotype.Repository;

/**
 * @author Chen Chao
 */

@Repository
public class MessageDao extends GenericHibernateDao<Message, Long>{
}

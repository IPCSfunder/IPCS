package com.ipcs.dao;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ipcs.model.Permission;

/**
 * @author Chen Chao
 *
 */
@Repository
public class PermissionDao extends GenericHibernateDao<Permission, Long> {

}

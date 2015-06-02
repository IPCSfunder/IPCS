package com.ipcs.dao;

import com.ipcs.model.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Chen Chao
 */
@Repository
public class PermissionDao extends GenericHibernateDao<Permission, Long> {

    public List<Permission> listPermissionByUserName(String userName) {
        return find("select pe from Person as p inner join p.roles as r inner join r.permissions pe where  p.account_name = '" + userName + "'");
    }

}

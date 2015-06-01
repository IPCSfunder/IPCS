/**
 * 
 */
package com.ipcs.service.impl;


import java.util.List;


import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipcs.dao.PermissionDao;
import com.ipcs.dao.PersonDao;
import com.ipcs.dao.RoleDao;
import com.ipcs.model.Permission;
import com.ipcs.model.Person;
import com.ipcs.model.Role;
import com.ipcs.service.SecurityService;

/**
 * @author Chen Chao
 *
 */

@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private PersonDao personDao;
    
    @Autowired
    private PermissionDao permissionDao;
    
    @Autowired
    private RoleDao roleDao;

   @Transactional
    public long getAuthenticatedUserID(String userName, String password) {
        Criterion[] criterion = { Restrictions.eq("account_name", userName) };
        List<Person> persons = personDao.createCriteria(criterion);
        if (null == persons || persons.size() > 1 || persons.size() == 0)
            return -1;
        if (!persons.get(0).getPassword_hash().equals(password)){
            return -1;
        }
        return persons.get(0).getObjectId();
    }
    @Transactional
    public boolean authenticateLoginInfo(String userName, String password) {
	Criterion[] criterion = { Restrictions.eq("account_name", userName) };
	List<Person> persons = personDao.createCriteria(criterion);
	if (null == persons || persons.size() > 1 || persons.size() == 0)
	    return false;
	return persons.get(0).getPassword_hash().equals(password);
    }

    @Transactional
    public List<Permission> listPermissionsByUserName(String userName) {
	return permissionDao.listPermissionByUserName(userName);
    }

    @Transactional
    public List<Role> listRolesByUserName(String userName) {
	return roleDao.listRolesByUserName(userName);
    }

}

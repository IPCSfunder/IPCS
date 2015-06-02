/**
 *
 */
package com.ipcs.service.impl;

import com.ipcs.dao.*;
import com.ipcs.model.*;
import com.ipcs.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Chen Chao
 */

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Transactional(readOnly=true)
    public Role getRoleByName(String name) {
        return roleDao.findRoleByName(name);
    }

}

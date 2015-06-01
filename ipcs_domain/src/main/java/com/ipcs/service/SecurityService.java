/**
 * 
 */
package com.ipcs.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ipcs.model.Permission;
import com.ipcs.model.Role;

/**
 * @author Chen Chao
 *
 */
public interface SecurityService {

    public long getAuthenticatedUserID(String userName, String password);

    public boolean authenticateLoginInfo(String userName, String password);

    public List<Permission> listPermissionsByUserName(String userName);

    public List<Role> listRolesByUserName(String userName);

}

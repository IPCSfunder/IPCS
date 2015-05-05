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

    public long authenticateLoginInfoWithID(String userName, String password);

    public boolean authenticateLoginInfo(String userName, String password);

    public List<Permission> listPermission(String userName);

    public List<Role> listRole(String userName);

}

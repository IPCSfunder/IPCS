package com.ipcs.model;

import com.ipcs.model.Base.BasicObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Chen Chao
 *
 */
public class Permission extends BasicObject {
	
	private String name;
	
	private List<Role> roles = new ArrayList<Role>();
	
	public Permission(){
		
	}
	
	public Permission(PermissionBuilder builder){
		this.name = builder.name;
	}
	
	public static class PermissionBuilder{
		private String name;
		
		public PermissionBuilder withName(String name){
			this.name = name;
			return this;
		}
		
		public Permission build(){
			return new Permission(this);
		}
	}
	

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public void addRole(Role role){
		roles.add(role);
	}
	
	public int hashCode(){
		int factor = 31;
		return 17*factor+name.hashCode();
	}
	
	public boolean equals(Object obj){
		if(null == obj)
			return false;
		if(this == obj)
			return true;
		if(obj.getClass() != Permission.class)
			return false;
		Permission permission = (Permission)obj;
		return this.name.equals(permission.name);
			
	}
	
	public String toString(){
		return "Permission name is "+ name +super.toString();
	}
	

}

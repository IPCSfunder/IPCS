package com.ipcs.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chen Chao
 *
 */
public class Role extends BasicObject{
	
	private String name;

	private List<Person> persons = new ArrayList<Person>();
	
	private List<Permission> permissions = new ArrayList<Permission>();
	
	public Role(String name){
		super();
		this.name = name;
	}
	
	public Role(){
		super();
	}
	
	public Role(RoleBuilder roleBuilder){
		this.name = roleBuilder.name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static class RoleBuilder{
		
		private String name;
		
		public RoleBuilder withName(String name){
			this.name = name;
			return this;
		}
		
		public Role build(){
			return new Role(this);
		}
	}
	

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public void addPerson(Person person){
		persons.add(person);
	}
	
	public void addPermission(Permission permission){
		permissions.add(permission);
	}
	
	
	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public int hashCode(){
		return 31*17+name.hashCode();
	}
	
	public boolean equals(Object obj){
		if (null ==obj)
			return false;
		if(this == obj)
			return true;
		if(obj.getClass() != Person.class)
			return false;
		Role role = (Role)obj;
		return role.getName().equals(this.name);
	}
	
	public String toString(){
		return "Role name is "+ name +super.toString();
	}

}

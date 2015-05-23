package com.ipcs.model;

import com.ipcs.model.Base.BasicObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Chen Chao
 *
 */
@Entity
@Table(name = "ROLE")
public class Role extends BasicObject {
	private Long objectId;
	
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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_OBJID", unique = true, nullable = false)
	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles",cascade = CascadeType.ALL)
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

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
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

}

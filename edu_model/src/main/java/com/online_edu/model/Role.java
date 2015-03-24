package com.online_edu.model;

import java.util.HashSet;
import java.util.Set;

public class Role extends BasicObject{
	
	private String name;

	private Set<Person> persons = new HashSet<Person>();
	
	public Role(String name){
		super();
		this.name = name;
	}
	
	public Role(){
		super();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public Set<Person> getPersons() {
		return persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	public void addPerson(Person person){
		persons.add(person);
	}
	
	
	public int hashCode(){
		return 31*name.hashCode();
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

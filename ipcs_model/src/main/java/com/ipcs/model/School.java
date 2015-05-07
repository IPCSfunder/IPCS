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
public class School extends BasicObject {
	
	private String name = "";
	
	private String address;
	
	private SchoolType type;

	private List<Person> persons = new ArrayList<Person>();

	private List<Activity> activities = new ArrayList<Activity>();

	public School() {
	}

	public School(String name) {
		this.name = name;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public SchoolType getType() {
		return type;
	}

	public void setType(SchoolType type) {
		this.type = type;
	}
	
	public void addPerson(Person person){
		persons.add(person);
	}
	
	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}


	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public int hashCode(){
		int factor = 31;
		return  17*factor+name.hashCode();
	}
	
	public boolean equals(Object obj){
		if(null == obj)
			return false;
		if(this == obj)
			return true;
		if(obj.getClass() != Permission.class)
			return false;
		School school = (School)obj;
		return this.name.equals(school.name)&&this.type.equals(school.type);
			
	}
	
	public String toString(){
		return "Schoold name is "+ name +" , type is "+type.getName() +super.toString();
	}

	
	
}

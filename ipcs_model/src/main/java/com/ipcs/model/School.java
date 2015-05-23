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
@Table(name = "SCHOOL")
public class School extends BasicObject {
	private Long objectId;
	
	private String name;
	
	private String address;
	
	private SchoolType type;

	private List<Person> persons = new ArrayList<Person>();

	private List<Activity> activities = new ArrayList<Activity>();

	public School() {
		super();
	}

	public School(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SCHOOL_OBJID", unique = true)
	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SCHOOL_TYPE_FK")
	public SchoolType getType() {
		return type;
	}

	public void setType(SchoolType type) {
		this.type = type;
	}

	public void addPersons(Person person){
		persons.add(person);
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "school", cascade =CascadeType.ALL)
	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}



	@OneToMany(fetch = FetchType.LAZY, mappedBy = "school", cascade =CascadeType.ALL)
	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
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

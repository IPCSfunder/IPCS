package com.ipcs.model;

import com.ipcs.model.Base.BasicObject;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Chen Chao
 *
 * Temporary not used. And may not be used any more.
 */
public class
		Class extends BasicObject {

	private String name;

	private Person teacher;

	private Set<Person> childrens = new HashSet<Person>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class(){
	}

	public Class(String name){
	    this.name = name;
	}

	public Person getTeacher() {
		return teacher;
	}

	public void setTeacher(Person teacher) {
		this.teacher = teacher;
	}


	public Set<Person> getChildrens() {
		return childrens;
	}

	public void setChildrens(Set<Person> childrens) {
		this.childrens = childrens;
	}

	public void addChildren(Person children){
		this.childrens.add(children);
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
		Class schoolClass = (Class)obj;
		return this.name.equals(schoolClass.name);
			
	}


	
	public String toString(){
		return "Class name is "+ name +super.toString();
	}

	
	
}

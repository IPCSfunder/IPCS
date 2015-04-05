package com.ipcs.model;

/**
 * @author Chen Chao
 *
 */
public class SchoolType extends BasicObject{
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public SchoolType(){
	}
	
	public SchoolType(String name){
	    this.name = name;
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
		SchoolType type = (SchoolType)obj;
		return this.name.equals(type.name);
			
	}
	
	public String toString(){
		return "Schoold type is "+ name +super.toString();
	}

	
	
}

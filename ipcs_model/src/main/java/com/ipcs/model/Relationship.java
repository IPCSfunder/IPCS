package com.ipcs.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Chen Chao
 *
 */
public class Relationship extends BasicObject{

	private Person whose;

	private RelationshipType type;

	private Person iswho;


	public Relationship() {
		super();
	}

	public RelationshipType getType() {
		return type;
	}

	public void setType(RelationshipType type) {
		this.type = type;
	}

	public Person getIswho() {
		return iswho;
	}

	public void setIswho(Person iswho) {
		this.iswho = iswho;
	}


	public Person getWhose() {
		return whose;
	}

	public void setWhose(Person whose) {
		this.whose = whose;
	}



	public int hashCode(){
		int factor = 31;
		int result = 17*factor+whose.hashCode();
		result = 17*factor+type.hashCode();
		result = 17*factor+whose.hashCode();
		return  result;
	}

	public boolean equals(Object obj){
		if(null == obj)
			return false;
		if(this == obj)
			return true;
		if(obj.getClass() != Permission.class)
			return false;
		Relationship relationship = (Relationship)obj;
		return this.whose.equals(relationship.whose)&&this.type.equals(relationship.type)&&this.iswho.equals(relationship.iswho);

	}

	public String toString(){
		return "Relationship type is "+ type.getName() +super.toString();
	}

	
	
}

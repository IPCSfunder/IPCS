package com.ipcs.model;

import com.ipcs.model.Base.BasicObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chen Chao
 *
 */

@Entity
@Table(name="SCHOOL_TYPE")
public class
		SchoolType extends BasicObject {
	private Long objectId;
	
	private String name;

	private List<School> schools = new ArrayList<School>();

	public SchoolType(){
		super();
	}

	public SchoolType(String name){
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SCHOOL_TYPE_OBJID", unique = true)
	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	@Column(name="NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "type", cascade =CascadeType.ALL)
	public List<School> getSchools() {
		return schools;
	}

	public void setSchools(List<School> schools) {
		this.schools = schools;
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

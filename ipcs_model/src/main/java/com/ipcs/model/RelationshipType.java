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
@Table(name = "RELATIONSHIP_TYPE")
public class
		RelationshipType extends BasicObject {
	private Long objectId;

	private String name;

	private List<Relationship> relationships= new ArrayList<Relationship>();

	public RelationshipType(){
		super();
	}

	public RelationshipType(String name){
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RELATIONSHIP_TYPE_OBJID", unique = true, nullable = false)
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
	public List<Relationship> getRelationships() {
		return relationships;
	}

	public void setRelationships(List<Relationship> relationships) {
		this.relationships = relationships;
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
		RelationshipType type = (RelationshipType)obj;
		return this.name.equals(type.name);
			
	}
	
	public String toString(){
		return "Relationship type is "+ name +super.toString();
	}

	
	
}

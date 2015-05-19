package com.ipcs.model;

import com.ipcs.model.Base.BasicObject;

import javax.persistence.*;

/**
 * @author Chen Chao
 *
 */

@Entity
@Table(name = "RELATIONSHIP")
public class Relationship extends BasicObject {
	private Long objectId;

	private Person whose;

	private RelationshipType type;

	private Person iswho;


	public Relationship() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RELATIONSHIP_OBJID", unique = true, nullable = false)
	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RELATIONSHIP_TYPE_FK")
	public RelationshipType getType() {
		return type;
	}

	public void setType(RelationshipType type) {
		this.type = type;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ISWHO")
	public Person getIswho() {
		return iswho;
	}

	public void setIswho(Person iswho) {
		this.iswho = iswho;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WHOSE")
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

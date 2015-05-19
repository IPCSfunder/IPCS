package com.ipcs.model;

import com.ipcs.model.Base.BasicObject;

import javax.persistence.*;

/**
 * @author Chen Chao
 *
 */

@Entity
@Table(name = "MESSAGE_TYPE")
public class MessageType extends BasicObject {
	private Long objectId;

	private String typeName;

	private String description;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MESSAGE_TYPE_OBJID", unique = true, nullable = false)
	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	@Column(name="TYPE_NAME")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MessageType(){
	}

	public MessageType(String typeName, String description){
	    this.typeName = typeName;
		this.description = description;
	}
	
	

	public int hashCode(){
		int factor = 31;
		return 17*factor+typeName.hashCode();
	}
	
	public boolean equals(Object obj){
		if(null == obj)
			return false;
		if(this == obj)
			return true;
		if(obj.getClass() != Permission.class)
			return false;
		MessageType type = (MessageType)obj;
		return this.typeName.equals(type.typeName);
			
	}
	
	public String toString(){
		return "Message type is "+ typeName +super.toString();
	}

	
	
}

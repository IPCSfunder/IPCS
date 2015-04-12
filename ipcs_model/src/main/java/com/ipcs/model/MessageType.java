package com.ipcs.model;

/**
 * @author Chen Chao
 *
 */
public class MessageType extends BasicObject{

	private String typeName;

	private String description;


	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

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

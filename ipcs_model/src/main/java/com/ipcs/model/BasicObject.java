package com.ipcs.model;

import java.util.Date;

public abstract class BasicObject {
	
	private Long objectId;
	
	private Date createdTime;
	
	private Date modifiedTime;

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	
	public String toString(){
		return "Object id is "+objectId
		+" ,created at "+createdTime
		+" modifed at "+modifiedTime;
	}
}

package com.ipcs.model.Base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @author Chen Chao
 *
 */

@MappedSuperclass
public class BasicObject implements java.io.Serializable{
	
	private Date createdTime;
	
	private Date modifiedTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_CREATED")
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_MODIFIED")
	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	
	public String toString(){
		return "Object created at "+createdTime
		+" and modifed at "+modifiedTime;
	}
}

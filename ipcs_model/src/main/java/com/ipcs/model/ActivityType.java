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
@Table(name="ACTIVITY_TYPE")
public class
		ActivityType extends BasicObject {
	private Long objectId;

	private String name;

	private List<Activity> activities = new ArrayList<Activity>();

	public ActivityType(){
		super();
	}

	public ActivityType(String name){
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACTIVITY_TYPE_OBJID", unique = true)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "activityType", cascade =CascadeType.ALL)
	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
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
		ActivityType type = (ActivityType)obj;
		return this.name.equals(type.name);
			
	}
	
	public String toString(){
		return "Activity type is "+ name +super.toString();
	}

	
	
}

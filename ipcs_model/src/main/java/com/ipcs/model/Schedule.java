package com.ipcs.model;

import javax.persistence.*;

/**
 * @author Chen Chao
 *
 */

@Entity
@Table(name = "SCHEDULE")
public class Schedule {
	private Long objectId;

	private Long activityFk;

	private Long personFk;

	public Schedule(){
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SCHEDULE_OBJID", unique = true, nullable = false)
	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}






	@Column(name="activity_fk")
	public Long getActivityFk() {
		return activityFk;
	}

	public void setActivityFk(Long activityFk) {
		this.activityFk = activityFk;
	}

	@Column(name="person_fk")
	public Long getPersonFk() {
		return personFk;
	}

	public void setPersonFk(Long personFk) {
		this.personFk = personFk;
	}
}

package com.ipcs.model;

import javax.persistence.*;

/**
 * @author Chen Chao
 *
 */

@Entity
@Table(name = "PERSON_ROLE")
public class PersonRole {
	private Long objectId;

	private Long personFk;

	private Long roleFk;

	public PersonRole(){
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PERSON_ROLE_OBJID", unique = true, nullable = false)
	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	@Column(name="person_fk")
	public Long getPersonFk() {
		return personFk;
	}

	public void setPersonFk(Long personFk) {
		this.personFk = personFk;
	}

	@Column(name="role_fk")
	public Long getRoleFk() {
		return roleFk;
	}

	public void setRoleFk(Long roleFk) {
		this.roleFk = roleFk;
	}
}

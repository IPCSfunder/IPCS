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
@Table(name = "ROLE_PERMISSION")
public class RolePermission {
	private Long objectId;

	private Long roleFk;

	private Long permissionFk;

	public RolePermission(){
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_PERMISSION_OBJID", unique = true, nullable = false)
	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	@Column(name="permission_fk")
	public Long getPermissionFk() {
		return permissionFk;
	}

	public void setPermissionFk(Long permissionFk) {
		this.permissionFk = permissionFk;
	}

	@Column(name="role_fk")
	public Long getRoleFk() {
		return roleFk;
	}

	public void setRoleFk(Long roleFk) {
		this.roleFk = roleFk;
	}
}

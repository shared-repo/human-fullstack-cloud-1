package com.humanda6.demoweb2.entity;

import java.util.Set;

import com.humanda6.demoweb2.dto.RoleDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_role")
public class RoleEntity {
	
	public RoleEntity(RoleDto role) {
		this.roleNo = role.getRoleNo();
		this.roleName = role.getRoleName();
		this.roleDesc = role.getRoleDesc();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleNo;
	
	@Column
	private String roleName;
	
	@Column 
	private String roleDesc;

	@ManyToMany(mappedBy = "roles")
	private Set<MemberEntity> members;

	public RoleDto toRoleDto() {
		RoleDto role = new RoleDto();
		role.setRoleNo(roleNo);
		role.setRoleName(roleName);
		role.setRoleDesc(roleDesc);
		
		return role;
	}
	
	
}

















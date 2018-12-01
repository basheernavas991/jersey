package com.jazeera.jersey.security.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;

import com.jazeera.jersey.setting.model.AuditableModel;

@Entity
@Table(schema="security", name = "roles")
public class Role extends AuditableModel implements GrantedAuthority{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
	@Column(unique=true)
	private String name;

	private String description;
	
	@ManyToMany
	@JoinTable(schema="security", name="role_permissions",
			   joinColumns = @JoinColumn(name="roleId"),
			   inverseJoinColumns = @JoinColumn(name="permissionId"))	
	private List<Permission> permissions;

	@Transient
	private List<Integer> permissionIds;
	
	public String getAuthority() {
		return getName();
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
//	 @ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
//    @JoinTable(schema="security", name="role_permissions",
//	 joinColumns = @JoinColumn(name="roleId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name="permissionId", referencedColumnName = "id"))
	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<Integer> getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(List<Integer> permissionIds) {
		this.permissionIds = permissionIds;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", description=" + description + ", permissions=" + permissions
				+ ", permissionIds=" + permissionIds + ", createdBy=" + createdBy + ", lastModifiedBy=" + lastModifiedBy
				+ ", createdDate=" + createdDate + ", LastModifiedDate=" + LastModifiedDate + ", version=" + version
				+ ", createdUser=" + createdUser + ", lastModifiedUser=" + lastModifiedUser + "]";
	}
}
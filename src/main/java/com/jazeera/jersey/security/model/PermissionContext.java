package com.jazeera.jersey.security.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="security", name="permission_contexts")
public class PermissionContext {
	
	@Id
	private Integer id;
	private String name;
	private String description;
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
	@Override
	public String toString() {
		return "PermissionContext [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
}
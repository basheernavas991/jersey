package com.jazeera.jersey.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;




@Table(schema="security", name="permissions")
@Entity
public class Permission implements GrantedAuthority{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(unique=true)
	private String name;
	
	private String readableName;
	
	private String Description;
	
	private Integer context;
	
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

	public String getReadableName() {
		return readableName;
	}
	public void setReadableName(String readableName) {
		this.readableName = readableName;
	}

	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Integer getContext() {
		return context;
	}
	public void setContext(Integer context) {
		this.context = context;
	}
	
	public String getAuthority() {
		return getName();
	}
	@Override
	public String toString() {
		return "Permission [id=" + id + ", name=" + name + ", readableName=" + readableName + ", Description="
				+ Description + ", context=" + context + "]";
	}
}

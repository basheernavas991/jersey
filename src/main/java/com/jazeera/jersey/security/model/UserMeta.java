package com.jazeera.jersey.security.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(schema="security", name="user_meta_view")

public class UserMeta {

	@Id
	@JsonView(DataTablesOutput.View.class)
	private Integer userId;
	@JsonView(DataTablesOutput.View.class)
	private String firstName;
	@JsonView(DataTablesOutput.View.class)
	private String lastName;
	@JsonView(DataTablesOutput.View.class)
	private String username;
	@JsonView(DataTablesOutput.View.class)
	private Boolean enabled;
	@JsonView(DataTablesOutput.View.class)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date lastAccessedDate;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Date getLastAccessedDate() {
		return lastAccessedDate;
	}

	public void setLastAccessedDate(Date lastAccessedDate) {
		this.lastAccessedDate = lastAccessedDate;
	}

	@Override
	public String toString() {
		return "UserMeta [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", username="
				+ username + ", enabled=" + enabled + ", lastAccessedDate=" + lastAccessedDate + "]";
	}
	
	
	
}


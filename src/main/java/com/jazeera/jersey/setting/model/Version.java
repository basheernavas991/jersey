package com.jazeera.jersey.setting.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity @Table(schema="settings", name="versions")
public class Version {
	@Id
	@GeneratedValue
	private Integer id; 
	@Column(unique=true)
	@NotNull
	private String version;
	@NotNull
	private Date date;
	@NotNull
	private String deployer;
	@NotNull
	private boolean active;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDeployer() {
		return deployer;
	}
	public void setDeployer(String deployer) {
		this.deployer = deployer;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "Version [id=" + id + ", version=" + version + ", date=" + date + ", deployer=" + deployer + ", active="
				+ active + "]";
	}

}

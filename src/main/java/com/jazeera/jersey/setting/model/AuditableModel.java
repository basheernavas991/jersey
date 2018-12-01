package com.jazeera.jersey.setting.model;

import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.jazeera.jersey.security.model.User;
/**
 * 
 * A Class containing Auditable fields which can be extended by the entities.
 * 
 * <pre>Auditable Model attributes will be applied to the entities and this should be present in your database if you are using RDBMS.
 * 
 * Auditable model uses Spring Data JPA Auditing, 
 * - createdBy and createdDate fields will be auto set during the first persisting.
 * - lastModifiedBy and lastModifiedDate fields will be auto set from the second versions onwards.
 * </pre>
 * @author navas
 * @since 0.1.0
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditableModel {

	@CreatedBy
	public Integer createdBy;
	
	@LastModifiedBy
	public Integer lastModifiedBy;
	
	
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
	@CreatedDate
	public Date createdDate;
	
	
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
	@LastModifiedDate
	public Date LastModifiedDate;
	
	@Version
	public Integer version;
	
	@Transient
	public User createdUser;
	
	@Transient
	public User lastModifiedUser;

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(Integer lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return LastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		LastModifiedDate = lastModifiedDate;
	}
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public User getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(User createdUser) {
		this.createdUser = createdUser;
	}

	public User getLastModifiedUser() {
		return lastModifiedUser;
	}

	public void setLastModifiedUser(User lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}
}

package com.jazeera.jersey.setting.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity for Person Telephone 
 * @author navas
 */
@Entity
@Table(schema="settings", name="person_telephone")
public class PersonTelephone extends AuditableModel{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private Integer personId;
	
	@NotNull
	private Long type;
	
	@NotEmpty
	private String telephoneNumber;	
	
	private Long country;
	
	private String notes;
	private Boolean active;
	@Transient
	private String telephoneTypeName;
	@Transient
	private String countryName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public Long getCountry() {
		return country;
	}
	public void setCountry(Long country) {
		this.country = country;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getTelephoneTypeName() {
		return telephoneTypeName;
	}
	public void setTelephoneTypeName(String telephoneTypeName) {
		this.telephoneTypeName = telephoneTypeName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	@Override
	public String toString() {
		return "PersonTelephone [id=" + id + ", personId=" + personId + ", type=" + type + ", telephoneTypeName=" + telephoneTypeName + ", telephoneNumber="
				+ telephoneNumber + ", country=" + country + ", countryName=" + countryName + ", notes=" + notes + ", active=" + active + ", createdBy="
				+ createdBy + ", lastModifiedBy=" + lastModifiedBy + ", createdDate=" + createdDate
				+ ", LastModifiedDate=" + LastModifiedDate + ", version=" + version + ", createdUser=" + createdUser
				+ ", lastModifiedUser=" + lastModifiedUser + "]";
	}
}
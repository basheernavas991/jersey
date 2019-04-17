package com.jazeera.jersey.setting.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Entity for Person 
 * @author navas
 */
@Entity
@Table(schema="settings", name="persons")
public class Person extends AuditableModel{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique=true)
	private String reference;
	private String nationalId;
	private Long type;
	
	private Long title;
	
	@NotEmpty
	private String firstName;
	private String secondName;
	private String thirdName;
	private String arabicFirstName;
	private String arabicSecondName;
	private String arabicThirdName;
	private String arabicLastName;
	@NotEmpty
	private String lastName;
	
	private Long gender;
	private Long maritalStatus;
	private Long nationality;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dob;
	private String arabicDob;
	@NotNull
	private Integer institution;
	
	@NotNull
	private Long status;
	private Long secondaryStatus;
	
	private String notes;
	
	
	@Transient
	@Valid
	private List<PersonAddress> addresses = new ArrayList<PersonAddress>();
	
	@Transient
	@Valid
	private List<PersonTelephone> telephones = new ArrayList<PersonTelephone>();

	@Transient
	private Integer age;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Long getTitle() {
		return title;
	}

	public void setTitle(Long title) {
		this.title = title;
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

	public Long getGender() {
		return gender;
	}

	public void setGender(Long gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public List<PersonAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<PersonAddress> addresses) {
		this.addresses = addresses;
	}

	public List<PersonTelephone> getTelephones() {
		return telephones;
	}

	public void setTelephones(List<PersonTelephone> telephones) {
		this.telephones = telephones;
	}
	
	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getThirdName() {
		return thirdName;
	}

	public void setThirdName(String thirdName) {
		this.thirdName = thirdName;
	}

	public String getArabicFirstName() {
		return arabicFirstName;
	}

	public void setArabicFirstName(String arabicFirstName) {
		this.arabicFirstName = arabicFirstName;
	}

	public String getArabicSecondName() {
		return arabicSecondName;
	}

	public void setArabicSecondName(String arabicSecondName) {
		this.arabicSecondName = arabicSecondName;
	}

	public String getArabicThirdName() {
		return arabicThirdName;
	}

	public void setArabicThirdName(String arabicThirdName) {
		this.arabicThirdName = arabicThirdName;
	}

	public String getArabicLastName() {
		return arabicLastName;
	}

	public void setArabicLastName(String arabicLastName) {
		this.arabicLastName = arabicLastName;
	}

	public Long getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(Long maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Long getNationality() {
		return nationality;
	}

	public void setNationality(Long nationality) {
		this.nationality = nationality;
	}

	public String getArabicDob() {
		return arabicDob;
	}

	public void setArabicDob(String arabicDob) {
		this.arabicDob = arabicDob;
	}

	public Integer getInstitution() {
		return institution;
	}

	public void setInstitution(Integer institution) {
		this.institution = institution;
	}
	public Long getSecondaryStatus() {
		return secondaryStatus;
	}

	public void setSecondaryStatus(Long secondaryStatus) {
		this.secondaryStatus = secondaryStatus;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", reference=" + reference + ", nationalId=" + nationalId + ", type=" + type
				+ ", title=" + title + ", firstName=" + firstName + ", secondName=" + secondName + ", thirdName="
				+ thirdName + ", arabicFirstName=" + arabicFirstName + ", arabicSecondName=" + arabicSecondName
				+ ", arabicThirdName=" + arabicThirdName + ", arabicLastName=" + arabicLastName + ", lastName="
				+ lastName + ", gender=" + gender + ", maritalStatus=" + maritalStatus + ", nationality=" + nationality
				+ ", dob=" + dob + ", arabicDob=" + arabicDob + ", institution=" + institution + ", status=" + status
				+ ", secondaryStatus=" + secondaryStatus + ", notes=" + notes + ", addresses=" + addresses
				+ ", telephones=" + telephones + ", age=" + age + "]";
	}
	

	

	

	
}

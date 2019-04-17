package com.jazeera.jersey.security.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jazeera.jersey.setting.model.AuditableModel;


@Entity
@Table(name="users", schema="security")
public class User extends AuditableModel implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Length(min=4)
	@Column(unique=true)
	@NotEmpty
	private String username;
	
	private String password;
	
	private Integer personId;
	
	@Transient
	private String confirmPassword;
	
	private String firstName;
	private String lastName;
	
	@Email
	private String email;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
 	private Date lastAccessedDate;
 	
 	@NotNull
	private Boolean enabled;
	private Boolean tokenExpired;
	
	private Boolean userPrivacyAccepted;
	@Transient
	private String personName;
	
	@ManyToMany
	@JoinTable(schema="security",name="user_roles", 
			   joinColumns = @JoinColumn(name="userId"),
			   inverseJoinColumns = @JoinColumn(name="roleId"))
	private List<Role> roles;
	
	@Transient
	private List<Integer> rolesIds;
	
	@Transient
	public List<Permission> getPermissions(){
		List<Permission> perms = new ArrayList<Permission>();
		for(Role role : roles){
			perms.addAll(role.getPermissions());
		}
		return perms;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.addAll(getPermissions());
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		
		if(enabled != null)
			return enabled;
		return false;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastAccessedDate() {
		return lastAccessedDate;
	}

	public void setLastAccessedDate(Date lastAccessedDate) {
		this.lastAccessedDate = lastAccessedDate;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getTokenExpired() {
		return tokenExpired;
	}

	public void setTokenExpired(Boolean tokenExpired) {
		this.tokenExpired = tokenExpired;
	}

	public String getPersonName() {
		return personName;
	}


	public void setPersonName(String personName) {
		this.personName = personName;
	}


	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Integer> getRolesIds() {
		return rolesIds;
	}

	public void setRolesIds(List<Integer> rolesIds) {
		this.rolesIds = rolesIds;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}


	public Boolean getUserPrivacyAccepted() {
		return userPrivacyAccepted;
	}


	public void setUserPrivacyAccepted(Boolean userPrivacyAccepted) {
		this.userPrivacyAccepted = userPrivacyAccepted;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", personId=" + personId
				+ ", confirmPassword=" + confirmPassword + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", lastAccessedDate=" + lastAccessedDate + ", enabled=" + enabled
				+ ", tokenExpired=" + tokenExpired + ", userPrivacyAccepted=" + userPrivacyAccepted + ", personName="
				+ personName + ", roles=" + roles + ", rolesIds=" + rolesIds + ", createdBy=" + createdBy
				+ ", lastModifiedBy=" + lastModifiedBy + ", createdDate=" + createdDate + ", LastModifiedDate="
				+ LastModifiedDate + ", version=" + version + ", createdUser=" + createdUser + ", lastModifiedUser="
				+ lastModifiedUser + "]";
	}
}

package com.cloudcode.tk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.cloudcode.framework.model.BaseModelObject;
import com.cloudcode.tk.ProjectConfig;

/**
 * Contact POJO
 * 
 * @author Loiane Groner
 * http://loianegroner.com (English)
 * http://loiane.com (Portuguese)
 */
@JsonAutoDetect
@Entity
@Table(name=ProjectConfig.NAME+"_CONTACT")
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true)
public class Contact extends BaseModelObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8270214226737823438L;
	private String name;
	private String email;
	private String firstname;

	@Column(name="LASTNAME")
	private String lastname;
	@Column(name="TELEPHONE")
	private String telephone;
	//@Column(name="CONTACT_NAME", nullable=false)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	//@Column(name="CONTACT_EMAIL", nullable=false)
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
}

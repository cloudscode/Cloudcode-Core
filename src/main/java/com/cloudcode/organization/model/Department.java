package com.cloudcode.organization.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cloudcode.framework.model.BaseTreeNodeModelObject;
import com.cloudcode.organization.ProjectConfig;

@Entity(name = ProjectConfig.NAME + "department")
@Table(name = ProjectConfig.NAME + "_department")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class Department extends BaseTreeNodeModelObject<Department> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7426713381796911096L;
	private String description;
	private String property;
	private String code;
	private String organizationId;
	private String master;
	// private Set<String> leaders;
	private boolean virtual = false;
	private String shortName;
	private String idCode;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
	public boolean isVirtual() {
		return virtual;
	}
	public void setVirtual(boolean virtual) {
		this.virtual = virtual;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getIdCode() {
		return idCode;
	}
	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}
}

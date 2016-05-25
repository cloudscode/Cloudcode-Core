package com.cloudcode.organization.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cloudcode.framework.model.BaseTreeNodeModelObject;
import com.cloudcode.organization.ProjectConfig;

@Entity(name = ProjectConfig.NAME + "organization")
@Table(name = ProjectConfig.NAME + "_organization")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class Organization extends BaseTreeNodeModelObject<Organization> {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 6986086022890101384L;
	private String shortName;
	private String functions;
	private String website;
	private String code;
	private String legalPerson;
	private String description;
	private String groupId;
	private String idCode;
	
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getFunctions() {
		return functions;
	}
	public void setFunctions(String functions) {
		this.functions = functions;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getIdCode() {
		return idCode;
	}
	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}
	
	
}

package com.cloudcode.organization.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cloudcode.framework.model.BaseTreeNodeModelObject;
import com.cloudcode.organization.ProjectConfig;

@Entity(name = ProjectConfig.NAME + "group")
@Table(name = ProjectConfig.NAME + "_group")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class Group extends BaseTreeNodeModelObject<Group> {

	/**   
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)    
	*/   
	private static final long serialVersionUID = -8624044375778997378L;
	private String description;
	private String shortName;
	private String idCode;
	private String code;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}

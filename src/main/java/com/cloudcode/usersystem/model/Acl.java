package com.cloudcode.usersystem.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cloudcode.framework.model.BaseModelObject;
import com.cloudcode.usersystem.ProjectConfig;

@Entity(name = ProjectConfig.NAME + "acl")
@Table(name = ProjectConfig.NAME + "_acl")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class Acl extends BaseModelObject {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -5807820251198465712L;
	// private String domainId = Domain.GLOBAL_DOMAIN.getId();
	private String subject;
	private String resource;
	private int permission = 0;
	private String description;
	private int priority = 0;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

}

package com.cloudcode.usersystem.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cloudcode.framework.model.BaseModelObject;
import com.cloudcode.usersystem.ProjectConfig;

@Entity(name  = ProjectConfig.NAME+"role")
@Table(name =  ProjectConfig.NAME+"_role")
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true)
public class Role extends BaseModelObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	  private Type type = Type.Bussiness;

	  private String name;
	  private String description;
	  private Integer status;

	  public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public static enum Type {
		    System, Admin, Bussiness;
	  }
}

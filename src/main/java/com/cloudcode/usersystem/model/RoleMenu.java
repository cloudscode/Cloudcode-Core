package com.cloudcode.usersystem.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cloudcode.framework.model.BaseModelObject;
import com.cloudcode.usersystem.ProjectConfig;

@Entity(name  = ProjectConfig.NAME+"rolemenu")
@Table(name =  ProjectConfig.NAME+"_rolemenu")
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true)
public class RoleMenu extends BaseModelObject{

	  /**
	 * 
	 */
	private static final long serialVersionUID = -5156300601794836026L;
	private String menuId;
	private String roleId;
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	 
}

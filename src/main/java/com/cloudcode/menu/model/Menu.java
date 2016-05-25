package com.cloudcode.menu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cloudcode.framework.model.BaseTreeNodeModelObject;
import com.cloudcode.menu.ProjectConfig;

@Entity(name = ProjectConfig.NAME + "menu")
@Table(name = ProjectConfig.NAME + "_menu")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class Menu extends BaseTreeNodeModelObject<Menu>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7707550801447568325L;
	/**
	 * 动作
	 */
	private String action;
	/**
	 * 类型
	 */
	private Type type;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 可见
	 */
	private boolean visible = true;
	/**
	 * 可用
	 */
	private boolean enabled = true;
	
	private String pname;
	private String params;
	
	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public Type getType() {
		return type;
	}


	public void setType(Type type) {
		this.type = type;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public boolean isVisible() {
		return visible;
	}


	public void setVisible(boolean visible) {
		this.visible = visible;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public String getPname() {
		return pname;
	}

	@Transient
	public void setPname(String pname) {
		this.pname = pname;
	}

	@Column(length = 4000)
	public String getParams() {
		return params;
	}


	public void setParams(String params) {
		this.params = params;
	}


	public static enum Type{
		System,Custom;
	}
}

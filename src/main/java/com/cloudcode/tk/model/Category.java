package com.cloudcode.tk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cloudcode.framework.model.BaseTreeNodeModelObject;
import com.cloudcode.tk.ProjectConfig;

@Entity(name = ProjectConfig.NAME + "category")
@Table(name = ProjectConfig.NAME + "_category")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class Category extends BaseTreeNodeModelObject<Category> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5132348076088189603L;

	private String code;

	@Column(length = 256)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}

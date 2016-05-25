package com.cloudcode.organization.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cloudcode.framework.model.BaseTreeNodeModelObject;
import com.cloudcode.organization.ProjectConfig;

@Entity(name = ProjectConfig.NAME + "job")
@Table(name = ProjectConfig.NAME + "_job")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class Job extends BaseTreeNodeModelObject<Job> {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -2492697531034431336L;
	private String organizationId;
	private String departmentId;
	private String description;
	private String idCode;
	private String jobpro;
	private String rolepro;
	private int total;
	private String code;
	
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIdCode() {
		return idCode;
	}
	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}
	public String getJobpro() {
		return jobpro;
	}
	public void setJobpro(String jobpro) {
		this.jobpro = jobpro;
	}
	public String getRolepro() {
		return rolepro;
	}
	public void setRolepro(String rolepro) {
		this.rolepro = rolepro;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}

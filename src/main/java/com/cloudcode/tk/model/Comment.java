package com.cloudcode.tk.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cloudcode.framework.model.BaseModelObject;
import com.cloudcode.tk.ProjectConfig;

@Entity(name=ProjectConfig.NAME+"comment")
@Table(name = ProjectConfig.NAME+"_comment")
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true)
public class Comment extends BaseModelObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7074614425410104119L;
	

	private String authorName;
	private String authorEmail;
	private String authorUrl;
	private String message;
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getAuthorEmail() {
		return authorEmail;
	}
	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}
	public String getAuthorUrl() {
		return authorUrl;
	}
	public void setAuthorUrl(String authorUrl) {
		this.authorUrl = authorUrl;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}

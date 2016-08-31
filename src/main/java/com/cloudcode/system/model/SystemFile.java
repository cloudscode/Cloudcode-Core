 package com.cloudcode.system.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cloudcode.framework.model.BaseModelObject;
import com.cloudcode.system.ProjectConfig;

@Entity(name = ProjectConfig.NAME + "file")
@Table(name = ProjectConfig.NAME + "_file")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class SystemFile  extends BaseModelObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2530420455448972261L;
	//fileSize
	private long fileSize;
	
	@Column(name="FILE_SIZE")
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	
	//path
	private String path;
	
	@Column(name="PATH",length=1000)
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	//type
	private String type;
	
	@Column(name="TYPE",length=32)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	//fileType
	private String fileType;
	
	@Column(name="FILE_TYPE",length=32)
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	//text
	private String text;
	
	@Column(name="TEXT",length=512)
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	//serviceId
	private String serviceId;
	
	@Column(name="SERVICE_ID",length=36)
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
	//parentServiceId
	private String parentServiceId;
	
	@Column(name="PARENT_SERVICE_ID",length=36)
	public String getParentServiceId() {
		return parentServiceId;
	}
	public void setParentServiceId(String parentServiceId) {
		this.parentServiceId = parentServiceId;
	}
	
}
package com.cloudcode.task.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.cloudcode.framework.model.BaseModelObject;
import com.cloudcode.task.ProjectConfig;

@Entity(name = ProjectConfig.NAME + "config")
@Table(name = ProjectConfig.NAME + "_config")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class TaskConfig extends BaseModelObject {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = 5784655268448092489L;
	private String name;
	private Date startDate;
	private int valid;
	private int day;
	private int hour;
	private int minute;
	private int second;
	
	private int hourRegister;
	private int minuteRegister;
	private int secondRegister;	



	private String execType;

	private String formula;

	

	@Column(name = "NAME_", length = 64)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_DATE_", length = 11)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "VALID_")
	public int getValid() {
		return valid;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}

	@Column(name = "DAY_")
	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	@Column(name = "HOUR_")
	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	@Column(name = "MINUTE_")
	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	@Column(name = "SECOND_")
	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}
	
	@Column(name = "EXEC_TYPE_", length = 64)
	public String getExecType() {
		return execType;
	}

	public void setExecType(String execType) {
		this.execType = execType;
	}

	@Column(name = "FORMULA_", length = 512)
	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	@Column(name = "HOUR_REGISTER_")
	public int getHourRegister() {
		return hourRegister;
	}

	public void setHourRegister(int hourRegister) {
		this.hourRegister = hourRegister;
	}

	@Column(name = "MINUTE_REGISTER_")
	public int getMinuteRegister() {
		return minuteRegister;
	}

	public void setMinuteRegister(int minuteRegister) {
		this.minuteRegister = minuteRegister;
	}

	@Column(name = "SECOND_REGISTER_")
	public int getSecondRegister() {
		return secondRegister;
	}

	public void setSecondRegister(int secondRegister) {
		this.secondRegister = secondRegister;
	}
	
	
}
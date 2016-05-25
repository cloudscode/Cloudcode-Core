package com.cloudcode.organization.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cloudcode.framework.model.BaseModelObject;
import com.cloudcode.organization.ProjectConfig;
import com.cloudcode.organization.enums.Gender;

@Entity(name = ProjectConfig.NAME + "staff")
@Table(name = ProjectConfig.NAME + "_staff")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class Staff extends BaseModelObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5301212518522845377L;
	private String name;
	private String pinyin;
	private String sn;
	private String cardNumber;
	private String idNumber;
	private String englishName;
	private String type;
	private Date birthday;
	private String education;
	private String nation;
	private String politics;
	private String nativePlace;
	private String faith;
	private String graduatedFrom;
	private String mobile;
	private Status status;
	private Date joinDate;
	private Date leaveDate;
	private Date startWorkingDate;
	private int workingMonths;
	private String avatar;
	private boolean probation;
	private String note;
	private String position;
	private String level;
	private String title;
	private String loginId;
	private String initPassword;
	private Gender gender;
	private String personId;
	private String organizationId;
	private String rankId;
	// private List<String> departmentIds;
	// private List<String> jobIds;
	// private List<ContactInfo> contactInfos;
	// private ContactInfo primaryContactInfo;
	private Integer index;
	private Date modifiyDate;
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getPolitics() {
		return politics;
	}

	public void setPolitics(String politics) {
		this.politics = politics;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getFaith() {
		return faith;
	}

	public void setFaith(String faith) {
		this.faith = faith;
	}

	public String getGraduatedFrom() {
		return graduatedFrom;
	}

	public void setGraduatedFrom(String graduatedFrom) {
		this.graduatedFrom = graduatedFrom;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public Date getStartWorkingDate() {
		return startWorkingDate;
	}

	public void setStartWorkingDate(Date startWorkingDate) {
		this.startWorkingDate = startWorkingDate;
	}

	public int getWorkingMonths() {
		return workingMonths;
	}

	public void setWorkingMonths(int workingMonths) {
		this.workingMonths = workingMonths;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public boolean isProbation() {
		return probation;
	}

	public void setProbation(boolean probation) {
		this.probation = probation;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getInitPassword() {
		return initPassword;
	}

	public void setInitPassword(String initPassword) {
		this.initPassword = initPassword;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getRankId() {
		return rankId;
	}

	public void setRankId(String rankId) {
		this.rankId = rankId;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Date getModifiyDate() {
		return modifiyDate;
	}

	public void setModifiyDate(Date modifiyDate) {
		this.modifiyDate = modifiyDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static enum Status {
		OnJob, Leave;
	}
}

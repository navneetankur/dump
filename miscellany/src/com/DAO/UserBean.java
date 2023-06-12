package com.DAO;
/**
 * @author Sanyam tyagi
 * A bean class for containing the info of the user
 */
public class UserBean {
	
String userId,userName,eMail,accessType,contact,dob,sex,active;

public UserBean(String userId, String userName, String eMail,
			 String contact, String accessType,String sex, String dob,
			String active) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.eMail = eMail;
		this.accessType = accessType;
		this.contact = contact;
		this.dob = dob;
		this.sex = sex;
		this.active = active;
}

public String getActive() {
	return active;
}

public void setActive(String active) {
	this.active = active;
}

public String getDob() {
	return dob;
}

public void setDob(String dob) {
	this.dob = dob;
}

public String getSex() {
	return sex;
}

public void setSex(String sex) {
	this.sex = sex;
}

public String getUserId() {
	return userId;
}

public void setUserId(String userId) {
	this.userId = userId;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String geteMail() {
	return eMail;
}

public void seteMail(String eMail) {
	this.eMail = eMail;
}

public String getAccessType() {
	return accessType;
}

public void setAccessType(String accessType) {
	this.accessType = accessType;
}

public String getContact() {
	return contact;
}

public void setContact(String contact) {
	this.contact = contact;
}
}

package com.glecom.products.models;

/**
 * @author navneet
 * model class for entries in address table.
 */
public class SessionBean {
	private int id;
	private String sessionId;
	private String userId;
	private String accessType;
	
	public SessionBean(String sessionId, String userId, String accessType) {
		this.sessionId = sessionId;
		this.userId = userId;
		this.accessType = accessType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSessionId() {
		return sessionId;
	}

	public String getUserId() {
		return userId;
	}

	public String getAccessType() {
		return accessType;
	}
}

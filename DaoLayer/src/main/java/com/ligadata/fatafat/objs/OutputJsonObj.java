package com.ligadata.fatafat.objs;

import java.sql.Timestamp;

public class OutputJsonObj extends GenericObjs{

	private int id;
	private String userId;
	private String json;
	private Timestamp createdOn;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public OutputJsonObj(int id, String userId, String json, Timestamp createdOn) {
		super();
		this.id = id;
		this.userId = userId;
		this.json = json;
		this.createdOn = createdOn;
	}

	public OutputJsonObj(String userId, String json) {
		super();
		this.userId = userId;
		this.json = json;
	}

	public OutputJsonObj(){}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
	
	@Override
	public String toString() {
		return "OutputJson [userId=" + userId + ", json=" + json + "]";
	}
}

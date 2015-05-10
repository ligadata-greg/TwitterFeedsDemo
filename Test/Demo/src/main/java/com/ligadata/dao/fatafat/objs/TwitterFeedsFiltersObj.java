package com.ligadata.dao.fatafat.objs;

import java.sql.Timestamp;

public class TwitterFeedsFiltersObj extends GenericObjs {

	private int id;
	private String name;
	private String csvWordSet;
	private Timestamp createdOn;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCsvWordSet() {
		return csvWordSet;
	}
	public void setCsvWordSet(String csvWordSet) {
		this.csvWordSet = csvWordSet;
	}
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	
	@Override
	public String toString() {
		return "TwitterFeedsFiltersObj [id=" + id + ", name=" + name
				+ ", csvWordSet=" + csvWordSet + ", createdOn=" + createdOn
				+ "]";
	}
}

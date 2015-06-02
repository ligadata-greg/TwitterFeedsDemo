package com.ligadata.datasets;

import java.util.Map;

public class DataSetsWrapper {

	private Map subjects = null;
	private Map industries = null;
	
	public DataSetsWrapper(){}
	
	public DataSetsWrapper(Map subjects, Map industries) {
		super();
		this.subjects = subjects;
		this.industries = industries;
	}
	public Map getSubjects() {
		return subjects;
	}
	public void setSubjects(Map subjects) {
		this.subjects = subjects;
	}
	public Map getIndustries() {
		return industries;
	}
	public void setIndustries(Map industries) {
		this.industries = industries;
	}
	
	@Override
	public String toString() {
		return "DataSetObj [subjects=" + subjects + ", industries="
				+ industries + "]";
	}
}

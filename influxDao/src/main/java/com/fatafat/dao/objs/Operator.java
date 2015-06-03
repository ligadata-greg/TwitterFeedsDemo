package com.fatafat.dao.objs;

public enum Operator {
	GREATERTHAN(">"), LESSTHAN("<"), EQUALS("=");
	
	private String typeValue;

	private Operator(String typeValue) {
		this.typeValue = typeValue;
	}

	public String getTypeValue() {
		return typeValue;
	}
}

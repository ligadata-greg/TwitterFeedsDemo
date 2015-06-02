package com.fatafat.dao.objs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DatasetObj extends GenericObj{

	private int id;
	private String name;
	private Map<String, Integer> dataset;
	private String type;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Map getDataset() {
		return dataset;
	}
	public void setDataset(String dataset) {
		String[] arr = dataset.split(",");
		Map map = new HashMap<String, Integer>();
		for (int i = 0; i < arr.length; i++) {
			map.put(arr[i], 1);
		}
		this.dataset = map;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "DatasetObj [id=" + id + ", dataset=" + dataset + "]";
	}
}

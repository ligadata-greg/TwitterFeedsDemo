package com.fatafat.idao;

import java.util.List;

import org.influxdb.InfluxDB;

import com.fatafat.dao.objs.GenericObj;

public interface IGenericDao {

	public List<GenericObj> selectAllStoredRecsInLastSecond(InfluxDB connection);
}

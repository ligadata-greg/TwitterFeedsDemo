package com.fatafat.idao;

import java.util.List;

import org.influxdb.InfluxDB;

import com.fatafat.dao.objs.GenericObj;
import com.fatafat.dao.objs.Operator;

public interface IGenericDao {

	public List<GenericObj> selectAllByEntryTime(Operator op, InfluxDB connection);
}

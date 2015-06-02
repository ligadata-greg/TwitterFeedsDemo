package com.fatafat.idao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.fatafat.dao.objs.GenericObj;

public interface IGenericDao {
	List<GenericObj> selectAll(Connection con);
}

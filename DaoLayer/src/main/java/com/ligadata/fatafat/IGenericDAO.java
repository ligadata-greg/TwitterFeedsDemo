package com.ligadata.fatafat;

import java.util.List;

import com.ligadata.fatafat.objs.GenericObjs;
import com.mysql.jdbc.Connection;

public interface IGenericDAO {

	void createOutputTable();
	 
    int insert(GenericObjs obj, Connection con);
 
    GenericObjs selectById(int id);
 
    List<GenericObjs> selectAll();
 
    void deleteById(int id);
 
    void updateById(GenericObjs person,int id);
}

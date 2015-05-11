package com.ligadata.fatafat;

import java.sql.Connection;
import java.util.List;

import com.ligadata.fatafat.objs.GenericObjs;

interface IGenericDAO {

	void createTable();
	
	void truncateTable();
	
    int insert(GenericObjs obj, Connection con);
 
    GenericObjs selectById(int id);
 
    List<GenericObjs> selectAll();
 
    void deleteById(int id);
 
    void updateById(GenericObjs person,int id);
}

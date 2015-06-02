package com.fatafat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fatafat.dao.GenericDao;
import com.fatafat.dao.objs.DatasetObj;
import com.fatafat.dao.objs.GenericObj;

public class DatasetsDao extends GenericDao{

	
	
    @Override
	public List<GenericObj> selectAll(Connection con) {
		// TODO Auto-generated method stub
    	DatasetObj datasetRec = null;
    	List<GenericObj> outputList = new ArrayList<GenericObj>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
        	preparedStatement = con.prepareStatement("SELECT * FROM " + GenericDao.getSCHEMA_NAME() + ".datasets;");
        	resultSet = preparedStatement.executeQuery();
        	 
            while (resultSet.next()) {
            	datasetRec = new DatasetObj();
            	datasetRec.setId(resultSet.getInt("id"));
            	datasetRec.setName(resultSet.getString("name"));
            	datasetRec.setType(resultSet.getString("type"));
            	datasetRec.setDataset(resultSet.getString("dataset"));
                
                outputList.add(datasetRec);
            }
 
        }
        catch(Exception e){
        	e.printStackTrace();
        }
		return outputList;
	}
    
}

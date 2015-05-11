package com.ligadata.fatafat.impl;

import java.sql.*;
import java.util.List;

import com.ligadata.fatafat.GenericDAO;
import com.ligadata.fatafat.IGenericDAO;
import com.ligadata.fatafat.objs.GenericObjs;
import com.ligadata.fatafat.objs.OutputJsonObj;

import java.sql.Connection;

public class OutputJsonDAO extends GenericDAO implements IGenericDAO{

	public void createOutputTable() {
		 Connection connection = null;
	        Statement statement = null;
	 
	        try {
	            connection = getConnection();
	            statement = connection.createStatement();
	            statement.execute("CREATE TABLE demo.`outputdata` (`id` int(100) NOT NULL AUTO_INCREMENT,`userid` varchar(100) NOT NULL,`json` text NOT NULL,`createdon` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,  PRIMARY KEY (`id`),  KEY `id` (`id`));");
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (statement != null) {
	                try {
	                    statement.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	 
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
		
	}

	public int insert(GenericObjs obj, PreparedStatement ps) {
		OutputJsonObj jsonObj = null;
		int output = 0;
		if (obj instanceof OutputJsonObj)
			jsonObj = (OutputJsonObj) obj;

		try {
			ps.setString(1, jsonObj.getUserId());
			ps.setString(2, jsonObj.getJson());
			output = ps.executeUpdate();
			System.out.println("INSERT INTO " + GenericDAO.getSCHEMA_NAME()
					+ ".outputdata(userid, json)" + "VALUES (?, ?)");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}
//	public int insert(GenericObjs obj, Connection con) {
//		OutputJsonObj jsonObj = null;
//		int output = 0;
//		if(obj instanceof OutputJsonObj)
//			jsonObj = (OutputJsonObj) obj;
//		
//		Connection connection = null;
//        PreparedStatement preparedStatement = null;
// 
//        try {
//            connection = con;
//            preparedStatement = connection.prepareStatement("INSERT INTO demo.outputdata(userid, json)" +
//                    "VALUES (?, ?)");
//            preparedStatement.setString(1, jsonObj.getUserId());
//            preparedStatement.setString(2, jsonObj.getJson());
//            output = preparedStatement.executeUpdate();
//            System.out.println("INSERT INTO demo.outputdata(userid, json)" +
//                    "VALUES (?, ?)");
// 
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (preparedStatement != null) {
//                try {
//                    preparedStatement.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
// 
////            if (connection != null) {
////                try {
////                    connection.close();
////                } catch (SQLException e) {
////                    e.printStackTrace();
////                }
////            }
//        }
//		return output;
//	}

	public GenericObjs selectLastRecord(Connection con){
		OutputJsonObj jsonOutput = new OutputJsonObj();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
 
        try {
            connection = con;
            preparedStatement = connection.prepareStatement("SELECT * FROM demo.outputdata order by createdon desc limit 1;");
//            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
 
            while (resultSet.next()) {
                jsonOutput.setId(resultSet.getInt("id"));
                jsonOutput.setUserId(resultSet.getString("userid"));
                jsonOutput.setJson(resultSet.getString("json"));
                jsonOutput.setCreatedOn(resultSet.getTimestamp("createdon"));
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
 
        return jsonOutput;
	}
	
	public GenericObjs selectById(int id) {
		OutputJsonObj jsonOutput = new OutputJsonObj();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
 
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM demo.outputdata WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
 
            while (resultSet.next()) {
                jsonOutput.setId(resultSet.getInt("id"));
                jsonOutput.setUserId(resultSet.getString("userid"));
                jsonOutput.setJson(resultSet.getString("json"));
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
 
        return jsonOutput;
	}

	public List<GenericObjs> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	public void updateById(GenericObjs person, int id) {
		// TODO Auto-generated method stub
		
	}

	public int insert(GenericObjs obj, com.mysql.jdbc.Connection con) {
		// TODO Auto-generated method stub
		return 0;
	}

}

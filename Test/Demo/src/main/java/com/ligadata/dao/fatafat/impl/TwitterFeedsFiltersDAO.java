package com.ligadata.dao.fatafat.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ligadata.dao.fatafat.objs.GenericObjs;
import com.ligadata.dao.fatafat.objs.OutputJsonObj;
import com.ligadata.dao.fatafat.objs.TwitterFeedsFiltersObj;

import java.sql.Connection;

public class TwitterFeedsFiltersDAO extends GenericDAO {

	public void createTable() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Connection connection = null;
		Statement statement = null;

		try {
			connection = getConnection();
			statement = connection.createStatement();
			statement
					.execute("CREATE TABLE "
							+ GenericDAO.getSCHEMA_NAME()
							+ ".`twitterfeedsfilters` (`id` int(100) NOT NULL AUTO_INCREMENT,`name` varchar(45) NOT NULL,`csvwordset` text NOT NULL,`createdon` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,PRIMARY KEY (`id`));");

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

	public void truncateTable() {
		// TODO Auto-generated method stub
		Connection connection = null;
        Statement statement = null;
 
        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.execute("TRUNCATE TABLE " + GenericDAO.getSCHEMA_NAME() + ".`twitterfeedsfilters`;");
 
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

	public int insert(GenericObjs obj, Connection con) {
		// TODO Auto-generated method stub
		TwitterFeedsFiltersObj twitterObj = null;
		int output = 0;
		if(obj instanceof TwitterFeedsFiltersObj)
			twitterObj = (TwitterFeedsFiltersObj) obj;
		
		Connection connection = null;
        PreparedStatement preparedStatement = null;
 
        try {
            connection = con;
            preparedStatement = connection.prepareStatement("INSERT INTO " + GenericDAO.getSCHEMA_NAME() + ".twitterfeedsfilters(name, csvwordset)" +
                    "VALUES (?, ?)");
            preparedStatement.setString(1, twitterObj.getName());
            preparedStatement.setString(2, twitterObj.getCsvWordSet());
            output = preparedStatement.executeUpdate();
            System.out.println("INSERT INTO " + GenericDAO.getSCHEMA_NAME() + ".twitterfeedsfilters(userid, json)" +
                    "VALUES (?, ?)");
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return output;
	}

	public GenericObjs selectById(int id) {
		// TODO Auto-generated method stub
		TwitterFeedsFiltersObj twitterFOutput = new TwitterFeedsFiltersObj();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
 
        try {
        	connection = getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM " + GenericDAO.getSCHEMA_NAME() + ".twitterfeedsfilters where id = ?;");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
 
            while (resultSet.next()) {
                twitterFOutput.setId(resultSet.getInt("id"));
                twitterFOutput.setName(resultSet.getString("name"));
                twitterFOutput.setCsvWordSet(resultSet.getString("csvwordset"));
                twitterFOutput.setCreatedOn(resultSet.getTimestamp("createdon"));
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
 
        return twitterFOutput;
	}

	public List<GenericObjs> selectAll() {
		// TODO Auto-generated method stub
		TwitterFeedsFiltersObj twitterFOutput = null;
		List<GenericObjs> outputList = new ArrayList<GenericObjs>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
 
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM " + GenericDAO.getSCHEMA_NAME() + ".twitterfeedsfilters;");
//            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
 
            while (resultSet.next()) {
            	twitterFOutput = new TwitterFeedsFiltersObj();
                twitterFOutput.setId(resultSet.getInt("id"));
                twitterFOutput.setName(resultSet.getString("name"));
                twitterFOutput.setCsvWordSet(resultSet.getString("csvwordset"));
                twitterFOutput.setCreatedOn(resultSet.getTimestamp("createdon"));
                outputList.add(twitterFOutput);
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
 
        return outputList;
	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	public void updateById(GenericObjs person, int id) {
		// TODO Auto-generated method stub

	}

}

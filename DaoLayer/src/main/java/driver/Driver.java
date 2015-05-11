package driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ligadata.fatafat.GenericDAO;
import com.ligadata.fatafat.impl.OutputJsonDAO;
import com.ligadata.fatafat.objs.OutputJsonObj;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		OutputJsonObj jsonObj = new OutputJsonObj("sample userid",
				"sample json");

		GenericDAO dao = new GenericDAO();
		Connection con = dao.getConnection();
		OutputJsonDAO ojDao = new OutputJsonDAO();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement("INSERT INTO "
					+ GenericDAO.getSCHEMA_NAME() + ".outputdata(userid, json)"
					+ "VALUES (?, ?)");

			System.out.println(ojDao.insert(jsonObj, preparedStatement));
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

package driver;

import java.sql.Connection;

import com.ligadata.fatafat.impl.OutputJsonDAO;
import com.ligadata.fatafat.impl.TwitterFeedsFiltersDAO;
import com.ligadata.fatafat.objs.OutputJsonObj;
import com.ligadata.fatafat.objs.TwitterFeedsFiltersObj;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection con = null;
		OutputJsonDAO jDao = null;
		TwitterFeedsFiltersDAO tDao = null;
		OutputJsonObj oDao = null;
		TwitterFeedsFiltersObj tObj = null;
		
		try{
			tDao = new TwitterFeedsFiltersDAO();
			con = tDao.getConnection();
			tObj = new TwitterFeedsFiltersObj();
			tObj.setName("test1");
			tObj.setCsvWordSet("test1,test2,test3");
//			System.out.println(tDao.insert(tObj, con));
			
//			System.out.println(tDao.selectAll());
			
			System.out.println(tDao.selectById(1));
				
		}catch(Exception e){
			
		}
		finally{
			
		}
	}

}

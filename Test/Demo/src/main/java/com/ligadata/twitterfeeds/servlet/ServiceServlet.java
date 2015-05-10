package com.ligadata.twitterfeeds.servlet;

import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ligadata.dao.fatafat.impl.GenericDAO;
import com.ligadata.dao.fatafat.impl.OutputJsonDAO;
import com.ligadata.dao.fatafat.objs.OutputJsonObj;

/**
 * Servlet implementation class TestServlet
 */
public class ServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OutputJsonDAO ojDao = new OutputJsonDAO();
	OutputJsonObj temp = null;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		Connection con = null;
		String param = request.getParameter("param");
		if(param == null)
			new Exception("parameter is empty");
		
//		Client c = new Client();
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		HttpSession session = request.getSession(true);

		synchronized (session) {
			con = new OutputJsonDAO().getConnection();
			java.io.PrintWriter out = response.getWriter();
			try {
//				out.println(c.getHashTagData("#Obama"));
//				c.setParam(param);
//				System.out.println("parameters stored in zooKeeper: " + param);
//				out.println(c.getData());
				temp = (OutputJsonObj) ojDao.selectLastRecord(con);
				out.println(temp.getJson());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
//					c.close();
					out.close();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	} // doGet

}

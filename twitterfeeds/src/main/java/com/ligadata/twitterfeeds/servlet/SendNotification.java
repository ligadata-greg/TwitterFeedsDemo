package com.ligadata.twitterfeeds.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.ligadata.twitterfeeds.utl.ServletsUtil;

/**
 * Servlet implementation class SendNotification
 */
public class SendNotification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendNotification() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ServletsUtil util = new ServletsUtil();
		PrintWriter out = response.getWriter();
		HttpResponse res = null;
		String str = null;
		HttpGet req = new HttpGet(
				"https://api.hipchat.com/v1/rooms/list?format=json&auth_token=e9308f67568031ae408bc5dbf6be45");
		req.addHeader("content-type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			res = httpclient.execute(req);
			str = util.getResponseStr(res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		out.print(str);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("msg-body") == null)
			new Exception("msg-body parameter is empty");
		if (request.getParameter("room-id") == null)
			new Exception("room-id parameter is empty");
		
		String msgBody = request.getParameter("msg-body");
		String roomId = request.getParameter("room-id");
		String str = null;
		ServletsUtil util = new ServletsUtil();
		PrintWriter out = response.getWriter();
		HttpResponse res = null;
		HttpPost req = new HttpPost(
				"https://api.hipchat.com/v1/rooms/message?format=json&auth_token=da1a91a0e4228e53e73f463f4f4c51");
		req.addHeader("content-type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		CloseableHttpClient httpclient = HttpClients.createDefault();
		StringEntity userEntity = new StringEntity(
				"room_id=" + roomId + "&from=Alerts&message=" + msgBody);
		
		try {
			req.setEntity(userEntity);
			res = httpclient.execute(req);
			str = util.getResponseStr(res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(str);
	}

}

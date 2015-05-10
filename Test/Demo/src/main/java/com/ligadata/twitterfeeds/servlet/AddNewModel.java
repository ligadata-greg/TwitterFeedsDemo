package com.ligadata.twitterfeeds.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;

import com.ligadata.twitterfeeds.params.GlobalParameters;
import com.ligadata.twitterfeeds.utl.ServletsUtil;
import com.ligadata.twitterfeeds.utl.XMLTemplete;

/**
 * Servlet implementation class AddNewHashTag
 */
public class AddNewModel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddNewModel() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		String url = "http://" + GlobalParameters.getServerip()
				+ ":" + GlobalParameters.getServiceport() + "/api/Model";
		
		System.out.println(this.getClass().getSimpleName()+"( url: " + url + ")");
		
		HttpPost req = new HttpPost(url);
		req.addHeader("content-type", "application/json; charset=UTF-8");
		CloseableHttpClient httpclient = HttpClients.createDefault();
		ServletsUtil util = new ServletsUtil();
		StringEntity userEntity = new StringEntity(request.getParameter("pmml"));
		req.setEntity(userEntity);
		PrintWriter out = response.getWriter();
		
		HttpResponse resp = httpclient.execute(req);
		String str = null;

		try {
			str = util.getResponseStr(resp);
			out.print(str);
		} catch (Exception e) {
			e.printStackTrace();
			out.print(str);
		}

	}
}

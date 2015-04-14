package com.ligadata.twitterfeeds.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

import com.ligadata.twitterfeeds.utl.XMLTemplete;

/**
 * Servlet implementation class AddNewHashTag
 */
public class AddNewModel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddNewModel() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpPost req = new HttpPost("http://localhost:8081/api/Model");
		req.addHeader("content-type", "application/xml; charset=UTF-8");
		CloseableHttpClient httpclient = HttpClients.createDefault();

		XMLTemplete xmlt = new XMLTemplete();
		StringEntity userEntity = new StringEntity(xmlt.getTHeaderRequestXML("PlusIntIntTest")+xmlt.getDD()+xmlt.getTD()+xmlt.getRlue());
		req.setEntity(userEntity);

		HttpResponse resp = httpclient.execute(req);
		String str;
		try {
			str = getResponseStr(resp);
			System.out.println("method : " + str);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String getResponseStr(HttpResponse response) throws Exception {

		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		String line = "";
		String str = "";

		while ((line = rd.readLine()) != null) {
			str = str + line;
		}

		return str;
	}
}

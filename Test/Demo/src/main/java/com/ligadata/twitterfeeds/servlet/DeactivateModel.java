package com.ligadata.twitterfeeds.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.ligadata.twitterfeeds.params.GlobalParameters;
import com.ligadata.twitterfeeds.utl.ServletsUtil;

/**
 * Servlet implementation class DeactivateModel
 */
public class DeactivateModel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeactivateModel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("model_name") == null)
			new Exception("parameter is empty");
		
		String url = "http://" + GlobalParameters.getServerip() + ":"
				+ GlobalParameters.getServiceport() + "/api/deactivate/Model/"
				+ request.getParameter("model_name");

		System.out.println(this.getClass().getSimpleName()+"( url: " + url + ")");
		
		HttpPut req = new HttpPut(url);
		PrintWriter out = response.getWriter();
		req.addHeader("content-type", "application/json; charset=UTF-8");
		CloseableHttpClient httpclient = HttpClients.createDefault();
		ServletsUtil util = new ServletsUtil();
		String str = null;
		
		HttpResponse resp = httpclient.execute(req);

		try {
			str = util.getResponseStr(resp);
			out.print(str);
		} catch (Exception e) {
			e.printStackTrace();
			out.print(str);
		}

	}

}
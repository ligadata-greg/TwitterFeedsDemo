package com.ligadata.twitterfeeds.utl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;

public class ServletsUtil {
	public String getResponseStr(HttpResponse response) throws Exception {

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

package com.ett.util;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
public class FCCWebServiceTest {

	public static void main(String[] args) {
		String webServiceURL = "https://172.23.28.27:9443/BOCFCCWS/webresources/bocFccWS/getbocfccdata?cif_id=";
		String userCompanyAbbvName = "11274386";
		String bankResponseXML = "";
		try {
			HttpClient httpClient = HttpClientBuilder.create().build();
			// String BankRequrl=URLEncoder.encode(bankReqXML.get(i),"utf-8");
			String EncodeReq;
			EncodeReq = webServiceURL  + userCompanyAbbvName;
			System.out.println("EncodeReq--->" + EncodeReq);
			URL url = new URL(EncodeReq);
			// String BankRequrl=URLEncoder.encode(bankReqXML.get(i),"utf-8");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			System.out.println("test1");
			System.out.println("Bank Request" + url);
			con.setRequestMethod("POST");
			// con.setRequestProperty("Content-Type", "application/json; utf-8");
			// con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			System.out.println("test");
			int Response_code = (con.getResponseCode());
			System.out.println("Response---" + Response_code);
			// HttpPost post = new HttpPost(url);
			// post.setHeader("Content-type", "application/json");
			// OutputStream os=con.getOutputStream();
			// HttpResponse response = httpClient.execute((HttpUriRequest) con);
			// System.out.println("Response---"+response);
			System.out.println("test2");
			if (Response_code == 200) {
				try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
					StringBuilder response1 = new StringBuilder();
					String responseLine = null;
					while ((responseLine = br.readLine()) != null) {
						response1.append(responseLine.trim());
					}
					System.out.println(response1.toString());
					bankResponseXML = response1.toString();

				}
			}
		} catch (Exception e) {
		   e.printStackTrace();
			// TODO: handle exception
		}
		System.out.println("bankResponseXML "+bankResponseXML);
	}
}

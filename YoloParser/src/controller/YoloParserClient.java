package controller;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.entity.StringEntity;

public class YoloParserClient {

	private static CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	
	public static void sendObjectsList(String objectsList) {
		try {
			
			System.out.println("SENDING DATA!");
			httpClient = HttpClientBuilder.create().build();
		    final HttpPost request = new HttpPost("http://localhost:8080/api/realobject/list");
		    final StringEntity params = new StringEntity(objectsList);
		    request.addHeader("content-type", "application/json");
		    request.setEntity(params);
		    HttpResponse response = httpClient.execute(request);
		    httpClient.close();
		}catch (Exception ex) {

		    System.out.println("Problem!");

		}
	}
}

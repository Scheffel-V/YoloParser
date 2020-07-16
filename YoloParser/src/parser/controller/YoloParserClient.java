package parser.controller;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.entity.StringEntity;

public class YoloParserClient {

	private static CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	
	public static void sendObjectPackList(String objectPackList) {
		try {
			System.out.println("SENDING DATA!");
			httpClient = HttpClientBuilder.create().build();
		    final HttpPost request = new HttpPost("http://localhost:8080/api/realobjectpack/list");
		    final StringEntity params = new StringEntity(objectPackList);
		    request.addHeader("content-type", "application/json");
		    request.setEntity(params);
		    HttpResponse response = httpClient.execute(request);
		    httpClient.close();
		}catch (Exception ex) {
		    System.out.println("Problem!");
		}
	}
	
	public static void sendInputObjectList(final String inputObjects) {
		try {
			System.out.println("SENDING DATA!");
			httpClient = HttpClientBuilder.create().build();
		    final HttpPost request = new HttpPost("http://localhost:8080/api/inputobject/list");
		    final StringEntity params = new StringEntity(inputObjects);
		    request.addHeader("content-type", "application/json");
		    request.setEntity(params);
		    HttpResponse response = httpClient.execute(request);
		    httpClient.close();
		}catch (Exception ex) {
		    System.out.println("Problem!");
		}
	}
	
	public static void sendPersons(final String inputObjects) {
		try {
			System.out.println("SENDING DATA!");
			httpClient = HttpClientBuilder.create().build();
		    final HttpPost request = new HttpPost("http://localhost:8080/api/person/list");
		    final StringEntity params = new StringEntity(inputObjects);
		    request.addHeader("content-type", "application/json");
		    request.setEntity(params);
		    HttpResponse response = httpClient.execute(request);
		    httpClient.close();
		}catch (Exception ex) {
		    System.out.println("Problem!");
		}
	}
}

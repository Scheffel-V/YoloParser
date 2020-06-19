package controller;

import java.net.http.HttpClient;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;

import com.google.gson.Gson;

import model.RealObject;

public abstract class YoloParser {
	
	public static List<RealObject> parse(final String input) {
		final String[] lineByLine = input.split("\n");
		final ArrayList<String> inputArray = new ArrayList<String>(Arrays.asList(lineByLine));
		final ArrayList<RealObject> objects = new ArrayList<RealObject>();
		boolean insideObjects = false;

		for (final String line : inputArray) {
			if (line.contains("Objects:")) {
				insideObjects = true;
				continue;
			}
			
			if (line.contains(";")) {
				insideObjects = false;
				objects.clear();
				continue;
			}
			
			if (!insideObjects) {
				continue;
			}
			
			if (!line.isEmpty()) {
				objects.add(lineToObject(line));
			}
		}
		
		return objects;
	}
	
	public static String objectListToJSON(final List<RealObject> realObjectList) {
		return new Gson().toJson(realObjectList);
	}
	
	private static RealObject lineToObject(final String line) {
		final String[] objectAttributes = line.split(":");
		final int objectProbability = Integer.parseInt(objectAttributes[1].substring(1, objectAttributes[1].length() - 1));
		
		return new RealObject(objectAttributes[0], objectProbability);
	}
}

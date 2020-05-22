package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.RealObject;

public abstract class YoloParser {
	
	public static List<RealObject> parse(final String input) {
		System.out.println(input);
		final String[] lineByLine = input.split("\n");
		final ArrayList<String> inputArray = new ArrayList<String>(Arrays.asList(lineByLine));
		System.out.println(inputArray.size());
		final ArrayList<RealObject> objects = new ArrayList<RealObject>();
		boolean insideObjects = false;
		
		
		for (final String line : inputArray) {
			//System.out.println(line);
			if (line.contains("Objects:")) {
				System.out.println("ENTREI NO OBJECTS");
				insideObjects = true;
				continue;
			}
			
			if (line.contains(";")) {
				System.out.println("ENTREI NO ;");
				insideObjects = false;
				objects.clear();
				continue;
			}
			
			if (!insideObjects) {
				System.out.println("ENTREI NO CONTINUE");
				continue;
			}
			
			if (!line.isEmpty()) {
				objects.add(lineToObject(line));
			}
		}
		
		return objects;
	}
	
	private static RealObject lineToObject(final String line) {
		System.out.println("ENTREI NO REALOBJECT");
		System.out.println(line);
		final String[] objectAttributes = line.split(":");
		System.out.println("Objects:" + objectAttributes[0] + " + [" + objectAttributes[1] + "]");
		final int objectProbability = Integer.parseInt(objectAttributes[1].substring(1, objectAttributes[1].length() - 1));
		
		return new RealObject(objectAttributes[0], objectProbability);
	}
}

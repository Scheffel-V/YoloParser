package parser.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;

import parser.model.InputObject;
import parser.model.InputObjects;
import parser.model.RealObjectPack;
import parser.ui.ParserUI;

public class YoloParserController {
	
	public static void startYoloParser(final String[] args) {
		if (args.length < 1) {
			System.out.println("Invalid input. Standard format: java YoloParser mode [ type ]");
			System.out.println("Where mode: ui or cli, and type: unity, quantity or persons.");
			System.exit(0);
		}
				
		switch (args[0]) {
		case "ui":
			initUiMode();
			break;
		
		case "cli":
			initCliMode(args);
			break;
			
		default: 
			System.out.println("Invalid input. Mode must be ui or cli.");
			System.exit(0);
		}
	}
	
	private static void initUiMode() {
		ParserUI parserUI = new ParserUI();
		parserUI.showUI();
	}
	
	private static void initCliMode(final String[] args) {
		if (args.length < 2) {
			System.out.println("Invalid input. Standard format: java YoloParser mode [ type ]");
			System.out.println("When mode equals cli, type must be informed: unity, quantity or persons.");
			System.exit(0);
		}
		
		switch (args[1]) {
		case "unity":
			initUnityTypeParsing();
			break;
			
		case "quantity":
			initQuantityTypeParsing();
			break;
			
		case "persons":
			initPersonsTypeParsing();
			break;
			
		default:
			System.out.println("Invalid input. Type must be unity, quantity or persons.");
			System.exit(0);
		}
	}
	
	private static void initUnityTypeParsing() {
		System.out.println("[0]");
		final InputStreamReader isReader = new InputStreamReader(System.in);
		final BufferedReader bufReader = new BufferedReader(isReader);
		String inputLine = "";
		System.out.println("[1]");	
		boolean starting = true;
		
		while(inputLine != null) {
			String inputStr = "";
		    try {
		    	if (bufReader.ready()) {
			    	do {
				    	inputLine = bufReader.readLine();
				    	inputStr += inputLine;
				    	} while(inputLine != null && !inputLine.equals("}"));
					System.out.println("[2]");
		    	} else {
		    		continue;
		    	}
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			} 
		    
		    if(inputLine != null) {		    	
			    	System.out.println("[3]");
					final InputObjects objects = YoloParser.parseUnitObjects(inputStr);
					final Gson g = new Gson();
					System.out.println("[4]");
					if (YoloParserLogic.objectListHasChanged(objects.getInputObjects())) {
						System.out.println("[5]");
						if (starting) {
							YoloParserClient.sendStartInputObjectList(g.toJson(objects.getInputObjects()));
							starting = false;
						} else {
							YoloParserClient.sendInputObjectList(g.toJson(objects.getInputObjects()));
						}
						System.out.println("[6]");
		    	}
		    } else {
		        System.out.println("EOF");
		    }
		}
	}
	
	private static void initQuantityTypeParsing() {	
		final InputStreamReader isReader = new InputStreamReader(System.in);
		final BufferedReader bufReader = new BufferedReader(isReader);
		String inputLine = "";
		boolean starting = true;
		
		while(inputLine != null) {
			String inputStr = "";
		    try {
		    	if (bufReader.ready()) {
			    	do {
				    	inputLine = bufReader.readLine();
				    	inputStr += inputLine;
				    	} while(inputLine != null && !inputLine.equals("}"));
					System.out.println("[2]");
		    	} else {
		    		continue;
		    	}
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			} 
		    
		    if(inputLine != null) {		    	
			    	System.out.println("[3]");
					final InputObjects objects = YoloParser.parseUnitObjects(inputStr);
					final Gson g = new Gson();
					System.out.println("[4]");
					if (YoloParserLogic.objectListHasChanged(objects.getInputObjects())) {
						System.out.println("[5]");
						if (starting) {
							YoloParserClient.sendStartTopInputObjectList(g.toJson(objects.getInputObjects()));
							starting = false;
						} else {
							YoloParserClient.sendTopInputObjectList(g.toJson(objects.getInputObjects()));
						}
						System.out.println("[6]");
		    	}
		    } else {
		        System.out.println("EOF");
		    }
		}
	}
	
	private static void initPersonsTypeParsing() {
		final InputStreamReader isReader = new InputStreamReader(System.in);
		final BufferedReader bufReader = new BufferedReader(isReader);
		String inputStr = "";
		
		while(inputStr != null) {
		    try {
				inputStr = bufReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			} 
		    
		    if(inputStr != null) {
				final InputObjects objects = YoloParser.parseUnitObjects(inputStr);
				final Gson g = new Gson();
				
				if (YoloParserLogic.objectListHasChanged(objects.getInputObjects())) {
					YoloParserClient.sendPersons(g.toJson(objects.getInputObjects()));
				}
		    } else {
		        System.out.println("EOF");
		    }
		}
	}
}

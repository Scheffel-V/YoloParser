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
		    	} else {
		    		continue;
		    	}
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			} 
		    
		    if(inputLine != null) {		    	
					final InputObjects objects = YoloParser.parseUnitObjects(inputStr);
					final Gson g = new Gson();
					if (YoloParserLogic.frontObjectListHasChanged(objects.getInputObjects())) {
						if (starting) {
							YoloParserClient.sendStartInputObjectList(g.toJson(objects.getInputObjects()));
							starting = false;
						} else {
							YoloParserClient.sendInputObjectList(g.toJson(objects.getInputObjects()));
						}
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
		    	} else {
		    		continue;
		    	}
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			} 
		    
		    if(inputLine != null) {		    	
					final InputObjects objects = YoloParser.parseUnitObjects(inputStr);
					final Gson g = new Gson();
					if (YoloParserLogic.topObjectListHasChanged(objects.getInputObjects())) {
						if (starting) {
							YoloParserClient.sendStartTopInputObjectList(g.toJson(objects.getInputObjects()));
							starting = false;
						} else {
							YoloParserClient.sendTopInputObjectList(g.toJson(objects.getInputObjects()));
						}
		    	}
		    } else {
		        System.out.println("EOF");
		    }
		}
	}
	
	private static void initPersonsTypeParsing() {
		//@TODO
	}
}

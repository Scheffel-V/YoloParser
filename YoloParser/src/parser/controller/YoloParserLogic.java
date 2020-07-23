package parser.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import parser.model.InputObject;


public class YoloParserLogic {
	
	private static List<InputObject> FRONT_OLD_INPUT_OBJECTS = new ArrayList<InputObject>();
	private static List<InputObject> TOP_OLD_INPUT_OBJECTS = new ArrayList<InputObject>();
	private static int FRONT_CHANGE_COUNTER = 10;
	private static int TOP_CHANGE_COUNTER = 10;
	private static List<InputObject> FRONT_CHANGE_INPUT_OBJECTS = new ArrayList<InputObject>();
	private static List<InputObject> TOP_CHANGE_INPUT_OBJECTS = new ArrayList<InputObject>();

	public static boolean frontObjectListHasChanged(final List<InputObject> newInputObjects) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		boolean changed = false;
		
		for (InputObject object : YoloParserLogic.FRONT_OLD_INPUT_OBJECTS) {
			
			final Integer quantity = map.get(object.getClasse());
			
			if (quantity == null) {
				map.put(object.getClasse(), 1);
			} else {
				map.put(object.getClasse(), quantity + 1);
			}
		}
		
		for (InputObject object : newInputObjects) {
			final Integer quantity = map.get(object.getClasse());
			
			if (quantity == null) {
				map.put(object.getClasse(), -1);
			} else {
				map.put(object.getClasse(), quantity - 1);
			}
		}
		
		YoloParserLogic.FRONT_OLD_INPUT_OBJECTS = newInputObjects;
		
		for (String key : map.keySet()) {
			final Integer quantity = map.get(key);
			
			if (quantity > 0) {
				changed = true;
			}
			
			if (quantity < 0) {
				changed = true;
			}
		}
		
		if (changed) {
			FRONT_CHANGE_COUNTER = 10;
			FRONT_CHANGE_INPUT_OBJECTS = newInputObjects;
		} else if (FRONT_CHANGE_COUNTER > 0) {
			return frontObjectListChangeIsConfirmed(newInputObjects);
		}
		
		return false;
	}
	
	private static boolean frontObjectListChangeIsConfirmed(final List<InputObject> newInputObjects) {
		if (!objectListHasChanged(newInputObjects, YoloParserLogic.FRONT_CHANGE_INPUT_OBJECTS)) {
			FRONT_CHANGE_COUNTER--;
			
			if (FRONT_CHANGE_COUNTER == 0) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean topObjectListHasChanged(final List<InputObject> newInputObjects) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		boolean changed = false;
		
		for (InputObject object : YoloParserLogic.TOP_OLD_INPUT_OBJECTS) {
			
			final Integer quantity = map.get(object.getClasse());
			
			if (quantity == null) {
				map.put(object.getClasse(), 1);
			} else {
				map.put(object.getClasse(), quantity + 1);
			}
		}
		
		for (InputObject object : newInputObjects) {
			final Integer quantity = map.get(object.getClasse());
			
			if (quantity == null) {
				map.put(object.getClasse(), -1);
			} else {
				map.put(object.getClasse(), quantity - 1);
			}
		}
		
		YoloParserLogic.TOP_OLD_INPUT_OBJECTS = newInputObjects;
		
		for (String key : map.keySet()) {
			final Integer quantity = map.get(key);
			
			if (quantity > 0) {
				changed = true;
			}
			
			if (quantity < 0) {
				changed = true;
			}
		}
		
		if (changed) {
			TOP_CHANGE_COUNTER = 10;
			TOP_CHANGE_INPUT_OBJECTS = newInputObjects;
		} else if (TOP_CHANGE_COUNTER > 0) {
			return topObjectListChangeIsConfirmed(newInputObjects);
		}
		
		return false;
	}
	
	private static boolean topObjectListChangeIsConfirmed(final List<InputObject> newInputObjects) {
		if (!objectListHasChanged(newInputObjects, YoloParserLogic.TOP_CHANGE_INPUT_OBJECTS)) {
			TOP_CHANGE_COUNTER--;
			
			if (TOP_CHANGE_COUNTER == 0) {
				return true;
			}
		}
		
		return false;
	}
	
	private static boolean objectListHasChanged(final List<InputObject> newInputObjects, final List<InputObject> oldInputObjects) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		for (InputObject object : oldInputObjects) {
			
			final Integer quantity = map.get(object.getClasse());
			
			if (quantity == null) {
				map.put(object.getClasse(), 1);
			} else {
				map.put(object.getClasse(), quantity + 1);
			}
		}
		
		for (InputObject object : newInputObjects) {
			final Integer quantity = map.get(object.getClasse());
			
			if (quantity == null) {
				map.put(object.getClasse(), -1);
			} else {
				map.put(object.getClasse(), quantity - 1);
			}
		}
		
		for (String key : map.keySet()) {
			final Integer quantity = map.get(key);
			
			if (quantity > 0) {
				return true;
			}
			
			if (quantity < 0) {
				return true;
			}
		}
		
		return false;
	}
}

package parser.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parser.model.InputObject;
import parser.model.RealObjectPack;


public class YoloParserLogic {
	
	private static List<InputObject> OLD_INPUT_OBJECTS = new ArrayList<InputObject>();
	private static Map<String, RealObjectPack> OLD_OBJECT_PACKS = new HashMap<String, RealObjectPack>();

	public static boolean objectListHasChanged(final List<InputObject> newInputObjects) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		for (InputObject object : YoloParserLogic.OLD_INPUT_OBJECTS) {
			
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
		
		YoloParserLogic.OLD_INPUT_OBJECTS = newInputObjects;
		
		for (String key : map.keySet()) {
			final Integer quantity = map.get(key);
			
			if (quantity > 0) {
				System.out.println("Foi retirado [" + quantity + "] unidades de [" + key + "]");
				return true;
			}
			
			if (quantity < 0) {
				System.out.println("Foi adicionado  [" + quantity + "] unidades de [" + key + "]");
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean objectPackHasChanged(final Map<String, RealObjectPack> newObjectPackHash) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		for (RealObjectPack objectPack : YoloParserLogic.OLD_OBJECT_PACKS.values()) {
			map.put(objectPack.getName(), objectPack.getQuantity());
		}
		
		for (RealObjectPack objectPack : newObjectPackHash.values()) {
			final Integer quantity = map.get(objectPack.getName());
			
			if (quantity == null) {
				map.put(objectPack.getName(), 0 - objectPack.getQuantity());
			} else {
				map.put(objectPack.getName(), quantity - objectPack.getQuantity());
			}
		}
		
		YoloParserLogic.OLD_OBJECT_PACKS = newObjectPackHash;
		
		for (String key : map.keySet()) {
			final Integer quantity = map.get(key);
			
			if (quantity > 0) {
				System.out.println("Foi retirado [" + quantity + "] unidades de [" + key + "]");
				return true;
			}
			
			if (quantity < 0) {
				System.out.println("Foi adicionado  [" + quantity + "] unidades de [" + key + "]");
				return true;
			}
		}
		
		return false;
	}
}

package parser.model;

import java.util.ArrayList;

public class InputObjects {
	private ArrayList<InputObject> Objects = new ArrayList<InputObject>();
	
	public InputObjects() {
		
	}
	
	public InputObjects(final ArrayList<InputObject> inputObjects) {
		this.setInputObjects(inputObjects);
	}

	public ArrayList<InputObject> getInputObjects() {
		return Objects;
	}

	public void setInputObjects(ArrayList<InputObject> inputObjects) {
		this.Objects = inputObjects;
	}
}

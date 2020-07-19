package parser.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class InputObjects {
	private List<InputObject> Objects = new ArrayList<InputObject>();
	
	public InputObjects() {
		
	}
	
	public InputObjects(final List<InputObject> inputObjects) {
		this.setInputObjects(inputObjects);
	}

	public List<InputObject> getInputObjects() {
        return Objects.stream().filter(java.util.Objects::nonNull).collect(Collectors.toList());
    }

	public void setInputObjects(List<InputObject> inputObjects) {
		this.Objects = inputObjects;
	}
}

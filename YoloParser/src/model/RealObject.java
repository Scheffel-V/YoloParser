package model;

public class RealObject {
	private String name;
	private int probability;
	
	public RealObject(final String name) {
		this.setName(name);
		this.setProbability(0);
	}
	
	public RealObject(final String name, final int probability) {
		this.setName(name);
		this.setProbability(probability);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getProbability() {
		return probability;
	}

	public void setProbability(int probability) {
		this.probability = probability;
	}
}

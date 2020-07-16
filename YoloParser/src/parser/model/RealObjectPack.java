package parser.model;

public class RealObjectPack {
	private String name;
	private int quantity;
	
	public RealObjectPack(final String name) {
		this.setName(name);
		this.setQuantity(1);
	}
	
	public RealObjectPack(final String name, final int quantity) {
		this.setName(name);
		this.setQuantity(quantity);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	public void addOne() {
		this.setQuantity(this.getQuantity() + 1);
	}
	
	public void subOne() {
		this.setQuantity(this.getQuantity() - 1);
	}
}

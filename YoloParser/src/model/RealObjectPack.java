package model;

public class RealObjectPack {
	private RealObject object;
	private int quantity;
	
	public RealObjectPack(final RealObject object) {
		this.setObject(object);
		this.setQuantity(1);
	}
	
	public RealObjectPack(final RealObject object, final int quantity) {
		this.setObject(object);
		this.setQuantity(quantity);
	}

	public RealObject getObject() {
		return object;
	}

	public void setObject(RealObject object) {
		this.object = object;
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

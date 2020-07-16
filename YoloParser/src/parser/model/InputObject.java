package parser.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InputObject {
	private String classe;
	private int prob;
	private double bx;
	private double by;
	private double bw;
	private double bh;
	private String timestamp;

	public InputObject() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		this.timestamp = formatter.format(date);
	}
	
	public InputObject(final String classe, final int probability, final double bx, final double by, final double bw, final double bh) {
		this.classe = classe;
		this.prob = probability;
		this.bx = bx;
		this.by = by;
		this.bw = bw;
		this.bh = bh;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		this.timestamp = formatter.format(date);
	}
	
	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public int getProbability() {
		return prob;
	}

	public void setProbability(int probability) {
		this.prob = probability;
	}

	public double getBx() {
		return bx;
	}

	public void setBx(double bx) {
		this.bx = bx;
	}

	public double getBy() {
		return by;
	}

	public void setBy(double by) {
		this.by = by;
	}
	
	public double getBw() {
		return bw;
	}

	public void setBw(double bw) {
		this.bw = bw;
	}

	public double getBh() {
		return bh;
	}

	public void setBh(double bh) {
		this.bh = bh;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}

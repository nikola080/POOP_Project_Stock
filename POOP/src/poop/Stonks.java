package poop;


import javax.swing.JFrame;

public class Stonks extends JFrame{
	
	private static Stonks instance = null;
	private Prikaz prikaz;
	
	private Stonks() {
		super("Stonks");
		prikaz = null;
	}
	
	public static Stonks getInstance() {
		if(instance == null) instance = new Stonks();
		return instance;
	}
	
	public void setPrikaz(Prikaz p) {
		prikaz = p;
		p.setStonks(this);
	}
	
	public void crtaj() {
		prikaz.prikazi();
	}
	

}

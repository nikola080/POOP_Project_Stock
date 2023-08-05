package poop;

public abstract class Prikaz {
	
	
	protected Stonks frame;
	public void setStonks(Stonks s) {
		frame = s;
	}
	
	public abstract void prikazi();
}

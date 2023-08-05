package poop;


public class Main {
	
	static {
		
	}

	public static void main(String[] args) {
		Transakcija.load();
		Stonks s = Stonks.getInstance();
		s.setPrikaz(new PrikazLogovanje());
		s.crtaj();
	}

}

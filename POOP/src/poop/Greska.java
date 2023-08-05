package poop;

public class Greska extends Throwable{
	private String poruka;
	
	public Greska(String p) {
		this.poruka = p;
	}
	
	public String getPoruka() { return this.poruka;}
}

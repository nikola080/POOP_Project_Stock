package poop;

public class Sveca {
	private double open;
	private double close;
	private double high;
	private double low;
	private int time;
	
	public Sveca(double open, double close, double high, double low, int time) {
		super();
		this.open = open;
		this.close = close;
		this.high = high;
		this.low = low;
		this.time = time;
	} 
	
	public double getOpen() {
		return open;
	}
	public double getClose() {
		return close;
	}
	public double getHigh() {
		return high;
	}
	public double getLow() {
		return low;
	}
	public int getTime() {
		return time;
	}
	
	@Override
	public String toString() {
		return time + "\t" + open + "\t" +high + "\t" +low + "\t" +close; 
		
	}

}

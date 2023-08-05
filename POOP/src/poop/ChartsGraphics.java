package poop;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

public class ChartsGraphics extends JPanel {
	
	private int brojSati = 50;
	private int umnozakX = 3600;
	private double umnozakY = 0.01;
	private double maxValue;
	private double minValue;
	private String comp = null;
	private int n = 0;
	private int pomeraj = 0;
	
	private int vreme1,vreme2;
	
	
	public void resetPomeraj() {
		pomeraj = 0;
	}
	public void resetN() {
		n = 0;
	}
	public void increasePomeraj() {
		pomeraj++;
	}
	
	public void decreasePomeraj() {
		if(pomeraj != 0) pomeraj--;
	}
	
	public void setComp(String com) {
		comp = com;
	}
	
	public void decreaseHours() {
		if(brojSati != 10) brojSati -= 6;
	}
	public void increaseHours() {
		brojSati += 6;
	}
	
	public void increaseValue() {
		n++;
	}
	
	public void decreaseValue() {
		 n--;
	}
	
	public void reset() {
		n = 0;
		pomeraj = 0;
		comp = null;
		brojSati = 50;
	}
	
	private double mininum(List<Sveca> svece) {	
		double min = 1000000000.;
		
		Iterator<Sveca> iter = svece.iterator();
		
		while(iter.hasNext()) {
			double vr = iter.next().getLow();
			if(vr < min) min = vr;
		}
		return min;
	}
	
	private double maximum(List<Sveca> svece) {	
		double max = 0.;
		
		Iterator<Sveca> iter = svece.iterator();
		
		while(iter.hasNext()) {
			double vr = iter.next().getHigh();
			if(vr > max) max = vr;
		}
		return max;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		this.setBackground(Color.white);
		Graphics2D gr = (Graphics2D)g;
		
		if(comp == null) return;
		
		List<Sveca> listaSveca = Kompanije.getKompanije().dohvatiSvece(comp.toUpperCase());
		
		if(listaSveca == null) return;
		
		if(listaSveca.isEmpty()) return;
		List<Sveca> listaPrikaz = new ArrayList<Sveca>();
		
		Iterator<Sveca> iterListaSveca = listaSveca.iterator();
		int lastTime = listaSveca.get(listaSveca.size() - 1).getTime() - pomeraj*3600;
		int firstTime = lastTime - brojSati * 3600;
		
		this.vreme1 = firstTime;
		this.vreme2 = lastTime;
		
		while(iterListaSveca.hasNext()) {
			Sveca s = iterListaSveca.next();
			
			if(s.getTime() <=  lastTime && s.getTime() > firstTime) {
				
				listaPrikaz.add(s);
				
			}
		}
		
		Iterator<Sveca> iter = listaPrikaz.iterator();
		
		int width = this.getWidth();
		int height = this.getHeight();
		
		double min = this.mininum(listaSveca) - n*0.1;
		double max = this.maximum(listaSveca) + n*0.1;
		
		this.minValue = min;
		this.maxValue = max;
		
		while(iter.hasNext()) {
			
			Sveca s = iter.next(); 
			
			int x1 = (int)(width - ((lastTime  - s.getTime()) / 3600.0) * ((width * 1.0) / ((lastTime - firstTime) / 3600.0)));
			int x2 = (int)(width - ((lastTime  - s.getTime() + 3600) / 3600.0) * ((width * 1.0) / ((lastTime - firstTime) / 3600.0)));
			
			int y1 = (int)(height - ((s.getClose()  - min)) * (height / ( max - min)) * 1.);
			int y2 = (int)(height - ((s.getOpen()  - min)) * (height / ( max - min)) * 1.);
			

			if(s.getOpen() < s.getClose()) gr.setColor(Color.green);
			else gr.setColor(Color.red);
			
			int y1High = (int)(height - ((s.getHigh()  - min)) * (height / ( max - min)) * 1.);
			int y2Low = (int)(height - ((s.getLow()  - min)) * (height / ( max - min)) * 1.);
			
			if(s.getOpen() < s.getClose()) {
				gr.setColor(Color.green);
				gr.fillRect(x2, y1, Math.abs(x1-x2), Math.abs(y1-y2));
				 
//				gr.setStroke(new BasicStroke((int)( Math.abs(x1 - x2) / 8)));
//				gr.drawLine((int)(Math.abs(x1 + (x1 + x2) / 2)), y2Low, (int)(Math.abs(x1 + (x1 + x2) / 2)), y1High);
				
				
			}
			else {
				gr.setColor(Color.red);
				
				gr.fillRect(x2, y2, Math.abs(x1-x2), Math.abs(y1-y2));
			}
			
		}
		
	}
	public int getBrojSati() {
		return brojSati;
	}
	public double getMaxValue() {
		return maxValue;
	}
	public double getMinValue() {
		return minValue;
	}
	public int getVreme1() {
		return vreme1;
	}
	public int getVreme2() {
		return vreme2;
	}
	
	
	

}

package poop;

import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;


public class Kompanije {
	private TreeMap<String, List<Sveca>> mapa = null;
	
	private static Kompanije instance = null; 
	
	private Kompanije() {
		this.mapa = new TreeMap<String,List<Sveca>>();
	}
	
	public static Kompanije getKompanije() {
		
		if(instance == null) instance = new Kompanije();
		return instance;
		
	}
	
	public void dodajSvece(String comp,List<Sveca> svece) {
		if(mapa.containsKey(comp)) {
			List<Sveca> sveceStare = mapa.get(comp);
			Iterator<Sveca> iter = svece.iterator();
			while(iter.hasNext()) {
				
				Sveca tren = iter.next();
				int checks = 0;
				int vremStare = tren.getTime();
				
				Iterator<Sveca> iterr = sveceStare.iterator();
				while(iterr.hasNext()) {
					
					Sveca star = iterr.next();
					if(star.getTime() == vremStare) {
						checks = 1;
						break;
					}
				}
				
				if(checks == 0) {
					sveceStare.add(tren);
				}
			}
			
			sveceStare.sort((Sveca s1,Sveca s2) -> {
				return ((Integer)s1.getTime()).compareTo((Integer)s2.getTime());
			});
			mapa.put(comp, sveceStare);
		}
		else {		
			mapa.put(comp, svece);
		}
		
		
	}
		
	public List<Sveca> dohvatiSvece(String company){
		List<Sveca> svece = null;
		svece = mapa.get(company);
		return svece;
	}
	
	private void ispis(Sveca s) {
		System.out.println(s.getTime() + " " + s.getOpen() + " " + s.getClose() + " " + s.getHigh() + " " + s.getLow());
	}
	public void stampajSvece(String comp) {
		List<Sveca> svece = mapa.get(comp);
		svece.forEach(this::ispis);
	}
	
	
}

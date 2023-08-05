package poop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Transakcija {
	
	private static int IdT;
	
	public static void load() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("src/fajlovi/Index.txt")));
			int k = Integer.parseInt(reader.readLine());
			IdT = k;
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void updateIdT(int k) {
		File fajl = new File("src/fajlovi/Index.txt");
		fajl.delete();
		try {
			fajl.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(fajl,true));
			PrintWriter printer = new PrintWriter(writer);	
			printer.print(k);
			printer.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public double getCurrentValue(String comp) {
		double ret = 0; 
		long unixTime = Instant.now().getEpochSecond() - 10;
		JsonReaderParser parser = JsonReaderParser.getInstance();
		String url = "https://query1.finance.yahoo.com/v8/finance/chart/" + comp + "?period1=" + unixTime + "&period2=" + unixTime + "&interval=1h";
		String json;
		try {
			json = parser.readJSONFromURL(url);
		} catch (IOException e) {
			return ret;
		}
        Pattern p = Pattern.compile("^.*\"regularMarketPrice\":([^\\,]*).*$");
        Matcher m = p.matcher(json);
        
        if(m.matches()){
        	ret = Double.parseDouble(m.group(1));
        }

		return ret;
	}
	
	//vraca false ako postoji, true u suprotnom
	private boolean napravi(String filename) {
		File fajl = new File("src/fajlovi/" + filename);
		if(fajl.exists()) {
			return false;
		}
		else {
			
			try {
				fajl.createNewFile();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return false;
	}
	
	public int kupi(String comp,int kol) {	
		
		String usr = Korisnik.getUsername();
		String psw = Korisnik.getPassword();
		int ret = 0;
		double cena = this.getCurrentValue(comp);
		
		if(!(usr == null || psw == null) && cena != 0) {
			
			String filename = "transakcije" + usr + ".txt";
			
			napravi(filename);
				double ukupno = kol * cena;
				double stanje = Double.parseDouble(Korisnik.getKolicina());
				String novoStanje = Double.toString(stanje - ukupno);
				if(ukupno <= stanje) {
					Korisnik.setKolicina(Double.toString(stanje - ukupno));
					
					List<String> niz = new ArrayList<String>();
					File korisnici = new File("src/fajlovi/korisnici.txt");
					try {
						BufferedReader reader;
						reader = new BufferedReader(new FileReader(korisnici));
						String linija;
						
						while((linija = reader.readLine()) != null){
							niz.add(new String(linija));
						}
						reader.close();
						
						korisnici.delete();
						korisnici.createNewFile();
						
						for(int i = 0; i < niz.size();i++) {
							String[] sp = niz.get(i).split("\t");
							if(sp[0].contentEquals(Korisnik.getUsername())) {
								String novi = Korisnik.getUsername() + "\t" + Korisnik.getPassword() + "\t" + novoStanje;
								niz.set(i, novi);
							}
						}
						
						
						
						BufferedWriter writer = new BufferedWriter(new FileWriter(korisnici,true));
						PrintWriter printer = new PrintWriter(writer);	
						
						
						niz.forEach(l -> {
							printer.println(l);
						});
						
						

						writer.close();
						printer.close();
			
						
						BufferedWriter writerk = new BufferedWriter(new FileWriter(new File("src/fajlovi/" + filename),true));
						PrintWriter printerk = new PrintWriter(writerk);	
					
						
						String unos_transkacija_kupovina = IdT + "\t" + usr + "\t" + kol + "\t" + comp +  "\t" + cena;
						printerk.println(unos_transkacija_kupovina);
						
						printerk.close();
						writerk.close();
						
						Transakcija.updateIdT(Transakcija.IdT++ + 1);
						
						return 1;
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				
				}
				
		}
		return ret;
	}
	
	public int prodaj(int IdT, int kol) {
		String usr = Korisnik.getUsername();
		String psw = Korisnik.getPassword();
		File fajl = new File("src/fajlovi/transakcije" + usr + ".txt");

	    if(!(!fajl.exists() || usr == null || psw == null)) {
	    	try {
				BufferedReader reader;
				reader = new BufferedReader(new FileReader(fajl));
				
				String linija;
				String[] data = null;
				while((linija = reader.readLine()) != null) {
					data = linija.split("\t");
					if(IdT == Integer.parseInt(data[0]) && usr.contentEquals(data[1])) {
						break;
					}
				}
				
				if(linija != null && (Integer.parseInt(data[2]) >= kol)) {
					String comp = data[3];
					double cena = this.getCurrentValue(comp);
					if(cena != 0) {					
						
						BufferedReader readerFinal  = new BufferedReader(new FileReader(fajl));
						
						List<String> nizTransakcija = new ArrayList<String>();
						
						while(true) {
							String read = readerFinal.readLine();
							if(read == null) break;
							
							String[] spl = read.split("\t");
							
							if(Integer.parseInt(spl[0]) == IdT) {
								spl[2] = Integer.toString(Integer.parseInt(spl[2]) - kol);
							}
							
							read = spl[0] + "\t" + spl[1] + "\t" + spl[2] + "\t" + spl[3] + "\t" + spl[4]; 
							nizTransakcija.add(read);
						}
	
						readerFinal.close();
						
						fajl.delete();
						fajl.createNewFile();
						
						BufferedWriter writer = new BufferedWriter(new FileWriter(fajl));
						PrintWriter printert = new PrintWriter(writer);
						
						nizTransakcija.forEach(l ->{							
							String[] spl = l.split("\t");
							if(Integer.parseInt(spl[2]) != 0)printert.println(l);
						});
						
						printert.close();
						writer.close();
						
						File fajlK = new File("src/fajlovi/korisnici.txt");
						
						if(fajlK.exists()) {
							BufferedReader readerK = new BufferedReader(new FileReader(fajlK));							
							
							List<String> nizKorisnika = new ArrayList<String>();
							
							while(true) {
								String line = readerK.readLine();
								if(line == null) break;
								
								String[] spl = line.split("\t");
								if(spl[0].contentEquals(usr)) {
									spl[2] = Double.toString(Double.parseDouble(spl[2]) + cena*kol);
									line = usr + "\t" + psw + "\t" + spl[2];
								}
								
								nizKorisnika.add(line);
							}
							
							readerK.close();
							fajlK.delete();
							fajlK.createNewFile();
							
							BufferedWriter writerK = new BufferedWriter(new FileWriter(fajlK));
							PrintWriter printerK = new PrintWriter(writerK);
							
							nizKorisnika.forEach(l ->{
								printerK.println(l);
							});
							
							printerK.close();
							writerK.close();
							return 1;
						}
	
					}
				}
				
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	
	    }

		return 0;
	}
	
	
	
//	public static void main(String[] args) {
//		Transakcija.load();
//		Korisnik.logIn("marko", "marko98");
//		Transakcija t = new Transakcija();
//		//t.kupi("nflx",100);
//		t.prodaj(3, 99);
//
//	}
	

}

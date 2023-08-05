package poop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Korisnik {
	
	private static String username = null;
	private static String password = null;
	private static String kolicina = null;
	

	
	public Korisnik(String username2, String password2,String kolicina2) {
		username = username2;
		password = password2;
		kolicina = kolicina2;
	}


	public static Korisnik logIn(String usr, String pas) {
		Korisnik ret = null;
		File fajl = new File("./src/fajlovi/korisnici.txt");
		String username = null;
		String password = null;
		String kolicina = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fajl));
			String red = null;
			while((red = reader.readLine()) != null) {
				String[] sp = red.split("\t");
				if(sp[0].contentEquals(usr) && sp[1].contentEquals(pas)) {
					username = new String(sp[0]);
					password = new String(sp[1]);
					kolicina  = new String(sp[2]);
					reader.close();
					return new Korisnik(username,password,kolicina);
					
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public static void signUp(String usr, String pas,String kol) {
		String unos =  usr + "\t" + pas + "\t" + kol;
		File fajl = new File("./src/fajlovi/korisnici.txt");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fajl,true));
			PrintWriter printer = new PrintWriter(writer);
			printer.println(unos);	
			printer.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void logOut() {
		Korisnik.password = null;
		Korisnik.username = null;
	}


	public static String getUsername() {
		return username;
	}


	public static String getPassword() {
		return password;
	}
	
	public static String getKolicina() {
		return kolicina;
	}


	public static void setKolicina(String kolicina) {
		Korisnik.kolicina = kolicina;
	}

	public static boolean postojiKorisnik(String usr) {
		boolean ret = false;
		
		try {
			File fajl = new File("src/fajlovi/korisnici.txt");
			BufferedReader reader = new BufferedReader(new FileReader(fajl));
			
			String linija;
			
			while(true) {
				linija = reader.readLine();
				
				if(linija == null) break;
				String[] spl = linija.split("\t");
				
				if(usr.contentEquals(spl[0])) {
					reader.close();
					return true;
				
				}
			}
			reader.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ret;
	}

	

}

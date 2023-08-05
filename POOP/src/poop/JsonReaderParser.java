package poop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class JsonReaderParser {
	
		  private static JsonReaderParser instance = null;
		  private JsonReaderParser() {
			  
		  }
		  
		  public static JsonReaderParser getInstance() {
			  if(instance == null) instance = new JsonReaderParser();
			  return instance;
		  }
		  
		  private String readAll(Reader rd) throws IOException {
		    StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
		    return sb.toString();
		  }

		  public String readJSONFromURL(String url) throws IOException {
		    InputStream is = new URL(url).openStream();
		    try {
		      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		      String jsonText = null;
		      jsonText = this.readAll(rd);
		      return jsonText;
		    } finally {
		      is.close();
		    }
		  }
		  
		  private static List<String> redosled(String jsonText) {
			  String[] red = new String[4];
			  red[0] = "open";
			  red[1] = "low";
			  red[2] = "high";
			  red[3] = "close";
			  	  
			  for(int i = 0; i < 4; i++) {
				  String prvi = red[i];
				  
				  for(int j = 0; j < 4; j++) {
					  String drugi = red[j];
					  if(drugi.equals(prvi)) continue;
					  
					  for(int k = 0; k < 4; k++) {
						  String treci = red[k];
						  if(treci.equals(prvi) || treci.equals(drugi)) continue;
						  
						  for(int l = 0; l < 4;l++) {
							  String cetvrti = red[l];
							  if(cetvrti.equals(prvi) || cetvrti.equals(drugi) || cetvrti.equals(treci)) continue;
							  
							  Pattern p = Pattern.compile("^.*\"(" + prvi + ")\".*\"(" + drugi + ")\".*\"(" + treci + ")\".*\"(" + cetvrti + ")\".*$");
							  Matcher m = p.matcher(jsonText);
							  
							  if(m.matches()) {
								  List<String> ret = new ArrayList<String>();
								  ret.add(prvi);
								  ret.add(drugi);
								  ret.add(treci);
								  ret.add(cetvrti);
								  return ret;
							  }
							
						  }
					  }
					  
				  }
			  }
			return null;
			  
			  
		  }
		
		  public void createCandles(String jsonText){
			  if(jsonText != null) {
						  
				  List<String> red = JsonReaderParser.redosled(jsonText);
				  Pattern pars = Pattern.compile("^.*\"symbol\":\"([^\"]*)\".*\"timestamp\":\\[([^\\]]*)\\].*\"" + red.get(0) + "\":\\[([^\\]]*)\\].*\"" + red.get(1) + "\":\\[([^\\]]*)\\].*\"" + red.get(2) + "\":\\[([^\\]]*)\\].*\"" + red.get(3) + "\":\\[([^\\]]*)\\].*$");
				  Matcher vrednosti = pars.matcher(jsonText);
				  
				  if(vrednosti.matches()) {
					  int indexOpen = red.indexOf(new String("open"));
					  int indexHigh = red.indexOf(new String("high"));
					  int indexLow = red.indexOf(new String("low"));
					  int indexClose = red.indexOf(new String("close"));
					  
					  String[] opens = vrednosti.group(indexOpen + 3).split(",");
					  String[] highs = vrednosti.group(indexHigh + 3).split(",");
					  String[] lows = vrednosti.group(indexLow + 3).split(",");
					  String[] closes = vrednosti.group(indexClose + 3).split(",");
					  String[] times = vrednosti.group(2).split(",");
					  
					  List<Sveca> svece = new ArrayList<Sveca>();
					  
					  for(int i = 0; i < opens.length; i++) {
						  
						  if(opens[i].contentEquals("null")) continue;
						  svece.add(new Sveca(
								    Double.parseDouble(opens[i]),
								    Double.parseDouble(closes[i]),
								    Double.parseDouble(highs[i]),
								    Double.parseDouble(lows[i]),
								    Integer.parseInt(times[i])
								    		)
								  );
					  }
					  
					  Kompanije k = Kompanije.getKompanije();
					  k.dodajSvece(vrednosti.group(1), svece);
				  }				
				  
			  }
			
		  }
		  
		  public void dohvatiPodatke(int vreme1 , int vreme2, String symbol) throws Greska, IOException{
			  if(vreme1 > vreme2) throw new Greska("Pogresan unos vremena!");
			  String zaParsiranje = this.readJSONFromURL("https://query1.finance.yahoo.com/v8/finance/chart/" + symbol + "?period1=" + vreme1 + "&period2=" + vreme2 + "&interval=1h");
			  this.createCandles(zaParsiranje);  
		  }
//		  public static void main(String[] args) {
//			  
//			  int a  = 5;
//			  System.out.println(a); 
//			  try {
//				  JsonReaderParser reader = JsonReaderParser.getInstance();
//				  String zaParsiranje = reader.readJSONFromURL("https://query1.finance.yahoo.com/v8/finance/chart/aapl?period1=1617272670&period2=1617531870&interval=1h");
//				  reader.createCandles(zaParsiranje);
//				  
//				  Kompanije.getKompanije().stampajSvece("AAPL");
//			  } 
//			  catch(IOException g) {
//				  System.out.println("Pogresno unet format!");
//			  }
//		  }
		  
		  
}

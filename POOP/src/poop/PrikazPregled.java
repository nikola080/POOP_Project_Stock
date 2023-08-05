package poop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class PrikazPregled extends Prikaz {

	@Override
	public void prikazi() {
		frame.getContentPane().removeAll();
		frame.setSize(1050, 600);
		frame.setJMenuBar(null);
		
		JMenuBar mb = new JMenuBar(); 
		mb.setPreferredSize(new Dimension(1050,20));
		JMenu menu = new JMenu("Opcije"); 
		menu.setPreferredSize(new Dimension(100,20));
		JMenuItem  i2, i3, i4;
		 
        i2=new JMenuItem("Napravi transakciju");  
        i3=new JMenuItem("Log out");  
        i4= new JMenuItem("Pocetna");
        
  
        i2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Stonks s  = Stonks.getInstance();
				s.setPrikaz(new PrikazTransakcija());
				s.crtaj();			
			}
        	
        });
        i3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Stonks s  = Stonks.getInstance();
				s.setPrikaz(new PrikazLogovanje());
				s.crtaj();			
			}
        	
        });
        i4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Stonks s  = Stonks.getInstance();
				s.setPrikaz(new PrikazGraf());
				s.crtaj();			
			}
        	
        });
 
        menu.add(i2); menu.add(i3); menu.add(i4);  
        mb.add(menu); 
        frame.setJMenuBar(mb);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		String usr = Korisnik.getUsername();
		File fajl = new File("src/fajlovi/transakcije" + usr + ".txt");
		List<String> transakcije = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fajl));
			
			while(true) {
				
				String line = reader.readLine();
				if(line == null) break;
				
				transakcije.add(line);
			}
			
			reader.close();
		} catch (IOException e) {
			
			
		} 
		File fajl1 = new File("src/fajlovi/korisnici.txt");
		String stanje = "0";
		try {
			BufferedReader reader1 = new BufferedReader(new FileReader(fajl1));
			
			while(true) {
				
				String line = reader1.readLine();
				if(line == null) break;
				
				String[] spll = line.split("\t");
				if(usr.contentEquals(spll[0])) {
					stanje = (spll[2].split("\n"))[0];
				}
			}
			
			reader1.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		Transakcija t = new Transakcija();
		TreeMap<String,Double> kompanije = new TreeMap<String,Double>();
		
		for(int i = 0; i < transakcije.size();i++) {
			String[] spl = transakcije.get(i).split("\t");
			
			if(!kompanije.containsKey(spl[3])) {
				
				kompanije.put(spl[3], t.getCurrentValue(spl[3]));
				
			}
			
		}
		JTextPane tekst = new JTextPane();
		tekst.setEditable(false);
		SimpleAttributeSet a = new SimpleAttributeSet();
		Document doc = tekst.getStyledDocument();
		
		StyleConstants.setBold(a,true);
		StyleConstants.setFontSize(a, 20);
		
		
		String top1 = "Stanje: " + stanje + "\n\n";
		String top2 = "IdTransakcije\tKompanija\tKolicina\tKupljena\tTrenutna\tRazlika\n";
		
		try {
			doc.insertString(doc.getLength(), top1, a);
			doc.insertString(doc.getLength(), top2, a);
			StyleConstants.setBold(a, false);
			for(int i = 0; i < transakcije.size(); i++) {
				  
				String[] spl = transakcije.get(i).split("\t");
				double tcena = kompanije.get(spl[3]);
				
				double razlika = Double.parseDouble(spl[4]) - tcena;
				
				String novi = spl[0] + "\t\t" + spl[3] + "\t\t" + spl[2] + "\t\t" + spl[4] + "\t\t" + tcena + "\t\t";
				doc.insertString(doc.getLength(), novi, a);
				
				
				if(razlika < 0) StyleConstants.setForeground(a, Color.red);
				else StyleConstants.setForeground(a, Color.green);
				
				String raz = razlika + "\n";
				
				double razz = Double.parseDouble(raz);
				DecimalFormat numberFormat = new DecimalFormat("#.0000000");
				numberFormat.format(razz); 
				doc.insertString(doc.getLength(), numberFormat.format(razz) + "\n", a);
				
				StyleConstants.setForeground(a, Color.black);
				
				
				
			}
			
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		JScrollPane scrol = new JScrollPane(tekst);
		scrol.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		frame.add(scrol,BorderLayout.CENTER);
		frame.setVisible(true);
		frame.revalidate();
		frame.repaint();
	}

}

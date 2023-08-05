package poop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PrikazGraf extends Prikaz {


	@Override
	public void prikazi() {
		
		frame.getContentPane().removeAll();
		frame.setSize(1550, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		JMenuBar mb = new JMenuBar(); 
		mb.setPreferredSize(new Dimension(1550,20));
		JMenu menu = new JMenu("Opcije"); 
		menu.setPreferredSize(new Dimension(100,20));
		JMenuItem i1, i2, i3, i4;
		
	    i1=new JMenuItem("Pregeld racuna");  
        i2=new JMenuItem("Napravi transakciju");  
        i3=new JMenuItem("Log out");  
        i4= new JMenuItem("Pocetna");
        
        i1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Stonks s  = Stonks.getInstance();
				s.setPrikaz(new PrikazPregled());
				s.crtaj();			
			}
        	
        });
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
 
        menu.add(i1); menu.add(i2); menu.add(i3); menu.add(i4);  
        mb.add(menu); 
        frame.setJMenuBar(mb);
        
        
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		JPanel panel2 = new JPanel();
		
		panel1.setPreferredSize(new Dimension(1200,1000));
		panel2.setPreferredSize(new Dimension(350,1000));
		
		
		
		JPanel panelForCandles = new JPanel();
		panelForCandles.setPreferredSize(new Dimension(1200,950));
		panelForCandles.setLayout(new BorderLayout());
		
											ChartsGraphics glavnaGrafika = new ChartsGraphics();
		
		panelForCandles.add(glavnaGrafika);
		
		JPanel panelForMoving = new JPanel();
		panelForMoving.setPreferredSize(new Dimension(1200,50));
		panelForMoving.setBackground(Color.gray);
		JLabel pare = new JLabel("$: ");
		pare.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,18));
		JTextField pareV = new JTextField();
		pareV.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,26));
		pareV.setPreferredSize(new Dimension(200,40));
		pareV.setEditable(false);
		
		JLabel vreme = new JLabel("Vreme: ");
		vreme.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,18));
		JTextField vremeV = new JTextField();
		vremeV.setPreferredSize(new Dimension(200,40));
		vremeV.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
		vremeV.setEditable(false);
		
		panelForMoving.setLayout(new GridBagLayout());
		JButton moveLeft = new JButton("<");
		JButton moveRight = new JButton(">");
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		panelForMoving.add(moveLeft,gbc);
		panelForMoving.add(moveRight,gbc);
		panelForMoving.add(pare,gbc);
		panelForMoving.add(pareV,gbc);
		panelForMoving.add(vreme,gbc);
		panelForMoving.add(vremeV,gbc);
		
		
		
		panel1.setLayout(new BorderLayout());
		panel1.add(panelForCandles,BorderLayout.CENTER);
		panel1.add(panelForMoving,BorderLayout.SOUTH);
		
		panel2.setBackground(Color.LIGHT_GRAY);
		panel2.setLayout(new GridLayout(3,1));
		
			JPanel panel21 = new JPanel();
			panel21.setLayout(new GridLayout(3,2));
				JLabel pocetno = new JLabel("Pocetno vreme: ");
				pocetno.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,18));
				JLabel krajnje = new JLabel("Krajnje vreme: ");
				krajnje.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,18));
				JTextField unosPocetno = new JTextField();
				unosPocetno.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,24));
				JTextField unosKrajnje = new JTextField();
				unosKrajnje.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,24));
				JLabel kompanija = new JLabel("Komapnija: ");
				kompanija.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,18));
				JTextField unosKompanije = new JTextField();
				unosKompanije.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,24));
				
				
				panel21.add(pocetno);
				
					JPanel panel212 = new JPanel();
					panel212.setLayout(new GridLayout(3,1));
					JPanel p212p1 = new JPanel();
					JPanel p212p2 = new JPanel();
					panel212.add(p212p1);
					panel212.add(unosPocetno);
					panel212.add(p212p2);
					panel21.add(panel212);
				
				panel21.add(krajnje);
					JPanel panel214 = new JPanel();
					panel214.setLayout(new GridLayout(3,1));
					JPanel p214p1 = new JPanel();
					JPanel p214p2 = new JPanel();
					panel214.add(p214p1);
					panel214.add(unosKrajnje);
					panel214.add(p214p2);
					panel21.add(panel214);
					
				panel21.add(kompanija);
					JPanel panel216 = new JPanel();
					panel216.setLayout(new GridLayout(3,1));
					JPanel p216p1 = new JPanel();
					JPanel p216p2 = new JPanel();
					panel216.add(p216p1);
					panel216.add(unosKompanije);
					panel216.add(p216p2);
					panel21.add(panel216);
				
			
			JPanel panel22 = new JPanel();
			panel22.setLayout(new GridLayout(7,1));
				JLabel errorSearch = new JLabel("");
				panel22.add(errorSearch);
				JButton pretraga = new JButton("Pretraga");
				panel22.add(pretraga);
				panel22.add(new JPanel());
				panel22.add(new JPanel());
				panel22.add(new JPanel());
				panel22.add(new JPanel());
				panel22.add(new JPanel());
				
			JPanel panel23 = new JPanel();
			panel23.setLayout(new GridLayout(2,1));
				
				JPanel panel231 = new JPanel();
				panel231.setLayout(new GridLayout(3,2));
				JLabel kmp = new JLabel("Kompanija za prikaz: ");
				kmp.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,18));
				JTextField unosKmpPretraga = new JTextField();
				unosKmpPretraga.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,24));
				panel231.add(new JPanel());
				panel231.add(new JPanel());
				panel231.add(kmp);
				panel231.add(unosKmpPretraga);
				panel231.add(new JPanel());
				panel231.add(new JPanel());
				
				JPanel panel232 = new JPanel();
				panel232.setLayout(new BorderLayout());
				JButton prikazPretraga = new JButton("Prikazi");
				panel232.add(prikazPretraga,BorderLayout.NORTH);
	
				
			panel23.add(panel231);
			panel23.add(panel232);
		panel2.add(panel21);
		panel2.add(panel22);
		panel2.add(panel23);
		
		pretraga.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(unosPocetno.getText().contentEquals("") ||
				   unosKrajnje.getText().contentEquals("") ||
				   unosKompanije.getText().contentEquals(""))
				{
					errorSearch.setText("Morate uneti sve podatke!");
					return;
				}
				errorSearch.setText("");
				int pocetak = Integer.parseInt(unosPocetno.getText());
				int kraj = Integer.parseInt(unosKrajnje.getText());
				String komp = unosKompanije.getText();
				
				try {
					JsonReaderParser.getInstance().dohvatiPodatke(pocetak, kraj, komp);
					errorSearch.setText("");
				} catch (IOException e1) {
					errorSearch.setText("Ne postoji uneta kompanija!");
					
				} catch (Greska e1) {
					errorSearch.setText("Pocetno vreme mora biti manje od krajnjeg!");
				}
				catch (NullPointerException e1) {
					errorSearch.setText("Ne postoji uneta kompanija!");
				}
				glavnaGrafika.setComp(komp);
				glavnaGrafika.repaint();
				glavnaGrafika.validate();
				
			}
			
		});
		
		prikazPretraga.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Prikazi pocetno stanje za tu kompaniju
				
			}
			
		});
		
		panelForCandles.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				int smer = e.getWheelRotation(); // -1 gore, 1 dole
				
				if(smer < 0) {
					glavnaGrafika.increaseValue();
					glavnaGrafika.repaint();
					glavnaGrafika.revalidate();
				}
				else {
					glavnaGrafika.decreaseValue();
					glavnaGrafika.repaint();
					glavnaGrafika.revalidate();
				}
				
			}
			
		});
		
		panelForCandles.setFocusable(true);
		panelForCandles.requestFocusInWindow();
	
		panelForCandles.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				int pressed = e.getKeyCode();
				
				if(pressed == KeyEvent.VK_LEFT) {
					glavnaGrafika.increaseHours();
					glavnaGrafika.repaint();
					glavnaGrafika.revalidate();
				}
				else if(pressed == KeyEvent.VK_RIGHT) {
					glavnaGrafika.decreaseHours();
					glavnaGrafika.repaint();
					glavnaGrafika.revalidate();
				}
				
			}
			
		});
		
		panelForCandles.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				
				int height = panelForCandles.getHeight();
				int width = panelForCandles.getWidth();
				
				double min = glavnaGrafika.getMinValue();
				double max = glavnaGrafika.getMaxValue();
						
				int prvo = glavnaGrafika.getVreme1();
				int drugo = glavnaGrafika.getVreme2();
				
				double valueDollars =  max - ((max - min)*y*1.0)/(height*1.0);
				long vreme = (long)(prvo + (x*1.0*(drugo - prvo)) / (width * 1.0));
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				
				vremeV.setText(Instant.ofEpochSecond(vreme)
				        .atZone(ZoneId.of("GMT-4"))
				        .format(formatter));
				 
				DecimalFormat numberFormat = new DecimalFormat("#.00");
				pareV.setText(numberFormat.format(valueDollars)); 
				
				
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		moveLeft.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				glavnaGrafika.increasePomeraj();
				glavnaGrafika.repaint();
				glavnaGrafika.revalidate();
				
			}
			
		});
		
		moveRight.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				glavnaGrafika.decreasePomeraj();
				glavnaGrafika.repaint();
				glavnaGrafika.revalidate();
				
			}
			
		});
		
		prikazPretraga.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String komp = unosKmpPretraga.getText();
				
				if(Kompanije.getKompanije().dohvatiSvece(komp.toUpperCase()) == null) return;
				
				glavnaGrafika.reset();
				glavnaGrafika.setComp(komp);
				glavnaGrafika.repaint();
				glavnaGrafika.revalidate();
				
				
			}
			
		});
		
		
	
		frame.add(panel1,BorderLayout.CENTER);
		frame.add(panel2,BorderLayout.EAST);
		frame.setVisible(true);
		frame.revalidate();
		frame.repaint();
		
	}

}

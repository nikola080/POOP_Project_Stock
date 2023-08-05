package poop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PrikazTransakcija extends Prikaz {

	
	@Override
	public void prikazi() {
		
		frame.getContentPane().removeAll();
		frame.setSize(650, 600);
		frame.setJMenuBar(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar mb = new JMenuBar(); 
		mb.setPreferredSize(new Dimension(650,20));
		JMenu menu = new JMenu("Opcije");  
		menu.setPreferredSize(new Dimension(100,20));
		JMenuItem i1, i3, i4;
		
	    i1=new JMenuItem("Pregeld racuna");  
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
 
        menu.add(i1); menu.add(i3); menu.add(i4); 
        mb.add(menu); 
        frame.setJMenuBar(mb);
		
        frame.setLayout(new GridLayout(1,2));
        
        JPanel panelWest = new JPanel();
        panelWest.setBackground(Color.LIGHT_GRAY); 
        JPanel panelEast = new JPanel();
        panelEast.setBackground(Color.GRAY);
        
        panelEast.setLayout(new GridLayout(4,1));
        JLabel buyLabel = new JLabel("Kupovina");
        buyLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,60));
        
        JPanel p11 = new JPanel();
        p11.setBackground(Color.GRAY);
        p11.setLayout(new GridBagLayout());
        p11.add(buyLabel);
        panelEast.add(p11);
        
        JTextArea unosKompanije = new JTextArea();
        unosKompanije.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,30));
        JTextArea unosKolicineBuy = new JTextArea();
        unosKolicineBuy.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,30));
        JLabel unosK = new JLabel("Kompanija:");
        unosK.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,30));
        JLabel unosKB = new JLabel("Kolicina:");
        unosKB.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,30));
        
        JPanel p12 = new JPanel();
        p12.setBackground(Color.GRAY);
        panelEast.add(p12);
        p12.setLayout(new GridLayout(1,2));
        p12.add(unosK);
        
        	JPanel p122 = new JPanel();
        	p122.setBackground(Color.GRAY);
        	p122.setLayout(new GridLayout(3,1));
        	JPanel pt1 = new JPanel();
        	pt1.setBackground(Color.GRAY);
        	p122.add(pt1);
        	p122.add(unosKompanije);
        	JPanel pt2 = new JPanel();
        	pt2.setBackground(Color.GRAY);
        	p122.add(pt2);
        
        p12.add(p122);
        
        JPanel p13 = new JPanel(); 
        p13.setBackground(Color.GRAY);
        p13.setLayout(new GridLayout(1,2));
        p13.add(unosKB);
        
        	JPanel p132 = new JPanel();
        	p132.setBackground(Color.GRAY);
        	p132.setLayout(new GridLayout(3,1));
        	
        	JPanel pt3 = new JPanel();
        	pt3.setBackground(Color.GRAY);
        	p132.add(pt3);
        	p132.add(unosKolicineBuy);
        	JPanel pt4 = new JPanel();
        	pt4.setBackground(Color.GRAY);
        	p132.add(pt4);
        	
        p13.add(p132);
        panelEast.add(p12);
        panelEast.add(p13);
        
        JPanel p14 = new JPanel();
        p14.setLayout(new GridLayout(2,1));
        JPanel p141 = new JPanel();
        p141.setLayout(new GridBagLayout());
        p141.setBackground(Color.GRAY);
        JPanel p142 = new JPanel();
        p142.setLayout(new GridBagLayout());
        p142.setBackground(Color.GRAY);
        p14.add(p141);
        p14.add(p142);
        panelEast.add(p14);
        JLabel errorBuy = new JLabel("");
        errorBuy.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        p141.add(errorBuy);
        JButton buy = new JButton("Kupi");
        buy.setPreferredSize(new Dimension(150,40));
        p142.add(buy);
        
        // WEST
        
        
        panelWest.setLayout(new GridLayout(4,1));
        JLabel sellLabel = new JLabel("Prodaja");
        sellLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,60));
        
        JPanel p21 = new JPanel();
        p21.setBackground(Color.LIGHT_GRAY);
        p21.setLayout(new GridBagLayout());
        p21.add(sellLabel);
        panelWest.add(p21);
        
        JTextArea unosIdT = new JTextArea();
        unosIdT.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,30));
        JTextArea unosKolicineSell = new JTextArea();
        unosKolicineSell.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,30));
        JLabel unosT = new JLabel("IdTrans:");
        unosT.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,30));
        JLabel unosKS = new JLabel("Kolicina:");
        unosKS.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,30));
        
        JPanel p22 = new JPanel();
        p22.setBackground(Color.LIGHT_GRAY);
        panelWest.add(p22);
        p22.setLayout(new GridLayout(1,2));
        p22.add(unosT);
        
        	JPanel p123 = new JPanel();
        	p123.setBackground(Color.LIGHT_GRAY);
        	p123.setLayout(new GridLayout(3,1));
        	JPanel pt5 = new JPanel();
        	pt5.setBackground(Color.LIGHT_GRAY);
        	p123.add(pt5);
        	p123.add(unosIdT);
        	JPanel pt6 = new JPanel();
        	pt6.setBackground(Color.LIGHT_GRAY);
        	p123.add(pt6);
        
        p22.add(p123);
        
        JPanel p23 = new JPanel();
        p23.setBackground(Color.LIGHT_GRAY);
        p23.setLayout(new GridLayout(1,2));
        p23.add(unosKS);
        
        	JPanel p133 = new JPanel();
        	p133.setBackground(Color.LIGHT_GRAY);
        	p133.setLayout(new GridLayout(3,1));
        	
        	JPanel pt7 = new JPanel();
        	pt7.setBackground(Color.LIGHT_GRAY);
        	p133.add(pt7);
        	p133.add(unosKolicineSell);
        	JPanel pt8 = new JPanel();
        	pt8.setBackground(Color.LIGHT_GRAY);
        	p133.add(pt8);
        	
        p23.add(p133);
        panelWest.add(p22);
        panelWest.add(p23);
        
        JPanel p24 = new JPanel();
        p24.setLayout(new GridLayout(2,1));
        JPanel p241 = new JPanel();
        p241.setLayout(new GridBagLayout());
        p241.setBackground(Color.LIGHT_GRAY);
        JPanel p242 = new JPanel();
        p242.setLayout(new GridBagLayout());
        p242.setBackground(Color.LIGHT_GRAY);
        p24.add(p241);
        p24.add(p242);
        panelWest.add(p24);
        JLabel errorSell = new JLabel("");
        errorSell.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        p241.add(errorSell);
        JButton sell = new JButton("Prodaj");
        sell.setPreferredSize(new Dimension(150,40));
        p242.add(sell);
        
        
        // listeners
        
        buy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				errorSell.setText("");
				errorBuy.setText("");
				String unoskmp = unosKompanije.getText();
				String unoskol = unosKolicineBuy.getText();
				
				if(unoskmp.contentEquals("") || unoskol.contentEquals("")) {
					errorBuy.setText("Morate sve uneti podatke!");
					return;
				}
				
				Transakcija t = new Transakcija();
				int ret = t.kupi(unoskmp, Integer.parseInt(unoskol));
				
				if(ret == 0) {
					errorBuy.setText("Transakcija neuspesna!");
				}
				
			}
        	
        });
        
        sell.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				errorSell.setText("");
				errorBuy.setText("");
				String unosidt = unosIdT.getText();
				String unoskb = unosKolicineSell.getText();
				
				if(unoskb.contentEquals("") || unoskb.contentEquals("")) {
					errorSell.setText("Morate sve uneti podatke!");
					return;
				}
				
				Transakcija t = new Transakcija();
				int ret = t.prodaj(Integer.parseInt(unosidt), Integer.parseInt(unoskb));
				
				if(ret == 0) {
					errorSell.setText("Transakcija neuspesna!");
				}
				
			}
        	
        });
        frame.add(panelWest);
        frame.add(panelEast);
		frame.setVisible(true);
		frame.revalidate();
		frame.repaint();
	}

}

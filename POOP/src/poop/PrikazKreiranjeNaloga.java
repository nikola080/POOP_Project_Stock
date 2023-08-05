package poop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PrikazKreiranjeNaloga extends Prikaz {

	@Override
	public void prikazi() {
		frame.getContentPane().removeAll();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(null);
		frame.setSize(650, 600);
		frame.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		
		
		
		JPanel panelNorth = new JPanel();
		panelNorth.setPreferredSize(new Dimension(650,200));
		panel.add(panelNorth,BorderLayout.NORTH);
			
//		JPanel panelSouth = new JPanel();
//		panelSouth.setPreferredSize(new Dimension(650,200));
//		panel.add(panelSouth,BorderLayout.SOUTH);
//		JButton create = new JButton("Create Account");	
//		create.setPreferredSize(new Dimension(200,30));
//		panelSouth.add(create);
		
		JPanel panelSouth = new JPanel();
		panelSouth.setPreferredSize(new Dimension(650,200));
		panel.add(panelSouth,BorderLayout.SOUTH);
		panelSouth.setLayout(new GridLayout(2,1));
		JPanel panelSouth1 = new JPanel();
		JPanel panelSouth2 = new JPanel();
		panelSouth.add(panelSouth1);
		panelSouth.add(panelSouth2);
		JLabel neuspesno = new JLabel("");
		neuspesno.setForeground(Color.red);
		panelSouth1.add(neuspesno);
		
		
		JButton create = new JButton("Create Account");	
		create.setPreferredSize(new Dimension(150,30));
		JButton nazad = new JButton("Go back");	
		nazad.setPreferredSize(new Dimension(150,30));
		panelSouth2.add(create);
		panelSouth2.add(nazad);
		
		
		JLabel usr = new JLabel();
		usr.setText("Username: ");
		JLabel psw = new JLabel();
		psw.setText("Password: ");
		JLabel kolicina = new JLabel();
		kolicina.setText("Kolicina: ");
		
		JTextField unosUsr = new JTextField();
		unosUsr.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,25));
		JTextField unosPsw = new  JPasswordField();
		unosPsw.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,25));
		JTextField unosKolicina = new JTextField();
		unosKolicina.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,25));
		
		JPanel panelCenterr = new JPanel();
		panelCenterr.setPreferredSize(new Dimension(650,200));
		panelCenterr.setLayout(new BorderLayout());
		panel.add(panelCenterr,BorderLayout.CENTER);
		
		JPanel panellCenter = new JPanel();
		panellCenter.setPreferredSize(new Dimension(100,200));
		panelCenterr.add(panellCenter,BorderLayout.EAST);
	
		JPanel panelCenter = new JPanel();
		panelCenter.setPreferredSize(new Dimension(550,200));
		panelCenter.setLayout(new GridLayout(1,2));
		panelCenterr.add(panelCenter,BorderLayout.WEST);
		
		JPanel panelCenter1 = new JPanel();
		panelCenter1.setLayout(new GridLayout(5,4));
		
		JPanel panelCenter2 = new JPanel();
		panelCenter2.setLayout(new GridLayout(5,1));
		
		panelCenter.add(panelCenter1);
		panelCenter.add(panelCenter2);
		
		panelCenter1.add(new JPanel());
		panelCenter1.add(new JPanel());
		panelCenter1.add(new JPanel());
		panelCenter1.add(new JPanel());
		
		panelCenter1.add(new JPanel());
		panelCenter1.add(new JPanel());
		panelCenter1.add(usr);
		panelCenter1.add(new JPanel());
		
		panelCenter1.add(new JPanel());
		panelCenter1.add(new JPanel());
		panelCenter1.add(psw);
		panelCenter1.add(new JPanel());
		
		panelCenter1.add(new JPanel());
		panelCenter1.add(new JPanel());
		panelCenter1.add(kolicina);
		panelCenter1.add(new JPanel());
		
		panelCenter1.add(new JPanel());
		panelCenter1.add(new JPanel());
		panelCenter1.add(new JPanel());
		panelCenter1.add(new JPanel());
		
		panelCenter2.add(new JPanel());
		panelCenter2.add(unosUsr);
		panelCenter2.add(unosPsw);
		panelCenter2.add(unosKolicina);
		panelCenter2.add(new JPanel());
		
		
		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(unosUsr.getText().contentEquals("") || unosPsw.getText().contentEquals("") || unosKolicina.getText().contentEquals("")) { 			
					neuspesno.setText("Morate uneti sve podatke!");
					unosUsr.setText("");
					unosPsw.setText("");
					unosKolicina.setText("");
					return;
				}
				
				if(Korisnik.postojiKorisnik(unosUsr.getText())) neuspesno.setText("Korisnik vec postoji!");
				else {
					Korisnik.signUp(unosUsr.getText(), unosPsw.getText(),unosKolicina.getText());
					Stonks s  = Stonks.getInstance();
					s.setPrikaz(new PrikazLogovanje());
					s.crtaj();
				}
			}
			
		});
		
		nazad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {		
				Stonks s  = Stonks.getInstance();
				s.setPrikaz(new PrikazLogovanje());
				s.crtaj();
			}
			
		});
		
		panel.setBackground(Color.LIGHT_GRAY);
		frame.add(panel,BorderLayout.CENTER);
		frame.setVisible(true);
		frame.revalidate();
		frame.repaint();

	}

}

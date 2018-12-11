package src.gameClient;

import javax.swing.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Room extends JPanel{
	private Image image;
	public JLabel prompt = new JLabel("Enter Username:");
	public JTextField username = new JTextField("");
	public JButton join = new JButton("Join Game");
	public JButton home = new JButton("Main Menu");

	public Room(Container container, JFrame frame){
		try{
			image = Toolkit.getDefaultToolkit().getImage("src/gameClient/homepage.jpg");
		}catch(Exception e){}
		this.setLayout(null);

		prompt.setBounds(70,150,295,30);
		username.setBounds(65,180,295,50);
		join.setBounds(60,250,305,50);
		home.setBounds(60,305,305,50);

		prompt.setFont(new Font("Impact", Font.BOLD, 30));
		prompt.setBackground(Color.BLACK);
		prompt.setForeground(Color.WHITE);

		username.setFont(new Font("Impact", Font.BOLD, 24));

		join.setBackground(Color.BLACK);
		join.setFont(new Font("Impact", Font.BOLD, 32));
		join.setForeground(Color.WHITE);

		home.setFont(new Font("Impact", Font.BOLD, 32));
		home.setBackground(Color.BLACK);
		home.setForeground(Color.WHITE);

		this.join.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					System.out.println(username.getText());
					frame.dispose();
					Multiplayer multiplayer = new Multiplayer();
					multiplayer.setVisible(true);
				}catch(Exception z){}
			}
		});;

		this.home.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.dispose();
				MyFrame frame = new MyFrame();	
			}
		});
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(this.image, 0, 0, null);

		this.add(prompt);
		this.add(username);
		this.add(join);
		this.add(home);
		Toolkit.getDefaultToolkit().sync();
	}
}
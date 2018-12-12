package src.gameClient;

import javax.swing.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Instructions extends JPanel{
	private Image image;
	public JButton home = new JButton("Main Menu");

	public Instructions(Container container, JFrame frame){
		try{
			image = Toolkit.getDefaultToolkit().getImage("src/gameClient/instruction.jpg");
		}catch(Exception e){}
		this.setLayout(null);

		home.setBounds(210,0, 210, 50);

		home.setFont(new Font("Impact", Font.BOLD, 24));
		home.setBackground(Color.BLACK);
		home.setForeground(Color.WHITE);

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

		this.add(home);
		Toolkit.getDefaultToolkit().sync();
	}
}
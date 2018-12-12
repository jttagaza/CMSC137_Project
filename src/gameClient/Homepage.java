package src.gameClient;

import javax.swing.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Homepage extends JPanel{
	private Image image;
	public JButton h2p = new JButton("How To Play");
	public JButton mplayer = new JButton("Multiplayer");
	public JButton exit = new JButton("Quit");

	public Homepage(Container container, JFrame frame){
		try{
			image = Toolkit.getDefaultToolkit().getImage("src/gameClient/homepage.jpg");
		}catch(Exception e){}
		this.setLayout(null);

		h2p.setBounds(60,150,305,50);
		mplayer.setBounds(60,200,305,50);
		exit.setBounds(60,250,305,50);

		h2p.setFont(new Font("Impact", Font.BOLD, 32));
		h2p.setBackground(Color.BLACK);
		h2p.setForeground(Color.WHITE);

		mplayer.setFont(new Font("Impact", Font.BOLD, 32));
		mplayer.setBackground(Color.BLACK);
		mplayer.setForeground(Color.WHITE);

		exit.setFont(new Font("Impact", Font.BOLD, 32));
		exit.setBackground(Color.BLACK);
		exit.setForeground(Color.WHITE);

		this.h2p.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					frame.dispose();
					HowToPlay htp = new HowToPlay();
					htp.setVisible(true);
				}catch(Exception z){}
			}
		});;

		this.mplayer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					frame.dispose();
					Lobby lobby = new Lobby();
					lobby.setVisible(true);
				}catch(Exception z){}
			}
		});

		this.exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(this.image, 0, 0, null);

		this.add(h2p);
		this.add(mplayer);
		this.add(exit);
		Toolkit.getDefaultToolkit().sync();
	}
}
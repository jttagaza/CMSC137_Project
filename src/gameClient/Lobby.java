package src.gameClient;

import javax.swing.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Lobby extends JFrame{
	public static final int DIMENSION_X = 420;//1100;
	public static final int DIMENSION_Y = 460;
	private JFrame frame = this;

	public Lobby (){
		this.setPreferredSize(new Dimension(DIMENSION_X,DIMENSION_Y));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		this.setLayout(new GridLayout(1,2));

		Container container = this.getContentPane();	

		Room room = new Room(container, this.frame);

		try{
			container.removeAll();
			container.add(room);
			container.revalidate();
			container.repaint();
		}catch(Exception e){}

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
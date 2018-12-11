package src.gameClient;

import javax.swing.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Singleplayer extends JFrame{
	public static final int DIMENSION_X = 550;
	public static final int DIMENSION_Y = 560;
	private JFrame frame = this;

	public Singleplayer(){
		this.setPreferredSize(new Dimension(DIMENSION_X,DIMENSION_Y));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		this.setLayout(new GridLayout(1,2));

		Container container = this.getContentPane();	

		Game game = new Game("localhost", "user", container, frame); //Start Menu

		try{
			container.removeAll();
			container.add(game);
			container.revalidate();
			container.repaint();
		}catch(Exception e){}

		game.addKeyListener(new KeyListener(){
			public void keyTyped(KeyEvent e){}
			public void keyReleased(KeyEvent e){}
			public void keyPressed(KeyEvent e){
				int key = e.getKeyCode();

				if(key == KeyEvent.VK_UP){
					game.pacman.setDirection(3);
					game.pacman.loadImage("src/gameClient/pacmanup.jpg");
					repaint();
					game.pacman.loadImage("src/gameClient/pacman.jpg");
					repaint();
					game.pacman.loadImage("src/gameClient/pacmanup.jpg");
					repaint();
					game.pacman.loadImage("src/gameClient/pacman.jpg");
					repaint();
				}

				if(key == KeyEvent.VK_DOWN){
					game.pacman.setDirection(4);
					game.pacman.loadImage("src/gameClient/pacmandown.jpg");
					repaint();
					game.pacman.loadImage("src/gameClient/pacman.jpg");
					repaint();
					game.pacman.loadImage("src/gameClient/pacmandown.jpg");
					repaint();
					game.pacman.loadImage("src/gameClient/pacman.jpg");
					repaint();
				}

				if(key == KeyEvent.VK_LEFT){
					game.pacman.setDirection(1);
					game.pacman.loadImage("src/gameClient/pacmanleft.jpg");
					repaint();
					game.pacman.loadImage("src/gameClient/pacman.jpg");
					repaint();
					game.pacman.loadImage("src/gameClient/pacmanleft.jpg");
					repaint();
					game.pacman.loadImage("src/gameClient/pacman.jpg");
					repaint();
				}

				if(key == KeyEvent.VK_RIGHT){
					game.pacman.setDirection(2);
					game.pacman.loadImage("src/gameClient/pacmanright.jpg");
					repaint();
					game.pacman.loadImage("src/gameClient/pacman.jpg");
					repaint();
					game.pacman.loadImage("src/gameClient/pacmanright.jpg");
					repaint();
					game.pacman.loadImage("src/gameClient/pacman.jpg");
					repaint();
				}
			}
		});

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
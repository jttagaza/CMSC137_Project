package src.gameClient;

import javax.swing.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Multiplayer extends JFrame{
	public static final int DIMENSION_X = 1100;
	public static final int DIMENSION_Y = 560;
	private JFrame frame = this;
	private String sname;
	private String uname;

	public Multiplayer(String servername, String username){
		this.sname = servername;
		this.uname = username;

		this.setPreferredSize(new Dimension(DIMENSION_X,DIMENSION_Y));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		this.setLayout(new GridLayout(1,2));


		this.setTitle("Pac-Man");
		Container container = this.getContentPane();	

		Game game = new Game(this.sname, this.uname, container, frame); //Start Menu
		Chat chat = new Chat(game);

		try{
			container.removeAll();
			container.add(game);
			container.add(chat);
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

		chat.addKeyListener(new KeyListener(){
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
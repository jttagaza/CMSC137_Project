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

		Sgame sgame = new Sgame(container, frame); //Start Menu

		try{
			container.removeAll();
			container.add(sgame);
			container.revalidate();
			container.repaint();
		}catch(Exception e){}

		sgame.addKeyListener(new KeyListener(){
			public void keyTyped(KeyEvent e){}
			public void keyReleased(KeyEvent e){}
			public void keyPressed(KeyEvent e){
				int key = e.getKeyCode();

				if(key == KeyEvent.VK_UP){
					sgame.pacman.setDirection(3);
					sgame.pacman.loadImage("pacmanup.jpg");
					repaint();
					sgame.pacman.loadImage("pacman.jpg");
					repaint();
					sgame.pacman.loadImage("pacmanup.jpg");
					repaint();
					sgame.pacman.loadImage("pacman.jpg");
					repaint();
				}

				if(key == KeyEvent.VK_DOWN){
					sgame.pacman.setDirection(4);
					sgame.pacman.loadImage("pacmandown.jpg");
					repaint();
					sgame.pacman.loadImage("pacman.jpg");
					repaint();
					sgame.pacman.loadImage("pacmandown.jpg");
					repaint();
					sgame.pacman.loadImage("pacman.jpg");
					repaint();
				}

				if(key == KeyEvent.VK_LEFT){
					sgame.pacman.setDirection(1);
					sgame.pacman.loadImage("pacmanleft.jpg");
					repaint();
					sgame.pacman.loadImage("pacman.jpg");
					repaint();
					sgame.pacman.loadImage("pacmanleft.jpg");
					repaint();
					sgame.pacman.loadImage("pacman.jpg");
					repaint();
				}

				if(key == KeyEvent.VK_RIGHT){
					sgame.pacman.setDirection(2);
					sgame.pacman.loadImage("pacmanright.jpg");
					repaint();
					sgame.pacman.loadImage("pacman.jpg");
					repaint();
					sgame.pacman.loadImage("pacmanright.jpg");
					repaint();
					sgame.pacman.loadImage("pacman.jpg");
					repaint();
				}
			}
		});

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
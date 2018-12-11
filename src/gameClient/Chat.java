package src.gameClient;

import javax.swing.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Chat extends JPanel implements KeyListener{
	private JButton send;
	private JTextField messageBox;
	private JTextArea messageDisplay;
	private Game game;

	public Chat(Game game){
		this.game = game;
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 0));

		this.messageDisplay = new JTextArea("Sample Text Here");
		this.messageDisplay.setPreferredSize(new Dimension(500, 475));
		this.messageDisplay.setEditable(false);
		this.add(this.messageDisplay);
		this.messageBox = new JTextField("Write Text Here");
		this.messageBox.setPreferredSize(new Dimension(325, 75));
		this.add(this.messageBox);

		this.send = new JButton("Send");
		this.send.setPreferredSize(new Dimension(125, 70));
		this.add(this.send);
	}

	public void keyTyped(KeyEvent e){
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
}
package src.gameClient;

import javax.swing.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Pacman extends Sprite implements Runnable{
	private String filename;
	private int direction;
	private Game g;
	private int id;

	public Pacman(int x, int y, String sprite, Game g){
		super(x,y,sprite);
		this.filename = sprite;
		this.direction = 0;
		this.g = g;
		this.id = 3;

		this.addKeyListener(new KeyListener(){
			public void keyTyped(KeyEvent e){}
			public void keyReleased(KeyEvent e){}
			public void keyPressed(KeyEvent e){
				int key = e.getKeyCode();
				System.out.println(key);

				if(e.getKeyCode() == KeyEvent.VK_UP){
					System.out.println("UPS");
					direction = 3;
					loadImage("src/gameClient/pacmanup.jpg");
				}

				if(e.getKeyCode() == KeyEvent.VK_DOWN){
					System.out.println("DOWNS");
					direction = 4;
					loadImage("src/gameClient/pacmandown.jpg");
				}

				if(e.getKeyCode() == KeyEvent.VK_LEFT){
					System.out.println("LEFTS");
					direction = 1;
					loadImage("src/gameClient/pacmanleft.jpg");
				}

				if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					System.out.println("RIGHTS");
					direction = 2;
					loadImage("src/gameClient/pacmanright.jpg");
				}
			}
		});
	}

	public void move(){
		int[][] temp = this.g.getMap();
		int tempid = this.id;

		switch(this.direction){
			case 1:
				if(xPos == 0 && yPos == 10){
					this.g.updateMap(tempid, xPos, yPos, 26, yPos);
					this.xPos = 26;
				}else if(xPos != 1 && temp[xPos-1][yPos] == 1 || temp[xPos-1][yPos] == 2){
					this.g.updateMap(tempid, xPos, yPos, xPos-1, yPos);
					this.xPos -= 1;
				}

				loadImage("src/gameClient/pacman.jpg");
				this.repaint();
				loadImage("src/gameClient/pacman.jpg");
				this.repaint();
				loadImage("src/gameClient/pacman.jpg");
				this.repaint();
				loadImage("src/gameClient/pacman.jpg");
				this.repaint();
				loadImage("src/gameClient/pacman.jpg");
				this.repaint();
				loadImage("src/gameClient/pacmanleft.jpg");
				this.repaint();
				break;
			case 2:
				if(xPos == 26 && yPos == 10){
					this.g.updateMap(tempid, xPos, yPos, 0, yPos);
					this.xPos = 0;
				}else if(xPos != 25 && temp[xPos+1][yPos] == 1 || temp[xPos+1][yPos] == 2){
					this.g.updateMap(tempid, xPos, yPos, xPos+1, yPos);
					this.xPos += 1;
				}

				loadImage("src/gameClient/pacman.jpg");
				this.repaint();
				loadImage("src/gameClient/pacmanright.jpg");
				this.repaint();
				break;
			case 3:
				if((yPos != 1 && xPos != 10) && temp[xPos][yPos-1] == 1 || temp[xPos][yPos-1] == 2){
					this.g.updateMap(tempid, xPos, yPos, xPos, yPos-1);
					this.yPos -= 1;
				}

				loadImage("src/gameClient/pacman.jpg");
				loadImage("src/gameClient/pacmanup.jpg");
				this.repaint();
				break;
			case 4:
				if((yPos != 21 && xPos != 10) && temp[xPos][yPos+1] == 1 || temp[xPos][yPos+1] == 2){
					this.g.updateMap(tempid, xPos, yPos, xPos, yPos+1);
					this.yPos += 1;
				}

				loadImage("src/gameClient/pacman.jpg");
				loadImage("src/gameClient/pacmandown.jpg");
				this.repaint();
				break;
			default:
				break;
		}
	}

	public void setDirection(int direction){
		this.direction = direction;
	}

	@Override
	public void run(){
		while(!this.g.checkVictory() && !this.g.checkDefeat()){
			this.move();
			this.repaint();

			try{
				Thread.sleep(200);
			}catch(Exception e){}
		}
	}
}
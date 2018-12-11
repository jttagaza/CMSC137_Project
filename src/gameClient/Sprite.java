package src.gameClient;

import javax.swing.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Sprite extends JPanel{
	private Image img;
	protected int xPos;
	protected int yPos;

	public Sprite(int x, int y, String filename){
		this.xPos = x;
		this.yPos = y;
		this.loadImage(filename);

		this.setSize(new Dimension(19,19));
	}

	protected void loadImage(String filename){
		try{
			img = ImageIO.read(new File(filename));
		} catch(Exception e){}	
	}

	public Image getImage(){
		return this.img;
	}

	public int getXPos(){
		return this.xPos;
	}

	public int getYPos(){
		return this.yPos;
	}

	public void incXPos(int distance){
		this.xPos+=distance;
	}

	public void decXPos(int distance){
		this.xPos-=distance;
	}

	public void decXPos(float distance){
		this.xPos-=distance;
	}

	public void setXPos(int distance){
		this.xPos=distance;
	}

	public void incYPos(int distance){
		this.yPos+=distance;
	}

	public void decYPos(int distance){
		this.yPos-=distance;
	}

	public void setYPos(int distance){
		this.yPos=distance;
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(this.getImage(), this.getXPos(), this.getYPos(), null);
		Toolkit.getDefaultToolkit().sync();
	}
}
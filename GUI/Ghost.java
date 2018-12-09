import javax.swing.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Ghost extends Sprite implements Runnable{
	private String filename;
	private String name;
	private int direction;
	private Sgame sg;
	private int id;

	public Ghost(int x, int y, String sprite, String name, Sgame sg){
		super(x,y,sprite);
		this.filename = sprite;
		this.name = name;
		this.direction = 0;
		this.sg = sg;

		if(this.name.equals("Red")) this.id = 10;
		if(this.name.equals("Yellow")) this.id = 20;
		if(this.name.equals("Blue")) this.id = 30;
		if(this.name.equals("Pink")) this.id = 40;
	}

	public void move(){
		Random r = new Random();
		int n = r.nextInt(4) + 1;

		int[][] temp = this.sg.getMap();
		switch(n){
			case 1:
				if(xPos != 0 && (temp[xPos-1][yPos] == 1 || temp[xPos-1][yPos] == 2) && xPos != 1) this.direction = n;
				break;
			case 2:
				if(xPos != 26 && (temp[xPos+1][yPos] == 1 || temp[xPos+1][yPos] == 2) && xPos != 26) this.direction = n;
				break;
			case 3:
				if(yPos != 0 && (temp[xPos][yPos-1] == 1 || temp[xPos][yPos-1] == 2) && yPos != 1) this.direction = n;
				break;
			case 4:
				if(yPos != 22 && (temp[xPos][yPos+1] == 1 || temp[xPos][yPos+1] == 2) && yPos != 22) this.direction = n;
				break;
			default:
				break;
		}

		int tempid = this.id;
		switch(this.direction){
			case 1: //LEFTWARD
				if(xPos == 0 && yPos == 10){
					if(temp[26][yPos] == 3) this.sg.setDefeat(true);
					this.sg.updateMap(tempid, xPos, yPos, 26, yPos);
					this.xPos = 26;
				}else if(xPos != 1 && temp[xPos-1][yPos] == 1 || temp[xPos-1][yPos] == 2 || temp[xPos-1][yPos] == 3){
					if(this.name.equals("Red")){
						if(temp[xPos-1][yPos] == 1) this.id = 11;
						if(temp[xPos-1][yPos] == 2) this.id = 10;
					}

					else if(this.name.equals("Yellow")){
						if(temp[xPos-1][yPos] == 1) this.id = 21;
						if(temp[xPos-1][yPos] == 2) this.id = 20;
					}

					else if(this.name.equals("Blue")){
						if(temp[xPos-1][yPos] == 1) this.id = 31;
						if(temp[xPos-1][yPos] == 2) this.id = 30;
					}

					else if(this.name.equals("Pink")){
						if(temp[xPos-1][yPos] == 1) this.id = 41;
						if(temp[xPos-1][yPos] == 2) this.id = 40;
					}

					if(temp[xPos-1][yPos] == 3) this.sg.setDefeat(true);
					this.sg.updateMap(tempid, xPos, yPos, xPos-1, yPos);
					this.xPos -= 1;
				}

				loadImage(this.filename);
				this.repaint();
				break;

			case 2: //RIGHTWARD
				if(xPos == 26 && yPos == 10){
					if(temp[0][yPos] == 3) this.sg.setDefeat(true);
					this.sg.updateMap(tempid, xPos, yPos, 0, yPos);
					this.xPos = 0;
				}else if(xPos != 25 && temp[xPos+1][yPos] == 1 || temp[xPos+1][yPos] == 2 || temp[xPos+1][yPos] == 3){
					if(this.name.equals("Red")){
						if(temp[xPos+1][yPos] == 1) this.id = 11;
						if(temp[xPos+1][yPos] == 2) this.id = 10;
					}

					else if(this.name.equals("Yellow")){
						if(temp[xPos+1][yPos] == 1) this.id = 21;
						if(temp[xPos+1][yPos] == 2) this.id = 20;
					}

					else if(this.name.equals("Blue")){
						if(temp[xPos+1][yPos] == 1) this.id = 31;
						if(temp[xPos+1][yPos] == 2) this.id = 30;
					}

					else if(this.name.equals("Pink")){
						if(temp[xPos+1][yPos] == 1) this.id = 41;
						if(temp[xPos+1][yPos] == 2) this.id = 40;
					}

					if(temp[xPos+1][yPos] == 3) this.sg.setDefeat(true);
					this.sg.updateMap(tempid, xPos, yPos, xPos+1, yPos);
					this.xPos += 1;
				}

				loadImage(this.filename);
				this.repaint();
				break;

			case 3:
				if((yPos != 1 && xPos != 10) && temp[xPos][yPos-1] == 1 || temp[xPos][yPos-1] == 2 || temp[xPos][yPos-1] == 3){
					if(this.name.equals("Red")){
						if(temp[xPos][yPos-1] == 1) this.id = 11;
						if(temp[xPos][yPos-1] == 2) this.id = 10;
						loadImage("ghost11.jpg");
					}

					else if(this.name.equals("Yellow")){
						if(temp[xPos][yPos-1] == 1) this.id = 21;
						if(temp[xPos][yPos-1] == 2) this.id = 20;
						loadImage("ghost21.jpg");
					}

					else if(this.name.equals("Blue")){
						if(temp[xPos][yPos-1] == 1) this.id = 31;
						if(temp[xPos][yPos-1] == 2) this.id = 30;
						loadImage("ghost31.jpg");
					}

					else if(this.name.equals("Pink")){
						if(temp[xPos][yPos-1] == 1) this.id = 41;
						if(temp[xPos][yPos-1] == 2) this.id = 40;
						loadImage("ghost41.jpg");
					}

					if(temp[xPos][yPos-1] == 3) this.sg.setDefeat(true);
					this.sg.updateMap(tempid, xPos, yPos, xPos, yPos-1);
					this.yPos -= 1;
				}

				this.repaint();
				break;

			case 4:
				if((yPos != 21 && xPos != 10) && temp[xPos][yPos+1] == 1 || temp[xPos][yPos+1] == 2 || temp[xPos][yPos+1] == 3){
					if(this.name.equals("Red")){
						if(temp[xPos][yPos+1] == 1) this.id = 11;
						if(temp[xPos][yPos+1] == 2) this.id = 10;
						loadImage("ghost10.jpg");
					}

					else if(this.name.equals("Yellow")){
						if(temp[xPos][yPos+1] == 1) this.id = 21;
						if(temp[xPos][yPos+1] == 2) this.id = 20;
						loadImage("ghost20.jpg");
					}

					else if(this.name.equals("Blue")){
						if(temp[xPos][yPos+1] == 1) this.id = 31;
						if(temp[xPos][yPos+1] == 2) this.id = 30;
						loadImage("ghost30.jpg");
					}

					else if(this.name.equals("Pink")){
						if(temp[xPos][yPos+1] == 1) this.id = 41;
						if(temp[xPos][yPos+1] == 2) this.id = 40;
						loadImage("ghost40.jpg");
					}

					if(temp[xPos][yPos+1] == 3) this.sg.setDefeat(true);
					this.sg.updateMap(tempid, xPos, yPos, xPos, yPos+1);
					this.yPos += 1;
				}

				this.repaint();
				break;

			default:
				break;
		}

		this.repaint();
	}

	public String getFName(){
		return this.filename;
	}

	@Override
	public void run(){
		while(!this.sg.checkVictory() && !this.sg.checkDefeat()){
			this.move();
			this.repaint();

			try{
				Thread.sleep(200);
			}catch(Exception e){}
		}
	}
}
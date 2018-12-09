import javax.swing.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Sgame extends JPanel{
	public JFrame jframe;
	private int[][] map = new int[27][23];
	public JButton back = new JButton("Main Menu");

	public Pacman pacman = new Pacman(13,17,"pacman.jpg", this);
	public Ghost red = new Ghost(13, 8, "ghost10.jpg", "Red", this);
	public Ghost yellow = new Ghost(11, 10, "ghost20.jpg", "Yellow", this);
	public Ghost blue = new Ghost(13, 10, "ghost31.jpg", "Blue", this);
	public Ghost pink = new Ghost(15, 10, "ghost41.jpg", "Pink", this);

	public Thread pacmanThread = new Thread(pacman);
	public Thread redThread = new Thread(red);
	public Thread yellowThread = new Thread(yellow);
	public Thread blueThread = new Thread(blue);
	public Thread pinkThread = new Thread(pink);

	private int score = 0;
	private boolean state = false;

	public Sgame(Container container, JFrame frame){
		this.jframe = frame;
		this.setLayout(null);

		for(int i = 0; i < 27; i++){ // MAP CREATION
			for(int j = 0; j < 23; j++){
				//OUTER BLOCKS
				if(i == 0 || j == 0 || i == 26 || j == 22) map[i][j] = 0;
				//PELLETS (Points)
				else map[i][j] = 1;

				//INSIDE BLOCKS
				if(j == 1 && i == 13) map[i][j] = 0;
				if(j == 2 && (i != 1 && i != 6 && i != 12 && i != 14 && i != 20 && i != 25)) map[i][j] = 0;
				if(j == 4 && (i != 1 && i != 6 && i != 8 && i != 18 && i != 20 && i != 25)) map[i][j] = 0;
				if(j == 5 && (i == 7 || i == 13 || i == 19 || i == 26)) map[i][j] = 0;
				if(j == 6 && (i != 6 && i != 12 && i != 14 && i != 20)) map[i][j] = 0;
				if(j == 7 && (i < 6 || i == 7 || i == 19 || i > 20)) map[i][j] = 0;
				if(j == 8 && (i < 6 || i == 7 || i == 9 || i == 10 || i == 11 || i == 15 || i == 16 || i == 17 || i == 19 || i > 20)) map[i][j] = 0;
				if(j == 9 && (i < 6 || i == 9 || i == 17 || i > 20)) map[i][j] = 0;
				if(j == 10 && (i == 9 || i == 17)) map[i][j] = 0;
				if(j == 11 && (i < 6 || i == 9 || i == 17 || i > 20)) map[i][j] = 0;
				if(j == 12 && (i < 6 || i == 7 || (i > 8 && i < 18) || i == 19 || i > 20)) map[i][j] = 0;
				if(j == 13 && (i < 6 || i == 7 || i == 19 || i > 20)) map[i][j] = 0;
				if(j == 14 && (i < 6 || i == 7 || (i > 8 && i < 18) || i == 19 || i > 20)) map[i][j] = 0;
				if(j == 15 && i == 13) map[i][j] = 0;
				if(j == 16 && (i != 1 && i != 6 && i != 12 && i != 14 && i != 20 && i != 25)) map[i][j] = 0;
				if(j == 17 && (i == 5 || i == 21)) map[i][j] = 0;
				if(j == 18 && (i < 4 || i == 5 || i == 7 || (i > 8 && i < 18) || i == 19 || i == 21 || i > 22)) map[i][j] = 0;
				if(j == 19 && (i == 7 || i == 13 || i == 19)) map[i][j] = 0;
				if(j == 20 && (i != 1 && i != 12 && i != 14 && i != 25)) map[i][j] = 0;

				//EMPTY FIELD
				if(j == 8 && (i > 11 && i < 15)) map[i][j] = 2;
				if(j == 9 && (i > 9 && i < 17)) map[i][j] = 2;
				if(j == 10 && (i == 0 || (i > 9 && i < 17) || i == 26)) map[i][j] = 2;
				if(j == 11 && (i > 9 && i < 17)) map[i][j] = 2;

				//PACMAN
				if(pacman.getXPos() == i && pacman.getYPos() == j) map[i][j] = 3;

				//GHOSTS
				if(red.getXPos() == i && red.getYPos() == j) map[i][j] = 10;
				if(yellow.getXPos() == i && yellow.getYPos() == j) map[i][j] = 20;
				if(blue.getXPos() == i && blue.getYPos() == j) map[i][j] = 30;
				if(pink.getXPos() == i && pink.getYPos() == j) map[i][j] = 40;
			}
		}

		this.pacmanThread.start();
		this.redThread.start();
		this.yellowThread.start();
		this.blueThread.start();
		this.pinkThread.start();

		back.setBounds(375,10,160,30);
		back.setFont(new Font("Impact", Font.BOLD, 20));
		back.setBackground(Color.BLACK);
		back.setForeground(Color.WHITE);

		this.back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					frame.dispose();
					MyFrame frame = new MyFrame();
				}catch(Exception z){}
			}
		});;
	}

	public void addNotify(){
		super.addNotify();
		requestFocus();
	}


	public int[][] getMap(){
		return this.map;
	}

	public boolean checkVictory(){
		for(int i = 0; i < 27; i++){
			for(int j = 0; j < 23; j++){
				if(map[i][j] == 1) return false;
			}
		}

		// STOP THREADS HERE
		// this.pacmanThread.interrupt();
		// this.redThread.join();
		// this.yellowThread.join();
		// this.blueThread.join();
		// this.pinkThread.join();

		// INFORM USER OF VICTORY
		// this.jframe.dispose();
		// MyFrame frame = new MyFrame();
		
		return true;
	}

	public void setDefeat(boolean defeat){
		this.state = defeat;
	}

	public boolean checkDefeat(){
		return this.state;
	}

	public void updateMap(int object, int x1, int y1, int x2, int y2){
		//PACMAN
		if(object == 3){
			if(this.map[x2][y2] == 1) this.score += 1;
			this.map[x2][y2] = object;
			this.map[x1][y1] = 2;
		//GHOST
		}else if(object == 10 || object == 20 || object == 30 || object == 40){
			if(this.map[x2][y2] == 1) this.map[x2][y2] = object + 1; //empty to pellet
			else this.map[x2][y2] = object; //empty to empty
			this.map[x1][y1] = 2;
		}else if(object == 11 || object == 21 || object == 31 || object == 41){
			if(this.map[x2][y2] == 1) this.map[x2][y2] = object; //empty to pellet
			else this.map[x2][y2] = object - 1; //empty to empty
			this.map[x1][y1] = 1;
		}
		
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.BLACK);
		g2d.fillRect(0,0,600,580);

		for(int i = 0; i < 27; i++){
			for(int j = 0; j < 23; j++){
				int x = (20 * i) + 5;
				int y = (20 * j) + 65;

				if(map[i][j] == 0){
					g2d.setColor(Color.BLUE);
					g2d.fillRect(x,y,20,20);
					g2d.setColor(Color.BLACK);
					g2d.drawRect(x,y,20,20);
				}

				if(map[i][j] == 1){
					g2d.setColor(Color.BLACK);
					g2d.fillRect(x,y,20,20);

					g2d.setColor(Color.WHITE);
					x += 8;
					y += 8;
					g2d.fillRect(x,y,4,4);
				}

				if(map[i][j] == 2){
					g2d.setColor(Color.BLACK);
					g2d.fillRect(x,y,20,20);
				}

				if(map[i][j] == 3) g2d.drawImage(pacman.getImage(), x, y, null);

				if(map[i][j] == 10 || map[i][j] == 11) g2d.drawImage(red.getImage(), x, y, null);
				if(map[i][j] == 20 || map[i][j] == 21) g2d.drawImage(yellow.getImage(), x, y, null);
				if(map[i][j] == 30 || map[i][j] == 31) g2d.drawImage(blue.getImage(), x, y, null);
				if(map[i][j] == 40 || map[i][j] == 41) g2d.drawImage(pink.getImage(), x, y, null);
			}
		}

		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("", Font.BOLD, 20));
		g2d.drawString("SCORE: " + this.score,10,545);

		this.add(back);
		this.add(pacman);
		Toolkit.getDefaultToolkit().sync();
	}
}
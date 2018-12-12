package src.gameClient;

import src.gameServer.Constants;
import javax.swing.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.imageio.ImageIO;

public class Game extends JPanel implements Runnable, Constants{
	public JFrame jframe;
	private int[][] map = new int[27][23];
	public JButton back = new JButton("Main Menu");

	public Pacman pacman = new Pacman(13, 17,"src/gameClient/pacman.jpg", this);
	public Ghost red = new Ghost(13, 8, "src/gameClient/ghost10.jpg", "Red", this);
	public Ghost yellow = new Ghost(11, 10, "src/gameClient/ghost20.jpg", "Yellow", this);
	public Ghost blue = new Ghost(13, 10, "src/gameClient/ghost31.jpg", "Blue", this);
	public Ghost pink = new Ghost(15, 10, "src/gameClient/ghost41.jpg", "Pink", this);
    private int x = 0;
    private int y = 0;

	public Thread pacmanThread = new Thread(pacman);
	public Thread redThread = new Thread(red);
	public Thread yellowThread = new Thread(yellow);
	public Thread blueThread = new Thread(blue);
	public Thread pinkThread = new Thread(pink);

	private int score = 0;
	private boolean state = false;

    private String sname;
    private String uname;
    private Thread t = new Thread(this);
    private boolean connected = false;
    private int pnum;
    private DatagramSocket socket;

	public Game(String servername, String username, Container container, JFrame frame){
		this.setPreferredSize(new Dimension(1100,560));
        this.sname = servername;
        this.uname = username;
        try {
            this.socket = new DatagramSocket();
            this.socket.setSoTimeout(100);
        } catch(Exception e){
            System.out.println(e);
        }

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
		
        t.start();
	}

    public void sendData(String msg){
        try{
            byte[] buf = msg.getBytes();
            InetAddress address = InetAddress.getByName(InetAddress.getLocalHost().getHostAddress());//InetAddress.getLocalHost().toString());
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, PORT);
            socket.send(packet);
        }catch(Exception e){}
    }

	public void addNotify(){
		super.addNotify();
		requestFocus();
	}


	public int[][] getMap(){
		return this.map;
	}

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

	public int getScore(){
		return this.score;
	}

    public String getUName(){
        return this.uname;  
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
		g2d.fillRect(0,0,550,560);

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

    public void run(){
		byte[] buf = null;
		DatagramPacket packet = null;
		String serverData = null;

        while(!this.checkVictory() && !this.checkDefeat()){
            try{
                Thread.sleep(1);
            }catch(Exception ioe){}

            buf = new byte[256];
            packet = new DatagramPacket(buf, buf.length);
            
            try{
                socket.receive(packet);
            }catch(Exception ioe){}

            serverData = new String(buf);
            serverData = serverData.trim();

            if(!connected && serverData.startsWith("CONNECTED")){
                connected = true;
            }else if(!connected){
                sendData("CONNECT " + this.uname);
            }else if(connected){
                System.out.println(serverData);

                if(serverData.startsWith("START")) {
                    this.pacmanThread.start();
                    this.redThread.start();
                    this.yellowThread.start();
                    this.blueThread.start();
                    this.pinkThread.start();
                }
                if(serverData.startsWith("PLAYER")){
                    String[] playersInfo = serverData.split(":");
                    for(int i = 0; i < playersInfo.length; i++){
                        String[] playerInfo = playersInfo[i].split(" ");
                        String pname = playerInfo[1];
                        int x = Integer.parseInt(playerInfo[2]);
                        int y = Integer.parseInt(playerInfo[3]);
						int score = Integer.parseInt(playerInfo[4]);
                    }

                    this.repaint();
                }
            }
        }

		boolean isWait = true;
		while(connected){
			if(isWait){
				sendData("WAITING " + this.uname);
				isWait = false;
			}

			if(serverData.startsWith("END")){
				connected = false;
				String[] playerInfo = serverData.split(" ");
				String winner = playerInfo[1];
				int max = Integer.parseInt(playerInfo[2]);

				JOptionPane.showMessageDialog(this.jframe,"The winner is " + winner + " with the score of " + max + " points!");
			}

			try{
                Thread.sleep(1);
            }catch(Exception ioe){}

            buf = new byte[256];
            packet = new DatagramPacket(buf, buf.length);
            
            try{
                socket.receive(packet);
            }catch(Exception ioe){}

            serverData = new String(buf);
            serverData = serverData.trim();
		}
    }


}
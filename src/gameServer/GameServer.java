package src.gameServer;

import src.gameChat.User;
import src.gameChat.Client;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GameServer implements Runnable, Constants {
    private DatagramSocket serverSocket;
    private GameState game;
    private Client client;
    private int gameStatus;
    private int playerCount;
    private int endCount;
    private Thread t = new Thread(this);
    
    public GameServer() {
        try {
            this.serverSocket = new DatagramSocket(PORT);
		    this.serverSocket.setSoTimeout(1000);
	    } 
        catch (IOException e) {
            System.err.println("Could not listen on port " + PORT + " .");
            System.exit(-1);
	    } 
        catch (Exception e) {}

        game = new GameState();

        this.gameStatus = WAIT;
        this.playerCount = 0;
        this.endCount = 0;

        t.start();
    }

    public void sendData(User user, byte[] buf) {		
		DatagramPacket packet = new DatagramPacket(buf, buf.length, user.getAddress(), user.getPort());	
        try {
			this.serverSocket.send(packet);
		}
        catch (IOException err) {
			err.printStackTrace();
		}
	}

    public void broadcast(String msg){
		for(Iterator ite = game.getPlayers().keySet().iterator();ite.hasNext();){
			String name = (String)ite.next();

            User user = (User) game.getPlayers().get(name);			
			sendData(user, msg.getBytes());	
		}
	}

    public void run(){
        while(true) {
            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            
            try{
                serverSocket.receive(packet);
            }catch(Exception ioe){}

            String playerData = new String(buf);
            playerData = playerData.trim();

            switch(gameStatus) {
                case WAIT:
                    if(playerData.startsWith("CONNECT")){
                        String tokens[] = playerData.split(" ");
                        User user = new User(tokens[1], packet.getAddress(), packet.getPort());
                        
                        switch(playerCount){
                            case 0:
                                user.setX(1);
                                user.setY(1);
                                break;
                            case 1:
                                user.setX(26);
                                user.setY(22);
                                break;
                            case 2:
                                user.setX(1);
                                user.setY(22);
                                break;
                            case 3:
                                user.setX(26);
                                user.setY(1);
                                break;
                        }
                        game.update(tokens[1], user);
                        broadcast("CONNECTED " + user);
                        this.playerCount += 1;
                        this.endCount += 1;

                        if(this.playerCount >= 3) this.gameStatus = START;
                    }
                    break;
                case START:
                    System.out.println("GAME START");
                    broadcast("START");
                    this.gameStatus = INGAME;
                    break;
                
                case INGAME:
                    if (playerData.startsWith("PLAYER")){
                        String[] playerInfo = playerData.split(" ");					  
                        String name = playerInfo[1];
                        int x = Integer.parseInt(playerInfo[2].trim());
                        int y = Integer.parseInt(playerInfo[3].trim());
                        int score = Integer.parseInt(playerInfo[4].trim());
                        User user = (User) game.getPlayers().get(name);					  
                        user.setX(x);
                        user.setY(y);
                        user.setScore(score);
                        game.update(name, user);
                        broadcast(game.toString());
					}

                    if (playerData.startsWith("WAITING")){
                        this.endCount -= 1;
                        System.out.println(this.endCount);
                        if(this.endCount == 0) this.gameStatus = END;
                    }
                    break;

                case END:
                    User top = game.getMax();
                    broadcast("END " + top.getName() + " " + top.getScore());
                    break;
            }
        }
    }

    public static void main(String args[]){		
		new GameServer();
	}
}
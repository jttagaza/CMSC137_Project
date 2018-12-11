package src.gameServer;

import src.gameChat.User;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GameServer implements Runnable, Constants {
    private DatagramSocket serverSocket;
    private GameState game;
    private int gameStatus;
    private int playerCount;
    
    public GameServer() {
        try {
            this.serverSocket = new DatagramSocket(PORT);
		    this.serverSocket.setSoTimeout(0);
	    } 
        catch (IOException e) {
            System.err.println("Could not listen on port " + PORT + " .");
            System.exit(-1);
	    } 
        catch (Exception e) {}

        this.gameStatus = WAIT;
        this.playerCount = 0;
    }

    public void sendData(User user, byte[] buf) {		
		DatagramPacket packet = new DatagramPacket(buf, buf.length);	
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
                this.serverSocket.receive(packet);
            }catch(Exception ioe){}

            String playerData = new String(buf);
            playerData = playerData.trim();

            switch(gameStatus) {
                case WAIT:
                    if(playerData.startsWith("CONNECT")){
                        String tokens[] = playerData.split(" ");
                        User user = new User(tokens[1]);
                        game.update(tokens[1].trim(), user);
                        broadcast("CONNECTED " + tokens[1]);
                        this.playerCount++;
                        if(playerCount == 4)
                            this.gameStatus = INGAME;
                    }
                    break;
                
                case INGAME:
                    if (playerData.startsWith("PLAYER")){
                        String[] playerInfo = playerData.split(" ");					  
                        String name = playerInfo[1];
                        int x = Integer.parseInt(playerInfo[2].trim());
                        int y = Integer.parseInt(playerInfo[3].trim());
                        User user = (User) game.getPlayers().get(name);					  
                        user.setX(x);
                        user.setY(y);
                        game.update(name, user);
                        broadcast(game.toString());
					}
                    break;
                
                case END:
                    break;
            }
        }
    }

    public static void main(String args[]){		
		GameServer gameServer = new GameServer();
        Thread t = new Thread(gameServer);
        t.start();
	}
}
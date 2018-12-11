package src;

import java.io.IOException;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class GameServer implements Runnable, Constants {
    private DatagramSocket serverSocket;
    private int gameStatus;
    
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
    }

    public void sendData(Player player, byte[] buf) {		
		DatagramPacket packet = new DatagramPacket(buf, buf.length, player.getAddress(), player.getPort());	
        try {
			this.serverSocket.send(packet);
		}
        catch (IOException err) {
			err.printStackTrace();
		}
	}

    // public void broadcast(String msg){
	// 	for(Iterator ite=game.getPlayers().keySet().iterator();ite.hasNext();){
	// 		String name=(String)ite.next();
    //         Player player = (Player)game.getPlayers().get(name);			
	// 		send(player,msg.toByteArray());	
	// 	}
	// }

    public void run(){
        while(true) {
            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            Player player = new String(buf);
            player = player.trim();

            switch(gameStatus) {
                case WAIT:
                    break;
                case START:
                    break;
                case INGAME:
                    break;
                case END:
            }
        }
    }
}
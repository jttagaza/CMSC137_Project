package src;

import packet.PlayerProtos.*;
import packet.TcpPacketProtos.TcpPacket.*;
import packet.TcpPacketProtos.TcpPacket;

import java.util.Scanner;

class ChatSend implements Runnable {
    private Packet packet;
    private User user;
    private String lobbyId;
    private Boolean connected;

    public ChatSend(Packet packet, User user, String lobbyId) {
        this.packet = packet;
        this.user = user;
        this.lobbyId = lobbyId;
        this.connected = true;
    }

    void send(){
        Scanner str = new Scanner(System.in);
        String message = str.nextLine();

        ChatPacket createMessage = packet.createMessage(message, this.user.getPlayer(), this.lobbyId);
        packet.send(createMessage.toByteArray());
        
        if (message.equals("quit")) {
            DisconnectPacket removeConnection = packet.removeConnection(this.user.getPlayer());
            packet.send(removeConnection.toByteArray());
            this.connected = false;
        }
    }

    @Override
    public void run(){
        while(this.connected){
            this.send();
        }
    }
}
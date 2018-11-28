package src;

import packet.PlayerProtos.*;
import packet.TcpPacketProtos.TcpPacket.*;
import packet.TcpPacketProtos.TcpPacket;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

import java.io.IOException;
import java.net.UnknownHostException;

class ChatSend implements Runnable {
    private Socket socket;
    private User user;
    private String lobbyId;
    private Boolean connected;

    public ChatSend(Socket socket, User user, String lobbyId) {
        this.socket = socket;
        this.user = user;
        this.lobbyId = lobbyId;
        this.connected = true;
    }

    void joinChat(String message, Player player, String lobbyId){
        ChatPacket.Builder chatBuilder = ChatPacket.newBuilder();
        chatBuilder.setType(PacketType.CHAT);
        chatBuilder.setMessage(message);
        if(player != null)  chatBuilder.setPlayer(player);
        if(lobbyId != null) chatBuilder.setLobbyId(lobbyId);
        ChatPacket connect = chatBuilder.build();

        try {
            OutputStream output = this.socket.getOutputStream();
            DataOutputStream sender = new DataOutputStream(output);
            sender.write(connect.toByteArray());
        }
        catch(IOException err) {
            err.printStackTrace();
        }
    }

    void leaveChat(Player player){
        DisconnectPacket.Builder disconnectBuilder = DisconnectPacket.newBuilder();
        disconnectBuilder.setType(PacketType.DISCONNECT);
        if(player != null) disconnectBuilder.setPlayer(player);
        DisconnectPacket disconnect = disconnectBuilder.build();

        try {
            OutputStream output = this.socket.getOutputStream();
            DataOutputStream sender = new DataOutputStream(output);
            sender.write(disconnect.toByteArray());
        }
        catch(IOException err) {
            err.printStackTrace();
        }
    }

    void send(){
        Scanner str = new Scanner(System.in);
        String message = str.nextLine();

        this.joinChat(message, this.user.getPlayer(), this.lobbyId);
        
        if (message.equals("quit")) {
            this.leaveChat(this.user.getPlayer());
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
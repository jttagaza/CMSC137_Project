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

class Chat extends Thread {
    private Socket socket;
    private Connection conn;
    private User user;
    private String lobbyId;

    ChatPacket createChat(String message, Player player, String lobbyId){
        ChatPacket.Builder chatBuilder = ChatPacket.newBuilder();
        chatBuilder.setType(PacketType.CHAT);
        chatBuilder.setMessage(message);
        if(player != null)  chatBuilder.setPlayer(player);
        if(lobbyId != null) chatBuilder.setLobbyId(lobbyId);
        ChatPacket sentMsg = chatBuilder.build();

        return sentMsg;
    }

    void send(){
        try {
            Scanner str = new Scanner(System.in);
            String message = str.nextLine();
            ChatPacket sentMsg = this.createChat(message, this.user.getPlayer(), this.lobbyId);
            OutputStream output = this.socket.getOutputStream();
            DataOutputStream sender = new DataOutputStream(output);
            sender.write(sentMsg.toByteArray());
        }
        catch(IOException err) {
            err.printStackTrace();
        }
    }

    void receive(){
        try {
            InputStream input = this.socket.getInputStream();
            DataInputStream receiver = new DataInputStream(input);
            while(receiver.available() == 0){}
            byte[] bytes = new byte[receiver.available()];
            receiver.read(bytes);
            TcpPacket receiveMsg = TcpPacket.parseFrom(bytes);
            PacketType type = receiveMsg.getType();
            String player;

            switch(type){
                case CONNECT:
                    ConnectPacket connect = ConnectPacket.parseFrom(bytes);
                    player = connect.getPlayer().getName();
                    System.out.println(player + " has connected to the lobby.");
                    break;
                case CHAT:
                    ChatPacket chat = ChatPacket.parseFrom(bytes);
                    if(chat.hasPlayer())
                        System.out.println(chat.getPlayer().getName() + ": " + chat.getMessage());
                    else
                        System.out.println(user.getPlayer().getName() + ": " + chat.getMessage());
                    break;
                case DISCONNECT:
                    DisconnectPacket disconnect = DisconnectPacket.parseFrom(bytes);
                    player = disconnect.getPlayer().getName();
                    System.out.println(player + " has disconnected from the lobby.");
                    break;
            }
        }
        catch(IOException err) {
            err.printStackTrace();
        }
    }

    public Chat(Socket socket, Connection conn, User user, String lobbyId) {
        this.socket = socket;
        this.conn = conn;
        this.user = user;
        this.lobbyId = lobbyId;
    }

    @Override
    public synchronized void run(){
        while(true){
            this.send();
            this.receive();
            
            // try {
            //     this.wait();
            // }catch(Exception e){};
        }
    }
}
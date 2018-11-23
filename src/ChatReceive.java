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

class ChatReceive extends Thread {
    private Socket socket;
    private Connection conn;
    private User user;
    private String lobbyId;

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

    public ChatReceive(Socket socket, Connection conn, User user, String lobbyId) {
        this.socket = socket;
        this.conn = conn;
        this.user = user;
        this.lobbyId = lobbyId;
    }

    @Override
    public void run(){
        while(true){
            this.receive();
        }
    }
}
package src;

import packet.PlayerProtos.*;
import packet.TcpPacketProtos.TcpPacket.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

import java.io.IOException;
import java.net.UnknownHostException;

class Connection {
    private Socket socket;

    ConnectPacket createConnection(Player player, String lobbyId, ConnectPacket.Update update){
        ConnectPacket.Builder connectBuilder = ConnectPacket.newBuilder();
        connectBuilder.setType(PacketType.CONNECT);
        if(player != null)      connectBuilder.setPlayer(player);
        if(lobbyId != null)     connectBuilder.setLobbyId(lobbyId);
        if(update == ConnectPacket.Update.SELF || update == ConnectPacket.Update.NEW)  
            connectBuilder.setUpdate(update);
        ConnectPacket connect = connectBuilder.build();

        return connect;
    }

    void send(ConnectPacket sentConnection){
        try {
            OutputStream output = this.socket.getOutputStream();
            DataOutputStream sender = new DataOutputStream(output);
            sender.write(sentConnection.toByteArray());
        }
        catch(IOException err) {
            err.printStackTrace();
        }
    }

    ConnectPacket receive(){
        try {
            InputStream input = this.socket.getInputStream();
            DataInputStream receiver = new DataInputStream(input);
            while(receiver.available() == 0){}
            byte[] bytes = new byte[receiver.available()];
            receiver.read(bytes);
            ConnectPacket receiveConnection = ConnectPacket.parseFrom(bytes);
            return receiveConnection;
        }
        catch(IOException err) {
            err.printStackTrace();
            return null;
        }
    }

    public Connection(Socket socket) {
        this.socket = socket;
    }
}
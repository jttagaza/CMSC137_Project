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

class Chat {
    private Socket socket;

    ChatPacket createChat(String message, Player player, String lobbyId){
        ChatPacket.Builder chatBuilder = ChatPacket.newBuilder();
        chatBuilder.setType(PacketType.CHAT);
        chatBuilder.setMessage(message);
        if(player != null)  chatBuilder.setPlayer(player);
        if(lobbyId != null) chatBuilder.setLobbyId(lobbyId);
        ChatPacket sentMsg = chatBuilder.build();

        return sentMsg;
    }

    void send(ChatPacket sentMsg){
        try {
            OutputStream output = this.socket.getOutputStream();
            DataOutputStream sender = new DataOutputStream(output);
            sender.write(sentMsg.toByteArray());
        }
        catch(IOException err) {
            err.printStackTrace();
        }
    }

    ChatPacket receive(){
        try {
            InputStream input = this.socket.getInputStream();
            DataInputStream receiver = new DataInputStream(input);
            while(receiver.available() == 0){}
            byte[] bytes = new byte[receiver.available()];
            receiver.read(bytes);
            ChatPacket receiveMsg = ChatPacket.parseFrom(bytes);
            return receiveMsg;
        }
        catch(IOException err) {
            err.printStackTrace();
            return null;
        }
    }

    public Chat(Socket socket) {
        this.socket = socket;
    }
}
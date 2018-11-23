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

class Chat {
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

    public Chat(Socket socket, Connection conn, User user, String lobbyId) {
        this.socket = socket;
        this.conn = conn;
        this.user = user;
        this.lobbyId = lobbyId;
    }
}
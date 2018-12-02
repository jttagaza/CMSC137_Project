package src;

import packet.PlayerProtos.*;
import packet.TcpPacketProtos.TcpPacket.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import java.io.IOException;

class Packet {
    private Socket socket;

    void setSocket(Socket socket) {
        this.socket = socket;
    }

    Player createPlayer(String name){
        Player.Builder playerBuilder = Player.newBuilder();
        playerBuilder.setName(name);
        Player player = playerBuilder.build();
        return player;
    }

    CreateLobbyPacket createLobby(int maxPlayers){
        CreateLobbyPacket.Builder lobbyBuilder = CreateLobbyPacket.newBuilder();
        lobbyBuilder.setType(PacketType.CREATE_LOBBY);
        if(maxPlayers != 0)  lobbyBuilder.setMaxPlayers(maxPlayers);
        CreateLobbyPacket lobby = lobbyBuilder.build();

        return lobby;
    }

    ConnectPacket createConnection(Player player, String lobbyId){
        ConnectPacket.Builder connectBuilder = ConnectPacket.newBuilder();
        connectBuilder.setType(PacketType.CONNECT);
        if(player != null)      connectBuilder.setPlayer(player);
        if(lobbyId != null)     connectBuilder.setLobbyId(lobbyId);
        ConnectPacket connect = connectBuilder.build();

        return connect;
    }

    ChatPacket createMessage(String message, Player player, String lobbyId) {
        ChatPacket.Builder chatBuilder = ChatPacket.newBuilder();
        chatBuilder.setType(PacketType.CHAT);
        chatBuilder.setMessage(message);
        if(player != null)  chatBuilder.setPlayer(player);
        if(lobbyId != null) chatBuilder.setLobbyId(lobbyId);
        ChatPacket chat = chatBuilder.build();

        return chat;
    }

    DisconnectPacket removeConnection(Player player) {
        DisconnectPacket.Builder disconnectBuilder = DisconnectPacket.newBuilder();
        disconnectBuilder.setType(PacketType.DISCONNECT);
        if(player != null) disconnectBuilder.setPlayer(player);
        DisconnectPacket disconnect = disconnectBuilder.build();

        return disconnect;
    }

    void send(byte[] bytes) {
        try {
            OutputStream output = this.socket.getOutputStream();
            DataOutputStream sender = new DataOutputStream(output);
            sender.write(bytes);
        }
        catch(IOException err) {
            err.printStackTrace();
        }
    }

    byte[] receive() {
        byte[] bytes = null;

        try {
            InputStream input = this.socket.getInputStream();
            DataInputStream receiver = new DataInputStream(input);
            while(receiver.available() == 0){}
            bytes = new byte[receiver.available()];
            receiver.read(bytes);
        }
        catch(IOException err) {
            err.printStackTrace();
        }

        return bytes;
    }
}
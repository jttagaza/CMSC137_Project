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

class Lobby {
    private Socket socket;

    public Lobby(Socket socket) {
        this.socket = socket;
    }

    CreateLobbyPacket createLobby(String lobbyId, int maxPlayers){
        CreateLobbyPacket.Builder lobbyBuilder = CreateLobbyPacket.newBuilder();
        lobbyBuilder.setType(PacketType.CREATE_LOBBY);
        if(lobbyId != null)  lobbyBuilder.setLobbyId(lobbyId);
        if(maxPlayers != 0)  lobbyBuilder.setMaxPlayers(maxPlayers);
        CreateLobbyPacket lobby = lobbyBuilder.build();

        return lobby;
    }

    void send(CreateLobbyPacket sentLobby){
        try {
            OutputStream output = this.socket.getOutputStream();
            DataOutputStream sender = new DataOutputStream(output);
            sender.write(sentLobby.toByteArray());
        }
        catch(IOException err) {
            err.printStackTrace();
        }
    }

    CreateLobbyPacket receive(){
        try {
            InputStream input = this.socket.getInputStream();
            DataInputStream receiver = new DataInputStream(input);
            while(receiver.available() == 0){}
            byte[] bytes = new byte[receiver.available()];
            receiver.read(bytes);
            CreateLobbyPacket receiveLobby = CreateLobbyPacket.parseFrom(bytes);
            return receiveLobby;
        }
        catch(IOException err) {
            err.printStackTrace();
            return null;
        }
    }
}
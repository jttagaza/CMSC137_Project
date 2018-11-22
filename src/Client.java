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

public class Client {
    private Socket socket;

    public Client(String addr, int port) {
        try {
            this.socket = new Socket(addr, port);
            System.out.println("Connected to " + this.socket.getRemoteSocketAddress());
            User user = new User();

            // Connection connect = new Connection(socket);
            // ConnectPacket sentConnection = connect.createConnection(user.getPlayer(), null, ConnectPacket.Update.NEW);
            // connect.send(sentConnection);
            // ConnectPacket receiveConnection = connect.receive();
            // System.out.println(receiveConnection);

            Lobby lobby = new Lobby(socket);
            CreateLobbyPacket sentLobby = lobby.createLobby("AB5L", 4);
            lobby.send(sentLobby);
            CreateLobbyPacket receiveLobby = lobby.receive();
            System.out.println(receiveLobby);

            Chat chat = new Chat(socket);
            ChatPacket sentMsg = chat.createChat("Hello", null, "AB5L");
            chat.send(sentMsg);
            ChatPacket receiveMsg = chat.receive();
            System.out.println(receiveMsg);
            this.socket.close();
        }
        catch(UnknownHostException unEx) {
            unEx.printStackTrace();
        }
        catch(IOException err) {
            err.printStackTrace();
        }
    }
}
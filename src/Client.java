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

public class Client implements Runnable{
    private Socket socket;

    public Client(String addr, int port) {
        try {
            this.socket = new Socket(addr, port);
            System.out.println("Connected to " + this.socket.getRemoteSocketAddress());
            User user = new User();
            int choice;
            Thread t = new Thread(this);

            t.start();
        }
        catch(UnknownHostException unEx) {
            unEx.printStackTrace();
        }
        catch(IOException err) {
            err.printStackTrace();
        }
    }

    public void run(){
        do{
            String lobbyId = null;
            choice = user.menu();

            if (choice == user.CREATE_LOBBY){
                Lobby lobby = new Lobby(socket);
                CreateLobbyPacket sentLobby = lobby.createLobby(null, 4);
                lobby.send(sentLobby);
                CreateLobbyPacket receiveLobby = lobby.receive();
                lobbyId = receiveLobby.getLobbyId();
                System.out.println(user.getPlayer().getName() + " successfully created new lobby " + lobbyId + ".");
            }
            else if (choice == user.CONNECT_LOBBY) {
                System.out.print("Enter Lobby Id: ");
                Scanner str = new Scanner(System.in);
                lobbyId = str.nextLine();
            } else {
                System.exit(0);
            }

            Connection connect = new Connection(socket);
            ConnectPacket sentConnection = connect.createConnection(user.getPlayer(), lobbyId);
            connect.send(sentConnection);
            ConnectPacket receiveConnection = connect.receive();
            System.out.println(user.getPlayer().getName() + " has connected to the lobby.");

            ChatSend chatSend = new ChatSend(socket, user, lobbyId);
            Thread chatS = new Thread(chatSend);
            ChatReceive chatReceive = new ChatReceive(socket, user, lobbyId);
            Thread chatR = new Thread(chatReceive);
            chatS.start();
            chatR.start();
            try {
                chatS.join();
                chatR.join();
            } catch(Exception e) {};

        }while(choice != user.EXIT);

        this.socket.close();
    }
}
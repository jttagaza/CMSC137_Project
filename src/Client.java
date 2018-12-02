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

    public Client(String addr, int port) {
        try {
            User user = new User();
            int choice;

            do{
                Socket socket = new Socket(addr, port);
                System.out.println("\nConnected to " + socket.getRemoteSocketAddress());

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
                System.out.println(user.getPlayer().getName() + " has joined to the lobby.");

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

                socket.close();
            }while(choice != user.EXIT);
        }
        catch(UnknownHostException unEx) {
            unEx.printStackTrace();
        }
        catch(IOException err) {
            err.printStackTrace();
        }
    }
}
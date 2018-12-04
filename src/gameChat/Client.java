package src.gameChat;

import src.gamePacket.PlayerProtos.*;
import src.gamePacket.TcpPacketProtos.TcpPacket.*;

import java.net.Socket;
import java.util.Scanner;

import java.io.IOException;
import java.net.UnknownHostException;

public class Client {

    public Client(String addr, int port) {
        try {
            Packet packet = new Packet();
            User user = new User(packet);
            int choice;

            do{
                Socket socket = new Socket(addr, port);
                System.out.println("\nConnected to " + socket.getRemoteSocketAddress());
                packet.setSocket(socket);

                String lobbyId = null;
                choice = user.menu();

                if (choice == user.CREATE_LOBBY){
                    CreateLobbyPacket createLobby = packet.createLobby(4);
                    packet.send(createLobby.toByteArray());
                    CreateLobbyPacket lobby = CreateLobbyPacket.parseFrom(packet.receive());
                    lobbyId = lobby.getLobbyId();
                    System.out.println(user.getPlayer().getName() + " successfully created new lobby " + lobbyId + ".");
                }
                else if (choice == user.CONNECT_LOBBY) {
                    System.out.print("Enter Lobby Id: ");
                    Scanner str = new Scanner(System.in);
                    lobbyId = str.nextLine();
                } else {
                    System.exit(0);
                }

                ConnectPacket createConnection = packet.createConnection(user.getPlayer(), lobbyId);
                packet.send(createConnection.toByteArray());
                ConnectPacket connect = ConnectPacket.parseFrom(packet.receive());
                user.setPlayer(connect.getPlayer());
                System.out.println(user.getPlayer().getName() + " has joined to the lobby.");

                ChatSend chatSend = new ChatSend(packet, user, lobbyId);
                Thread send = new Thread(chatSend);
                ChatReceive chatReceive = new ChatReceive(packet, user, lobbyId);
                Thread receive = new Thread(chatReceive);

                send.start();
                receive.start();
                try {
                    send.join();
                    receive.join();
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
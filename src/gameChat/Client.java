package src.gameChat;

import src.gamePacket.PlayerProtos.*;
import src.gamePacket.TcpPacketProtos.TcpPacket.*;
import src.gamePacket.TcpPacketProtos.TcpPacket;

import java.net.Socket;
import java.util.Scanner;

import java.io.IOException;
import java.net.UnknownHostException;

public class Client {

    public Client(User user, String addr, int port) {
        try {
            Packet packet = new Packet();
            user.setPacket(packet);
            // User user = new User(packet);
            int choice;

            do{
                String lobbyId = null;
                Socket socket = new Socket(addr, port);
                packet.setSocket(socket);
                System.out.println("\nConnected to " + socket.getRemoteSocketAddress());
                choice = user.menu();

                if (choice == user.CREATE_LOBBY){
                    CreateLobbyPacket createLobby = packet.createLobby(4);
                    packet.send(createLobby.toByteArray());
                    CreateLobbyPacket lobby = CreateLobbyPacket.parseFrom(packet.receive());
                    PacketType type = lobby.getType();
                    lobbyId = lobby.getLobbyId();

                    if (type == PacketType.ERR) {
                        System.out.println("\n"+ lobbyId);
                        continue;
                    }
                    else
                        System.out.println("\n" + user.getPlayer().getName() + " successfully created new lobby " + lobbyId + ".");
                }
                else if (choice == user.CONNECT_LOBBY) {
                    System.out.print("Enter Lobby Id: ");
                    Scanner str = new Scanner(System.in);
                    lobbyId = str.nextLine();
                    System.out.println();
                } else {
                    System.exit(0);
                }

                ConnectPacket createConnection = packet.createConnection(user.getPlayer(), lobbyId);
                packet.send(createConnection.toByteArray());

                byte[] bytes = packet.receive();
                TcpPacket data = TcpPacket.parseFrom(bytes);
                PacketType type = data.getType();

                if (type == PacketType.ERR_LDNE)
                    System.out.println("The lobby is not exist.");   
                else if (type == PacketType.ERR_LFULL)
                    System.out.println("The lobby is already full."); 
                else {
                    ConnectPacket connect = ConnectPacket.parseFrom(bytes);
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
                }
            }while(choice != user.EXIT);
        }
        catch(UnknownHostException err) {
            err.printStackTrace();
        }
        catch(IOException err) {
            err.printStackTrace();
        }
    }
}
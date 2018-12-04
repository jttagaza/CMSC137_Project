package src.gameChat;

import src.gamePacket.PlayerProtos.*;
import src.gamePacket.TcpPacketProtos;

import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.InputMismatchException;

class User {
    private Player player;

    public final static int CREATE_LOBBY = 1;
    public final static int CONNECT_LOBBY = 2;
    public final static int EXIT = 0;

    public User(Packet packet) {
        System.out.print("Enter Name: ");
        Scanner str = new Scanner(System.in);
        String name = str.nextLine();
        this.player = packet.createPlayer(name);
    }

    public int menu() {
        Boolean check = true;
        int choice = 0;
        
        while(check){
            try {
                check = false;
                System.out.println("-------- MENU --------");
                System.out.println("[1] Create Lobby");
                System.out.println("[2] Connect to a Lobby");
                System.out.println("[0] Exit");
                System.out.println("----------------------");
                System.out.print("Enter choice: ");

                Scanner str = new Scanner(System.in);
                choice = str.nextInt();
            }
            catch(InputMismatchException err) {
                check = true;
                System.out.println("\nInvalid choice. Try again.\n");
                continue;
            }
        }

        return choice;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
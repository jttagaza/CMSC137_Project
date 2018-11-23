package src;

import packet.PlayerProtos.*;
import packet.TcpPacketProtos;

import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;

import java.io.IOException;
import java.net.UnknownHostException;

class User {
    private Player player;

    public final static int CREATE_LOBBY = 1;
    public final static int CONNECT_LOBBY = 2;
    public final static int EXIT = 0;

    public int menu() {
        System.out.println("[1] Create Lobby");
        System.out.println("[2] Connect to a Lobby");
        System.out.println("[0] Exit");
        System.out.print("Enter choice: ");

        Scanner str = new Scanner(System.in);
        int choice = str.nextInt();
        return choice;
    }

    public User() {
        System.out.print("Enter Name: ");
        Scanner str = new Scanner(System.in);
        String name = str.nextLine();
        player = Player.newBuilder().setName(name).build();
    }

    public Player getPlayer() {
        return this.player;
    }
}
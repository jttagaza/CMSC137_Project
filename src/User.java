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

    public User() {
        System.out.println("Enter Name: ");
        Scanner str = new Scanner(System.in);
        String name = str.nextLine();
        player = Player.newBuilder().setName(name).build();
    }

    public Player getPlayer() {
        return this.player;
    }
}
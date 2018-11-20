package src;

import packet.PlayerProtos;
import packet.TcpPacketProtos;

import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;

import java.io.IOException;
import java.net.UnknownHostException;

class Player {
    private PlayerProtos.Player player;

    public Player() {
        System.out.println("Enter Name: ");
        Scanner str = new Scanner(System.in);
        String name = str.nextLine();
        PlayerProtos.Player player = PlayerProtos.Player.newBuilder().setName(name).build();
    }
}
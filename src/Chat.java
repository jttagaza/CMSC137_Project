package src;

import packet.PlayerProtos;
import packet.TcpPacketProtos.TcpPacket.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import java.io.IOException;
import java.net.UnknownHostException;

public class Chat {
    private Socket socket;

    public Chat(String addr, int port) {
        try {
            socket = new Socket(addr, port);
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            User user = new User();
            ConnectPacket connect = ConnectPacket.newBuilder().setType(PacketType.CONNECT).setPlayer(user.getPlayer()).setLobbyId("AB5L").build();
            socket.close();
        }
        catch(UnknownHostException unEx) {
            System.out.println(unEx);
        }
        catch(IOException err) {
            System.out.println(err);
        }
    }
}
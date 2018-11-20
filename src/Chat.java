package src;

import packet.PlayerProtos;
import packet.TcpPacketProtos;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

import java.io.IOException;
import java.net.UnknownHostException;

public class Chat {
    private Socket socket;
    private InputStream input;
    private OutputStream output;

    public Chat(String addr, int port) {
        try {
            String str = "Hello";
            socket = new Socket(addr, port);
            input = socket.getInputStream();
            output = socket.getOutputStream();

            Player player = new Player();
            // System.out.println(input);
            // System.out.println(socket.getOutputStream());
            // System.out.println(socket.getLocalAddress());
            // System.out.println(socket.getPort());
            // System.out.println(socket.getRemoteSocketAddress());
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
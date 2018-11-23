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

class Disconnection {
    private Socket socket;

    DisconnectPacket removeConnection(Player player){
        DisconnectPacket.Builder disconnectBuilder = DisconnectPacket.newBuilder();
        disconnectBuilder.setType(PacketType.DISCONNECT);
        if(player != null)      disconnectBuilder.setPlayer(player);
        DisconnectPacket disconnect = disconnectBuilder.build();

        return disconnect;
    }

    void send(DisconnectPacket sentDisconnection){
        try {
            OutputStream output = this.socket.getOutputStream();
            DataOutputStream sender = new DataOutputStream(output);
            sender.write(sentDisconnection.toByteArray());
        }
        catch(IOException err) {
            err.printStackTrace();
        }
    }

    DisconnectPacket receive(){
        try {
            InputStream input = this.socket.getInputStream();
            DataInputStream receiver = new DataInputStream(input);
            while(receiver.available() == 0){}
            byte[] bytes = new byte[receiver.available()];
            receiver.read(bytes);
            DisconnectPacket receiveDisconnection = DisconnectPacket.parseFrom(bytes);
            System.out.println(receiveDisconnection);
            return receiveDisconnection;
        }
        catch(IOException err) {
            err.printStackTrace();
            return null;
        }
    }

    public Disconnection(Socket socket) {
        this.socket = socket;
    }
}
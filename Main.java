import src.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        GameServer server = new GameServer(0);        
        Client client = new Client("202.92.144.45", 80);
    }
}
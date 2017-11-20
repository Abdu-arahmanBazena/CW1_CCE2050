package services;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerConnection {
    private static int port = 5555;
   
    
    public void runServer() {
        try {
            //create new Socket for the new client 
            final ServerSocket server = new ServerSocket(port);
            while (true)//a loop that loops and wiats for a new client to connect
            {
                //wait for a new client to connect
                Socket socket = server.accept();
                //if client connected then create the i/o streams for that client 
                new Server(socket).start();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}

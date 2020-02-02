package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;

public class Server {
    public static void main(String[] args) {
        ServerSocket server;
        {
            try {
                server = new ServerSocket(7788);
                Socket client = server.accept();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void userAuthentication(){

    }
}

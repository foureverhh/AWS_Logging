package controller.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(7788);
            System.out.println("Server started");
            Socket client = null;
            //Socket channel
            int count = 0;
            while (true) {
                client = server.accept();
                ServerThread channel = new ServerThread(client);
                channel.start();
                count++;
                System.out.println("Now " + count + " clients connected." );
            }
        } catch(IOException e){
            e.printStackTrace();
        }

    }
}
class ServerThread extends Thread{
    Socket client;

    public ServerThread(Socket client){
        this.client = client;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter writer = new PrintWriter(client.getOutputStream())){
            String input = null;
            if((input=reader.readLine()) != null) {
                System.out.println("Server receives " + input);
                writer.write("server writes back " + input);
                if(input.equals("bye")){
                    System.out.println("Clients");
                    shutdown(reader,writer,client);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void shutdown(BufferedReader reader,PrintWriter writer,Socket socket){
        try{
            if (reader!=null)
                reader.close();
            if(writer!=null)
                writer.close();
            if(socket!=null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
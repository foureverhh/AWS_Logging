package client;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Socket client;
        {
            try {
                client = new Socket("localhost",7788);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

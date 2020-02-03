package controller.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket client;
        {
            try {
                client = new Socket("localhost",7788);
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter writer = new PrintWriter(client.getOutputStream());
                //Read thread
                new Thread(()->{
                    try {
                        String input = null;
                        if((input = reader.readLine())!=null){
                            System.out.println("Client receives " + input);
                        }else if(input.equals("bye")){
                            writer.write("Socket ends,bye");
                            writer.flush();
                            //shut down system
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

                //Writer thread
                new Thread(()->{
                    System.out.println("User input:");
                    Scanner scanner = new Scanner(System.in);
                    String output = scanner.nextLine();
                    if(output != null && !output.isEmpty()) {
                        writer.write(output+"\n");
                        writer.flush();
                    System.out.println("output: " + output);
                        if(output.equals("bye")){
                            //shutdown system
                        }

                    }
                }).start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}

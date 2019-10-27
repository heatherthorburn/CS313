import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.net.*;
import java.io.*;

public class TCPServer extends Thread{
    private ServerSocket server;

    public static void main(String [] args){
        if(args.length != 1){
            System.out.println("Please enter a port like so: \n TCPServer <port>");
        }

        int port_number = Integer.parseInt(args[0]); //Get the port number from command line
        try{
            Thread thread = new TCPServer(port_number); //create a new thread for server
            thread.run(); //call run
        }
        catch(Exception e){
            System.out.println("Something went wrong. Please check exception: \n" + e);
        }
    }

    public TCPServer(int port) throws  IOException{
        int port_number = port;

        System.out.println("Setting up server on port " + port_number);
        server = new ServerSocket(port_number); //Creating a new socket for the server to communicate through
        server.setSoTimeout(10000); //Timeout is set to 10s
    }

    public void run(){
        try {
            System.out.println("Waiting on client...");
            Socket serverSock = server.accept(); //Accept first TCP connection

            System.out.println("Connected to " + serverSock.getRemoteSocketAddress()); //Display the clients address
            while (true) {

                DataInputStream from_client = new DataInputStream(serverSock.getInputStream()); //Get request from the client
                String str = from_client.readUTF(); //Convert request to a string
                System.out.println(str);

                DataOutputStream to_client = new DataOutputStream(serverSock.getOutputStream()); //Create a data stream to send response
                to_client.writeUTF(str.toUpperCase()); //Send the client the response

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

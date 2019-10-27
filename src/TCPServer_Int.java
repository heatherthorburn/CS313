import java.net.*;
import java.io.*;
import java.util.Date;

public class TCPServer_Int extends Thread{
    private ServerSocket server;

    public static void main(String [] args){
        int port_number = Integer.parseInt(args[0]); //Get port no from command line
        try{
            Thread thread = new TCPServer_Int(port_number); //create a new thread for the server to run on
            thread.run(); //call run
        }
        catch(Exception e){
            System.out.println("Something went wrong. Please check exception: \n" + e);
        }
    }

    public TCPServer_Int(int port) throws  IOException{
        int port_number = port;

        System.out.println("Setting up server on port " + port_number);
        server = new ServerSocket(port_number); //Sets up a Socket on port_number
        server.setSoTimeout(10000); //Sets the timeout to 10s
    }

    public void run(){
        try {
            System.out.println("Waiting on client...");
            Socket serverSock = server.accept(); //Accept first TCP connection

            System.out.println("Connected to " + serverSock.getRemoteSocketAddress()); //Display clients address
            while (true) {

                DataInputStream from_client = new DataInputStream(serverSock.getInputStream()); //Get request
                Integer input_int = from_client.readInt(); //Parse to an int
                System.out.println(input_int);

                DataOutputStream to_client = new DataOutputStream(serverSock.getOutputStream()); //Create a output stream to push the response
                input_int = input_int*2; //Double recieved number
                to_client.writeInt(input_int); //Write the new number to the client

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

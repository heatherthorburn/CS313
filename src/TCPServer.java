import java.net.*;
import java.io.*;

public class TCPServer extends Thread{
    private ServerSocket server;

    public static void main(String [] args){
        int port_number = Integer.parseInt(args[0]);
        try{
            Thread thread = new TCPServer(port_number);
            thread.run();
        }
        catch(Exception e){
            System.out.println("Something went wrong. PLese check exception: \n" + e);
        }
    }

    public TCPServer(int port) throws  IOException{
        int port_number = port;

        System.out.println("Setting up server on port " + port_number);
        server = new ServerSocket(port_number);
        server.setSoTimeout(10000);
    }

    public void run(){
        try {
            System.out.println("Waiting on client...");
            Socket serverSock = server.accept();

            System.out.println("Connected to " + serverSock.getRemoteSocketAddress());
            while (true) {

                DataInputStream from_client = new DataInputStream(serverSock.getInputStream());
                String str = from_client.readUTF();
                System.out.println(str);

                DataOutputStream to_client = new DataOutputStream(serverSock.getOutputStream());
                to_client.writeUTF(str.toUpperCase());

            }
        }
        catch (Exception e){
                e.printStackTrace();
        }
        }
}





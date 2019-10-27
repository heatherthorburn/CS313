import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPClient {
    public static void main(String [] args){
        if(args.length != 2){
            System.out.println("PLease enter a hostname and port like so: \n TCPClient <hostname> <port>");
        }
        else {
            try {
                Scanner scan = new Scanner(System.in);
                String ip_address = args[0]; //Getting the ip_address from the list of strings passed in
                int port_number = Integer.parseInt(args[1]); //Getting the port number the same way

                System.out.println("[*] Attempting connection to " + ip_address + ".");
                Socket client_socket = new Socket(ip_address, port_number); //Setting up a socket object for the connection to ip_address:port_number

                System.out.println("[!] Connection to " + client_socket.getRemoteSocketAddress() + " Successful!");

                OutputStream toHost = client_socket.getOutputStream(); //Set up and outputStream to send requests to the server
                DataOutputStream output = new DataOutputStream(toHost);
                output.writeUTF("[*] Contacting:" + client_socket.getRemoteSocketAddress()); //Sending request to host
                System.out.println("[*] Contacting");

                while (true) {
                    DataInputStream in = new DataInputStream(client_socket.getInputStream());
                    System.out.println("[!] Response from server: " + in.readUTF()); //display response from host

                    System.out.println("[*] >> "); //Get more user inputted requests
                    String request = scan.nextLine();

                    output.writeUTF(request);
                }
            } catch (Exception e) {
                System.out.println("[!] Error - Something went wrong. See exception stack: \n");
                e.printStackTrace();
            }
        }
    }
}

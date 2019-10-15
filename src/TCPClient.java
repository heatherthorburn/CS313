
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPClient {
    public static void main(String [] args){
        try {
            String ip_address = args[0];
            int port_number = Integer.parseInt(args[1]);

            System.out.println("[*] Attempting connection to " + ip_address + ".");
            Socket client_socket = new Socket(ip_address, port_number);

            System.out.println("[!] Connection to " + client_socket.getRemoteSocketAddress() + " Successful!");

            OutputStream toHost = client_socket.getOutputStream();
            DataOutputStream output = new DataOutputStream(toHost);
            output.writeUTF("[*] Contacting:" + client_socket.getRemoteSocketAddress());
            output.flush();
            System.out.println("[*]Contacting");


            InputStream fromHost = client_socket.getInputStream();
            DataInputStream in = new DataInputStream(fromHost);
            System.out.println("[!] Response from server: " + in.readUTF());

            while (true){
                Scanner scan = new Scanner(System.in);
                System.out.println("[*] >>");
                String request = scan.nextLine().trim().toLowerCase();

                output.writeUTF(request);
                fromHost = client_socket.getInputStream();
                System.out.println(fromHost);


            }
        }
        catch (Exception e){
            System.out.println("[!] Error - Something went wrong. See exception stack: \n" + e);
        }
    }
}




import java.net.*;
import java.io.*;
import java.util.Scanner;

public class StringClient {

    public static void main(String[] args) {
        try{
            Scanner scan = new Scanner(System.in);
            String ip_address = args[0];
            int port_number = Integer.parseInt(args[1]);

            System.out.println("[*] Attempting Connection to " + ip_address + ":" + port_number);
            Socket client_socket = new Socket(ip_address, port_number);

            System.out.println("[!] Connection to " + client_socket.getRemoteSocketAddress() + " Successful!");

            OutputStream out = client_socket.getOutputStream();
            DataOutputStream toHost = new DataOutputStream(out);

            System.out.println("Please Enter a command to send to server");
            System.out.println("getFirstName <firstname> <secondname>");
            System.out.println("getSecondName <firstname> <secondname>");
            System.out.println("toUpper <firstname> <secondname>");
            System.out.println("toLower <firstname> <secondname>");

            while(true){
                System.out.print(">>");
                String request = scan.nextLine();
                toHost.writeUTF(request);

                DataInputStream in = new DataInputStream(client_socket.getInputStream());
                System.out.println("Response: " + in.readUTF());

            }

        }
        catch(Exception e){
            System.out.println("Oh No! Something went wrong!");
            e.printStackTrace();
        }
    }
}

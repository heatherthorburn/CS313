import java.net.*;
import java.io.*;
import java.util.Scanner;

public class StringClient {

    public static void main(String[] args) {
        if(args.length != 2){
            System.out.println("Please enter a port and host like so: \n StringClient <hostname> <port>");
        }
        else {
            try {
                run(args, System.in);
            } catch (Exception e) {
                System.out.println("Shutting Down");
            }
        }
    }

    public static void run(String[] args, InputStream type) {
        try {
            Scanner scan = new Scanner(type);
            String ip_address = args[0];
            int port_number = Integer.parseInt(args[1]);

            Socket client_socket = new Socket(ip_address, port_number);

            OutputStream out = client_socket.getOutputStream();
            DataOutputStream toHost = new DataOutputStream(out);

            while (true) {
               // System.out.print(">>");
                String request = scan.nextLine();
                toHost.writeUTF(request);

                DataInputStream in = new DataInputStream(client_socket.getInputStream());
                System.out.println("Response: " + in.readUTF());
                client_socket.close();
                break;

            }
        }
        catch (EOFException e){
            System.out.println("Shutting Down");
        }
        catch(Exception e){
            System.out.println("Oh No! Something went wrong!");
            e.printStackTrace();
        }
    }

}

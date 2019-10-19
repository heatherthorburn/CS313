import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String [] args){
        try{
            String address = args[0];
            int port = Integer.parseInt(args[1]);

            InetAddress addr = InetAddress.getByName(address);
            DatagramSocket sock = new DatagramSocket();
            Scanner scan = new Scanner(System.in);

            while(true){
                byte[] request = new byte[1024];
                byte[] response = new byte[1024];

                System.out.println(">>");

                String sentence = scan.nextLine();
                request = sentence.getBytes();

                DatagramPacket outPacket = new DatagramPacket(request, request.length, addr, port);
                sock.send(outPacket);

                DatagramPacket inPacket = new DatagramPacket(response, response.length);
                sock.receive(inPacket);

                String modSentence = new String(inPacket.getData());
                System.out.println(modSentence);
            }
        }
        catch(Exception e){
            System.out.println("Something went wrong:");
            e.printStackTrace();
        }
    }
}

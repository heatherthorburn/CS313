import org.omg.CORBA.INTERNAL;
import javax.xml.crypto.Data;
import java.net.*;
import java.io.*;

public class UDPServer {
    public static void main(String [] args){
        try {
            int port = Integer.parseInt(args[0]);

            DatagramSocket sock = new DatagramSocket(port);
            byte[] recieved = new byte[1024];
            byte[] response = new byte[1024];

            while (true){
                DatagramPacket inPacket = new DatagramPacket(recieved, recieved.length);
                sock.receive(inPacket);

                String sentence = new String(inPacket.getData());
                System.out.println(sentence);
                InetAddress addr = inPacket.getAddress();
                int clientPort = inPacket.getPort();

                String outSentence = sentence.toUpperCase();

                response = outSentence.getBytes();
                DatagramPacket outPacket = new DatagramPacket(response, response.length, addr, clientPort);
                sock.send(outPacket);
            }

        }
        catch (Exception e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }

    }
}

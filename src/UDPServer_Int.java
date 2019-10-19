import org.omg.CORBA.INTERNAL;
import javax.xml.crypto.Data;
import java.net.*;
import java.io.*;

public class UDPServer_Int {
    public static void main(String [] args){
        try{
            int port = Integer.parseInt(args[0]);

            DatagramSocket sock = new DatagramSocket(port);
            byte[] recieved = new byte[1024];
            byte[] response = new byte[1024];

            while (true){
                DatagramPacket inPacket = new DatagramPacket(recieved, recieved.length);
                sock.receive(inPacket);

                String sentence = new String(inPacket.getData());
                Integer number = Integer.parseInt(sentence.trim());
                System.out.println(number);
                InetAddress addr = inPacket.getAddress();
                int clientPort = inPacket.getPort();

                int outNumber = number*2;

                response = Integer.toString(outNumber).getBytes();
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

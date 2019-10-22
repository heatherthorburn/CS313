import java.io.*;
import java.net.*;

class UDPServer
{
    public static void main(String args[]) throws Exception {

        /* Create server socket with same port */
        DatagramSocket socket = new DatagramSocket(9999);

        /* Create new packet, receive and alter data*/
        byte[] numByte = new byte[1024];
        DatagramPacket packet = new DatagramPacket(numByte, numByte.length);

        socket.receive(packet);
        String data = new String(packet.getData());

        int number = Integer.parseInt(data.trim());
        number = number * 2;

        /* Send back altered data to client */
        byte[] resultByte = Integer.toString(number).getBytes();
        InetAddress ip = InetAddress.getLocalHost();

        DatagramPacket packet2 = new DatagramPacket(resultByte, resultByte.length, ip, packet.getPort());
        socket.send(packet2);

    }
}
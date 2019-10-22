import java.io.*;
import java.net.*;

class UDPServer
{
    public static void main(String args[]) throws Exception {

        /* Create server socket with same port */
        DatagramSocket socket = new DatagramSocket(9999);

        /* Create new packet, receive and alter data*/
        byte[] senByte = new byte[1024];
        DatagramPacket packet = new DatagramPacket(senByte, senByte.length);

        socket.receive(packet);
        String data = new String(packet.getData());

        String result = data.trim().toUpperCase();

        /* Send back altered data to client */
        byte[] resultByte = result.getBytes();
        InetAddress ip = InetAddress.getLocalHost();

        DatagramPacket packet2 = new DatagramPacket(resultByte, resultByte.length, ip, packet.getPort());
        socket.send(packet2);

    }
}
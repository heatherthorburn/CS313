import java.io.*;
import java.net.*;
import java.util.Scanner;

class UDPClient
{
    public static void main(String args[]) throws Exception
    {
        /* Create client socket */
        DatagramSocket socket = new DatagramSocket();

        /* Get input from client */
        Scanner input = new Scanner (System.in);
        System.out.print("Enter integer: ");
        int number = input.nextInt();

        /* Get info for packet*/
        byte[] numByte = Integer.toString(number).getBytes();
        InetAddress ip = InetAddress.getLocalHost();

        /*Create and send packet to server*/
        DatagramPacket packet = new DatagramPacket (numByte, numByte.length, ip, 9999);
        socket.send(packet);

        /* Receive altered user input*/
        byte[] changedByte = new byte[1024];
        DatagramPacket changedPacket = new DatagramPacket(changedByte, changedByte.length);
        socket.receive(changedPacket);

        /* Output result*/
        String result = new String(changedPacket.getData());
        System.out.println("Client Output: " + result.trim());

    }
}
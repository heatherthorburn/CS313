package UDP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class UDPClient {

	public static void main(String[] args) throws Exception {
		
		DatagramSocket ds = new DatagramSocket();
		System.out.println("Please enter a number or a word: ");
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String user_input = input.readLine();
		byte[] b = String.valueOf(user_input).getBytes();
		
		InetAddress ia  = InetAddress.getLocalHost();
		DatagramPacket dp = new DatagramPacket(b,b.length,ia,1888);
		ds.send(dp);
		
		
		byte[] b1 = new byte[1024];
		DatagramPacket dp1 = new DatagramPacket(b1, b1.length);
		ds.receive(dp1);
		
		String str = new String(dp1.getData(), 0, dp1.getLength());
		System.out.println("result is " + str);

	}

}

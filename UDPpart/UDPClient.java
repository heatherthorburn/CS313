package UDP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class UDPClient {

	public static void main(String[] args) throws Exception {
		
		/*create socket */
		DatagramSocket ds = new DatagramSocket();
		
		/*ask client to input int or string to be changed by server*/
		System.out.println("Please enter a number or a word: ");
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String user_input = input.readLine();
		byte[] send = String.valueOf(user_input).getBytes();
		
		/*send client data to server*/
		InetAddress ia  = InetAddress.getLocalHost();
		DatagramPacket dp = new DatagramPacket(send,send.length,ia,1888);
		ds.send(dp);
		
		/* take data with changes in from server*/
		byte[] receive = new byte[1024];
		DatagramPacket dp1 = new DatagramPacket(receive, receive.length);
		ds.receive(dp1);
		/*return the result in the terminal*/
		String str = new String(dp1.getData(), 0, dp1.getLength());
		System.out.println("result is " + str);

	}

}

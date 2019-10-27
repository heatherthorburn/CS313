package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class UDPServer {
	
	public static void main(String a[]) throws Exception
	{
		/* server socket with port*/
		DatagramSocket ds = new DatagramSocket(1888);
		
		/* data to be received and sent back*/
		byte[] receive = new byte[1024];
		byte[] send = new byte[1024];
		
		/*create new packet for the data */
		DatagramPacket dp = new DatagramPacket(receive, receive.length);
		ds.receive(dp);
		/*create a string to accept an integer or a string and perform the appropriate action*/
		String str = new String(dp.getData(),0,dp.getLength());
		try {
			int num = Integer.parseInt(str.trim());
			String result = String.valueOf(num*2);
			send = result.getBytes();
			} catch (NumberFormatException e) {
			    str = str.toUpperCase();
			    send = str.getBytes();
				}
			/*send the integer or string that has been changed back to the client*/
		InetAddress ia = InetAddress.getLocalHost();
		DatagramPacket dp1 = new DatagramPacket(send, send.length,ia,dp.getPort());
		ds.send(dp1);
		}
	}


package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class UDPServer {
	
	public static void main(String a[]) throws Exception
	{
		DatagramSocket ds = new DatagramSocket(1888);
		
		byte[] b1 = new byte[1024];
		byte[] send = new byte[1024];
		
		DatagramPacket dp = new DatagramPacket(b1, b1.length);
		ds.receive(dp);
		String str = new String(dp.getData(),0,dp.getLength());
		try {
			int num = Integer.parseInt(str.trim());
			String result = String.valueOf(num*2);
			send = result.getBytes();
			} catch (NumberFormatException e) {
			    str = str.toUpperCase();
			    send = str.getBytes();
				}
			
		InetAddress ia = InetAddress.getLocalHost();
		DatagramPacket dp1 = new DatagramPacket(send, send.length,ia,dp.getPort());
		ds.send(dp1);
		}
	}


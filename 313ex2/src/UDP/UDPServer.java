package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class UDPServer {
	
	public static void main(String a[]) throws Exception
	{
		DatagramSocket ds = new DatagramSocket(9999);
		
		byte[] b1 = new byte[1024];
		
		DatagramPacket dp = new DatagramPacket(b1, b1.length);
		ds.receive(dp);
		String str = new String(dp.getData(),0,dp.getLength());
		int num = Integer.parseInt(str.trim());
		int result = num*2;
		byte[] b2 = (result + "").getBytes();
		InetAddress ia = InetAddress.getLocalHost();
		DatagramPacket dp1 = new DatagramPacket(b2, b2.length,ia,dp.getPort());
		ds.send(dp1);
		
	}

}

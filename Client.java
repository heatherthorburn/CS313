import java.net.*;
import java.io.*;

public class Client {
    private Socket socket            = null;
    private DataInputStream  input   = null;
    private DataOutputStream out     = null;


    public Client(String address, int port)
    {
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected to" + socket);
            input  = new DataInputStream(System.in);
            out    = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }

        String line = "";
        boolean digit = false;

        while (!line.equals("quit"))
        {
            try
            {
                line = input.readLine();
                out.writeUTF(line);
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
            //try
            //{
                //String answer = in.readUTF();
            //   System.out.println(answer);
            //}
            //catch(IOException i) {
             //   System.out.println(i);
            //}
        }

        try
        {
            input.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String args[])
    {
        Client client = new Client("127.0.0.1", 5000);
    }
}

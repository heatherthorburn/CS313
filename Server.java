import java.net.*;
import java.io.*;

public class Server
{
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream in       =  null;
    private DataOutputStream out     = null;


    public Server(int port)
    {
        try
        {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("Client accepted");

            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            out = new DataOutputStream(
                    new BufferedOutputStream(socket.getOutputStream()));


            String line = "";
            while (!line.equals("quit"))
            {
                try
                {
                    line = in.readUTF();
                    char c = line.charAt(0);

                    if (!Character.isDigit(c)) {
                        String lineUpper = line.toUpperCase();
                        System.out.println(lineUpper);
                        out.writeUTF(lineUpper);
                    } else {
                        Integer lineout = Integer.parseInt(line) + Integer.parseInt(line);
                        System.out.println(lineout);
                        String lineUpper = String.valueOf(lineout);
                        out.writeUTF(lineUpper);
                    }
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");

            socket.close();
            in.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String args[])
    {
        Server server = new Server(5000);
    }
}

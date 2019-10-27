import java.net.*;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Client {

    public OurObject run(OurObject obj, int port) {
        try {
            /* Make socket and send to server */
            Socket socket = new Socket(InetAddress.getLocalHost(), port);
            System.out.println("Connecting...");
            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
            outStream.writeObject(obj);
            OurObject changedObj = (OurObject) inStream.readObject();
            System.out.println("Object received from Server");
            System.out.println("Command: " + changedObj.getCommand());
            System.out.println("Arguments: " + Arrays.toString(changedObj.getArguments()));
            System.out.println("Result Field: " + changedObj.getResult());
            outStream.close();
            inStream.close();
            socket.close();
            return changedObj;
        } catch (UnknownHostException u) {
            System.out.println(u);
            return obj;
        } catch (IOException | ClassNotFoundException i) {
            System.out.println(i);
            return obj;
        }
    }

    public static void main(String arg[]) throws Exception {
        /* Make object and run with object and port*/
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter port: ");
        int port = scanner.nextInt();
        System.out.println("Please enter command and two arguments: ");
        String comArgInput = scanner.nextLine();
        String tokens[] = comArgInput.split(" ");

        while (tokens.length != 3) {
            System.out.println("Please re-enter command and two arguments: ");
            comArgInput = scanner.nextLine();
            tokens = comArgInput.split(" ");
        }

        String commands[] = {tokens[1], tokens[2]};

        OurObject obj = new OurObject(tokens[0], commands);
        Client start = new Client();
        start.run(obj, port);
    }

}

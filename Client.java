import java.net.*;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Client {

    public static void main(String arg[]) throws Exception {

        /* Make object */
        Scanner scanner = new Scanner(System.in);
        OurObject obj = new OurObject();
        System.out.println("Please enter command: ");
        String comInput = scanner.nextLine();
        obj.setCommand(comInput);
        System.out.println("Please enter arguments: ");
        String argInput = scanner.nextLine();
        String tokens[] = argInput.split(" ");
        obj.setArguments(tokens);

        try {
            /* Make socket and send to server */
            Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
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
        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println(i);
        }
    }

}

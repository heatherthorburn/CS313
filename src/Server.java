import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Server {

    public void run(int port) {
        try {
            ServerSocket sock = new ServerSocket(port);
            System.out.println("Server started...");
            Socket socket = sock.accept();
            System.out.println("Connected!");

            OutputStream outStream = socket.getOutputStream();
            ObjectOutputStream objOutStream = new ObjectOutputStream(outStream);

            InputStream inStream = socket.getInputStream();
            ObjectInputStream objInStream = new ObjectInputStream(inStream);

            OurObject obj = (OurObject) objInStream.readObject();

            String command = obj.getCommand();
            String[] args = obj.getArguments();

            switch (command) {
                case "getFirstName":
                    obj.setResult(args[0]);
                    break;
                case "getSecondName":
                    obj.setResult(args[1]);
                    break;
                case "concat":
                    obj.setResult(args[0] + args[1]);
                    break;
                case "capitalise":
                    obj.setResult(args[0].toUpperCase() + " " + args[1].toUpperCase());
                    break;
            }

            objOutStream.writeObject(obj);
            sock.close();
            socket.close();
            inStream.close();
            outStream.close();

        } catch (IOException | ClassNotFoundException i) {
            System.out.println(i);
        }
    }
    public static void main(String arg[]) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter port: ");
        int port = scanner.nextInt();
        Server start = new Server();
        start.run(port);
    }
}

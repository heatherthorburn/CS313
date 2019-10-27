import java.net.*;
import java.io.*;

public class StringServer extends Thread{
    private ServerSocket server;

    public static void main(String [] args){
        if(args.length != 1){
            System.out.println("Please enter a port like so: StringServer <port>");
        }
        else {
            try {
                int port_number = Integer.parseInt(args[0]);
                Thread thread = new StringServer(port_number);
                thread.run();
            } catch (Exception e) {
                System.out.println("Something went wrong. PLese check exception: \n" + e);
            }
        }
    }

    public StringServer(int port) throws IOException{
        System.out.println("Setting up serve on port:" + port);

        server = new ServerSocket(port);
        server.setSoTimeout(10000);
    }

    public void run(){
        try{
            System.out.println("Waiting on client");
            Socket serverSock = server.accept();

            System.out.println("Connected to " + serverSock.getRemoteSocketAddress());

            while(true){
                DataInputStream from_client = new DataInputStream(serverSock.getInputStream());
                String str = from_client.readUTF();
                String[] arguments = str.split(" ");

                String response = "";

                if(arguments.length == 3 || arguments.length == 1) {
                    switch (arguments[0]) {
                        case "getFirstName":
                            response = arguments[1];
                            break;
                        case "getSecondName":
                            response = arguments[2];
                            break;
                        case "toUpperCase":
                            response = arguments[1] + " " + arguments[2];
                            response = response.toUpperCase();
                            break;
                        case "concat":
                            response = arguments[1] + arguments[2];
                            break;
                        case "quit":
                            response = "Bye!";
                            break;
                        default:
                            response = "No Valid Command entered";
                            break;
                    }
                }
                else {
                    response = "Incorrect number of arguments entered";
                }
                DataOutputStream to_client = new DataOutputStream(serverSock.getOutputStream());
                to_client.writeUTF(response);

                if(response == "Bye!"){
                    serverSock.close();
                    break;
                }
            }

        }
        catch(Exception e){
            System.out.println("Oh No! Something went wrong!");
            e.printStackTrace();
        }
    }

}

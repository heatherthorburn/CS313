import java.net.*;
import java.io.*;

public class StringServer extends Thread{
    private ServerSocket server;

    public static void main(String [] args){
        int port_number = Integer.parseInt(args[0]);
        try{
            Thread thread = new StringServer(port_number);
            thread.run();
        }
        catch(Exception e){
            System.out.println("Something went wrong. PLese check exception: \n" + e);
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
                if(arguments.length == 3) {
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
                        case "toLowerCase":
                            response = arguments[1] + " " + arguments[2];
                            response = response.toLowerCase();
                            break;
                        default:
                            response = "No Valid Command entered";
                            break;
                    }
                }
                else{
                    response = "Incorrect number of arguments entered";
                }
                DataOutputStream to_client = new DataOutputStream(serverSock.getOutputStream());
                to_client.writeUTF(response);


            }
        }
        catch(Exception e){
            System.out.println("Oh No! Something went wrong!");
            e.printStackTrace();
        }
    }

}

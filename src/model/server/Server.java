package model.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {

    private int port;
    private ServerSocket serverS;
    private DataInputStream din;
    private DataOutputStream dout;
    private List<ClientHandler> clientList;

    public Server(int port) {
        this.port = port;
        clientList = new ArrayList<>();
    }

    public List<ClientHandler> getClientList() {
        return clientList;
    }

    public static void main(String[] args) {
        Server s = new Server(5065);
        s.start();
    }

    public void start() {
        System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°START°°°°°°°°°°°°°°°°°°°°°°°°°°°");
        System.out.println("Server starting...");
        try {
            //Initialise serversocket
            serverS = new ServerSocket(port);
            System.out.println("Server started!");
            //Allow clients to connect and handle them in their own thread
            while(true) {
                Socket clientS = serverS.accept();
                System.out.println("A new client is connected: " + clientS);

                din = new DataInputStream(clientS.getInputStream());
                dout = new DataOutputStream(clientS.getOutputStream());

                ClientHandler clientT = new ClientHandler(clientS, din, dout, this);
                clientList.add(clientT);

                clientT.start();

                if(clientList.size() >= 8) {
                    break;
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
            System.out.println("Server starting failed!");
        }
    }

    public void close() {
        System.out.println("Server closing...");
        try {
            serverS.close();
            System.out.println("Server closed!");
            System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°END°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server closing failed!");
        }
    }

    public void sendToAllHandler(String msg) {
        System.out.println("Received message: " + msg);

        if(msg.contains("/sysinf dir:")) {
            //TODO: Save direction and send to every player
        }else {
            for(int i = 0; i < clientList.size(); i++) {
                clientList.get(i).sendToClient(msg);
            }
        }
    }
}
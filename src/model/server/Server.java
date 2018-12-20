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

    public static void main(String[] args) {
        Server s = new Server(5065);
        s.start();
    }

    public void start() {
        try {
            //Initialise serversocket
            serverS = new ServerSocket(port);

            //Allow clients to connect and handle them in their own thread
            while(true) {
                Socket clientS = serverS.accept();
                System.out.println("A new model.client is connected: " + clientS);

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
        }
    }

    public void stop() {
        try {
            serverS.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendToAllHandler(String msg) {
        System.out.println("Recieved message: " + msg);
        for(int i = 0; i < clientList.size(); i++) {
            clientList.get(i).sendToClient(msg);
        }
    }
}
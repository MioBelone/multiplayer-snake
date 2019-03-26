package model.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class ClientHandler extends Thread {

    final Socket clientS;
    final DataInputStream din;
    final DataOutputStream dout;
    final Server server;

    private String name;

    public ClientHandler(Socket clientS, DataInputStream din, DataOutputStream dout, Server server) {
        this.clientS = clientS;
        this.din = din;
        this.dout = dout;
        this.server = server;
    }

    public String getNameOfClient() {
        return name;
    }

    @Override
    public void run() {

        String msg;

        try {
            while(true) {
                msg = din.readUTF();

                if(msg.substring(0, 1).equals("/")) {
                    if(msg.equals("/disconnect")) {
                        clientS.close();
                        System.out.println("Client " + clientS + " is disconnected!");
                        break;
                    }

                    if(msg.contains("/clientInf clientName")) {
                        String name = msg.split(":")[1];
                        this.name = name;
                    }

                    if(msg.contains("/gameCmd move")) {
                        msg = msg + " player:" + name;
                    }

                    if(msg.contains("/sysCmd getClientNames")) {
                        sendNewClientNames();
                    }

                } else {
                    server.sendToAllHandler(msg);
                }
            }

            //Closing input-/outputstream after disconnecting
            din.close();
            dout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendToClient(String msg) {
        try {
            dout.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNewClientNames() {
        String clientNames = "";

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<ClientHandler> clientList = server.getClientList();

        for(ClientHandler client:clientList) {
            clientNames += client.getNameOfClient() + ";";
        }

        clientNames = "/sysCmd clientNames names:{" + clientNames + "}";

        sendToClient(clientNames);
    }
}

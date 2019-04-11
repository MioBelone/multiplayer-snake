package model.server;

import model.game.Direction;

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
                        server.removeClientHander(this);
                        clientS.close();
                        System.out.println("Client " + clientS + " is disconnected!");
                        break;
                    }

                    if(msg.contains("/clientInf clientName")) {
                        String name = msg.split(":")[1];
                        this.name = name;
                        server.updateAllClients();
                    }

                    if(msg.contains("/gameCmd move")) {
                        Direction dir;

                        switch(msg.split(" ")[2]) {
                            case "UP":
                                dir = Direction.UP;
                                break;
                            case "DOWN":
                                dir = Direction.DOWN;
                                break;
                            case "LEFT":
                                dir = Direction.LEFT;
                                break;
                            case "RIGHT":
                                dir = Direction.RIGHT;
                                break;
                            default:
                                dir = null;
                                break;
                        }
                        server.switchDirection(dir, name);
                    }

                    if(msg.contains("/gameCmd start")) {
                        sendToClient(msg);
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

        List<ClientHandler> clientList = server.getClientList();

        for(ClientHandler client:clientList) {
            clientNames += client.getNameOfClient() + ";";
        }

        clientNames = "/sysCmd clientNames names:{" + clientNames + "}";

        sendToClient(clientNames);
    }
}

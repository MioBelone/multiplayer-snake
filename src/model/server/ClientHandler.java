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
    private boolean isRdy;

    public ClientHandler(Socket clientS, DataInputStream din, DataOutputStream dout, Server server) {
        this.clientS = clientS;
        this.din = din;
        this.dout = dout;
        this.server = server;
    }

    public String getNameOfClient() {
        return name;
    }

    public boolean isRdy() {
        return isRdy;
    }

    @Override
    public void run() {

        String msg;

        try {
            while(true) {
                msg = din.readUTF();

                if(msg.equals("/disconnect")) {

                    server.removeClientHander(this);
                    clientS.close();
                    System.out.println("Client " + clientS + " is disconnected!");
                    break;

                } else if(msg.contains("/clientInf")) {
                    switch(msg.split(" ")[1]) {

                        case "clientName":
                            String nameStr = msg.split(":")[1];
                            this.name = nameStr;
                            server.updateAllClients();
                            break;

                        case "readyInformation":
                            String rdy = msg.split(":")[1];

                            if(rdy.equals("true")) {
                                isRdy = true;
                                System.out.println("Client " + name + " is ready!");
                                sendToClient("/sysCmd rdyConfirmed");
                            } else {
                                isRdy = false;
                                System.out.println("Client " + name + " is not ready!");
                                sendToClient("/sysCmd rdyCancelled");
                            }
                            break;
                    }
                } else if(msg.contains("/gameCmd")) {
                    switch (msg.split(" ")[1]) {

                        case "move":
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
                            break;

                        case "start":
                            sendToClient(msg);
                            break;
                    }
                } else if(msg.contains("/sysCmd")) {
                    switch (msg.split(" ")[1]) {

                        case "getClientNames":
                            sendNewClientNames();
                            break;
                    }
                } else {
                    server.sendToAllHandler(msg);
                }
            }

            //Closing input/output stream after disconnecting
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

    public void setRdy(boolean rdy) {
        isRdy = rdy;
    }
}

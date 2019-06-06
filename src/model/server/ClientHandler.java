package model.server;

import model.game.Direction;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 * This class handles the input of one client as a thread. It holds the input and output streams of the client. Furthermore this
 * this class knows the clients name and if the client is ready or not.
 *
 * @author Maximilian Gräfe
 */
public class ClientHandler extends Thread {

    final Socket clientS;
    final DataInputStream din;
    final DataOutputStream dout;
    final Server server;

    //Client variables
    private String name;
    private boolean isRdy;

    /**
     * Constructor of the ClientHandler Class. In this method the ClientHandler will be initialised. The method must be
     * called with the client, its input and output streams und the server on which the client connected.
     *
     * @param clientS the client which connected to the server
     * @param din the input szream of the client
     * @param dout the output stream of the client
     * @param server the server on which the client connected
     */
    public ClientHandler(Socket clientS, DataInputStream din, DataOutputStream dout, Server server) {
        this.clientS = clientS;
        this.din = din;
        this.dout = dout;
        this.server = server;
    }

    /**
     * Getter
     *
     * @return the name of the client
     */
    public String getNameOfClient() {
        return name;
    }

    /**
     * Getter
     *
     * @return if the client is ready or not
     */
    public boolean isRdy() {
        return isRdy;
    }

    /**
     * This method contains the logic of the thread and handles all the input and output of the client.
     */
    @Override
    public void run() {

        String msg;

        try {
            while(true) {
                msg = din.readUTF();

                if(msg.equals("/disconnect")) {

                    server.removeClientHander(this);
                    clientS.close();
                    server.writeLog("Client " + clientS + " is disconnected!");
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
                                server.writeLog("Client " + name + " is ready!");
                                sendToClient("/sysCmd rdyConfirmed");
                            } else {
                                isRdy = false;
                                server.writeLog("Client " + name + " is not ready!");
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

    /**
     * This method sends a message to the the client of this handler object
     *
     * @param msg
     */
    public void sendToClient(String msg) {
        try {
            dout.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method prepares a list of all client names, to send it to the client of this handler object.
     */
    public void sendNewClientNames() {
        String clientNames = "";

        List<ClientHandler> clientList = server.getClientList();

        for(ClientHandler client:clientList) {
            clientNames += client.getNameOfClient() + ";";
        }

        clientNames = "/sysCmd clientNames names:{" + clientNames + "}";

        sendToClient(clientNames);
    }

    /**
     * Setter
     *
     * @param rdy if the client is ready or not as boolean
     */
    public void setRdy(boolean rdy) {
        isRdy = rdy;
    }
}

package model;

import model.client.Client;
import model.server.Server;

/**
 * This class saves and works with the data received from the users input on the specific view.
 *
 * @author Maximilian Gräfe
 */
public class InitialModel {

    private String username;
    private int port;
    private String ip;

    /**
     * This method creates a new server object and a new client object which is the host of the server and displays the
     * client view.
     *
     * @param username the username of the host
     * @param port the port on which the server will be running
     */
    public Server hostGame(String username, int port) {
        //Start server
        Server server = new Server(port);
        Thread serverRunning = new Thread() {
            @Override
            public void run() {
                server.start();
            }
        };
        serverRunning.start();
        //Create client
        Client client = new Client(username);
        client.connect("localhost", port);
        client.sendMsgToServer("/clientInf readyInformaton value:true");

        server.setHostClient(client);

        return server;
    }

    /**
     * This method creates a new client object and will connect this client to the server with the given ip and port.
     * It also displays the client view after connecting.
     *
     * @param username the username of the client
     * @param port the port on which the client will connect to the server
     * @param ip the ip address on which the client will connect to the server
     */
    public Client joinGame(String username, int port, String ip) {
        Client client = new Client(username);
        client.connect(ip, port);

        return client;
    }
}

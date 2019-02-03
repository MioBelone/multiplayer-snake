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
     * @param username
     * @param port
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
        System.out.println(port);
        //Create client
        Client client = new Client(username);
        client.connect("localhost", port);

        return server;

    }

    /**
     * This method creates a new client object and will connect this client to the server with the given ip and port.
     * It also displays the cleint view after connecting.
     *
     * @param username
     * @param port
     * @param ip
     */
    public void joinGame(String username, int port, String ip) {
        Client client = new Client(username);
        client.connect("localhost", port);

    }
}

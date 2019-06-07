package model.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.client.Client;
import model.game.Direction;
import model.game.SnakeContents.Snake;
import model.game.SnakeGame;
import presenter.PlaygroundPresenter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.*;

/**
 * This class accepts all clients who are trying to connect to the server and passes them to a new ClientHandler thread.
 * It also got a logging function to keep track of all the actions that happen within the class.
 * Another task of this class is to handle a snake game when its started.
 *
 * @author Maximilian Gräfe
 */
public class Server {

    private int port;
    private ServerSocket serverS;
    private DataInputStream din;
    private DataOutputStream dout;
    private Client hostClient;
    private ObservableList<ClientHandler> clientList;
    private SnakeGame snakeGame;

    //Logging
    private Logger logger;
    private FileHandler fHandler;
    private SimpleFormatter simpleFormatter;

    /**
     * Contructor of the Server class. In this method the list of all ClientHandlers and its connected clients and the
     * logger will be initialised.
     *
     * @param port the port on which the server will run
     */
    public Server(int port) {
        this.port = port;
        clientList = FXCollections.observableArrayList();

        try {
            //Initialise Logger
            logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
            logger.setLevel(Level.ALL);

            fHandler = new FileHandler("server.log");
            fHandler.setLevel(Level.ALL);
            logger.addHandler(fHandler);

            simpleFormatter = new SimpleFormatter();
            fHandler.setFormatter(simpleFormatter);

            logger.config("Logger initialised.");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter
     *
     * @return a list of all ClientHandlers with all clients
     */
    public ObservableList<ClientHandler> getClientList() {
        return clientList;
    }

    /**
     * This method allows us to start just this class. This method is only relevant for testing.
     *
     * @param args
     */
    public static void main(String[] args) {
        Server s = new Server(5065);
        s.start();
    }

    /**
     * Setter
     *
     * @param hostClient the client which hosts the game and has control of the server
     */
    public void setHostClient(Client hostClient) {
        this.hostClient = hostClient;
    }

    /**
     * Getter
     *
     * @return the client which hosts the game and has control of the server
     */
    public Client getHostClient() {
        return hostClient;
    }

    /**
     * This method allows the server to accept all clients and passes them directly to a ClientHandler.
     */
    public void start() {
        logger.info("°°°°°°°°°°°°°°°°°°°°°°°°°°°START°°°°°°°°°°°°°°°°°°°°°°°°°°°");
        logger.info("Server starting...");
        try {
            //Initialise serversocket
            serverS = new ServerSocket(port);
            logger.info("Server started!");
            //Allow clients to connect and handle them in their own thread
            while (true) {
                Socket clientS = serverS.accept();
                logger.info("A new client is connected: " + clientS);

                din = new DataInputStream(clientS.getInputStream());
                dout = new DataOutputStream(clientS.getOutputStream());

                ClientHandler clientT = new ClientHandler(clientS, din, dout, this);
                clientList.add(clientT);

                clientT.start();

                if (clientList.size() >= 8) {
                    break;
                }
            }
        } catch (SocketException se) {
            if(!se.getMessage().equals("socket closed")) {
                se.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.warning("Server starting failed!");
        }
    }

    /**
     * This method handles all disconnected clients and removes the ClientHandler of the disconnected client.
     *
     * @param clientT
     */
    public void removeClientHander(ClientHandler clientT) {
        clientList.remove(clientT);
        updateAllClients();
        logger.info("ClientHandler removed!");
    }

    /**
     * This method handles the direction change of a client while a snake game is running.
     * It passes the desired direction to the snake game.
     *
     * @param dir desired direction for the snake
     * @param clientName the client who changed the direction
     */
    public void switchDirection(Direction dir, String clientName) {
        for (Snake snake:snakeGame.getSnakes()) {
            if(snake.getPlayername().equals(clientName)) {
                snake.getSnakeHead().setDir(dir);
            }
        }
    }

    /**
     * This method will close the server and stop all the ClientHandlers.
     */
    public void close() {
        //Stop all ClientHandler
        for (ClientHandler ch:clientList) {
            ch.stop();
        }

        //Close Server
        logger.info("Server closing...");
        try {
            serverS.close();
            logger.info("Server closed!");
            logger.info("°°°°°°°°°°°°°°°°°°°°°°°°°°°END°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
        } catch (IOException e) {
            e.printStackTrace();
            logger.warning("Server closing failed!");
        }
    }

    /**
     * This method sends a message to all ClientHandler which then will pass it to their clients.
     *
     * @param msg the message to send to all clients
     */
    public void sendToAllHandler(String msg) {
        logger.info("Received message: " + msg);

        for (int i = 0; i < clientList.size(); i++) {
            clientList.get(i).sendToClient(msg);
        }
    }

    /**
     * This method will refresh the name lists of the clients for each client connected to the server.
     */
    public void updateAllClients() {
        for (int i = 0; i < clientList.size(); i++) {
            clientList.get(i).sendNewClientNames();
        }
    }

    /**
     * This method declares a new snake game.
     *
     * @param playgroundPresenter the presenter on which the game will be shown
     * @throws JsonProcessingException
     */
    public void startSnakeGame(PlaygroundPresenter playgroundPresenter) throws JsonProcessingException {
        snakeGame = new SnakeGame(this, playgroundPresenter);
        logger.info("New SnakeGame started");
    }

    /**
     * This method sets all clients to not ready after a snake game is finished.
     */
    public void unreadyAll() {
        for(ClientHandler ch : clientList) {
            ch.setRdy(false);
            ch.sendToClient("/sysCmd rdyCancelled");
        }

        hostClient.sendMsgToServer("/clientInf readyInformation value:true");
    }

    /**
     * This method allows to call the logger from other classes. It is used in the ClientHandler to log if a client
     * is ready or not or if a client disconnected.
     *
     * @param msg the message which will be written in the log
     */
    public void writeLog(String msg) {
        logger.info(msg);
    }

    /**
     * Getter
     *
     * @return the current snake game
     */
    public SnakeGame getSnakeGame() {
        return snakeGame;
    }
}
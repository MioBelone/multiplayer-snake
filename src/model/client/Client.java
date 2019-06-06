package model.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.game.Food;
import model.game.SnakeContents.Snake;
import presenter.LobbyPresenter;
import presenter.PlaygroundPresenter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

/**
 * This class will handle all the operations done by the client. It will hold the connection to the server, the
 * input and output streams and will handle all incoming information.
 *
 * @author Maximilian Gräfe
 */
public class Client {

    //Client variables
    private Socket clientS;
    private String name;
    private boolean isRdy;
    private DataInputStream din;
    private DataOutputStream dout;
    private LobbyPresenter presenter;
    private PlaygroundPresenter playgroundPresenter;
    private ObservableList<String> clientNames;

    //SnakeGame variables
    private List<Snake> snakes;
    private List<Food> foods;

    /**
     * Constructor for Client class. Initialises the class with a name and automatically sets the client to not ready.
     *
     *
     * @param name a name for the client which will be visible in the lobby
     */
    public Client(String name) {
        this.name = name;
        this.isRdy = false;
        clientNames = FXCollections.observableArrayList();
    }

    /**
     * This method allows us to start just this class. This method is only relevant for testing.
     *
     * @param args
     */
    public static void main(String[] args) {
        Client c = new Client("Testclient");
        c.connect("localhost", 5065);
        c.sendMsgToServer("Testnachricht 123");
    }

    /**
     * Setter
     *
     * @param presenter the LobbyPresenter to display the lobby
     */
    public void setPresenter(LobbyPresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * This method connects the client to the server and holds the connection to transfer information between server
     * and client. The method starts a thread which holds the connection to allow the client to continue with other
     * operations. This thread catches all incoming commands sent by the server and decides what to do with them.
     *
     * @param ip the address to which the user wants to connect
     * @param port the port on which the server is hosted
     */
    public void connect(String ip, int port) {
        try {
            clientS = new Socket(ip, port);

            //input and output streams
            din = new DataInputStream(clientS.getInputStream());
            dout = new DataOutputStream(clientS.getOutputStream());

            ObjectMapper objectMapper = new ObjectMapper();

            //Thread for handling the input and output
            Thread msgReading = new Thread() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String msg;
                            msg = din.readUTF();

                            if (msg.contains("/gameCmd")) {

                                //Code block for incoming game commands sent by the server
                                switch (msg.split(" ")[1]) {

                                    case "snakes":
                                        snakes = objectMapper.readValue(msg.split(" ")[2], new TypeReference<List<Snake>>() {
                                        });
                                        break;

                                    case "foods":
                                        foods = objectMapper.readValue(msg.split(" ")[2], new TypeReference<List<Food>>() {
                                        });
                                        break;

                                    case "loop":
                                        Platform.runLater(new Runnable() {
                                            @Override
                                            public void run() {
                                                playgroundPresenter.drawSnake();
                                                playgroundPresenter.drawFood();
                                            }
                                        });
                                        break;

                                    case "start":
                                        presenter.startGame();
                                        break;

                                    default:
                                        //TODO: Command wurde nicht erkannt
                                        break;
                                }

                            } else if (msg.contains("/sysCmd")) {

                                //Code block for incoming system commands sent by the server
                                switch (msg.split(" ")[1]) {

                                    case "clientNames":
                                        Platform.runLater(new Runnable() {
                                            @Override
                                            public void run() {
                                                //Saves the names from the server in its list.
                                                clientNames.clear();

                                                String nameString = msg.split("\\{")[1].split("}")[0];

                                                for (String name : nameString.split(";")) {
                                                    clientNames.add(name);
                                                }
                                            }
                                        });
                                        break;

                                    case "rdyConfirmed":
                                        isRdy = true;
                                        if(presenter != null) {
                                            presenter.ready(isRdy);
                                        }
                                        break;

                                    case "rdyCancelled":
                                        isRdy = false;
                                        if(presenter != null) {
                                            presenter.ready(isRdy);
                                        }
                                        break;

                                    default:
                                        //TODO: Command wurde nicht erkannt
                                        break;
                                }

                            } else {
                                //If the message isn't a system command it will be displayed in the chat
                                writeMsgToGUI(msg);
                            }
                        } catch (SocketException se) {
                            //Socket is closed
                            if (se.getMessage().equals("Socket closed")) {
                                break;
                            }
                        } catch (EOFException eofe) {
                            //Connection interrupted
                            presenter.closeLobby();
                            this.stop();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            msgReading.start();

            //Send name of Client to Server
            sendMsgToServer("/clientInf clientName name:" + name);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method closes the connection between server and client. It will send a command to the server so the
     * connection will be closed by both sides (server and client side).
     */
    public void close() {
        try {
            //Closing connection
            sendMsgToServer("/disconnect");
            clientS.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method sends the input message to the server by using the output stream. If the message is not a command
     * it will include the clients name in front if the message so it will be displayed in the chat.
     *
     * @param msg the message or command which will be send to the server
     */
    public void sendMsgToServer(String msg) {

        if(!msg.equals("") && !msg.isEmpty() && msg != null) {
            if (!msg.substring(0, 1).equals("/")) {
                msg = name + ": " + msg;
            }

            try {
                dout.writeUTF(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Getter
     *
     * @return the list of all snakes in game.
     */
    public List<Snake> getSnakes() {
        return snakes;
    }

    /**
     * Getter
     *
     * @return the list of all food in the game
     */
    public List<Food> getFoods() {
        return foods;
    }

    /**
     * Getter
     *
     * @return the name of the client
     */
    public String getName() {
        return name;
    }

    /**
     * This method prepares the command to set the direction of the player's snake.
     *
     * @param dir the direction of the users input
     */
    public void sendDirectionToServer(String dir) {
        sendMsgToServer("/gameCmd move dir:" + dir);
    }

    /**
     * This method prepares the command to get all connected client names from the server.
     */
    public void updateClientNameList() {
        sendMsgToServer("/sysCmd getClientNames");
    }

    /**
     * Getter
     *
     * @return the list of all connected client names
     */
    public ObservableList<String> getClientNameList() {
        return clientNames;
    }

    /**
     * This method prepares the message which will be shown in the lobby chat.
     *
     * @param msg the message for the lobby chat
     */
    private void writeMsgToGUI(String msg) {
        presenter.writeMsg(msg + "\n");
    }

    /**
     * Setter
     *
     * @param playgroundPresenter the PlaygroundPresenter to display the game
     */
    public void setPlaygroundPresenter(PlaygroundPresenter playgroundPresenter) {
        this.playgroundPresenter = playgroundPresenter;
    }
}

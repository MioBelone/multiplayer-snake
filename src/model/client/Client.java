package model.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jdk.nashorn.internal.parser.JSONParser;
import model.game.Food;
import model.game.SnakeContents.Snake;
import presenter.LobbyPresenter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Client {

    private Socket clientS;
    private String name;
    private DataInputStream din;
    private DataOutputStream dout;
    private LobbyPresenter presenter;
    private ObservableList<String> clientNames;

    //SnakeGame variablen
    private List<Snake> snakes;
    private List<Food> foods;

    public Client(String name) {
        this.name = name;
        clientNames = FXCollections.observableArrayList();
    }

    public static void main(String[] args) {
        Client c = new Client("Testclient");
        c.connect("localhost", 5065);
        c.sendMsgToServer("Testnachricht 123");
    }

    public void setPresenter(LobbyPresenter presenter) {
        this.presenter = presenter;
    }

    public void connect(String ip, int port) {
        try {
            clientS = new Socket(ip, port);

            din = new DataInputStream(clientS.getInputStream());
            dout = new DataOutputStream(clientS.getOutputStream());

            Thread msgReading = new Thread() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String msg;
                            msg = din.readUTF();
                            System.out.println(msg);

                            if (msg.contains("/gameCmd")) {

                                switch (msg.split(" ")[1]) {
                                    case "move":
                                        //If the message is a systemcommand it will be handled
                                        String[] parts = msg.split(" ");

                                        //Save player and its direction
                                        String dir = parts[2].split(":")[1];
                                        String player = parts[3].split(":")[1];

                                        //TODO: Call method of game to do move in given direction
                                        break;

                                    case "snakes":
                                        break;

                                    case "foods":
                                        break;

                                    default:
                                        System.out.println("Command wurde nicht erkannt");
                                        break;
                                }

                            } else if (msg.contains("/sysCmd")) {

                                switch (msg.split(" ")[1]) {
                                    case "clientNames":
                                        //Saves the names from the server in its list.
                                        clientNames.clear();

                                        String nameString = msg.split("\\{")[1].split("}")[0];

                                        for (String name : nameString.split(";")) {
                                            clientNames.add(name);
                                        }
                                        break;

                                    default:
                                        System.out.println("Command wurde nicht erkannt");
                                        break;
                                }

                            } else {
                                //If the message isn't a systemcommand it will be displayed in the chat
                                writeMsgToGUI(msg);
                            }
                        } catch (SocketException se) {
                            if(se.getMessage().equals("Socket closed")) {
                                break;
                            }
                        } catch (EOFException eofe) {
                            System.out.println("Connection interrupted");
                            this.stop();
                            //TODO: Client muss aus der Lobby geworfen werden.
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            msgReading.start();

            //Send name of Client to Server
            sendMsgToServer("/clientInf clientName:" + name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            sendMsgToServer("/disconnect");
            clientS.close();
            System.out.println("Connection closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsgToServer(String msg) {

        if(!msg.substring(0, 1).equals("/")) {
            msg = name + ": " + msg;
        }

        try {
            dout.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void sendDirectionToServer(String dir) {
        sendMsgToServer("/gameCmd move dir:" + dir);
    }

    public void updateClientNameList() {
        sendMsgToServer("/sysCmd getClientNames");
    }

    public ObservableList<String> getClientNameList() {
        return clientNames;
    }

    private void writeMsgToGUI(String msg) {
        presenter.writeMsg(msg + "\n");
    }
}

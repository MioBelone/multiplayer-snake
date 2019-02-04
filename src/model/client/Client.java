package model.client;

import javafx.collections.ObservableList;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {

    private Socket clientS;
    private String name;
    private DataInputStream din;
    private DataOutputStream dout;
    private ArrayList<String> clientNames;

    public Client(String name) {
        this.name = name;
        clientNames = new ArrayList<>();
    }

    public static void main(String[] args) {
        Client c = new Client("Testclient");
        c.connect("localhost", 5065);
        c.sendMsgToServer("Testnachricht 123");
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

                                    default:
                                        System.out.println("Command wurde nicht erkannt");
                                        break;
                                }

                            } else if(msg.contains("/sysCmd clientNames")) {
                                //Saves the names from the server in its list.
                                clientNames.clear();

                                String nameString = msg.split("\\{")[1].split("}")[0];

                                for(String name:nameString.split(";")) {
                                    clientNames.add(name);
                                }

                            } else {
                                //If the message isn't a systemcommand it will be displayed in the chat
                                writeMsgToGUI(msg);
                            }
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

    public void sendMsgToServer(String msg) {
        try {
            dout.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendDirectionToServer(String dir) {
        sendMsgToServer("/gameCmd move dir:" + dir);
    }

    public void updateClientNameList() {
        sendMsgToServer("/sysCmd getClientNames");
    }

    public ArrayList<String> getClientNameList() {
        return clientNames;
    }

    private void writeMsgToGUI(String msg) {
        //TODO: Msg must be passed to the presenter
    }
}

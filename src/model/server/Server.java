package model.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.print.PageLayout;
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
import java.util.*;

public class Server {

    private int port;
    private ServerSocket serverS;
    private DataInputStream din;
    private DataOutputStream dout;
    private Client hostClient;
    private ObservableList<ClientHandler> clientList;
    private SnakeGame snakeGame;

    public Server(int port) {
        this.port = port;
        clientList = FXCollections.observableArrayList();
    }

    public ObservableList<ClientHandler> getClientList() {
        return clientList;
    }

    public static void main(String[] args) {
        Server s = new Server(5065);
        s.start();
    }

    public void setHostClient(Client hostClient) {
        this.hostClient = hostClient;
    }

    public Client getHostClient() {
        return hostClient;
    }

    public void start() {
        System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°START°°°°°°°°°°°°°°°°°°°°°°°°°°°");
        System.out.println("Server starting...");
        try {
            //Initialise serversocket
            serverS = new ServerSocket(port);
            System.out.println("Server started!");
            //Allow clients to connect and handle them in their own thread
            while (true) {
                Socket clientS = serverS.accept();
                System.out.println("A new client is connected: " + clientS);

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
            System.out.println("Server starting failed!");
        }
    }

    public void removeClientHander(ClientHandler clientT) {
        clientList.remove(clientT);
        updateAllClients();
        System.out.println("ClientHandler removed!");
    }

    public void switchDirection(Direction dir, String clientName) {
        for (Snake snake:snakeGame.getSnakes()) {
            if(snake.getPlayername().equals(clientName)) {
                snake.getSnakeHead().setDir(dir);
            }
        }
    }

    public void close() {
        for (ClientHandler ch:clientList) {
            try {
                ch.stop();
                ch.clientS.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Server closing...");
        try {
            serverS.close();
            System.out.println("Server closed!");
            System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°END°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server closing failed!");
        }
    }

    public void sendToAllHandler(String msg) {
        System.out.println("Received message: " + msg);

        for (int i = 0; i < clientList.size(); i++) {
            clientList.get(i).sendToClient(msg);
        }
    }

    public void updateAllClients() {
        for (int i = 0; i < clientList.size(); i++) {
            clientList.get(i).sendNewClientNames();
        }
    }

    public void startSnakeGame(PlaygroundPresenter playgroundPresenter) throws JsonProcessingException {
        snakeGame = new SnakeGame(this, playgroundPresenter);
        System.out.println("New SnakeGame started");
    }

    public void unreadyAll() {
        for(ClientHandler ch : clientList) {
            ch.setRdy(false);
            ch.sendToClient("/sysCmd rdyCancelled");
        }

        hostClient.sendMsgToServer("/clientInf readyInformaton value:true");
    }

    public SnakeGame getSnakeGame() {
        return snakeGame;
    }
}
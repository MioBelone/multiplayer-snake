package presenter;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.client.Client;
import model.game.Loop;
import model.game.SnakeGame;
import model.server.ClientHandler;
import model.server.Server;
import view.*;
import javafx.stage.Stage;
import model.InitialModel;
import java.net.InetAddress;

import java.net.UnknownHostException;

/**
 * This class is the connection between the view and the model. It handles user input and prepares data to be shown
 * in the view.
 *
 * @author Fabian Haese
 */
@SuppressWarnings("FieldCanBeLocal")
public class LobbyHostPresenter implements LobbyPresenter {

    private LobbyHost view;
    private InitialModel initialModel;
    private Stage primaryStage;
    private Client client;
    private Server server;
    private ObservableList<String> clientNameList ;
    private Loop loop;
    private PlaygroundPresenter playgroundPresenter;

    public LobbyHostPresenter(Stage primaryStage, InitialModel initialModel, Client client, Server server) {

        this.primaryStage = primaryStage;
        this.initialModel = initialModel;
        this.client = client;
        this.server = server;
        this.view = new LobbyHost();

        clientNameList = client.getClientNameList();
        view.getPlayerList().setItems(clientNameList);

        clientNameList.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                view.getPlayerList().setItems(clientNameList);
            }
        });

        //Initialise ip-address
        try {
            view.getLblLobbyIP().setText("IP-Adresse:\n"+InetAddress.getLocalHost().getHostAddress()+"");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        //Handlers for events on view
        view.getBtnStart().setOnAction(new BtnStartEventHandler());
        view.getBtnLeave().setOnAction(new BtnLeaveEventHandler());
        view.getBtnSend().setOnAction(new BtnSendEventHandler());
        view.getBtnSettSave().setOnAction(new BtnSettSaveEventHandler());
        view.getBtnSettDefault().setOnAction(new BtnSettDefaultEventHandler());
    }

    public LobbyHostPresenter() {
    }

    public void writeMsg(String msg) {
        view.getTaChat().appendText(msg);
    }

    private void hostGame() {
        boolean isEveryoneRdy = true;

        for (ClientHandler clientH : server.getClientList()) {
            if(!clientH.isRdy()) {
                isEveryoneRdy = false;
            }
        }

        if(isEveryoneRdy) {
            playgroundPresenter = new PlaygroundPresenter(primaryStage, client, this);

            try {
                server.sendToAllHandler("/gameCmd start");
                server.startSnakeGame(playgroundPresenter);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            loop = new Loop(server.getSnakeGame(), playgroundPresenter);
            loop.start();

            //All clients must be set to not ready
            server.unreadyAll();

        } else {
            String alertMsg = "Sie können das Spiel erst starten, wenn alle Spieler in der Lobby bereit sind. Prüfen Sie ob alle bereit sind und versuchen Sie es anschließend erneut.";
            Alert alert = new Alert(Alert.AlertType.INFORMATION, alertMsg, ButtonType.OK);
            alert.setTitle("Information");
            alert.setHeaderText("Es sind noch nicht alle Spieler bereit!");
            alert.showAndWait();
        }

    }

    public void startGame() {
        playgroundPresenter.show();
    }

    //This method must be implemented because of the interface
    public void ready(boolean isRdy) {
    }

    /**
     * In this method the .show method of the view is called to display the view to the user.
     */
    public void show() {
        view.show(primaryStage);
    }

    public void closeLobby() {
        server.close();

        InitialModel initialModel = new InitialModel();

        InitialViewPresenter initialViewPresenter = new InitialViewPresenter(primaryStage, initialModel);
        initialViewPresenter.show();
    }

    //**********************************************************************
    // Events
    //**********************************************************************

    class BtnStartEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            hostGame();
        }
    }

    class BtnLeaveEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            closeLobby();
        }
    }

    class BtnSendEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            //Send Msg to Server
            client.sendMsgToServer(view.getTfChatInput().getText());
            view.getTfChatInput().setText("");
        }
    }

    class BtnSettSaveEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            //TODO: Validation

            ///Setting custom game settings
            SnakeGame.setFieldSize(Integer.parseInt(view.getTfFieldSize().getText()));
            SnakeGame.setGameSpeed(Integer.parseInt(view.getTfGameSpeed().getText()));
            SnakeGame.setFoodCount(Integer.parseInt(view.getTfFoodCount().getText()));
        }
    }

    class BtnSettDefaultEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            //Clearing custom setting fields
            view.getTfFieldSize().setText("");
            view.getTfGameSpeed().setText("");
            view.getTfFoodCount().setText("");

            //Setting standad game settings
            SnakeGame.setSettingsToDefault();
        }
    }

    public ObservableList<String> getPlayerList() {
        return clientNameList;
    }
}

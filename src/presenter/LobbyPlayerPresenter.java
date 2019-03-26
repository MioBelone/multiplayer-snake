package presenter;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import model.client.Client;
import model.server.ClientHandler;
import view.*;
import javafx.stage.Stage;
import model.InitialModel;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the connection between the view and the model. It handles user input and prepares data to be shown
 * in the view.
 *
 * @author Fabian Haese
 */
public class LobbyPlayerPresenter implements LobbyPresenter {

    private LobbyPlayer view;
    private InitialModel initialModel;
    private Stage primaryStage;
    private Client client;
    private ObservableList<String> clientNameList;

    public LobbyPlayerPresenter(Stage primaryStage, InitialModel initialModel, Client client) {
        this.primaryStage = primaryStage;
        this.initialModel = initialModel;
        this.client = client;
        this.view = new LobbyPlayer();

        clientNameList = client.getClientNameList();
        view.getPlayerList().setItems(clientNameList);

        clientNameList.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                view.getPlayerList().setItems(clientNameList);
            }
        });

        //Handlers for events on view
        view.getBtnStart().setOnAction(new BtnStartEventHandler());
        view.getBtnSend().setOnAction(new BtnSendEventHandler());
    }

    public LobbyPlayerPresenter() {
    }

    public void writeMsg(String msg) {
        view.getTaChat().appendText(msg);
    }

    /**
     * In this method the .show method of the view is called to display the view to the user.
     */
    public void show() {
        view.show(primaryStage);
    }

    //**********************************************************************
    // Events
    //**********************************************************************

    class BtnStartEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            PlaygroundPresenter playgroundPresenter = new PlaygroundPresenter(primaryStage);
            playgroundPresenter.show();
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

    public ObservableList<String> getPlayerList() {
        return clientNameList;
    }
}
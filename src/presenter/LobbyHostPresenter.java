package presenter;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.client.Client;
import model.server.ClientHandler;
import model.server.Server;
import view.*;
import javafx.stage.Stage;
import model.InitialModel;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the connection between the view and the model. It handles user input and prepares data to be shown
 * in the view.
 *
 * @author Fabian Haese
 */
public class LobbyHostPresenter {

    private LobbyHost view;
    private InitialModel initialModel;
    private Stage primaryStage;
    private Server server;
    public ObservableList<ClientHandler> clientList;

    public LobbyHostPresenter(Stage primaryStage, InitialModel initialModel, Server server) {

        this.primaryStage = primaryStage;
        this.initialModel = initialModel;
        this.server = server;
        this.view = new LobbyHost();

        clientList = server.getClientList();
        view.getPlayerList().setItems(clientList);

        clientList.addListener(new ListChangeListener<ClientHandler>() {
            @Override
            public void onChanged(Change<? extends ClientHandler> c) {
                view.getPlayerList().setItems(clientList);
            }
        });

        //Handlers for events on view
        view.getBtnStart().setOnAction(new BtnStartEventHandler());
    }

    public LobbyHostPresenter() {
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

    public ObservableList<ClientHandler> getPlayerList() {
        return clientList;
    }
}

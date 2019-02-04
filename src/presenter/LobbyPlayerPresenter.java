package presenter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.client.Client;
import model.server.ClientHandler;
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
public class LobbyPlayerPresenter {

    private LobbyPlayer view;
    private InitialModel initialModel;
    private Stage primaryStage;
    private Client client;

    private List<String> myplayer = new ArrayList<String>();

    public static ObservableList<String> oPlayer = FXCollections.observableArrayList();

    public LobbyPlayerPresenter(Stage primaryStage, InitialModel initialModel, Client client) {
        this.primaryStage = primaryStage;
        this.initialModel = initialModel;
        this.client = client;
        this.view = new LobbyPlayer();

        updateList();

        //Handlers for events on view
        view.getBtnStart().setOnAction(new BtnStartEventHandler());
    }

    public LobbyPlayerPresenter() {
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

    public ObservableList initiateList() {
        return oPlayer;
    }

    public void updateList(){
        client.updateClientNameList();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        oPlayer.addAll(client.getClientNameList());
    }
}
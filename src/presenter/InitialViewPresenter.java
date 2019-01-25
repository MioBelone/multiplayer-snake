package presenter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.*;
import javafx.stage.Stage;
import model.InitialModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is the connection between the view and the model. It handles user input and prepares data to be shown
 * in the view.
 *
 * @author Maximilian Gräfe, Fabian Haese
 */
public class InitialViewPresenter {

    private InitialViewLobbyHost view;
    private InitialModel initialModel;
    private Stage primaryStage;

    private List<String> myplayer = new ArrayList<String>();

    public static final ObservableList<String> oPlayer = FXCollections.observableArrayList();

    public InitialViewPresenter(Stage primaryStage, InitialModel initialModel) {
        this.primaryStage = primaryStage;
        this.initialModel = initialModel;
        this.view = new InitialViewLobbyHost();

        //Handlers for events on view
        view.getBtnHostLobby().setOnAction(new BtnHostLobbyEventHandler());
        view.getBtnJoinLobby().setOnAction(new BtnJoinLobbyEventHandler());
    }

    public InitialViewPresenter(){}

    /**
     * In this method the .show method of the view is called to display the view to the user.
     */
    public void show() {
        view.show(primaryStage);
    }

    //**********************************************************************
    // Events
    //**********************************************************************

    class BtnHostLobbyEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            initialModel.hostGame("", 0);
        }
    }

    class BtnJoinLobbyEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            initialModel.joinGame("", 0, "");
        }
    }

    public ObservableList fillList() {
        myplayer.add("Ein Thorsten Legat");
        myplayer.add("Manfred");
        oPlayer.addAll(myplayer);
        return oPlayer;
    }
}

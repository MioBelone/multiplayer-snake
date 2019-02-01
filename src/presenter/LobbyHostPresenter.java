package presenter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

    private List<String> myplayer = new ArrayList<String>();

    public static final ObservableList<String> oPlayer = FXCollections.observableArrayList();

    public LobbyHostPresenter(Stage primaryStage, InitialModel initialModel) {
        this.primaryStage = primaryStage;
        this.initialModel = initialModel;
        this.view = new LobbyHost();

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

    public ObservableList fillList() {
        myplayer.add("Ein Thorsten Legat");
        myplayer.add("Manfred");
        oPlayer.addAll(myplayer);
        return oPlayer;
    }
}

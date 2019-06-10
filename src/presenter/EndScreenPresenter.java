package presenter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.InitialModel;
import view.EndScreen;


/**
 * This class is the connection between the view and the model. It handles user input and prepares data to be shown
 * in the view.
 *
 * @author Fabian Haese
 */
public class EndScreenPresenter {
    private EndScreen view;
    private InitialModel initialModel;
    private Stage primaryStage;
    private LobbyPresenter lobby;

    public EndScreenPresenter(Stage primaryStage, InitialModel initialModel, LobbyPresenter lobby) {
        this.primaryStage = primaryStage;
        this.initialModel = initialModel;
        this.view = new EndScreen();
        this.lobby = lobby;

        //Handlers for events on view
        view.getBtnReturnLobby().setOnAction(new BtnReturnLobby());
    }

    public EndScreenPresenter() {
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

    class BtnReturnLobby implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            returnToLobby();
        }
    }

    private void returnToLobby() {
        lobby.show();
    }
}

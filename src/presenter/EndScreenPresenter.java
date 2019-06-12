package presenter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.InitialModel;
import model.client.Client;
import model.game.SnakeContents.Snake;
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
    private Client client;

    public EndScreenPresenter(Stage primaryStage, InitialModel initialModel, LobbyPresenter lobby, Client client) {
        this.primaryStage = primaryStage;
        this.initialModel = initialModel;
        this.view = new EndScreen();
        this.lobby = lobby;
        this.client = client;

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

    public void showWinningPoints() {
        int points = 0;
        String winner = "";
        for (Snake snake : client.getSnakes()) {
            if (snake.getScore() >= points) {
                points = snake.getScore();
                winner = snake.getPlayername();
            }
        }
        view.getlWinner().setText("Gewonnen hat " + winner + "\nmit " + points + " Punkten");
    }

    private void returnToLobby() {
        lobby.show();
    }
}

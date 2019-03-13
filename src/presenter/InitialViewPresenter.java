package presenter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.client.Client;
import model.server.Server;
import view.*;
import javafx.stage.Stage;
import model.InitialModel;


/**
 * This class is the connection between the view and the model. It handles user input and prepares data to be shown
 * in the view.
 *
 * @author Maximilian Gräfe, Fabian Haese
 */
public class InitialViewPresenter {

    private InitialView view;
    private InitialModel initialModel;
    private Stage primaryStage;

    public InitialViewPresenter(Stage primaryStage, InitialModel initialModel) {
        this.primaryStage = primaryStage;
        this.initialModel = initialModel;
        this.view = new InitialView();

        //Handlers for events on view
        view.getBtnHostLobby().setOnAction(new BtnHostLobbyEventHandler());
        view.getBtnJoinLobby().setOnAction(new BtnJoinLobbyEventHandler());
    }

    public InitialViewPresenter() {
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

    class BtnHostLobbyEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            Server server = initialModel.hostGame(view.getTfUserNameHost().getText(), Integer.parseInt(view.getTfPortHost().getText()));
            Client client = server.getHostClient();

            //Initialising the LobbyHost presenter which handles the view and the model
            LobbyHostPresenter lobbyHostPresenter = new LobbyHostPresenter(primaryStage, initialModel, client);
            client.setPresenter(lobbyHostPresenter);
            lobbyHostPresenter.show();
        }
    }

    class BtnJoinLobbyEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            Client client = initialModel.joinGame(view.getTfUserNameJoin().getText(), Integer.parseInt(view.getTfPortJoin().getText()), "localhost");

            //Initialising the LobbyPlayer presenter which handles the view and the model
            LobbyPlayerPresenter lobbyPlayerPresenter = new LobbyPlayerPresenter(primaryStage, initialModel, client);
            client.setPresenter(lobbyPlayerPresenter);
            lobbyPlayerPresenter.show();
        }
    }
}
package presenter;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.client.Client;
import view.*;
import javafx.stage.Stage;
import model.InitialModel;

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
        view.getBtnReady().setOnAction(new BtnReadyEventHandler());
        view.getBtnLeave().setOnAction(new BtnLeaveEventHandler());
        view.getBtnSend().setOnAction(new BtnSendEventHandler());
    }

    public LobbyPlayerPresenter() {
    }

    public void writeMsg(String msg) {
        view.getTaChat().appendText(msg);
    }

    public void startGame() {
        PlaygroundPresenter playgroundPresenter = new PlaygroundPresenter(primaryStage, client, this);
        playgroundPresenter.show();
    }

    /**
     * In this method the .show method of the view is called to display the view to the user.
     */
    public void show() {
        view.show(primaryStage);
    }

    public void closeLobby() {
        client.close();

        InitialModel initialModel = new InitialModel();

        InitialViewPresenter initialViewPresenter = new InitialViewPresenter(primaryStage, initialModel);
        initialViewPresenter.show();
    }

    public void ready(boolean isRdy) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(isRdy) {
                    view.getBtnReady().setText("Nicht bereit");
                    view.getLblCurrStatus().getStyleClass().clear();
                    view.getLblCurrStatus().getStyleClass().addAll("label", "label-ready");
                    view.getLblCurrStatus().setText("Bereit");
                } else {
                    view.getBtnReady().setText("Bereit");
                    view.getLblCurrStatus().getStyleClass().clear();
                    view.getLblCurrStatus().getStyleClass().addAll("label", "label-not-ready");
                    view.getLblCurrStatus().setText("Nicht bereit");
                }
            }
        });
    }

    //**********************************************************************
    // Events
    //**********************************************************************

    class BtnReadyEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            if(view.getBtnReady().getText().equals("Bereit")) {
                client.sendMsgToServer("/clientInf readyInformation value:true");
            } else if(view.getBtnReady().getText().equals("Nicht bereit")) {
                client.sendMsgToServer("/clientInf readyInformation value:false");
            }
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

    public ObservableList<String> getPlayerList() {
        return clientNameList;
    }
}
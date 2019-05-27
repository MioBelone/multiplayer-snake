package view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import presenter.LobbyHostPresenter;

/**
 * This class is the view which the user can see after the .show method is called.
 *
 * @author Fabian Haese
 */
public class LobbyHost {

    private Scene scene;

    LobbyHostPresenter presenter = new LobbyHostPresenter();

    private GridPane grid;
    private GridPane gridChatInput;
    private TabPane tabPane;
    private BorderPane bPaneHost;
    private BorderPane bPaneSettings;
    private VBox vboxPlayerList;
    private VBox vBoxHostContent;
    private VBox vboxButtons;
    private AnchorPane apBrand;
    private StackPane spLobbyIp;

    private Tab tabHost;
    private Tab tabSettings;

    private Label lblHostHead;
    private Label lblBrandInfoHead;
    private Label lblSettingsHead;
    private Label lblLobbyIP;

    private TextArea taChat;

    private TextField tfChatInput;

    private Button btnStart;
    private Button btnLeave;
    private Button btnChatSend;

    private ListView playerList;


    public LobbyHost() {

        //Main container as GridPane
        grid = new GridPane();
        grid.getStyleClass().add("grid-pane-root");

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(20);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(80);
        grid.getColumnConstraints().addAll(col1, col2);

        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(100);
        grid.getRowConstraints().add(row1);

        //TabPane and Tabs
        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        grid.add(tabPane, 1, 0);

        tabHost = new Tab("Lobby");
        tabPane.getTabs().add(tabHost);

        tabSettings = new Tab("Einstellungen");
        tabPane.getTabs().add(tabSettings);

        //Main container in tabs
        bPaneHost = new BorderPane();
        bPaneHost.setPadding(new Insets(20));
        tabHost.setContent(bPaneHost);

        bPaneSettings = new BorderPane();
        bPaneSettings.setPadding(new Insets(20));
        tabSettings.setContent(bPaneSettings);

        //Container for specific content
        vBoxHostContent = new VBox();
        vBoxHostContent.setPadding(new Insets(40));
        vBoxHostContent.setSpacing(40);
        bPaneHost.setCenter(vBoxHostContent);

        gridChatInput = new GridPane();
        gridChatInput.setPadding(new Insets(20, 0, 0, 0));
        ColumnConstraints colChat1 = new ColumnConstraints();
        colChat1.setPercentWidth(85);
        ColumnConstraints colChat2 = new ColumnConstraints();
        colChat2.setPercentWidth(15);
        colChat2.setHalignment(HPos.RIGHT);
        gridChatInput.getColumnConstraints().addAll(colChat1, colChat2);
        bPaneHost.setBottom(gridChatInput);

        //Content for HostTab
        lblHostHead = new Label("Host");
        lblHostHead.getStyleClass().add("label-head");
        bPaneHost.setTop(lblHostHead);

        tfChatInput = new TextField();
        gridChatInput.add(tfChatInput, 0, 0);

        btnChatSend = new Button("Senden");
        btnChatSend.setDefaultButton(true);
        gridChatInput.add(btnChatSend, 1, 0);

        taChat = new TextArea();
        taChat.setEditable(false);
        bPaneHost.setCenter(taChat);

        //Content for SettingsTab
        lblSettingsHead = new Label("Einstellungen");
        lblSettingsHead.getStyleClass().add("label-head");
        bPaneSettings.setTop(lblSettingsHead);

        //BrandBox for logo and short info text
        apBrand = new AnchorPane();
        apBrand.getStyleClass().add("brand-box");
        apBrand.setMinWidth(0);
        grid.add(apBrand, 0, 0);

        //Content for BrandBox
        vboxPlayerList = new VBox();
        vboxPlayerList.setAlignment(Pos.TOP_CENTER);
        vboxPlayerList.setSpacing(40);
        vboxPlayerList.prefWidthProperty().bind(apBrand.widthProperty());
        apBrand.getChildren().add(vboxPlayerList);
        apBrand.setTopAnchor(vboxPlayerList, 10.0);

        lblBrandInfoHead = new Label("Spieler");
        lblBrandInfoHead.getStyleClass().add("label-head");
        vboxPlayerList.getChildren().add(lblBrandInfoHead);

        playerList = new ListView();
        playerList.setMouseTransparent(true);
        playerList.setFocusTraversable(false);
        vboxPlayerList.getChildren().add(playerList);

        vboxButtons = new VBox();
        vboxButtons.prefWidthProperty().bind(apBrand.widthProperty());
        apBrand.getChildren().add(vboxButtons);
        apBrand.setBottomAnchor(vboxButtons, 0.0);

        //Label for lobby IP-Address
        lblLobbyIP = new Label("IP-Adresse:\n");
        lblLobbyIP.setStyle("-fx-font-weight: bold");
        lblLobbyIP.setPadding(new Insets(5, 5, 15, 5));
        lblLobbyIP.setTextAlignment(TextAlignment.CENTER);

        spLobbyIp = new StackPane();
        spLobbyIp.getChildren().add(lblLobbyIP);
        spLobbyIp.prefWidthProperty().bind(vboxButtons.widthProperty());
        vboxButtons.getChildren().add(spLobbyIp);

        //Buttons
        btnStart = new Button("Start Game");
        btnStart.prefWidthProperty().bind(vboxButtons.widthProperty());
        vboxButtons.getChildren().add(btnStart);

        btnLeave = new Button("Close Lobby");
        btnLeave.prefWidthProperty().bind(vboxButtons.widthProperty());
        vboxButtons.getChildren().add(btnLeave);

        //Inititalising scene
        scene = new Scene(grid, 1000, 700);
        scene.getStylesheets().add("/resources/style.css");
    }

    /**
     * In this method the title is set and the stage is displayed to the user.
     *
     * @param stage
     */
    public void show(Stage stage) {
        stage.setTitle("SnakeBasket");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Getter
     *
     * @return the button in the methods name.
     */
    public Button getBtnStart() {
        return btnStart;
    }

    public Button getBtnLeave() { return btnLeave; }

    public Button getBtnSend() { return btnChatSend; }

    public TextField getTfChatInput() { return tfChatInput; }

    public TextArea getTaChat() { return taChat; }

    public ListView getPlayerList() {
        return playerList;
    }

    public Label getLblLobbyIP() {
        return lblLobbyIP;
    }
}

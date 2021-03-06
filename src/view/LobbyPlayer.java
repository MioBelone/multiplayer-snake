package view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import presenter.LobbyPlayerPresenter;

/**
 * This class is the view which the user can see after the .show method is called.
 *
 * @author Fabian Haese
 */
@SuppressWarnings({"ALL", "FieldCanBeLocal"})
public class LobbyPlayer {

    private Scene scene;

    LobbyPlayerPresenter presenter = new LobbyPlayerPresenter();

    private GridPane grid;
    private GridPane gridChatInput;
    private TabPane tabPane;
    private BorderPane bPaneJoin;
    private BorderPane bPaneSettings;
    private VBox vboxPlayerList;
    private VBox vBoxJoinContent;
    private VBox vboxButtons;
    private VBox vbStatus;
    private AnchorPane apBrand;
    private StackPane spStatus;

    private Tab tabPlayer;
    private Tab tabSettings;

    private Label lblPlayerHead;
    private Label lblBrandInfoHead;
    private Label lblSettingsHead;
    private Label lblStatus;
    private Label lblCurrStatus;

    private TextArea taChat;

    private TextField tfChatInput;

    private Button btnReady;
    private Button btnLeave;
    private Button btnChatSend;

    private ListView playerList;

    public LobbyPlayer() {

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

        tabPlayer = new Tab("Lobby");
        tabPane.getTabs().add(tabPlayer);

        tabSettings = new Tab("Einstellungen");
        tabPane.getTabs().add(tabSettings);

        //Main container in tabs
        bPaneJoin = new BorderPane();
        bPaneJoin.setPadding(new Insets(20));
        tabPlayer.setContent(bPaneJoin);

        bPaneSettings = new BorderPane();
        bPaneSettings.setPadding(new Insets(20));
        tabSettings.setContent(bPaneSettings);

        //Container for specific content
        vBoxJoinContent = new VBox();
        vBoxJoinContent.setPadding(new Insets(40));
        vBoxJoinContent.setSpacing(40);
        bPaneJoin.setCenter(vBoxJoinContent);

        gridChatInput = new GridPane();
        gridChatInput.setPadding(new Insets(20, 0, 0, 0));
        ColumnConstraints colChat1 = new ColumnConstraints();
        colChat1.setPercentWidth(85);
        ColumnConstraints colChat2 = new ColumnConstraints();
        colChat2.setPercentWidth(15);
        colChat2.setHalignment(HPos.RIGHT);
        gridChatInput.getColumnConstraints().addAll(colChat1, colChat2);
        bPaneJoin.setBottom(gridChatInput);

        //Content for HostTab
        lblPlayerHead = new Label("Spieler");
        lblPlayerHead.getStyleClass().add("label-head");
        bPaneJoin.setTop(lblPlayerHead);

        tfChatInput = new TextField();
        gridChatInput.add(tfChatInput, 0, 0);

        btnChatSend = new Button("Senden");
        btnChatSend.setDefaultButton(true);
        gridChatInput.add(btnChatSend, 1, 0);

        taChat = new TextArea();
        taChat.setEditable(false);
        bPaneJoin.setCenter(taChat);

        //Content for JoinTab
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
        AnchorPane.setTopAnchor(vboxPlayerList, 10.0);

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
        AnchorPane.setBottomAnchor(vboxButtons, 0.0);

        //Statusanzeige
        spStatus = new StackPane();
        spStatus.prefWidthProperty().bind(vboxButtons.widthProperty());
        vboxButtons.getChildren().add(spStatus);

        vbStatus = new VBox();
        vbStatus.setPadding(new Insets(5, 5, 15, 5));
        vbStatus.setAlignment(Pos.CENTER);
        vbStatus.prefWidthProperty().bind(spStatus.widthProperty());
        spStatus.getChildren().add(vbStatus);

        lblStatus = new Label("Status:");
        vbStatus.getChildren().add(lblStatus);

        lblCurrStatus = new Label("Nicht bereit");
        lblCurrStatus.getStyleClass().add("label-not-ready");
        vbStatus.getChildren().add(lblCurrStatus);

        btnReady = new Button("Bereit");
        btnReady.prefWidthProperty().bind(vboxPlayerList.widthProperty());
        vboxButtons.getChildren().add(btnReady);

        btnLeave = new Button("Verlasse Lobby");
        btnLeave.prefWidthProperty().bind(vboxPlayerList.widthProperty());
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
    public Button getBtnReady() {
        return btnReady;
    }

    public Button getBtnLeave() { return btnLeave; }

    public Button getBtnSend() { return btnChatSend; }

    public TextField getTfChatInput() { return tfChatInput; }

    public TextArea getTaChat() { return taChat; }

    public ListView getPlayerList() {
        return playerList;
    }

    public Label getLblCurrStatus() {
        return lblCurrStatus;
    }
}

package view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
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
    private GridPane gridSettings;
    private TabPane tabPane;
    private BorderPane bPaneHost;
    private BorderPane bPaneSettings;
    private VBox vboxPlayerList;
    private VBox vBoxHostContent;
    private VBox vboxButtons;
    private VBox vboxFieldSize;
    private VBox vboxGameSpeed;
    private VBox vboxFoodCount;
    private HBox hboxSettBtn;
    private AnchorPane apBrand;
    private StackPane spLobbyIp;

    private Tab tabHost;
    private Tab tabSettings;

    private Label lblHostHead;
    private Label lblBrandInfoHead;
    private Label lblSettingsHead;
    private Label lblLobbyIP;
    private Label lblFieldSize;
    private Label lblGameSpeed;
    private Label lblFoodCount;
    private Label lblErrFieldSize;
    private Label lblErrGameSpeed;
    private Label lblErrFoodCount;
    private Label lblNotice;

    private TextArea taChat;

    private TextField tfChatInput;
    private TextField tfFieldSize;
    private TextField tfGameSpeed;
    private TextField tfFoodCount;

    private Button btnStart;
    private Button btnLeave;
    private Button btnChatSend;
    private Button btnSettSave;
    private Button btnSettDefault;

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

        gridSettings = new GridPane();
        gridSettings.setPadding(new Insets(50, 10, 10, 10));
        gridSettings.setVgap(50);
        gridSettings.setHgap(50);

        ColumnConstraints colS1 = new ColumnConstraints();
        colS1.setPercentWidth(50);
        ColumnConstraints colS2 = new ColumnConstraints();
        colS2.setPercentWidth(50);
        gridSettings.getColumnConstraints().addAll(colS1, colS2);

        bPaneSettings.setCenter(gridSettings);

        hboxSettBtn = new HBox();
        hboxSettBtn.setSpacing(20);
        hboxSettBtn.setAlignment(Pos.CENTER_LEFT);
        bPaneSettings.setBottom(hboxSettBtn);

        btnSettSave = new Button("Speichern");
        hboxSettBtn.getChildren().add(btnSettSave);

        btnSettDefault = new Button("Standard");
        hboxSettBtn.getChildren().add(btnSettDefault);

        lblNotice = new Label();
        lblNotice.getStyleClass().add("label-success");
        hboxSettBtn.getChildren().add(lblNotice);

        vboxFieldSize = new VBox();
        gridSettings.add(vboxFieldSize, 0, 0);

        vboxGameSpeed = new VBox();
        gridSettings.add(vboxGameSpeed, 1, 0);

        vboxFoodCount = new VBox();
        gridSettings.add(vboxFoodCount, 0, 1);

        lblFieldSize = new Label("Feldgröße:");
        lblFieldSize.getStyleClass().add("label-focus");
        vboxFieldSize.getChildren().add(lblFieldSize);

        tfFieldSize = new TextField();
        tfFieldSize.setPromptText("Bsp.: 100 -> 100x100");
        vboxFieldSize.getChildren().add(tfFieldSize);

        lblErrFieldSize = new Label();
        lblErrFieldSize.getStyleClass().add("label-error");
        vboxFieldSize.getChildren().add(lblErrFieldSize);

        lblGameSpeed = new Label("Spielgeschwindigkeit:");
        lblGameSpeed.getStyleClass().add("label-focus");
        vboxGameSpeed.getChildren().add(lblGameSpeed);

        tfGameSpeed = new TextField();
        tfGameSpeed.setPromptText("Bsp.: 200 -> 200ms pro Bewegung");
        vboxGameSpeed.getChildren().add(tfGameSpeed);

        lblErrGameSpeed = new Label();
        lblErrGameSpeed.getStyleClass().add("label-error");
        vboxGameSpeed.getChildren().add(lblErrGameSpeed);

        lblFoodCount = new Label("Food pro Spieler:");
        lblFoodCount.getStyleClass().add("label-focus");
        vboxFoodCount.getChildren().add(lblFoodCount);

        tfFoodCount = new TextField();
        tfFoodCount.setPromptText("Bsp.: 2 -> Pro Spieler erscheinen 2 Foods");
        vboxFoodCount.getChildren().add(tfFoodCount);

        lblErrFoodCount = new Label();
        lblErrFoodCount.getStyleClass().add("label-error");
        vboxFoodCount.getChildren().add(lblErrFoodCount);

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
        btnStart = new Button("Starte Spiel");
        btnStart.prefWidthProperty().bind(vboxButtons.widthProperty());
        vboxButtons.getChildren().add(btnStart);

        btnLeave = new Button("Schließe Lobby");
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
     * @return the node in the methods name.
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

    public TextField getTfFieldSize() {
        return tfFieldSize;
    }

    public TextField getTfGameSpeed() {
        return tfGameSpeed;
    }

    public TextField getTfFoodCount() {
        return tfFoodCount;
    }

    public Button getBtnSettSave() {
        return btnSettSave;
    }

    public Button getBtnSettDefault() {
        return btnSettDefault;
    }

    public Label getLblErrFieldSize() {
        return lblErrFieldSize;
    }

    public Label getLblErrGameSpeed() {
        return lblErrGameSpeed;
    }

    public Label getLblErrFoodCount() {
        return lblErrFoodCount;
    }

    public Label getLblNotice() {
        return lblNotice;
    }
}

package view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.server.ClientHandler;
import presenter.LobbyPlayerPresenter;

/**
 * This class is the view which the user can see after the .show method is called.
 *
 * @author Fabian Haese
 */
public class LobbyPlayer {

    private Scene scene;

    LobbyPlayerPresenter presenter = new LobbyPlayerPresenter();

    private GridPane grid;
    private GridPane gridChatInput;
    private TabPane tabPane;
    private BorderPane bPaneJoin;
    private BorderPane bPaneSettings;
    private VBox vboxBrand;
    private VBox vBoxJoinContent;

    private Tab tabPlayer;
    private Tab tabSettings;

    private Label lblPlayerHead;
    private Label lblBrandLogo;
    private Label lblBrandInfoHead;
    private Label lblSettingsHead;

    private TextArea taChat;

    private TextField tfChatInput;

    private Button btnStart;
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
        gridChatInput.add(btnChatSend, 1, 0);

        taChat = new TextArea();
        taChat.setEditable(false);
        bPaneJoin.setCenter(taChat);

        //Content for JoinTab
        lblSettingsHead = new Label("Einstellungen");
        lblSettingsHead.getStyleClass().add("label-head");
        bPaneJoin.setTop(lblSettingsHead);

        //BrandBox for logo and short info text
        vboxBrand = new VBox();
        vboxBrand.getStyleClass().add("brand-box");
        vboxBrand.setAlignment(Pos.TOP_CENTER);
        vboxBrand.setPadding(new Insets(20, 0, 20, 0));
        vboxBrand.setSpacing(40);
        grid.add(vboxBrand, 0, 0);

        //Content for BrandBox
        lblBrandLogo = new Label("<Ihr Logo>");
        lblBrandLogo.getStyleClass().add("label-head");
        vboxBrand.getChildren().add(lblBrandLogo);

        lblBrandInfoHead = new Label("Spieler");
        lblBrandInfoHead.getStyleClass().add("label-head");
        vboxBrand.getChildren().add(lblBrandInfoHead);

        playerList = new ListView();
        playerList.setMouseTransparent(true);
        playerList.setFocusTraversable(false);
        vboxBrand.getChildren().add(playerList);

        btnStart = new Button("Start Game");
        btnStart.prefWidthProperty().bind(vboxBrand.widthProperty());
        vboxBrand.getChildren().add(btnStart);

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

    public Button getBtnSend() { return btnChatSend; }

    public TextField getTfChatInput() { return tfChatInput; }

    public TextArea getTaChat() { return taChat; }

    public ListView getPlayerList() {
        return playerList;
    }
}

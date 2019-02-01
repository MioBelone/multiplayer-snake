package view;

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
public class LobbyPlayer {

    private Scene scene;

    LobbyPlayerPresenter presenter = new LobbyPlayerPresenter();

    private GridPane grid;
    private TabPane tabPane;
    private BorderPane bPaneHost;
    private BorderPane bPaneJoin;
    private VBox vboxBrand;
    private VBox vBoxHostContent;
    private VBox vBoxJoinContent;

    private Tab tabPlayer;
    private Tab tabSettings;

    private Label lblPlayerHead;
    private Label lblBrandLogo;
    private Label lblBrandInfoHead;
    private Label lblChat;

    private Label lblSettingsHead;

    private Button btnStart;

    private ListView playerList = new ListView();

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
        bPaneHost = new BorderPane();
        bPaneHost.setPadding(new Insets(20));
        tabPlayer.setContent(bPaneHost);

        bPaneJoin = new BorderPane();
        bPaneJoin.setPadding(new Insets(20));
        tabSettings.setContent(bPaneJoin);

        //Container for specific content
        vBoxHostContent = new VBox();
        vBoxHostContent.setPadding(new Insets(40));
        vBoxHostContent.setSpacing(40);
        bPaneHost.setCenter(vBoxHostContent);

        vBoxJoinContent = new VBox();
        vBoxJoinContent.setPadding(new Insets(40));
        vBoxJoinContent.setSpacing(40);
        bPaneJoin.setCenter(vBoxJoinContent);

        //Content for HostTab
        lblPlayerHead = new Label("Spieler");
        lblPlayerHead.getStyleClass().add("label-head");
        bPaneHost.setTop(lblPlayerHead);

        btnStart = new Button("Spiel starten");
        bPaneHost.setBottom(btnStart);

        lblChat = new Label("<Chat>");
        lblChat.getStyleClass().add("label-head");
        bPaneHost.setCenter(lblChat);

        //Content for JoinTab
        lblSettingsHead = new Label("Einstellungen");
        lblSettingsHead.getStyleClass().add("label-head");
        bPaneJoin.setTop(lblSettingsHead);

        //BrandBox for logo and short info text
        vboxBrand = new VBox();
        vboxBrand.getStyleClass().add("brand-box");
        vboxBrand.setAlignment(Pos.TOP_CENTER);
        vboxBrand.setPadding(new Insets(20));
        vboxBrand.setSpacing(40);
        grid.add(vboxBrand, 0, 0);

        //Content for BrandBox
        lblBrandLogo = new Label("<Ihr Logo>");
        lblBrandLogo.getStyleClass().add("label-head");
        vboxBrand.getChildren().add(lblBrandLogo);

        lblBrandInfoHead = new Label("Spieler");
        lblBrandInfoHead.getStyleClass().add("label-head");
        vboxBrand.getChildren().add(lblBrandInfoHead);

        playerList.setItems(presenter.fillList());
        playerList.setMouseTransparent(true);
        playerList.setFocusTraversable(false);
        vboxBrand.getChildren().add(playerList);

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
}

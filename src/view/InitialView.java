package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * This class is the view which the user can see after the .show method is called.
 *
 * @author Maximilian Gräfe
 */
public class InitialView {

    private Scene scene;

    private GridPane grid;
    private TabPane tabPane;
    private BorderPane bPaneHost;
    private BorderPane bPaneJoin;
    private VBox vboxBrand;
    private VBox vBoxHostContent;
    private VBox vBoxJoinContent;

    private Tab tabHost;
    private Tab tabJoin;

    private Label lblHostHead;
    private Label lblJoinHead;
    private Label lblBrandLogo;
    private Label lblBrandText;
    private Label lblBrandInfo;

    private Button btnHostLobby;
    private Button btnJoinLobby;

    private TextField tfUserNameHost;
    private TextField tfPortHost;
    private TextField tfUserNameJoin;
    private TextField tfPortJoin;
    private TextField tfIpJoin;

    public InitialView() {

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

        tabHost = new Tab("Lobby erstellen");
        tabPane.getTabs().add(tabHost);

        tabJoin = new Tab("Lobby beitreten");
        tabPane.getTabs().add(tabJoin);

        //Main container in tabs
        bPaneHost = new BorderPane();
        bPaneHost.setPadding(new Insets(20));
        tabHost.setContent(bPaneHost);

        bPaneJoin = new BorderPane();
        bPaneJoin.setPadding(new Insets(20));
        tabJoin.setContent(bPaneJoin);

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
        lblHostHead = new Label("Lobby erstellen:");
        lblHostHead.getStyleClass().add("label-head");
        bPaneHost.setTop(lblHostHead);

        tfUserNameHost = new TextField();
        tfUserNameHost.setPromptText("Benutzername");
        vBoxHostContent.getChildren().add(tfUserNameHost);

        tfPortHost = new TextField();
        tfPortHost.setPromptText("Port (0000-9999)");
        vBoxHostContent.getChildren().add(tfPortHost);

        btnHostLobby = new Button("Erstellen");
        bPaneHost.setBottom(btnHostLobby);

        //Content for JoinTab
        lblJoinHead = new Label("Offener Lobby beitreten:");
        lblJoinHead.getStyleClass().add("label-head");
        bPaneJoin.setTop(lblJoinHead);

        tfUserNameJoin = new TextField();
        tfUserNameJoin.setPromptText("Benutzername");
        vBoxJoinContent.getChildren().add(tfUserNameJoin);

        tfPortJoin = new TextField();
        tfPortJoin.setPromptText("Port (0000-9999)");
        vBoxJoinContent.getChildren().add(tfPortJoin);

        tfIpJoin = new TextField();
        tfIpJoin.setPromptText("IP-Adresse");
        vBoxJoinContent.getChildren().add(tfIpJoin);

        btnJoinLobby = new Button("Beitreten");
        bPaneJoin.setBottom(btnJoinLobby);

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

        lblBrandText = new Label("SnakeBasket");
        lblBrandText.getStyleClass().add("label-head");
        vboxBrand.getChildren().add(lblBrandText);

        lblBrandInfo = new Label("Hier wird ein Text stehen der kurze Infos über das Spiel enthält und " +
                "eventuell noch Verlinkungen zu hilfreichen Webseiten.");
        lblBrandInfo.setTextAlignment(TextAlignment.CENTER);
        lblBrandInfo.setWrapText(true);
        vboxBrand.getChildren().add(lblBrandInfo);

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
    public Button getBtnHostLobby() {
        return btnHostLobby;
    }

    /**
     * Getter
     *
     * @return the button in the methods name.
     */
    public Button getBtnJoinLobby() {
        return btnJoinLobby;
    }

    public TextField getTfUserNameHost() {
        return tfUserNameHost;
    }

    public TextField getTfPortHost() {
        return tfPortHost;
    }

    public TextField getTfUserNameJoin() {
        return tfUserNameJoin;
    }

    public TextField getTfPortJoin() {
        return tfPortJoin;
    }
}

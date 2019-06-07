package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * This class is the view which the user can see after the .show method is called.
 *
 * @author Maximilian Gräfe
 */
@SuppressWarnings({"ALL", "FieldCanBeLocal"})
public class InitialView {

    private Scene scene;

    private GridPane grid;
    private TabPane tabPane;
    private BorderPane bPaneHost;
    private BorderPane bPaneJoin;
    private VBox vboxBrand;
    private VBox vBoxHostContent;
    @SuppressWarnings("FieldCanBeLocal")
    private VBox vBoxJoinContent;
    private VBox vBoxHostName;
    private VBox vBoxHostPort;
    private VBox vBoxJoinName;
    private VBox vBoxJoinPort;
    private VBox vBoxJoinIp;
    private StackPane spImgContainer;

    private Tab tabHost;
    private Tab tabJoin;

    private Label lblHostHead;
    private Label lblJoinHead;
    private Label lblBrandText;
    private Label lblBrandInfo;
    private Label lblHostName;
    private Label lblHostPort;
    private Label lblJoinName;
    private Label lblJoinPort;
    private Label lblJoinIp;

    private Button btnHostLobby;
    private Button btnJoinLobby;

    private TextField tfUserNameHost;
    private TextField tfPortHost;
    private TextField tfUserNameJoin;
    private TextField tfPortJoin;
    private TextField tfIpJoin;

    private Image imgLogo;

    private ImageView imgViewLogo;

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
        lblHostName = new Label();
        lblHostName.getStyleClass().add("label-error");
        vBoxHostName = new VBox();
        vBoxHostName.getChildren().addAll(tfUserNameHost, lblHostName);
        vBoxHostContent.getChildren().add(vBoxHostName);

        tfPortHost = new TextField();
        tfPortHost.setPromptText("Port (1024-61000)");
        lblHostPort = new Label();
        lblHostPort.getStyleClass().add("label-error");
        vBoxHostPort = new VBox();
        vBoxHostPort.getChildren().addAll(tfPortHost, lblHostPort);
        vBoxHostContent.getChildren().addAll(vBoxHostPort);

        btnHostLobby = new Button("Erstellen");
        btnHostLobby.setDefaultButton(true);
        bPaneHost.setBottom(btnHostLobby);

        //Content for JoinTab
        lblJoinHead = new Label("Offener Lobby beitreten:");
        lblJoinHead.getStyleClass().add("label-head");
        bPaneJoin.setTop(lblJoinHead);

        tfUserNameJoin = new TextField();
        tfUserNameJoin.setPromptText("Benutzername");
        lblJoinName = new Label();
        lblJoinName.getStyleClass().add("label-error");
        vBoxJoinName = new VBox();
        vBoxJoinName.getChildren().addAll(tfUserNameJoin, lblJoinName);
        vBoxJoinContent.getChildren().add(vBoxJoinName);

        tfPortJoin = new TextField();
        tfPortJoin.setPromptText("Port (1024-61000)");
        lblJoinPort = new Label();
        lblJoinPort.getStyleClass().add("label-error");
        vBoxJoinPort = new VBox();
        vBoxJoinPort.getChildren().addAll(tfPortJoin, lblJoinPort);
        vBoxJoinContent.getChildren().add(vBoxJoinPort);

        tfIpJoin = new TextField();
        tfIpJoin.setPromptText("IP-Adresse");
        lblJoinIp = new Label();
        lblJoinIp.getStyleClass().add("label-error");
        vBoxJoinIp = new VBox();
        vBoxJoinIp.getChildren().addAll(tfIpJoin, lblJoinIp);
        vBoxJoinContent.getChildren().add(vBoxJoinIp);

        btnJoinLobby = new Button("Beitreten");
        bPaneJoin.setBottom(btnJoinLobby);

        //BrandBox for logo and short info text
        vboxBrand = new VBox();
        vboxBrand.getStyleClass().add("brand-box");
        vboxBrand.setAlignment(Pos.TOP_CENTER);
        vboxBrand.setSpacing(40);
        vboxBrand.setPadding(new Insets(30, 10, 0, 10));
        grid.add(vboxBrand, 0, 0);

        //Content for BrandBox
        imgLogo = new Image("resources/images/SnakeBasketLogo.png", true);
        imgViewLogo = new ImageView();
        imgViewLogo.setImage(imgLogo);
        imgViewLogo.setPreserveRatio(true);
        spImgContainer = new StackPane(imgViewLogo);
        spImgContainer.setMinHeight(0);
        spImgContainer.setMinWidth(0);
        imgViewLogo.fitWidthProperty().bind(spImgContainer.widthProperty().add(-50));
        imgViewLogo.fitHeightProperty().bind(spImgContainer.widthProperty().add(-50));
        vboxBrand.getChildren().add(spImgContainer);

        lblBrandText = new Label("SnakeBasket");
        lblBrandText.getStyleClass().add("label-head");
        vboxBrand.getChildren().add(lblBrandText);

        lblBrandInfo = new Label("Hier wird ein Text stehen der kurze Infos über das Spiel enthält und " +
                "eventuell noch Verlinkungen zu hilfreichen Webseiten.");
        lblBrandInfo.setTextAlignment(TextAlignment.CENTER);
        lblBrandInfo.setWrapText(true);
        vboxBrand.getChildren().add(lblBrandInfo);

        //Add Listener
        tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
                if(newValue.getText().equals("Lobby erstellen")) {
                    btnJoinLobby.setDefaultButton(false);
                    btnHostLobby.setDefaultButton(true);
                } else {
                    btnHostLobby.setDefaultButton(false);
                    btnJoinLobby.setDefaultButton(true);
                }
            }
        });

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

    public TextField getTfIpJoin() { return tfIpJoin; }

    public Label getLblHostName() {
        return lblHostName;
    }

    public Label getLblHostPort() {
        return lblHostPort;
    }

    public Label getLblJoinName() {
        return lblJoinName;
    }

    public Label getLblJoinPort() {
        return lblJoinPort;
    }

    public Label getLblJoinIp() {
        return lblJoinIp;
    }
}

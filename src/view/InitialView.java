package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
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
    private VBox vbox;

    private Tab tabHost;
    private Tab tabJoin;

    private Button btnHostLobby;
    private Button btnJoinLobby;

    private TextField tfUserNameHost;
    private TextField tfPortHost;
    private TextField tfUserNameJoin;
    private TextField tfPortJoin;
    private TextField tfIpJoin;

    public InitialView() {
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

        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        grid.add(tabPane, 1, 0);

        tabHost = new Tab("Lobby erstellen");
        tabPane.getTabs().add(tabHost);

        tabJoin = new Tab("Lobby beitreten");
        tabPane.getTabs().add(tabJoin);

        vbox = new VBox();
        vbox.getStyleClass().add("brand-box");
        grid.add(vbox, 0, 0);

        scene = new Scene(grid, 1000, 600);
        scene.getStylesheets().add("/resources/style.css");
    }

    /**
     * In this method the title is set and the stage is displayed to the user.
     *
     * @param stage
     */
    public void show(Stage stage) {
        stage.setTitle("Initial View");
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

    /**
     * Getter
     *
     * @return the grid in the methods name.
     */
    public GridPane getGridPane() {
        return grid;
    }
}

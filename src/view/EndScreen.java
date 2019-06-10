package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;


/**
 * This class is the connection between the view and the model. It handles user input and prepares data to be shown
 * in the view.
 *
 * @author Fabian Haese
 */
public class EndScreen {
    private Scene scene;

    private GridPane grid;

    private Button btnReturnLobby;

    public EndScreen(){
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

        btnReturnLobby = new Button("Zurück zur Lobby");
        grid.add(btnReturnLobby,0,0);


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

    public Button getBtnReturnLobby() {
        return btnReturnLobby;
    }
}

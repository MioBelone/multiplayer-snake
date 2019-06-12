package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
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
    private BorderPane borderPaneButton;
    private BorderPane borderPaneLabel;

    private Button btnReturnLobby;
    private Label lWinner;

    public EndScreen(){
        //Main container as GridPane
        grid = new GridPane();
        grid.getStyleClass().add("grid-pane-root");

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(20);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(60);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(20);
        grid.getColumnConstraints().addAll(col1, col2, col3);

        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(50);
        RowConstraints row2 = new RowConstraints();
        row1.setPercentHeight(50);
        grid.getRowConstraints().addAll(row1,row2);



        borderPaneButton = new BorderPane();
        btnReturnLobby = new Button("Zurück zur Lobby");
        borderPaneButton.setCenter(btnReturnLobby);
        grid.add(borderPaneButton,1,1);

        borderPaneLabel = new BorderPane();
        lWinner = new Label();
        lWinner.setFont(new Font(35));
        borderPaneLabel.setCenter(lWinner);
        grid.add(borderPaneLabel,1,0);


        //Inititalising scene
        scene = new Scene(grid, 500, 400);
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

    public Button getBtnReturnLobby() {
        return btnReturnLobby;
    }

    public Label getlWinner() {
        return lWinner;
    }
}

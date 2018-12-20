package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * This class is the view which the user can see after the .show method is called.
 *
 * @author Maximilian Gräfe
 */
public class InitialView {

    private Scene scene;

    private GridPane grid;

    private Button btn1;
    private Button btn2;

    public InitialView() {
        grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setPadding(new Insets(25));
        grid.setGridLinesVisible(true);

        btn1 = new Button("Test Button");
        grid.add(btn1, 1, 1);

        btn2 = new Button("Anderer Button");
        grid.add(btn2, 2, 1);

        scene = new Scene(grid, 1000, 600);
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
    public Button getBtn1() {
        return btn1;
    }
}

package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import presenter.PlaygroundPresenter;


/**
 * This class is the view which the user can see after the .show method is called.
 *
 * @author Fabian Haese
 */
public class Playground {

    private GridPane grid;
    private Scene scene;

    private GraphicsContext gc;

    PlaygroundPresenter presenter = new PlaygroundPresenter();

    public Playground() {

        grid = new GridPane();
        grid.getStyleClass().add("grid-pane-root");

        Canvas canvas = new Canvas(100,100);
        gc = canvas.getGraphicsContext2D();
        grid.getChildren().add(canvas);

        gc.fillRoundRect(50,50,100,100,10,10);
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
}

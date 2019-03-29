package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.game.Food;
import model.game.SnakeContents.Snake;
import model.game.SnakeContents.SnakeBody;
import model.server.Server;
import presenter.PlaygroundPresenter;


/**
 * This class is the view which the user can see after the .show method is called.
 *
 * @author Fabian Haese
 */
public class Playground {

    private GridPane grid;
    private Scene scene;

    private Server server;
    private GraphicsContext graphicsContext;

    private static final int WINDOW_HEIGHT = 700;
    private static final int WINDOW_WIDTH = 1000;

    public Playground() {

        grid = new GridPane();
        grid.getStyleClass().add("grid-pane-root");

        Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        graphicsContext = canvas.getGraphicsContext2D();
        grid.getChildren().add(canvas);

        grid.prefWidthProperty().bind(canvas.widthProperty());
        grid.prefHeightProperty().bind(canvas.heightProperty());


        //Inititalising scene
        scene = new Scene(grid, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.getStylesheets().add("/resources/style.css");

    }

    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
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

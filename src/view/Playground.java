package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
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

    private AnchorPane anchor;
    private Scene scene;

    private static final int WINDOW_HEIGHT = 700;
    private static final int WINDOW_WIDTH = 1000;

    public Playground() {

        anchor = new AnchorPane();
        anchor.getStyleClass().add("anchor-pane-root");


        //Inititalising scene
        scene = new Scene(anchor, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.getStylesheets().add("/resources/style.css");

    }

    public Scene getScene() {
        return scene;
    }

    public AnchorPane getAnchor() {
        return anchor;
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

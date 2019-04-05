package view;

import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;


/**
 * This class is the view which the user can see after the .show method is called.
 *
 * @author Fabian Haese
 */
public class Playground {

    private AnchorPane anchor;
    private GridPane grid;
    private VBox vBox;

    private Scene scene;

    public Playground() {

        grid = new GridPane();
        grid.getStyleClass().add("grid-pane-root");

        anchor = new AnchorPane();
        anchor.getStyleClass().add("anchor-pane-game");
        anchor.setMinHeight(700);
        anchor.setMinWidth(700);
        anchor.setMaxSize(700, 700);

        vBox = new VBox();
        vBox.getStyleClass().add("vBox-pane-game");
        vBox.setMinHeight(700);
        vBox.setMinWidth(50);

        grid.setConstraints(anchor, 0, 0);
        grid.setHgrow(anchor, Priority.NEVER);
        grid.setVgrow(anchor, Priority.NEVER);

        grid.setConstraints(vBox, 1, 0);
        grid.setHgrow(vBox, Priority.ALWAYS);
        grid.setVgrow(vBox, Priority.ALWAYS);

        grid.setGridLinesVisible(true);
        grid.getChildren().addAll(anchor, vBox);

        //Inititalising scene
        scene = new Scene(grid, 750, 700);
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

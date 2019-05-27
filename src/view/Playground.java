package view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private HBox hBoxA;
    private HBox hBoxT;

    Label scoreT;
    Label scoreA;

    //TODO: Wieder entfernen
    private Button btnTest;

    private Scene scene;

    public Playground() {

        grid = new GridPane();
        grid.getStyleClass().add("grid-pane-game");

        anchor = new AnchorPane();
        anchor.getStyleClass().add("anchor-pane-game");
        anchor.setMinHeight(650);
        anchor.setMinWidth(650);
        anchor.setMaxSize(650, 650);


        vBox = new VBox();
        vBox.getStyleClass().add("vBox-pane-game");
        vBox.setMinHeight(700);
        vBox.setMinWidth(70);

        hBoxA = new HBox();
        hBoxT = new HBox();

        grid.setConstraints(anchor, 0, 0);
        grid.setHgrow(anchor, Priority.NEVER);
        grid.setVgrow(anchor, Priority.NEVER);

        grid.setConstraints(vBox, 1, 0);
        grid.setHgrow(vBox, Priority.ALWAYS);
        grid.setVgrow(vBox, Priority.ALWAYS);

        grid.getChildren().addAll(anchor, vBox);
        grid.setPadding(new Insets(0,0,50,25));

        scoreT = new Label("Punkte");
        scoreT.getStyleClass().add("label-score");
        scoreA = new Label("0");
        scoreA.getStyleClass().add("label-score");

        hBoxT.getChildren().addAll(scoreT);
        hBoxA.getChildren().addAll(scoreA);

        //Button nur zum TEST
        //TODO: Das Event um in die Lobby zu gehen muss aus einer anderen Quelle kommen, der Button muss wieder entfernt werden!
        btnTest = new Button("Return");
        hBoxA.getChildren().add(btnTest);

        hBoxT.setAlignment(Pos.CENTER);
        hBoxA.setAlignment(Pos.CENTER);

        vBox.setPadding(new Insets(15,0,15,0));
        vBox.getChildren().addAll(hBoxT, hBoxA);

        //Inititalising scene
        scene = new Scene(grid, 1000, 700);
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
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stage.setScene(scene);
                stage.show();
            }
        });
    }

    public void setScoreA(String scoreA) {
        this.scoreA.setText(scoreA);
    }

    //TODO: Wieder entfernen
    public Button getBtnTest() {
        return btnTest;
    }
}

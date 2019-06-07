package view;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
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

    private AnchorPane anchorGame;
    private AnchorPane anchorInfo;
    private GridPane grid;
    private VBox vBox;
    private HBox hBoxA;
    private HBox hBoxT;

    private Label scoreT;
    private Label scoreA;

    private Button btnReturn;

    private Scene scene;

    private double gameSize;

    public Playground() {

        //This value describes the width and height of the snake field
        gameSize = 650;

        grid = new GridPane();
        grid.getStyleClass().add("grid-pane-game");

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(80);
        col1.setHalignment(HPos.CENTER);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(20);
        grid.getColumnConstraints().addAll(col1, col2);

        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(100);
        row1.setValignment(VPos.CENTER);
        grid.getRowConstraints().add(row1);

        //Game area
        anchorGame = new AnchorPane();
        anchorGame.getStyleClass().add("anchor-pane-game");
        anchorGame.setMinSize(0, 0);
        anchorGame.setMinHeight(gameSize);
        anchorGame.setMinWidth(gameSize);
        anchorGame.setMaxSize(gameSize, gameSize);
        grid.setMargin(anchorGame, new Insets(20));
        grid.add(anchorGame, 0, 0);

        //Info area
        anchorInfo = new AnchorPane();
        anchorInfo.getStyleClass().add("brand-box");
        anchorInfo.setMinWidth(0);
        grid.add(anchorInfo, 1, 0);

        vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(10));
        vBox.prefWidthProperty().bind(anchorInfo.widthProperty());
        anchorInfo.getChildren().add(vBox);
        anchorInfo.setTopAnchor(vBox, 0.0);

        scoreT = new Label("Punkte");
        scoreT.getStyleClass().add("label-score");
        vBox.getChildren().addAll(scoreT);

        scoreA = new Label("0");
        scoreA.getStyleClass().add("label-score");
        vBox.getChildren().addAll(scoreA);

        btnReturn = new Button("Zurück zur Lobby");
        btnReturn.prefWidthProperty().bind(anchorInfo.widthProperty());
        anchorInfo.getChildren().add(btnReturn);
        anchorInfo.setBottomAnchor(btnReturn, 0.0);

        //Inititalising scene
        scene = new Scene(grid, 1000, 700);
        scene.getStylesheets().add("/resources/style.css");
    }

    public Scene getScene() {
        return scene;
    }

    public AnchorPane getAnchorGame() {
        return anchorGame;
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
    public Button getBtnReturn() {
        return btnReturn;
    }
}

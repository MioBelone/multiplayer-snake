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
    private GraphicsContext gc;

    private static final int SQUARE_SIZE = 20;
    private static final int WINDOW_HEIGHT = 700;
    private static final int WINDOW_WIDTH = 1000;
    private int faktorY;
    private int faktorX;

    PlaygroundPresenter presenter = new PlaygroundPresenter();

    public Playground() {

        grid = new GridPane();
        grid.getStyleClass().add("grid-pane-root");

        Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        gc = canvas.getGraphicsContext2D();
        grid.getChildren().add(canvas);

        grid.prefWidthProperty().bind(canvas.widthProperty());
        grid.prefHeightProperty().bind(canvas.heightProperty());


        //Inititalising scene
        scene = new Scene(grid, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.getStylesheets().add("/resources/style.css");

    }

    private void umrechnung() {
        faktorY = (int) gc.getCanvas().getHeight() / 100;

        faktorX = (int) gc.getCanvas().getWidth() / 100;
    }


    private void drawFirstPart() {
        for (Snake snake : server.getSnakeGame().getSnakes()) {
            gc.setFill(snake.getColor());
            for (SnakeBody body : snake.getSnakeBodies()) {
                gc.fillRect(body.getX() * faktorX, body.getY() * faktorY, SQUARE_SIZE, SQUARE_SIZE);
            }
        }

       /* if (server.getSnakeGame().getSnakes().size() > 0) {
            for (int i = server.getSnakeGame().getSnakes().size(); i >= 0; i--) {
                gc.setFill(server.getSnakeGame().getSnakes().get(i).getColor());
                gc.fillRect(server.getSnakeGame().getSnakes().get(i).getSnakeBodies().get(0).getX() * faktorX, server.getSnakeGame().getSnakes().get(i).getSnakeBodies().get(0).getY() * faktorY, SQUARE_SIZE, SQUARE_SIZE);
            }
        }*/
    }

    private void clearLastPart() {
        for (Snake snake : server.getSnakeGame().getSnakes()) {
            for (SnakeBody body : snake.getSnakeBodies()) {
                gc.clearRect(body.getX() * faktorX, body.getY() * faktorY, SQUARE_SIZE, SQUARE_SIZE);
            }
        }

        /*for (int i = server.getSnakeGame().getSnakes().size(); i >= 0; i--) {
            if (server.getSnakeGame().getSnakes().get(i).getSnakeBodies().size() > 0) {
                gc.clearRect(server.getSnakeGame().getSnakes().get(i).getSnakeBodies().get(server.getSnakeGame().getSnakes().get(i).getSnakeBodies().size()).getX() * faktorX, server.getSnakeGame().getSnakes().get(i).getSnakeBodies().get(server.getSnakeGame().getSnakes().get(i).getSnakeBodies().size()).getY() * faktorY, SQUARE_SIZE, SQUARE_SIZE);
            }
        }*/

    }

    private void drawHead() {
        for (Snake snake : server.getSnakeGame().getSnakes()) {
            gc.setFill(snake.getColor());
            gc.fillRect(snake.getSnakeHead().getX() * faktorX, snake.getSnakeHead().getY() * faktorY, SQUARE_SIZE, SQUARE_SIZE);
        }

      /*  for (int i = server.getSnakeGame().getSnakes().size(); i >= 0; i--) {
            gc.setFill(server.getSnakeGame().getSnakes().get(i).getColor());
            gc.fillRect(server.getSnakeGame().getSnakes().get(i).getSnakeHead().getX() * faktorX, server.getSnakeGame().getSnakes().get(i).getSnakeHead().getY() * faktorY, SQUARE_SIZE, SQUARE_SIZE);
        }*/
    }

    /*private void drawParts() {
      if (server.getSnakeGame().getSnakes().size() > 0) {
          for (int i = server.getSnakeGame().getSnakes().size(); i >= 0; i--) {
              for (int j = server.getSnakeGame().getSnakes().get(i).getSnakeBodies().size(); j >= 0; j--) {
                  gc.setFill(server.getSnakeGame().getSnakes().get(i).getColor());
                  gc.fillRect(server.getSnakeGame().getSnakes().get(i).getSnakeBodies().get(j).getX() * faktorX, server.getSnakeGame().getSnakes().get(i).getSnakeBodies().get(j).getX() * faktorY, SQUARE_SIZE, SQUARE_SIZE);
              }
          }
      }

  }*/

    public void drawSnake() {
        umrechnung();
        drawHead();
        clearLastPart();
        drawFirstPart();
    }

    public void drawFood() {
        gc.setFill(Color.RED);
        for (Food food : server.getSnakeGame().getFoods()) {
            gc.fillRect(food.getX() * faktorX, food.getY() * faktorY, SQUARE_SIZE, SQUARE_SIZE);

        }
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

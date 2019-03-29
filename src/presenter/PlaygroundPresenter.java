package presenter;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.game.Food;
import model.game.SnakeContents.Snake;
import model.game.SnakeContents.SnakeBody;
import model.server.Server;
import view.Playground;


/**
 * This class is the connection between the view and the model. It handles user input and prepares data to be shown
 * in the view.
 *
 * @author Fabian Haese
 */
public class PlaygroundPresenter {

    private Playground view;
    private Stage primaryStage;
    private Server server;

    private int faktorY;
    private int faktorX;

    private static final int SQUARE_SIZE = 20;
    private static final int WINDOW_HEIGHT = 700;
    private static final int WINDOW_WIDTH = 1000;


    public PlaygroundPresenter(Stage primaryStage, Server server) {
        this.primaryStage = primaryStage;
        this.view = new Playground();
        this.server = server;
    }

    public PlaygroundPresenter() {
    }

    /**
     * In this method the .show method of the view is called to display the view to the user.
     */
    public void show() {
        view.show(primaryStage);
    }

    public Playground getView() {
        return view;
    }

    private void umrechnung() {
        faktorY = (int) view.getGraphicsContext().getCanvas().getHeight() / 100;

        faktorX = (int) view.getGraphicsContext().getCanvas().getWidth() / 100;
    }

    private void drawFirstPart() {
        for (Snake snake : server.getSnakeGame().getSnakes()) {
            view.getGraphicsContext().setFill(snake.getColor());
            for (SnakeBody body : snake.getSnakeBodies()) {
                view.getGraphicsContext().fillRect(body.getX() * faktorX, body.getY() * faktorY, SQUARE_SIZE, SQUARE_SIZE);
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
                view.getGraphicsContext().clearRect(body.getX() * faktorX, body.getY() * faktorY, SQUARE_SIZE, SQUARE_SIZE);
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
            view.getGraphicsContext().setFill(snake.getColor());
            view.getGraphicsContext().fillRect(snake.getSnakeHead().getX() * faktorX, snake.getSnakeHead().getY() * faktorY, SQUARE_SIZE, SQUARE_SIZE);
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
        umrechnung();
        view.getGraphicsContext().setFill(Color.WHITE);
        for (Food food : server.getSnakeGame().getFoods()) {
            view.getGraphicsContext().fillOval(food.getX() * faktorX, food.getY() * faktorY, SQUARE_SIZE, SQUARE_SIZE);

        }
    }

    public void drawGrid() {
        umrechnung();
        view.getGraphicsContext().setFill(Color.WHITE);
        view.getGraphicsContext().fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        view.getGraphicsContext().setStroke(Color.LIGHTGRAY);
        view.getGraphicsContext().setLineWidth(0.5);

        for (int x = 0; x < WINDOW_WIDTH; x += faktorX) {
            view.getGraphicsContext().strokeLine(x, 0, x, x + WINDOW_HEIGHT);
        }

        for (int y = 0; y < WINDOW_HEIGHT; y += faktorY) {
            view.getGraphicsContext().strokeLine(0, y, y + WINDOW_WIDTH, y);
        }
    }
}

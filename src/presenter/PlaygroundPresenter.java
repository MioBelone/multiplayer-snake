package presenter;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.game.Food;
import model.game.SnakeContents.Snake;
import model.game.SnakeContents.SnakeBody;
import model.server.Server;
import view.Playground;

import java.util.ArrayList;
import java.util.List;


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
    SnakePresenter snakePresenter;

    private int faktorY;
    private int faktorX;

    private List<Rectangle> snakeBodiesShape;
    private List<SnakePresenter> snakePresenterList;
    FoodPresenter foodPresenter;

    private static final int SQUARE_SIZE = 20;
    private static final int WINDOW_HEIGHT = 700;
    private static final int WINDOW_WIDTH = 1000;


    public PlaygroundPresenter(Stage primaryStage, Server server) {
        this.primaryStage = primaryStage;
        this.view = new Playground();
        this.server = server;
        snakePresenterList = new ArrayList<>();
        foodPresenter = new FoodPresenter();
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
        faktorY = (int) view.getScene().getHeight() / 100;

        faktorX = (int) view.getScene().getWidth() / 100;
    }

    public void initializeSnakes() {
        umrechnung();
        for (Snake snake : server.getSnakeGame().getSnakes()) {
            snakeBodiesShape = new ArrayList<>();
            Rectangle snakeHead = new Rectangle(snake.getSnakeHead().getX() * faktorX, snake.getSnakeHead().getY() * faktorY);
            SnakePresenter snakePresenter = new SnakePresenter(snakeHead, snakeBodiesShape);
            snakePresenterList.add(snakePresenter);
        }
    }

    private void addPart(Integer x, Integer y, Integer snake) {
        umrechnung();
        Rectangle rect = new Rectangle(x * faktorX, y * faktorY);
        snakePresenterList.get(snake).getSnakeBodiesShape().add(rect);
    }

    private void drawFirstPart() {
        for (Snake snake : server.getSnakeGame().getSnakes()) {
            for (SnakeBody body : snake.getSnakeBodies()) {
                for (SnakePresenter snakeP : snakePresenterList) {
                    snakeP.getSnakeBodiesShape().get(0).setFill(snake.getColor());
                    view.getAnchor().getChildren().add(snakeP.getSnakeBodiesShape().get(0));
                }
            }
        }
    }

    private void clearLastPart() {
        for (Snake snake : server.getSnakeGame().getSnakes()) {
            for (SnakeBody body : snake.getSnakeBodies()) {
                // view.getGraphicsContext().clearRect(body.getX() * faktorX, body.getY() * faktorY, SQUARE_SIZE, SQUARE_SIZE);
                for (SnakePresenter snakeP : snakePresenterList) {
                    view.getAnchor().getChildren().remove(snakeP.getSnakeBodiesShape().get(0));
                }
            }
        }
    }

    private void drawHead() {
        for (Snake snake : server.getSnakeGame().getSnakes()) {
            for (SnakePresenter snakeP : snakePresenterList) {
                snakeP.getSnakeHeadShape().setFill(snake.getColor());
                view.getAnchor().getChildren().add(snakeP.getSnakeHeadShape());
            }
        }
    }

    private void clearHead() {
        for (Snake snake : server.getSnakeGame().getSnakes()) {
            for (SnakePresenter snakeP : snakePresenterList) {
                view.getAnchor().getChildren().remove(snakeP.getSnakeHeadShape());
            }
        }
    }

    public void drawSnake() {
        clearHead();
        drawHead();
        clearLastPart();
        drawFirstPart();
    }

    public void addFood(Integer x, Integer y) {
        umrechnung();
        Circle circle = new Circle();
        circle.setCenterX(x * faktorX);
        circle.setCenterY(y * faktorY);
        circle.setRadius(SQUARE_SIZE / 2);
        foodPresenter.getFoods().add(circle);
    }

    public void drawFood() {
        umrechnung();
        for (Food food : server.getSnakeGame().getFoods()) {

            for (Circle foodShape : foodPresenter.getFoods()) {
                foodShape.setFill(Color.WHITE);
                view.getAnchor().getChildren().add(foodShape);

            }
        }
    }
}

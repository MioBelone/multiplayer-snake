package presenter;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.game.Food;
import model.game.SnakeContents.Snake;
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

    private int faktorY;
    private int faktorX;

    private List<Rectangle> snakeBodiesShape;
    private List<SnakePresenter> snakePresenterList;
    FoodPresenter foodPresenter;


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

    private void umrechnung() {
        faktorY = (int) view.getAnchor().getHeight() / 100;

        faktorX = (int) view.getAnchor().getWidth() / 100;
    }

    public void initializeSnakes() {
        umrechnung();
        for (Snake snake : server.getSnakeGame().getSnakes()) {
            snakeBodiesShape = new ArrayList<>();
            Rectangle snakeHead = new Rectangle(snake.getSnakeHead().getX() * faktorX, snake.getSnakeHead().getY() * faktorY,faktorX,faktorY);
            SnakePresenter snakePresenter = new SnakePresenter(snakeHead, snakeBodiesShape, snake.getColor(), snake);
            snakePresenterList.add(snakePresenter);
        }
    }

    public void addPart(Integer x, Integer y, Integer snake) {
        umrechnung();
        Rectangle rect = new Rectangle(x * faktorX, y * faktorY);
        snakePresenterList.get(snake).getSnakeBodiesShape().add(rect);
    }

    private void drawFirstPart() {
        for (SnakePresenter snakeP : snakePresenterList) {
            if (snakeP.getSnakeBodiesShape().size() > 0) {
                snakeP.getSnakeBodiesShape().get(0).setFill(snakeP.getColor());
                view.getAnchor().getChildren().add(snakeP.getSnakeBodiesShape().get(0));
            }
        }
    }

    private void clearLastPart() {
        for (SnakePresenter snakeP : snakePresenterList) {
            if (snakeP.getSnakeBodiesShape().size() > 0) {
                view.getAnchor().getChildren().remove(snakeP.getSnakeBodiesShape().get(0));
            }
        }
    }

    private void drawHead() {
        for (SnakePresenter snakeP : snakePresenterList) {
            snakeP.getSnakeHeadShape().setX(snakeP.getSnake().getSnakeHead().getX() * faktorX);
            snakeP.getSnakeHeadShape().setY(snakeP.getSnake().getSnakeHead().getY() * faktorY);
            snakeP.getSnakeHeadShape().setFill(snakeP.getColor());
            view.getAnchor().getChildren().add(snakeP.getSnakeHeadShape());
        }
    }

    private void clearHead() {
        for (SnakePresenter snakeP : snakePresenterList) {
            view.getAnchor().getChildren().remove(snakeP.getSnakeHeadShape());
        }
    }

    public void drawSnake() {
        umrechnung();
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
        circle.setRadius(faktorX / 2);
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

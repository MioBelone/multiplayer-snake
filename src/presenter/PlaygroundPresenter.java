package presenter;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.client.Client;
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
    private Client client;

    private int faktorY;
    private int faktorX;

    FoodPresenter foodPresenter;

    public PlaygroundPresenter(Stage primaryStage, Server server) {
        this.primaryStage = primaryStage;
        this.view = new Playground();
        this.server = server;
        foodPresenter = new FoodPresenter();

        view.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println("1 " + event.getCode());
                key(event.getCode().toString());
            }
        });
    }

    public PlaygroundPresenter() {
    }

    /**
     * In this method the .show method of the view is called to display the view to the user.
     */
    public void show() {
        view.show(primaryStage);
        umrechnung();
    }

    private void umrechnung() {
        faktorY = (int) view.getAnchor().getHeight() / 100;

        faktorX = (int) view.getAnchor().getWidth() / 100;
    }

    public void initializeSnakes() {
        SnakePresenter snakePresenter = new SnakePresenter();
    }

    public void drawSnake() {
        clear();
        drawHead();
        drawBody();
    }

    private void clear() {
        view.getAnchor().getChildren().clear();
    }

    private void drawHead() {
        for (Snake snake : client.getSnakes()) {
            Rectangle rect = new Rectangle(snake.getSnakeHead().getX() * faktorX, snake.getSnakeHead().getY() * faktorY);
            rect.setFill(snake.getColor());
            view.getAnchor().getChildren().add(rect);
        }
    }

    private void drawBody() {
        for (Snake snake : client.getSnakes()) {
            for (SnakeBody body : snake.getSnakeBodies()) {
                Rectangle rect = new Rectangle(body.getX() * faktorX, body.getY() * faktorY);
                rect.setFill(snake.getColor());
                view.getAnchor().getChildren().add(rect);
            }
        }
    }

    public void drawFood() {
        for (Food food : client.getFoods()) {
            Circle circle = new Circle(food.getX() + faktorX, food.getY() * faktorY, faktorX);
            circle.setFill(Color.WHITE);
            view.getAnchor().getChildren().add(circle);
        }
    }

    public void key(String msg) {
        System.out.println(msg);
        client.sendMsgToServer("/gameCmd move" + msg + "Playername");
    }
}

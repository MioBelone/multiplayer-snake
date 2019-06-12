package presenter;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.InitialModel;
import model.client.Client;
import model.game.Food;
import model.game.SnakeContents.Snake;
import model.game.SnakeContents.SnakeBody;
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
    private Client client;
    private LobbyPresenter lobby;
    private InitialModel initialModel;

    private int faktorY;
    private int faktorX;

    /**
     * Constructor
     *
     * @param primaryStage = PrimaryStage
     * @param client       = Connected Client
     * @param initialModel = Initial Model
     * @param lobby = Lobby
     */
    public PlaygroundPresenter(Stage primaryStage, Client client, InitialModel initialModel, LobbyPresenter lobby) {
        this.primaryStage = primaryStage;
        this.view = new Playground();
        this.client = client;
        this.initialModel = initialModel;
        this.lobby = lobby;

        client.setPlaygroundPresenter(this);


        view.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyPressed(event.getCode().toString());
            }
        });
    }

    /**
     * Empty Constructor
     */
    public PlaygroundPresenter() {
    }

    /**
     * In this method the .show method of the view, is called to display the view to the user.
     */
    public void show() {
        view.show(primaryStage);
    }

    /**
     * Calculates the X and Y multiplier for the drawing.
     */
    private void umrechnung() {
        faktorY = (int) view.getAnchorGame().getHeight() / 50;

        faktorX = (int) view.getAnchorGame().getWidth() / 50;
    }

    /**
     * Method which initialize the drawing of the snakes.
     */
    public void drawSnake() {
        umrechnung();
        clear();
        drawHead();
        drawBody();
        setLabelText();
    }

    /**
     * This method clears the whole AnchorPane.
     */
    private void clear() {
        view.getAnchorGame().getChildren().clear();
    }

    /**
     * This method draws the head of every snake in the game, for one client.
     */
    private void drawHead() {
        for (Snake snake : client.getSnakes()) {
            Rectangle rect = new Rectangle(snake.getSnakeHead().getX() * faktorX, snake.getSnakeHead().getY() * faktorY, faktorX, faktorY);
            rect.setFill(Color.valueOf(snake.getSnakeColorAsString()));
            view.getAnchorGame().getChildren().add(rect);
        }
    }

    private void drawBody() {
        for (Snake snake : client.getSnakes()) {
            for (SnakeBody body : snake.getSnakeBodies()) {
                Rectangle rect = new Rectangle(body.getX() * faktorX, body.getY() * faktorY, faktorX, faktorY);
                rect.setFill(Color.valueOf(snake.getSnakeColorAsString()));
                view.getAnchorGame().getChildren().add(rect);
            }
        }
    }

    public void drawFood() {
        umrechnung();
        for (Food food : client.getFoods()) {
            Circle circle = new Circle(food.getX() * faktorX + faktorX / 2, food.getY() * faktorY + faktorY / 2, faktorX / 2);
            circle.setFill(Color.WHITE);
            view.getAnchorGame().getChildren().add(circle);

        }
    }

    private void keyPressed(String msg) {
        client.sendMsgToServer("/gameCmd move " + msg);
    }

    private void setLabelText() {
        for (Snake snake : client.getSnakes()) {
            if (snake.getPlayername().equals(client.getName())) {
                view.setScoreA("" + snake.getScore());

            }
        }
    }

    public void endGame() {
        EndScreenPresenter endScreenPresenter = new EndScreenPresenter(primaryStage, initialModel, lobby, client);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                endScreenPresenter.showWinningPoints();
                endScreenPresenter.show();
            }
        });
    }
}

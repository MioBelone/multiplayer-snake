package presenter;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.game.SnakeContents.Snake;
import model.game.SnakeGame;

import java.util.ArrayList;
import java.util.List;

public class SnakePresenter {
    private Rectangle snakeHeadShape;
    private Color color;
    private Snake snake;


    private List<Rectangle> snakeBodiesShape = new ArrayList<>();

    public SnakePresenter(Rectangle snakeHeadShape, List<Rectangle> snakeBodiesShape, Color color, Snake snake) {
        this.snakeHeadShape = snakeHeadShape;
        this.snakeBodiesShape = snakeBodiesShape;
        this.color = color;
        this.snake = snake;
    }

    public SnakePresenter() {

    }

    public Rectangle getSnakeHeadShape() {
        return snakeHeadShape;
    }

    public List<Rectangle> getSnakeBodiesShape() {
        return snakeBodiesShape;
    }

    public Color getColor() {
        return color;
    }

    public Snake getSnake() {
        return snake;
    }
}

package presenter;

import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class SnakePresenter {
    private Rectangle snakeHeadShape;

    private List<Rectangle> snakeBodiesShape = new ArrayList<>();

    public SnakePresenter(Rectangle snakeHeadShape, List<Rectangle> snakeBodiesShape) {
        this.snakeHeadShape = snakeHeadShape;
        this.snakeBodiesShape = snakeBodiesShape;
    }

    public SnakePresenter(){

    }

    public Rectangle getSnakeHeadShape() {
        return snakeHeadShape;
    }

    public List<Rectangle> getSnakeBodiesShape() {
        return snakeBodiesShape;
    }
}

package model.game.SnakeContents;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * This class is added to a snake after a food has been consumed.
 *
 * @author Alexander Schleiter
 */
public class SnakeBody {

    private int x;
    private int y;

    @JsonCreator
    public SnakeBody() {
        super();
    }

    public SnakeBody(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}

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

    /**
     * Constructor that is used for the creation of JSON strings
     */
    @JsonCreator
    public SnakeBody() {
        super();
    }

    /**
     * Normal cosntructor
     * @param x x coordinate
     * @param y y coordinate
     */
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

package model.game.SnakeContents;

import model.game.Direction;

/**
 * This class stores the direction where the snake is heading and is essential for collision checks.
 *
 * @author Alexander Schleiter
 */
public class SnakeHead {

    private int x;
    private int y;
    private Direction dir;


    public SnakeHead(int x, int y, Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
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

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }
}

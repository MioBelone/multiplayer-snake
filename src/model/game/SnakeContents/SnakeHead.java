package model.game.SnakeContents;

import com.fasterxml.jackson.annotation.JsonCreator;
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
    //variable used to prevent multiple direction changes in the same tick (see Loop and setDir())
    private boolean lockDirection;

    /**
     * Constructor that is used for the creation of JSON strings
     */
    @JsonCreator
    public SnakeHead() {
        super();
    }

    /**
     * Normal contructor
     * @param x x coordinate
     * @param y y coordinate
     * @param dir direction in which the snake will be heading
     */
    public SnakeHead(int x, int y, Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        lockDirection = false;
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

    /**
     * Direction changes are only allowed once per tick and are therefore the head is locked after a change.
     * The head is unlocked at the beginning of each tick.
     * @param dir
     */
    public void setDir(Direction dir) {
        if (!isLockDirection()) {
            if (dir != null) {
                switch (dir) {
                    case RIGHT:
                        if (this.dir != Direction.LEFT) {
                            this.dir = dir;
                            lockDirection=true;
                        }
                        break;
                    case LEFT:
                        if (this.dir != Direction.RIGHT) {
                            this.dir = dir;
                            lockDirection=true;
                        }
                        break;
                    case UP:
                        if (this.dir != Direction.DOWN) {
                            this.dir = dir;
                            lockDirection=true;
                        }
                        break;
                    case DOWN:
                        if (this.dir != Direction.UP) {
                            this.dir = dir;
                            lockDirection=true;
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private boolean isLockDirection() {
        return lockDirection;
    }

    public void setLockDirection(boolean lockDirection) {
        this.lockDirection = lockDirection;
    }
}

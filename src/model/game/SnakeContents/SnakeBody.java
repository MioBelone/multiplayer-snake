package model.game.SnakeContents;

/**
 * This class is added to a snake after a food has been consumed.
 *
 * @author Alexander Schleiter
 */
public class SnakeBody {

    private int x;
    private int y;

    public SnakeBody(int x, int y) {
        this.x = x;
        this.y = y;
    }



    public int getX() {
        return x;
    }

    public void setX(int currentX) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int currentY) {
        this.y = y;
    }

}

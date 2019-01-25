package model.game;

import javafx.scene.paint.Color;

public class SnakeHead {

    private int x;
    private int y;
    //private Color color = Color.BLUE;
    private Direction dir;


    public SnakeHead(int x, int y) {
        this.x = x;
        this.y = y;
        this.dir = Direction.RIGHT;
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

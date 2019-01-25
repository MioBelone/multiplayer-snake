package model.game;

import javafx.scene.paint.Color;

import java.util.Random;

public class Food {

    private int x;
    private int y;
    private Random random = new Random();

    public Food(int x, int y) {

        this.x = x;
        this.y = y;

    }

    public void reset() {

        this.x = (random.nextInt(100) + 1);
        this.y = (random.nextInt(100) + 1);
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

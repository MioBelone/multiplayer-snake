package model.game;

import javafx.scene.paint.Color;

import java.util.Random;

public class Food {

    private int x;
    private int y;
    //private static Color color = Color.RED;

    public Food(int x, int y) {

        this.x = x;
        this.y = y;

    }

    public void reset() {

        this.x = (random.nextInt(15) + 1)*Draw.getAbstand();
        this.y = (random.nextInt(15) + 1)*Draw.getAbstand();
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

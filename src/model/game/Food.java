package model.game;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Random;

/**
 * This class defines the foods that can be consumed by snakes.
 *
 * @author Alexander Schleiter
 */
public class Food {

    private int x;
    private int y;
    private Random random = new Random();

    @JsonCreator
    public Food() {
        super();
        this.x = random.nextInt(100);
        this.y = random.nextInt(100);
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

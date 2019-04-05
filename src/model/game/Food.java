package model.game;
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

    public Food() {

        this.x = random.nextInt(100)+1;
        this.y = random.nextInt(100)+1;

    }

    public Food(int x, int y, Random random) {
        this.x = x;
        this.y = y;
        this.random = random;
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

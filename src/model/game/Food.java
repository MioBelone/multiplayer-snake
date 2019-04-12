package model.game;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    private int breite;

    public Food(int breite) {
        //randomize food spawn location
        this.breite=breite;
        this.x = random.nextInt(breite);
        this.y = random.nextInt(breite);
    }

    @JsonCreator
    public Food() {
        super();
    }

    public void reset() {

        this.x = (random.nextInt(breite));
        this.y = (random.nextInt(breite));
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

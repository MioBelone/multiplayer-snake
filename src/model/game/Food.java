package model.game;

import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Random;

public class Food {

    private int x;
    private int y;
    //private static int[] usedCoords=new int[16];
    private Random random = new Random();

    public Food() {

        this.x = random.nextInt(100)+1;
        this.y = random.nextInt(100)+1;

    }

    public void reset() {

        this.x = (random.nextInt(100) + 1);
        this.y = (random.nextInt(100) + 1);
    }


    /*
    public Array<int> randomCoordWithException(Random random, int fieldsize, int... exception){
       Array<int> back = new Array<int>;
       for(int i =0; i <= fieldsize; i++){

       }


        int c = start + random.nextInt(end - start + 1 - exception.length);
        for (int ex : exception) {
            if (c < ex) {
                break;
            }
            c++;
        }
        return c;
    }
    */

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

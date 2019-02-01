import model.game.Food;
import model.game.Snake;
import model.game.SnakeGame;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;

public class SnakeGameTest {

    Food f;
    Food f1;
    ArrayList<Food> foods = new ArrayList<>();
    Snake snake;
    SnakeGame snakeGame;

    @Test
    public void checkForFoodPositions(){

        f.setX(1);
        f.setY(1);
        f1.setX(1);
        f1.setY(1);

        foods.set(0,f);
        foods.set(1,f1);



        snakeGame.checkForFoodPositions(foods,2);

        assertNotEquals(f.getX(),f1.getX());
        assertNotEquals(f.getY(),f1.getY());
    }


}

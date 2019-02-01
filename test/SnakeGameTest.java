import model.game.Food;
import model.game.Snake;
import model.game.SnakeGame;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnakeGameTest {

    Food f;
    Food f1;
    ArrayList<Food> foods = new ArrayList<>();
    Snake snake;
    SnakeGame snakeGame;

    @Test
    public void checkForFoodPositions() {

        snakeGame = new SnakeGame();

        f = new Food();
        f1 = new Food();

        f.setX(1);
        f.setY(1);
        f1.setX(1);
        f1.setY(1);

        foods.add(f);
        foods.add(f1);


        snakeGame.checkForFoodPositions(foods, 2);

        assertEquals(false, (f.getX() == f1.getX() || f.getY() == f1.getY()));


    }


}

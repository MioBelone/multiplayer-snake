import model.game.Collision;
import model.game.Food;
import model.game.SnakeContents.Snake;
import model.game.SnakeGame;
import model.server.Server;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnakeGameTest {

    Food f;
    Food f1;
    ArrayList<Food> foods = new ArrayList<>();
    Snake snake;
    SnakeGame snakeGame;
    Collision collision = new Collision();
    Server s = new Server(333);

    @Test
    public void generateStartingPositionsEqualNumber() {
        snakeGame = new SnakeGame(s);

        for(int i =0;i<100;i++){
            System.out.println(snakeGame.getSnakes().get(i).getSnakeHead().getX()+" "+snakeGame.getSnakes().get(i).getSnakeHead().getY());
        }
        //assertEquals(true,());
    }

    @Test
    public void generateStartingPositionUnequalNumber() {

        snakeGame = new SnakeGame(s);

        for(int i =0;i<100;i++){
            System.out.println(snakeGame.getSnakes().get(i).getSnakeHead().getX()+" "+snakeGame.getSnakes().get(i).getSnakeHead().getY());
        }
        //assertEquals(true,());

    }

    @Test
    public void checkForFoodPositions() {

        snakeGame = new SnakeGame(s);

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

    @Test
    public void checkCollision(){
        snakeGame = new SnakeGame(s);
        snakeGame.getSnakes().get(1).getSnakeHead().setX(101);
        snakeGame.getSnakes().get(1).getSnakeHead().setY(101);
        //snakeGame.getSnakes().get(1).getSnakeHead().setX(3);
        //snakeGame.getSnakes().get(1).getSnakeHead().setY(3);

        snakeGame.getCollision().checkCollision(snakeGame);
        assertEquals(snakeGame.getClientSize()-1,snakeGame.getSnakes().size());
    }

    //@Test



}

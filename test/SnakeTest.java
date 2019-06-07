import com.fasterxml.jackson.core.JsonProcessingException;
import model.game.SnakeContents.Snake;
import model.game.ObjectToJson;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SnakeTest {

    private Snake snake;
    private Snake snake2;
    private ArrayList<Snake> snakes=new ArrayList<>();

    @Test
    void addSnakeBody() {

        snake = new Snake(2,3);
        snake.addSnakeBody();

        assertEquals(1,snake.getSnakeBodies().size());
    }

    @Test
    void move() {

        //snake = new Snake();

        snake.move();

        //assertEquals(2, Snake.getSnakeHead().getX());

    }

    @Test
    void jsonStringMethode() throws JsonProcessingException {

        snake = new Snake(2,3);

        snake.addSnakeBody();
        snake.addSnakeBody();
        snake.addSnakeBody();
        snake.addSnakeBody();

        snake2 = new Snake(4,5);

        snake2.addSnakeBody();
        snake2.addSnakeBody();
        snake2.addSnakeBody();
        snake2.addSnakeBody();

        snakes.add(snake);
        snakes.add(snake2);

        ObjectToJson otj = new ObjectToJson(snakes);
        System.out.println(otj.parseSnakes());
    }
}
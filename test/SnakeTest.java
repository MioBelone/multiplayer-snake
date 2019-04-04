import com.fasterxml.jackson.core.JsonProcessingException;
import model.game.SnakeContents.Snake;
import model.game.SnakeToJson;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SnakeTest {

    Snake snake;

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
    void stringMethode() throws JsonProcessingException {

        snake = new Snake(2,3);
        snake.addSnakeBody();
        snake.addSnakeBody();
        snake.addSnakeBody();
        snake.addSnakeBody();

        SnakeToJson sj = new SnakeToJson(snake);
        System.out.println(sj.parse());
    }
}
import javafx.scene.paint.Color;
import model.client.Client;
import model.game.Collision;
import model.game.Food;
import model.game.SnakeContents.Snake;
import model.game.SnakeGame;
import model.server.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SnakeGameTest {

    Food f;
    Food f1;
    ArrayList<Food> foods = new ArrayList<>();
    Snake snake;
    SnakeGame snakeGame;
    Collision collision = new Collision();

    Server server;
    Thread serverRunning;
    int port = 5065;

    @BeforeAll
    public void initServer() {
        server = new Server(port);

        serverRunning = new Thread() {
            @Override
            public void run() {
                server.start();
            }
        };
        serverRunning.start();

        Client client1 = new Client("Client1");
        client1.connect("localhost", port);
        client1.sendMsgToServer("erster Client");

        Client client2 = new Client("Client2");
        client2.connect("localhost", port);
        client2.sendMsgToServer("zweiter Client");
    }

    @Test
    public void generateStartingPositionsEqualNumber() {
        //snakeGame = new SnakeGame(server);

        for(Snake s : snakeGame.getSnakes()){
            System.out.println(snakeGame.getSnakes().size());
            System.out.println(s.getSnakeHead().getX()+" "+s.getSnakeHead().getY());
        }
        //assertEquals(true,());
    }

    @Test
    public void generateStartingPositionUnequalNumber() {

        Client client3 = new Client("Client3");
        client3.connect("localhost", port);
        client3.sendMsgToServer("dritter Client");

        //snakeGame = new SnakeGame(server);

        for(Snake s : snakeGame.getSnakes()){
            System.out.println(s.getSnakeHead().getX()+" "+s.getSnakeHead().getY());
        }

        System.out.println("roflcopter");
        //assertEquals(true,());

    }

    @Test
    public void checkForFoodPositions() {

        //snakeGame = new SnakeGame(server);

        f = new Food();
        f1 = new Food();

        f.setX(1);
        f.setY(1);
        f1.setX(1);
        f1.setY(1);

        foods.add(f);
        foods.add(f1);


        snakeGame.checkForFoodPositions(foods);

        assertFalse(f.getX() == f1.getX() || f.getY() == f1.getY());


    }

    @Test
    public void checkCollision(){
        //snakeGame = new SnakeGame(server);
        snakeGame.getSnakes().get(1).getSnakeHead().setX(101);
        snakeGame.getSnakes().get(1).getSnakeHead().setY(101);
        //snakeGame.getSnakes().get(1).getSnakeHead().setX(3);
        //snakeGame.getSnakes().get(1).getSnakeHead().setY(3);

        //snakeGame.getCollision().checkCollision(snakeGame);
        assertEquals(snakeGame.getClientSize()-1,snakeGame.getSnakes().size());
    }

    @Test
    public void checkColor(){


        //snakeGame = new SnakeGame(server);
        assertEquals(snakeGame.getSnakes().get(0).getColor(), Color.RED);
        //assertEquals(snakeGame.getSnakes().get(1).getColor(), Color.GREEN);

    }

    @AfterAll
    public void endServer() {
        server.close();
        serverRunning.stop();
    }



}

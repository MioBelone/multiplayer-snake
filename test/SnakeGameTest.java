import com.fasterxml.jackson.core.JsonProcessingException;
import model.client.Client;
import model.game.Collision;
import model.game.Food;
import model.game.SnakeContents.Snake;
import model.game.SnakeContents.SnakeColor;
import model.game.SnakeGame;
import model.server.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import presenter.PlaygroundPresenter;

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
    PlaygroundPresenter pp = new PlaygroundPresenter();

    Server server;
    Client client1;
    Client client2;
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

        client1 = new Client("Client1");
        client1.connect("localhost", port);
        client1.sendMsgToServer("erster Client");

        client2 = new Client("Client2");
        client2.connect("localhost", port);
        client2.sendMsgToServer("zweiter Client");
    }

    @Test
    public void randomFood() throws JsonProcessingException {
        snakeGame = new SnakeGame(server, pp);

        System.out.println("jebem" + snakeGame.getFoods().get(0).getX());
        System.out.println("jebem" + snakeGame.getFoods().get(0).getY());
    }

    @Test
    public void generateStartingPositionsEqualNumber() throws JsonProcessingException {
       snakeGame = new SnakeGame(server, pp);

        for (Snake s : snakeGame.getSnakes()) {
            System.out.println(snakeGame.getSnakes().size());
            System.out.println(s.getSnakeHead().getX() + " " + s.getSnakeHead().getY());
        }
        //assertEquals(true,());
    }

    @Test
    public void generateStartingPositionUnequalNumber() throws JsonProcessingException{

        Client client3 = new Client("Client3");
        client3.connect("localhost", port);
        client3.sendMsgToServer("dritter Client");

        snakeGame = new SnakeGame(server, pp);

        for (Snake s : snakeGame.getSnakes()) {
            System.out.println(s.getSnakeHead().getX() + " " + s.getSnakeHead().getY());
        }

        System.out.println("roflcopter");
        //assertEquals(true,());

    }

    @Test
    public void checkForFoodPositions() throws JsonProcessingException {

        snakeGame = new SnakeGame(server, pp);

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
    public void checkCollision() throws JsonProcessingException{
        snakeGame = new SnakeGame(server, pp);
        snakeGame.getSnakes().get(1).getSnakeHead().setX(101);
        snakeGame.getSnakes().get(1).getSnakeHead().setY(101);
        //snakeGame.getSnakes().get(1).getSnakeHead().setX(3);
        //snakeGame.getSnakes().get(1).getSnakeHead().setY(3);

        //snakeGame.getCollision().checkCollision(snakeGame);
        assertEquals(snakeGame.getClientSize() - 1, snakeGame.getSnakes().size());
    }

    @Test
    public void checkColor() throws JsonProcessingException {


        snakeGame = new SnakeGame(server, pp);
        assertEquals(snakeGame.getSnakes().get(0).getSnakeColor(), "RED");
        //assertEquals(snakeGame.getSnakes().get(1).getSnakeColor(), SnakeColor.GREEN);

    }

    @Test
    public void sendToAllHandlers() throws JsonProcessingException, InterruptedException {
        snakeGame = new SnakeGame(server,pp);
        serverRunning.sleep(100);

        System.out.println(client1.getSnakes());
        System.out.println(client2.getSnakes());
    }

    @AfterAll
    public void endServer() {
        server.close();
        serverRunning.stop();
    }


}

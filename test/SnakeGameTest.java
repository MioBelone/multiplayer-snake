import com.fasterxml.jackson.core.JsonProcessingException;
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
import presenter.PlaygroundPresenter;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SnakeGameTest {

    private Food f;
    private Food f1;
    private ArrayList<Food> foods = new ArrayList<>();
    Snake snake;
    private SnakeGame snakeGame;
    Collision collision = new Collision();
    private PlaygroundPresenter pp = new PlaygroundPresenter();

    private Server server;
    private Client client1;
    private Client client2;
    private Thread serverRunning;
    private int port = 5065;

    @BeforeAll
    void initServer() {
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
    void randomFood() throws JsonProcessingException {
        snakeGame = new SnakeGame(server, pp);

        System.out.println("jebem" + snakeGame.getFoods().get(0).getX());
        System.out.println("jebem" + snakeGame.getFoods().get(0).getY());
    }

    @Test
    void generateStartingPositionsEqualNumber() throws JsonProcessingException {
       snakeGame = new SnakeGame(server, pp);

        for (Snake s : snakeGame.getSnakes()) {
            System.out.println(snakeGame.getSnakes().size());
            System.out.println(s.getSnakeHead().getX() + " " + s.getSnakeHead().getY());
        }
        //assertEquals(true,());
    }

    @Test
    void generateStartingPositionUnequalNumber() throws JsonProcessingException{

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
    void checkForFoodPositions() throws JsonProcessingException {

        snakeGame = new SnakeGame(server, pp);

        f = new Food(snakeGame.getFieldSize());
        f1 = new Food(snakeGame.getFieldSize());

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
    void checkCollision() throws JsonProcessingException{
        snakeGame = new SnakeGame(server, pp);
        snakeGame.getSnakes().get(1).getSnakeHead().setX(101);
        snakeGame.getSnakes().get(1).getSnakeHead().setY(101);
        //snakeGame.getSnakes().get(1).getSnakeHead().setX(3);
        //snakeGame.getSnakes().get(1).getSnakeHead().setY(3);

        //snakeGame.getCollision().checkCollision(snakeGame);
        assertEquals(snakeGame.getClientSize() - 1, snakeGame.getSnakes().size());
    }

    @Test
    void checkColor() throws JsonProcessingException {


        snakeGame = new SnakeGame(server, pp);
        assertEquals(snakeGame.getSnakes().get(0).getSnakeColor(), "RED");
        //assertEquals(snakeGame.getSnakes().get(1).getSnakeColor(), SnakeColor.GREEN);

    }

    @Test
    void sendToAllHandlers() throws JsonProcessingException, InterruptedException {
        snakeGame = new SnakeGame(server,pp);
        Thread.sleep(100);

        System.out.println(client1.getSnakes());
        System.out.println(client2.getSnakes());
    }

    @AfterAll
    void endServer() {
        server.close();
        serverRunning.stop();
    }


}

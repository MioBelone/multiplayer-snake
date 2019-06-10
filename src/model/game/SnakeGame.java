package model.game;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.game.SnakeContents.Snake;
import model.game.SnakeContents.SnakeColor;
import model.server.Server;
import presenter.PlaygroundPresenter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is the main class of the game and stores most of the important variables.
 *
 * @author Alexander Schleiter
 */
public class SnakeGame {

    //Static game settings
    private static int fieldSize = 50;
    private static int gameSpeed = 200;
    private static int foodCount = 1;

    private Server server;
    private ArrayList<Food> foods = new ArrayList<>();
    private ArrayList<Snake> snakes = new ArrayList<>();
    private int clientSize;
    private int quotient;
    private int sidespace = fieldSize / 4;

    private ObjectToJson otjSnakes;
    private ObjectToJson otjFoods;

    private HashMap<Snake, Integer> scores = new HashMap<Snake, Integer>();
    private Snake winner;
    //TODO: check if playgroundPresenter needs to assigend in SnakeGame
    private PlaygroundPresenter playgroundPresenter;

    /**
     * Constructor that:
     * 1. Generates starting positions for all snakes
     * 2. Adds all foods
     * 3. Assigns colors and names to all snakes
     * 4. Generates the Json converters for snakes and foods
     *
     * @param server
     * @param playgroundPresenter
     * @throws JsonProcessingException
     */
    public SnakeGame(Server server, PlaygroundPresenter playgroundPresenter) throws JsonProcessingException {

        this.server = server;
        this.clientSize = server.getClientList().size();
        this.playgroundPresenter = playgroundPresenter;
        generateStartingPositions(clientSize);


        for (int i = 0; i < clientSize; i++) {
            for (int j = 0; j < foodCount; j++) {
                foods.add(new Food(fieldSize));
            }
            snakes.get(i).setSnakeColor(SnakeColor.values()[i]);
            snakes.get(i).setPlayername(server.getClientList().get(i).getNameOfClient());
            snakes.get(i).setId(i);
            scores.put(snakes.get(i), snakes.get(i).getScore());
        }

        otjSnakes = new ObjectToJson(snakes);
        otjFoods = new ObjectToJson(foods);

        sendGameInfo();
    }

    /**
     * Checks if the foods in a list have the same coordinates.
     *
     * @param foods
     */
    public void checkForFoodPositions(ArrayList<Food> foods) {

        for (Food f : foods) {

            //Compare taken food to other foods in list
            for (Food otherFood : foods) {

                //if it is the same food object, skip it (because the values are obviously equal)
                if (f.equals(otherFood)) {
                    break;
                } else {

                    //if the coordinates of the selected food are equal to another one, reset the selected food's coordinates
                    if (f.getX() == otherFood.getX() && f.getY() == otherFood.getY()) {
                        //Generate new coordinates
                        f.reset();
                        //If there is even one reset, coordinates have to be checked again to make sure the random numbers aren't equal.
                        checkForFoodPositions(foods);
                    }

                }
            }
        }
    }

    /**
     * Inititializes the starting postions for every snake and adds them to the ArrayList.
     */
    private void generateStartingPositions(int clientSize) {
        //for equal number of players
        if (clientSize % 2 == 0) {
            quotient = fieldSize / (clientSize / 2);

            for (int i = 0; i < clientSize / 2; i++) {
                //generate left snakes
                snakes.add(new Snake(sidespace, i * quotient + quotient / 2));
                //generate right snakes
                snakes.add(new Snake(fieldSize - sidespace, i * quotient + quotient / 2));
            }
        }

        //for unequal number of players
        if (clientSize % 2 != 0) {


            quotient = fieldSize / (clientSize / 2 + 1);

            for (int i = 0; i < clientSize / 2 - 0.5; i++) {
                //generate left snakes
                snakes.add(new Snake(sidespace, i * quotient + quotient / 2));
                //generate right snakes
                snakes.add(new Snake(fieldSize - sidespace, i * quotient + quotient / 2));
            }

            for (int i = clientSize / 2; i < clientSize / 2 + 1; i++) {
                //generate the snake that has no opposite snake
                snakes.add(new Snake(sidespace, i * quotient + quotient / 2));
            }
        }
    }

    /**
     * Converts the position of all snakes and foods to Json strings to be sent to all clients.
     *
     * @throws JsonProcessingException
     */
    public void sendGameInfo() throws JsonProcessingException {
        server.sendToAllHandler("/gameCmd snakes " + otjSnakes.parseSnakes());
        server.sendToAllHandler("/gameCmd foods " + otjFoods.parseFoods());
        server.sendToAllHandler("/gameCmd loop");
    }

    /**
     * Unlocks all snakes. This is to handle multiple direction changes in a single snake in the same tick.
     * (see Loop and SnakeHead.setDir() for further information)
     */
    public void lockSnakes() {
        for (Snake s : snakes) {
            s.getSnakeHead().setLockDirection(false);
        }
    }

    public static void setSettingsToDefault() {

        //Standad game settings
        fieldSize = 100;
        gameSpeed = 200;
        foodCount = 1;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public ArrayList<Snake> getSnakes() {
        return snakes;
    }

    public void setSnakes(ArrayList<Snake> snakes) {
        this.snakes = snakes;
    }

    public int getClientSize() {
        return clientSize;
    }

    public void setClientSize(int clientSize) {
        this.clientSize = clientSize;
    }

    public int getQuotient() {
        return quotient;
    }

    public void setQuotient(int quotient) {
        this.quotient = quotient;
    }

    public int getSidespace() {
        return sidespace;
    }

    public void setSidespace(int sidespace) {
        this.sidespace = sidespace;
    }

    public HashMap<Snake, Integer> getScores() {
        return scores;
    }

    public void setScores(HashMap<Snake, Integer> scores) {
        this.scores = scores;
    }

    public Snake getWinner() {
        return winner;
    }

    public void setWinner(Snake winner) {
        this.winner = winner;
    }

    public PlaygroundPresenter getPlaygroundPresenter() {
        return playgroundPresenter;
    }

    public Server getServer() {
        return server;
    }

    public ObjectToJson getOtjSnakes() {
        return otjSnakes;
    }

    public ObjectToJson getOtjFoods() {
        return otjFoods;
    }

    public static int getFieldSize() {
        return fieldSize;
    }

    public static void setFieldSize(int fieldSize) {
        SnakeGame.fieldSize = fieldSize;
    }

    public static int getGameSpeed() {
        return gameSpeed;
    }

    public static void setGameSpeed(int gameSpeed) {
        SnakeGame.gameSpeed = gameSpeed;
    }

    public static int getFoodCount() {
        return foodCount;
    }

    public static void setFoodCount(int foodCount) {
        SnakeGame.foodCount = foodCount;
    }


}

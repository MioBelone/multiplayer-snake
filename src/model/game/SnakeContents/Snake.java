package model.game.SnakeContents;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import model.game.Direction;

import java.util.ArrayList;

/**
 * This class combines SnakeHead and SnakeBody, stores the current score and snakeColor of the player.
 *
 * @author Alexander Schleiter
 */
public class Snake {

    private int id;
    private String playername;
    private int score;
    private SnakeColor snakeColor;
    private SnakeHead snakeHead;
    private ArrayList<SnakeBody> snakeBodies = new ArrayList<>();


    /**
     * Constructor that is used for the creation of JSON strings
     */
    @JsonCreator
    public Snake() {
        super();
    }

    /**
     * Normal contructor
     * @param x x coordinate for the SnakeHead
     * @param y y coordinate for the SnakeHead
     */
    public Snake(int x, int y) {

        this.snakeHead = new SnakeHead(x, y, Direction.RIGHT);
        this.score = 0;
    }

    /**
     * Method to add a SnakeBody to the Snake
     * If no body is present, the first body part will use the coords of the head,
     * otherwise the coords of the last body part
     */
    public void addSnakeBody() {
        if (snakeBodies.size() < 1) {
            snakeBodies.add(new SnakeBody(snakeHead.getX(), snakeHead.getY()));
        } else {
            snakeBodies.add(new SnakeBody(snakeBodies.get(snakeBodies.size() - 1).getX(), snakeBodies.get(snakeBodies.size() - 1).getY()));
        }

    }

    /**
     * Method to move a snake, it is called each tick in the loop
     * First each body part is moved to the position of the body part in front of it (with exception of the first body part after the head)
     * After that the first body part is moved to the position of the head
     * At last the head is moved in the appropriate direction
     */
    public void move() {
        //Move Bodies
        if (snakeBodies.size() >= 2) {
            for (int i = snakeBodies.size() - 1; i >= 1; i--) {

                snakeBodies.get(i).setX(snakeBodies.get(i - 1).getX());
                snakeBodies.get(i).setY(snakeBodies.get(i - 1).getY());

            }
        }

        //Move first Body to Head
        if (snakeBodies.size() >= 1) {

            snakeBodies.get(0).setX(snakeHead.getX());
            snakeBodies.get(0).setY(snakeHead.getY());

        }

        //Move Head
        switch (snakeHead.getDir()) {
            case RIGHT:
                snakeHead.setX(snakeHead.getX() + 1);
                break;
            case UP:
                snakeHead.setY(snakeHead.getY() - 1);
                break;
            case LEFT:
                snakeHead.setX(snakeHead.getX() - 1);
                break;
            case DOWN:
                snakeHead.setY(snakeHead.getY() + 1);
                break;
        }
    }


    public void increaseScore() {
        this.score += 10;
    }


    public SnakeHead getSnakeHead() {
        return snakeHead;
    }

    public void setSnakeHead(SnakeHead snakeHead) {
        this.snakeHead = snakeHead;
    }

    public ArrayList<SnakeBody> getSnakeBodies() {
        return snakeBodies;
    }

    public void setSnakeBodies(ArrayList<SnakeBody> snakeBodies) {
        this.snakeBodies = snakeBodies;
    }

    public SnakeColor getSnakeColor() {
        return snakeColor;
    }

    @JsonIgnore
    public String getSnakeColorAsString() {
        return snakeColor+"";
    }

    public void setSnakeColor(SnakeColor snakeColor) {
        this.snakeColor = snakeColor;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

package model.game.SnakeContents;

import javafx.scene.paint.Color;
import model.game.Direction;

import java.util.ArrayList;

/**
 * This class combines SnakeHead and SnakeBody, stores the current score and color of the player.
 *
 * @author Alexander Schleiter
 */
public class Snake {

    private int id;
    private String playername;
    private int score;
    private Color color;
    private SnakeHead snakeHead;
    private ArrayList<SnakeBody> snakeBodies = new ArrayList<>();


    public Snake(int x, int y) {

        this.snakeHead = new SnakeHead(x, y, Direction.RIGHT);
        this.score = 0;
    }

    public Snake(int id, String playername, int score, Color color, SnakeHead snakeHead, ArrayList<SnakeBody> snakeBodies) {
        this.id = id;
        this.playername = playername;
        this.score = score;
        this.color = color;
        this.snakeHead = snakeHead;
        this.snakeBodies = snakeBodies;
    }

    /**
     * Methode, um der Snake einen Body hinzuzufügen
     * Sollte noch kein Body vorhanden sein, wird der erste auf die Koordinaten des Head gesetzt,
     * ansonsten auf die des letzten Body
     */
    public void addSnakeBody() {
        if (snakeBodies.size() < 1) {
            snakeBodies.add(new SnakeBody(snakeHead.getX(), snakeHead.getY()));
        } else {
            snakeBodies.add(new SnakeBody(snakeBodies.get(snakeBodies.size() - 1).getX(), snakeBodies.get(snakeBodies.size() - 1).getY()));
        }

    }

    /**
     * Methode zum Bewegen einer Snake, die bei jedem Tick aufgerufen wird.
     * Zuerst wird jeder Body (bis auf den ersten) auf die Position des vorherigen Bodys gesetzt.
     * Anschließend der erste Body auf die Position des Heads.
     * Zuletzt wird der Head bewegt.
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

    /*
    @Override
    public String toString() {

        String returnString =
                "Snake: [ID="
                        + id
                        + ", Score="
                        + score
                        + ", Playername="
                        + playername
                        + ", SnakeHead="
                        + this.getSnakeHead().getX()
                        + ";"
                        + this.getSnakeHead().getY()
                        + ",";

        int index = 0;

        for (SnakeBody sb : this.getSnakeBodies()) {
            returnString = returnString + " SnakeBody" + index + "=" + sb.getX() + ";" + sb.getY() + ",";
            index++;
        }

        returnString = returnString.substring(0, returnString.length() - 1);
        returnString = returnString + "]";


        return returnString;
    }
    */

    public void increaseScore() {
        this.score += 10;
    }


    public SnakeHead getSnakeHead() {
        return snakeHead;
    }

    public void setSnakeHead(SnakeHead snakeHead) {
        snakeHead = snakeHead;
    }

    public ArrayList<SnakeBody> getSnakeBodies() {
        return snakeBodies;
    }

    public void setSnakeBodies(ArrayList<SnakeBody> snakeBodies) {
        snakeBodies = snakeBodies;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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

package model.game;

import java.util.ArrayList;

public class Snake {

    private static SnakeHead snakeHead = new SnakeHead();
    private static ArrayList<SnakeBody> snakeBodies = new ArrayList<>();


    public void addSnakeTail() {
        if (snakeBodies.size() < 1) {
            snakeBodies.add(new SnakeBody(snakeHead.getX(), snakeHead.getY()));
        } else {
            snakeBodies.add(new SnakeBody(snakeBodies.get(snakeBodies.size() - 1).getX(), snakeBodies.get(snakeBodies.size() - 1).getY()));
        }

    }

    public void move() {
        //Move Tails
        if (snakeBodies.size() >= 2) {
            for (int i = snakeBodies.size() - 1; i >= 1; i--) {

                snakeBodies.get(i).setX(snakeBodies.get(i - 1).getX());
                snakeBodies.get(i).setY(snakeBodies.get(i - 1).getY());

            }
        }

        //Move first Tail to Head
        if (snakeBodies.size() >= 1) {

            snakeBodies.get(0).setX(snakeHead.getX());
            snakeBodies.get(0).setY(snakeHead.getY());

        }

        //Move Head
        switch (snakeHead.getDir()) {
            case RIGHT:
                snakeHead.setX(snakeHead.getX() + draw.getAbstand());
                break;
            case UP:
                snakeHead.setY(snakeHead.getY() - draw.getAbstand());
                break;
            case LEFT:
                snakeHead.setX(snakeHead.getX() - draw.getAbstand());
                break;
            case DOWN:
                snakeHead.setY(snakeHead.getY() + draw.getAbstand());
                break;
        }
    }


    public static SnakeHead getSnakeHead() {
        return snakeHead;
    }

    public static void setSnakeHead(SnakeHead snakeHead) {
        Snake.snakeHead = snakeHead;
    }

    public static ArrayList<SnakeBody> getSnakeTails() {
        return snakeBodies;
    }

    public static void setSnakeTails(ArrayList<SnakeBody> snakeTails) {
        Snake.snakeBodies = snakeTails;
    }
}

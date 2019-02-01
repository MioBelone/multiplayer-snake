package model.game;

import java.util.ArrayList;

public class Snake {

    private SnakeHead snakeHead;
    private ArrayList<SnakeBody> snakeBodies = new ArrayList<>();

    public Snake(int x,int y){

        this.snakeHead = new SnakeHead(x,y);
    }




    /**
     * Methode, um der Snake einen Body hinzuzufügen
     * Sollte noch kein Body vorhanden sein, wird der erste auf die Koordinaten des Head gesetzt,
     *  ansonsten auf die des letzten Body
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
                snakeHead.setX(snakeHead.getX() +1);
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


    public  SnakeHead getSnakeHead() {
        return snakeHead;
    }

    public  void setSnakeHead(SnakeHead snakeHead) {
        snakeHead = snakeHead;
    }

    public  ArrayList<SnakeBody> getSnakeBodies() {
        return snakeBodies;
    }

    public  void setSnakeBodies(ArrayList<SnakeBody> snakeBodies) {
        snakeBodies = snakeBodies;
    }
}

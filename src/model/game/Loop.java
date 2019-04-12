package model.game;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.game.SnakeContents.Snake;
import presenter.PlaygroundPresenter;

/**
 * This class defines the steps that have to be taken each tick and keeps track if the game is still going on. It also defines the winner.
 *
 * @author Alexander Schleiter
 */
public class Loop extends Thread {

    public static boolean running = true;

    private SnakeGame sg;
    private Collision collision;
    private PlaygroundPresenter p;

    private Snake lastSnake1;
    private Snake lastSnake2;

    public Loop(SnakeGame sg, PlaygroundPresenter playgroundPresenter) {
        this.sg = sg;
        this.collision = new Collision();
        this.p = playgroundPresenter;
    }


    /**
     * Defines how fast the game plays and keeps track of the steps to be taken.
     */
    @Override
    public void run() {
        while (running) {
            try {
                sleep(200);
                sg.lockSnakes();
                for (Snake s : sg.getSnakes()) {
                    //move snakes
                    s.move();

                    //update player scores
                    sg.getScores().replace(s, s.getScore());
                }

                //check for collision
                collision.checkCollision(sg);

                //assign last snakes for the case they both collide with their head (because they get deleted)
                if (sg.getSnakes().size() == 2) {
                    lastSnake1 = sg.getSnakes().get(0);
                    lastSnake2 = sg.getSnakes().get(1);
                }


                //check if only one or no players left

                /*
                if (sg.getSnakes().size() <= 1) {

                    //checks for the rare case the last snakes collide with their heads,
                    //the snake with the higher score wins, if they're equal there is a draw
                    if (sg.getSnakes().size() == 0) {
                        if (lastSnake1.getScore() > lastSnake2.getScore()) {
                            sg.setWinner(lastSnake1);
                        } else if (lastSnake1.getScore() == lastSnake2.getScore()) {
                            sg.setWinner(null);
                        } else {
                            sg.setWinner(lastSnake2);
                        }
                    } else {
                        //set last remaining player as winner
                        sg.setWinner(sg.getSnakes().get(0));
                    }
                    //stop game
                    kill();
                }*/

                //mach in presenter

                try {
                    sg.sendGameInfo();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void kill() {
        running = false;
    }

}

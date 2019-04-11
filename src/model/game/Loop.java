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
                for (Snake s : sg.getSnakes()) {
                    //move snakes
                    s.move();

                    //update player scores
                    sg.getScores().replace(s, s.getScore());
                }

                //check for collision
                collision.checkCollision(sg);


                //check if only one or no players left
             /*   if (sg.getSnakes().size() <= 1) {

                    //set last remaining player as winner
                    sg.setWinner(sg.getSnakes().get(0));

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

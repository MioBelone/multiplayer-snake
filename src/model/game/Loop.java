package model.game;

import model.game.SnakeContents.Snake;

/**
 * This class defines the steps that have to be taken each tick and keeps track if the game is still going on. It also defines the winner.
 *
 * @author Alexander Schleiter
 */
public class Loop extends Thread{

    public static boolean running = true;

    SnakeGame sg;

    public Loop(SnakeGame sg){
        this.sg=sg;
    }


    /**
     * Defines how fast the game plays and keeps track of the steps to be taken.
     */
    public void run(){
        while(running){
            try {
                sleep(200);
                for(int i = 0;i<sg.getSnakes().size();i++){
                    //move snakes
                    sg.getSnakes().get(i).move();

                    //update player scores
                    sg.getScores().replace(sg.getSnakes().get(i),sg.getSnakes().get(i).getScore());
                }

                //check for collision
                sg.getCollision().checkCollision(sg);


                //check if players still alive
                if(sg.getSnakes().size()==0){

                    //if no players are left, decide who wins
                    int bestScore = 0;
                    for (Snake s : sg.getScores().keySet()){

                        if(s.getScore()>bestScore){
                            bestScore=s.getScore();
                            sg.setWinner(s);
                        }

                    }
                    //stop game
                    kill();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void kill(){
        running=false;
    }

}

package model.game;

/**
 * This class checks for every snake all types of possible collisions: 1. Wall 2. Self 3. Food 4.Other snakes
 *
 * @author Alexander Schleiter
 */
public class Collision {

    public void checkCollision(SnakeGame sg) {


        //check for every player
        for (int i = 0; i < sg.getSnakes().size(); i++) {
            int size = sg.getSnakes().size();

            //check for wall collision
            wallCollision(sg, i);

            //check for collision with self
            selfCollision(sg, i);

            //check for collision with food
            foodCollision(sg, i);

            //check for collision with other player
            playerCollision(sg, i);

            //check if a snake has been removed
            if(size<sg.getSnakes().size()){
                i--;
            }
        }
    }

    private void wallCollision(SnakeGame sg, int i) {
        //check for wall collision
        if (sg.getSnakes().get(i).getSnakeHead().getX() >= sg.getBreite() || sg.getSnakes().get(i).getSnakeHead().getY() >= sg.getBreite() || sg.getSnakes().get(i).getSnakeHead().getX() < 0 || sg.getSnakes().get(i).getSnakeHead().getY() < 0) {
            sg.getSnakes().remove(i);
            //i--;

        }
    }

    private void foodCollision(SnakeGame sg, int i) {
        //check all foods
        for (int j = 0; j < sg.getFoods().size(); j++) {
            if (sg.getSnakes().get(i).getSnakeHead().getX() == sg.getFoods().get(j).getX() && sg.getSnakes().get(i).getSnakeHead().getY() == sg.getFoods().get(j).getY()) {

                sg.getSnakes().get(i).addSnakeBody();
                sg.getSnakes().get(i).increaseScore();

                //ckecks if players are missing and lets food respawn or diappear
                if (sg.getFoods().size() == sg.getSnakes().size()) {
                    sg.getFoods().get(j).reset();
                    sg.checkForFoodPositions(sg.getFoods(), sg.getFoods().size());
                } else {
                    sg.getFoods().remove(j);
                }
            }
        }
    }

    private void selfCollision(SnakeGame sg, int i) {
        for (int j = 0; j < sg.getSnakes().get(j).getSnakeBodies().size(); j++) {
            if (sg.getSnakes().get(j).getSnakeHead().getX() == sg.getSnakes().get(j).getSnakeBodies().get(j).getX() && sg.getSnakes().get(j).getSnakeHead().getY() == sg.getSnakes().get(j).getSnakeBodies().get(j).getY()) {

                sg.getSnakes().remove(i);
                //i--;
                break;

            }
        }
    }

    private void playerCollision(SnakeGame sg, int i) {
        //check all other players
        for (int j = 0; j < sg.getSnakes().size(); j++) {

            //check that player i won't be compared with himself
            if (i != j) {
                //check for head of other player
                if (sg.getSnakes().get(i).getSnakeHead().getX() == sg.getSnakes().get(j).getSnakeHead().getX() && sg.getSnakes().get(i).getSnakeHead().getY() == sg.getSnakes().get(j).getSnakeHead().getY()) {
                    //if a collision with two snakes occurs, both will be deleted
                    sg.getSnakes().remove(i);
                    sg.getSnakes().remove(j);
                    break;
                }
                //check for every body part of current other player
                for (int k = 0; k < sg.getSnakes().get(j).getSnakeBodies().size(); k++) {
                    if (sg.getSnakes().get(i).getSnakeHead().getX() == sg.getSnakes().get(j).getSnakeBodies().get(k).getX() && sg.getSnakes().get(i).getSnakeHead().getY() == sg.getSnakes().get(j).getSnakeBodies().get(k).getY()) {
                        sg.getSnakes().remove(i);
                        //i--;
                        break;
                    }
                }
            }
        }
    }
}

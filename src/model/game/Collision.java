package model.game;

import model.game.SnakeContents.Snake;
import model.game.SnakeContents.SnakeBody;

/**
 * This class checks for every snake all types of possible collisions: 1. Wall 2. Self 3. Food 4.Other snakes
 *
 * @author Alexander Schleiter
 */
public class Collision {

    public void checkCollision(SnakeGame sg) {


        //check for every player
        for (Snake s : sg.getSnakes()) {

            //check for wall collision
            wallCollision(sg, s);

            //check for collision with self
            selfCollision(sg, s);

            //check for collision with food
            foodCollision(sg, s);

            //check for collision with other player
            playerCollision(sg, s);

            //check if a snake has been removed
            //if(size<sg.getSnakes().size()){
            //    i--;
            //}
        }
    }

    private void wallCollision(SnakeGame sg, Snake s) {
        //check for wall collision
        if (s.getSnakeHead().getX() >= sg.getBreite() || s.getSnakeHead().getY() >= sg.getBreite() || s.getSnakeHead().getX() < 0 || s.getSnakeHead().getY() < 0) {
            sg.getSnakes().remove(s);

        }
    }

    private void foodCollision(SnakeGame sg, Snake s) {
        //check all foods
        for (Food f : sg.getFoods()) {
            if (s.getSnakeHead().getX() == f.getX() && s.getSnakeHead().getY() == f.getY()) {

                s.addSnakeBody();
                sg.getPlaygroundPresenter().addPart(s.getSnakeBodies().get(s.getSnakeBodies().size()-1).getX(),s.getSnakeBodies().get(s.getSnakeBodies().size()-1).getY(),s.getId());
                s.increaseScore();
                //ckecks if players are missing and lets food respawn or diappear
                if (sg.getFoods().size() == sg.getSnakes().size()) {
                    f.reset();
                    sg.checkForFoodPositions(sg.getFoods());
                    sg.getPlaygroundPresenter().drawFood();
                } else {
                    sg.getFoods().remove(f);
                }
            }
        }
    }

    private void selfCollision(SnakeGame sg, Snake s) {

        for (SnakeBody sb : s.getSnakeBodies()) {
            if (s.getSnakeHead().getX() == sb.getX() && s.getSnakeHead().getY() == sb.getY()) {
                sg.getSnakes().remove(s);
                break;
            }
        }
    }

    private void playerCollision(SnakeGame sg, Snake s) {
        //check all other players
        for (Snake otherPlayer : sg.getSnakes()) {

            //check that player i won't be compared with himself
            if (!s.equals(otherPlayer)) {
                //check for head of other player
                if (s.getSnakeHead().getX() == otherPlayer.getSnakeHead().getX() && s.getSnakeHead().getY() == otherPlayer.getSnakeHead().getY()) {
                    //if a collision with two snakes occurs, both will be deleted
                    sg.getSnakes().remove(s);
                    sg.getSnakes().remove(otherPlayer);
                    break;
                }
                //check for every body part of current other player
                for (SnakeBody otherBody : otherPlayer.getSnakeBodies()) {
                    if (s.getSnakeHead().getX() == otherBody.getX() && s.getSnakeHead().getY() == otherBody.getY()) {
                        sg.getSnakes().remove(s);
                        break;
                    }
                }
            }
        }
    }
}

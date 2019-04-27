package model.game;

import model.game.SnakeContents.Snake;
import model.game.SnakeContents.SnakeBody;

/**
 * This class checks for every snake all types of possible collisions: 1. Wall 2. Self 3. Food 4.Other snakes
 *
 * @author Alexander Schleiter
 */
public class Collision {

    /**
     * Checks for every living snake in the game if it is colliding with either itself, a food, the wall or another snake
     * @param sg The current snake game played
     */
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

        }
    }

    /**
     * Checks whethter the snake collides with a wall and if it does lets it disappear.
     * @param sg The current snake game played
     * @param s A snake for which the collision will be checked
     */
    private void wallCollision(SnakeGame sg, Snake s) {
        //check for wall collision
        if (s.getSnakeHead().getX() >= sg.getBreite() || s.getSnakeHead().getY() >= sg.getBreite() || s.getSnakeHead().getX() < 0 || s.getSnakeHead().getY() < 0) {
            sg.getSnakes().remove(s);

        }
    }

    /**
     * Checks whether the snake collides with a food and if it does a body part will be added to the snake and the food respawns.
     * Should the number of snakes be greater than the number of foods, the food will not respawn.
     * @param sg The current snake game played
     * @param s A snake for which the collision will be checked
     */
    private void foodCollision(SnakeGame sg, Snake s) {
        //check all foods
        for (Food f : sg.getFoods()) {
            //if a snake collides with a food: add a snake and increase score
            if (s.getSnakeHead().getX() == f.getX() && s.getSnakeHead().getY() == f.getY()) {

                s.addSnakeBody();
                s.increaseScore();
                //ckecks if players are missing and lets food respawn or diappear
                if (sg.getFoods().size() == sg.getSnakes().size()) {
                    f.reset();
                    sg.checkForFoodPositions(sg.getFoods());
                } else {
                    sg.getFoods().remove(f);
                }
            }
        }
    }

    /**
     * Checks whether a snake collides with itself and if it does lets it disappear.
     * @param sg The current snake game played
     * @param s A snake for which the collision will be checked
     */
    private void selfCollision(SnakeGame sg, Snake s) {

        //check if snake head collides with its own body
        for (SnakeBody sb : s.getSnakeBodies()) {
            if (s.getSnakeHead().getX() == sb.getX() && s.getSnakeHead().getY() == sb.getY()) {
                sg.getSnakes().remove(s);
                break;
            }
        }
    }

    /**
     * Ckecks if the snake collides with any of the other snakes. If it does it will be deleted.
     * If a frontal collision occurs (the head coordinates of two snakes are identical) both will be deleted.
     * @param sg The current snake game played
     * @param s A snake for which the collision will be checked
     */
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

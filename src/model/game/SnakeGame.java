package model.game;

import java.util.ArrayList;

public class SnakeGame {

    //private Server server = new Server();
    //private Food allFoods[] = new Food[server.getClients.size()];
    private static ArrayList<Food> foods = new ArrayList<>();

    /*
    public SnakeGame() {

        for (int i = 0; i < server.getClients().size(); i++) {
            foods.set(i, new Food());


        }
    }*/

    public SnakeGame() {

        for (int i = 0; i < 2; i++) {
            foods.add(new Food());

        }
    }

    /**
     * Checks if the foods in a list have the same coordinates
     * @param foods
     * @param length
     */
    public void checkForFoodPositions(ArrayList<Food> foods, int length) {

        for (int i = 0; i < length; i++) {
            //Take food from list to compare it to other foods
            Food f = foods.get(i);

            //Compare taken food to other foods in list
            for (int j = 0; j < length; j++) {

                //if it is the same food object, skip it (because the values are obviously equal)
                if (i == j) {
                    break;
                } else {

                    //if the coordinates of the selected food are equal to another one, reset the selected food's coordinates
                    if (f.getX() == foods.get(j).getX() && f.getY() == foods.get(j).getY()) {
                        //Generate new coordinates
                        f.reset();
                        //If there is even one reset, coordinates have to be checked again to make sure the random numbers aren't equal.
                        checkForFoodPositions(foods, length);
                    }

                }
            }
        }
    }


}

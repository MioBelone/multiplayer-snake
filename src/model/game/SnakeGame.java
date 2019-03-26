package model.game;

import java.util.ArrayList;

public class SnakeGame {

    //private Server server = new Server();
    //private Food allFoods[] = new Food[server.getClients.size()];
    private static ArrayList<Food> foods = new ArrayList<>();
    private static ArrayList<Snake> snakes = new ArrayList<>();
    //private int clientSize=server.getClients.size(();
    private int clientSize = 7;
    private double uClientSize;
    private int breite = 100;
    private int quotient;
    private int sidespace = breite / 4;
    private Collision collision = new Collision();
    private Loop loop = new Loop(this);


    /*
    public SnakeGame() {

        for (int i = 0; i < server.getClients().size(); i++) {
            foods.set(i, new Food());


        }
    }*/

    public SnakeGame() {

        for (int i = 0; i < clientSize; i++) {
            foods.add(new Food());
        }

        generateStartingPositions();


        //loop.start();
    }

    /**
     * Checks if the foods in a list have the same coordinates
     *
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

    /**
     * Inititializes the starting postions for every snake and adds them to the ArrayList
     */
    public void generateStartingPositions(){
        if (clientSize % 2 == 0) {
            quotient = breite / (clientSize / 2);

            for (int i = 0; i < clientSize / 2; i++) {
                snakes.add(new Snake(sidespace, i * quotient + quotient / 2));
                snakes.add(new Snake(breite - sidespace, i * quotient + quotient / 2));
            }
        }

        if (clientSize % 2 != 0) {


            quotient = breite / (clientSize / 2+1);

            System.out.println(quotient);

            for (int i = 0; i < clientSize / 2 - 0.5; i++) {
                snakes.add(new Snake(sidespace, i * quotient + quotient / 2));
                snakes.add(new Snake(breite - sidespace, i * quotient + quotient / 2));
            }

            for (int i = clientSize / 2 ; i < clientSize / 2 +1; i++) {
                snakes.add(new Snake(sidespace, i * quotient + quotient / 2));
            }
        }
    }

    public static ArrayList<Food> getFoods() {
        return foods;
    }

    public static void setFoods(ArrayList<Food> foods) {
        SnakeGame.foods = foods;
    }

    public static ArrayList<Snake> getSnakes() {
        return snakes;
    }

    public static void setSnakes(ArrayList<Snake> snakes) {
        SnakeGame.snakes = snakes;
    }

    public int getClientSize() {
        return clientSize;
    }

    public void setClientSize(int clientSize) {
        this.clientSize = clientSize;
    }

    public double getuClientSize() {
        return uClientSize;
    }

    public void setuClientSize(double uClientSize) {
        this.uClientSize = uClientSize;
    }

    public int getBreite() {
        return breite;
    }

    public void setBreite(int breite) {
        this.breite = breite;
    }

    public int getQuotient() {
        return quotient;
    }

    public void setQuotient(int quotient) {
        this.quotient = quotient;
    }

    public int getSidespace() {
        return sidespace;
    }

    public void setSidespace(int sidespace) {
        this.sidespace = sidespace;
    }

    public Collision getCollision() {
        return collision;
    }

    public void setCollision(Collision collision) {
        this.collision = collision;
    }
}

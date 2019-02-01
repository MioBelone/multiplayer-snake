package model.game;

import model.server.Server;

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
            foods.set(i, new Food());


        }
    }

    public void checkForFoodPositions(ArrayList<Food> foods, int length){

        for( int i=0; i<length;i++){
            Food f =foods.get(i);

            for(int j = 0;j<length;j++){
                if(i==j){
                    break;
                }else{
                    if(f.getX()==foods.get(j).getX() && f.getY()==foods.get(j).getY()){
                        //erstelle neues food
                        f.reset();
                    }
                }
            }
        }
    }


}

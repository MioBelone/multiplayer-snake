package model.game;

public class Collision {

    public void checkCollision(SnakeGame sg) {


        //check for every player
        for (int i = 0; i < sg.getSnakes().size(); i++) {
            System.out.println(i+"current");
            //System.out.println(sg.getSnakes().size()+"size");
            //wall
            if (sg.getSnakes().get(i).getSnakeHead().getX() >= sg.getBreite() || sg.getSnakes().get(i).getSnakeHead().getY() >= sg.getBreite() || sg.getSnakes().get(i).getSnakeHead().getX() < 0 || sg.getSnakes().get(i).getSnakeHead().getY() < 0) {
                //System.out.println(sg.getSnakes().get(i).getSnakeHead().getX());
                //System.out.println(i+"remove");
                sg.getSnakes().remove(i);
                i--;
                System.out.println(sg.getSnakes().size()+"size after remove");

                //remove ein food

                /*
                Main.getL().kill();
                Main.setL(null);
                Main.setL(new Loop());
                System.out.println(Main.getL().getS().getSnakeHead().getCurrentX());
                Main.getL().start();
*/

            }

            //self
            for (int j = 0; j < sg.getSnakes().get(j).getSnakeBodies().size(); j++) {
                if (sg.getSnakes().get(j).getSnakeHead().getX() == sg.getSnakes().get(j).getSnakeBodies().get(j).getX() && sg.getSnakes().get(j).getSnakeHead().getY() == sg.getSnakes().get(j).getSnakeBodies().get(j).getY()) {

                    sg.getSnakes().remove(i);

                    //remove ein food

                    /*Main.getL().kill();
                    Main.getL().setS(null);
                    Main.getL().getS().setSnakeHead(null);
                    Main.setL(null);
                    Main.setL(new Loop());

                    Main.getL().setS(new Snake());
                    Main.getL().setSh(Main.getL().getSh());
                    d.paintGrid();

                    d.spawnSnakeHead(Main.getL().getS().getSnakeHead().getCurrentX(), (Main.getL().getS().getSnakeHead().getCurrentY()));
                    Main.setFood(new Food());
                    d.spawnFood(f);
                    Main.getL().start();*/

                }
            }

            //essen
            for (int j = 0; j < sg.getFoods().size(); j++) {
                if (sg.getSnakes().get(i).getSnakeHead().getX() == sg.getFoods().get(j).getX() && sg.getSnakes().get(i).getSnakeHead().getY() ==sg.getFoods().get(j).getY()) {

                    sg.getSnakes().get(i).addSnakeBody();
                    if(sg.getFoods().size()==sg.getSnakes().size()){
                        sg.getFoods().get(j).reset();
                        sg.checkForFoodPositions(sg.getFoods(),sg.getFoods().size());
                    }
                    else{
                        sg.getFoods().remove(j);
                    }
                }
            }


        }
    }
}

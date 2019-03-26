package model.game;

public class Loop extends Thread{

    public static boolean running = true;


    SnakeGame sg;

    public Loop(SnakeGame sg){
        this.sg=sg;
    }



    public void run(){
        while(running){
            try {
                sleep(200);
                for(int i = 0;i<sg.getSnakes().size();i++){
                    sg.getSnakes().get(i).move();
                }
                sg.getCollision().checkCollision(sg);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void kill(){
        running=false;
    }

}

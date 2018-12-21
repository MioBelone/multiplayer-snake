package model;

/**
 * This class saves and works with the data received from the users input on the specific view.
 *
 * @author Maximilian Gräfe
 */
public class InitialModel {

    private String username;
    private int port;
    private String ip;

    public void hostGame(String username, int port) {
        //Code um Lobby zu hosten
        System.out.println("Host Lobby");
    }

    public void joinGame(String username, int port, String ip) {
        //Code um Lobby beizutreten
        System.out.println("Join Lobby");
    }
}

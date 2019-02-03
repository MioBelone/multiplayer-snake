package presenter;

import javafx.stage.Stage;
import view.Playground;


/**
 * This class is the connection between the view and the model. It handles user input and prepares data to be shown
 * in the view.
 *
 * @author Fabian Haese
 */
public class PlaygroundPresenter {

    private Playground view;
    private Stage primaryStage;

    public PlaygroundPresenter(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.view = new Playground();
    }

    public PlaygroundPresenter() {
    }

    /**
     * In this method the .show method of the view is called to display the view to the user.
     */
    public void show() {
        view.show(primaryStage);
    }

}

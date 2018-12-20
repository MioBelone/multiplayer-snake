package presenter;

import view.InitialView;
import javafx.stage.Stage;
import model.InitialModel;

/**
 * This class is the connection between the view and the model. It handles user input and prepares data to be shown in the view.
 *
 * @author Maximilian Gräfe
 */
public class InitialViewPresenter {

    private InitialView view;
    private InitialModel initialModel;
    private Stage primaryStage;

    public InitialViewPresenter(Stage primaryStage, InitialModel initialModel) {
        this.primaryStage = primaryStage;
        this.initialModel = initialModel;
        this.view = new InitialView();
    }

    /**
     * In this method the .show method of the view is called to display the view to the user.
     */
    public void show() {
        view.show(primaryStage);
    }
}

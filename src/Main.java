import presenter.InitialViewPresenter;
import javafx.application.Application;
import javafx.stage.Stage;
import model.InitialModel;

/**
 * This class initialises the program and displays the first view to the User.
 *
 * @author Maximilian Gräfe
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * In this method, the initial view and it's controller is declared and shown.
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        //Initialising the first model which needs to be used for the start screen of the program
        InitialModel initialModel = new InitialModel();

        //Initialising the start presenter which handles the view and the model
        InitialViewPresenter initialViewPresenter = new InitialViewPresenter(primaryStage, initialModel);
        initialViewPresenter.show();
    }
}

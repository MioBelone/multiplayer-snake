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

        InitialModel initialModel = new InitialModel();

        InitialViewPresenter initialViewPresenter = new InitialViewPresenter(primaryStage, initialModel);
        initialViewPresenter.show();
    }
}

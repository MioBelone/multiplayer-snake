package validation;

import view.LobbyHost;

/**
 * This class handles all the validation for the LobbyHost.
 *
 * @author Maximilian Gräfe
 */
public class lobbyHostValidator {

    /**
     * This method resets the previous validation to ensure no false information.
     *
     * @param view the view on which the validation must be reset
     */
    private static void resetValidation(LobbyHost view) {
        //Resetting error-label of hosting
        view.getLblErrFieldSize().setText("");
        view.getLblErrGameSpeed().setText("");
        view.getLblErrFoodCount().setText("");

        //Resetting text field css of joining
        view.getTfFieldSize().getStyleClass().clear();
        view.getTfFieldSize().getStyleClass().addAll("text-field", "text-input");
        view.getTfGameSpeed().getStyleClass().clear();
        view.getTfGameSpeed().getStyleClass().addAll("text-field", "text-input");
        view.getTfFoodCount().getStyleClass().clear();
        view.getTfFoodCount().getStyleClass().addAll("text-field", "text-input");

        //Resetting info label
        view.getLblNotice().setText("");
    }

    /**
     * This method does all the validation for all text fields of LobbyHost.
     *
     * @param view the view which must be validated
     * @return if the validation was successful (true) or not (false) as boolean
     */
    public static boolean validateSettings(LobbyHost view) {
        boolean validation = true;

        //Reset last validation
        resetValidation(view);

        //Check if field size is empty
        if(!textfieldValidator.textfieldContainsIntAboveZero(view.getTfFieldSize())) {
            validation = false;
            view.getTfFieldSize().getStyleClass().add("text-field-error");
            view.getLblErrFieldSize().setText("Geben sie eine positive Zahl ein!");
        }

        //Check if game speed is empty
        if(!textfieldValidator.textfieldContainsIntAboveZero(view.getTfGameSpeed())) {
            validation = false;
            view.getTfGameSpeed().getStyleClass().add("text-field-error");
            view.getLblErrGameSpeed().setText("Geben sie eine positive Zahl ein!");
        }

        //Check if food count is empty
        if(!textfieldValidator.textfieldContainsIntAboveZero(view.getTfFoodCount())) {
            validation = false;
            view.getTfFoodCount().getStyleClass().add("text-field-error");
            view.getLblErrFoodCount().setText("Geben sie eine positive Zahl ein!");
        }

        return validation;
    }
}

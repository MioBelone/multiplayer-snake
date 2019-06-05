package validation;

import view.InitialView;

public class initialViewValidator {

    /**
     * This method resets the previous validation to ensure no false information.
     *
     * @param view the view on which the validation must be reset
     */
    public static void resetValidation(InitialView view) {
        //Resetting error-label of hosting
        view.getLblHostPort().setText("");
        view.getLblHostName().setText("");

        //Resetting error-label of joining
        view.getLblJoinName().setText("");
        view.getLblJoinPort().setText("");
        view.getLblJoinIp().setText("");

        //Resetting textfield css of hosting
        view.getTfUserNameHost().getStyleClass().clear();
        view.getTfUserNameHost().getStyleClass().addAll("text-field", "text-input");
        view.getTfPortHost().getStyleClass().clear();
        view.getTfPortHost().getStyleClass().addAll("text-field", "text-input");

        //Resetting textfield css of joining
        view.getTfUserNameJoin().getStyleClass().clear();
        view.getTfUserNameJoin().getStyleClass().addAll("text-field", "text-input");
        view.getTfPortJoin().getStyleClass().clear();
        view.getTfPortJoin().getStyleClass().addAll("text-field", "text-input");
        view.getTfIpJoin().getStyleClass().clear();
        view.getTfIpJoin().getStyleClass().addAll("text-field", "text-input");
    }

    /**
     * This method does all the validation for all text fields on the input view of hosting a lobby.
     *
     * @param view the view which must be validated
     * @return if the validation was successful (true) or not (false) as boolean
     */
    public static boolean validateHost(InitialView view) {
        boolean validation = true;

        //Reset last validation
        resetValidation(view);

        //Check if username is empty
        if(!textfieldValidator.isTextfieldNotEmpty(view.getTfUserNameHost())) {
            validation = false;
            view.getTfUserNameHost().getStyleClass().add("text-field-error");
            view.getLblHostName().setText("Geben sie einen Benutzernamen ein");
        }

        //Check if port is empty
        if(!textfieldValidator.isTextfieldNotEmpty(view.getTfPortHost())) {
            validation = false;
            view.getTfPortHost().getStyleClass().add("text-field-error");
            view.getLblHostPort().setText("Geben sie einen Port ein");
        } else {
            if(!textfieldValidator.textfieldContainsPort(view.getTfPortHost())) {
                validation = false;
                view.getTfPortHost().getStyleClass().add("text-field-error");
                view.getLblHostPort().setText("Der Port ist nicht gültig (0000-9999)");
            }
        }

        return validation;
    }

    /**
     * This method does all the validation for all text fields on the input view of joining a lobby.
     *
     * @param view the view which must be validated
     * @return if the validation was successful (true) or not (false) as boolean
     */
    public static boolean validateJoin(InitialView view) {
        boolean validation = true;

        //Reset last validation
        resetValidation(view);

        //Check if username is empty
        if(!textfieldValidator.isTextfieldNotEmpty(view.getTfUserNameJoin())) {
            validation = false;
            view.getTfUserNameJoin().getStyleClass().add("text-field-error");
            view.getLblJoinName().setText("Geben sie einen Benutzernamen ein");
        }

        //Check if port is empty
        if(!textfieldValidator.isTextfieldNotEmpty(view.getTfPortJoin())) {
            validation = false;
            view.getTfPortJoin().getStyleClass().add("text-field-error");
            view.getLblJoinPort().setText("Geben sie einen Port ein");
        } else {
            //Check if port has correct format
            if(!textfieldValidator.textfieldContainsPort(view.getTfPortJoin())) {
                validation = false;
                view.getTfPortJoin().getStyleClass().add("text-field-error");
                view.getLblJoinPort().setText("Der Port ist nicht gültig (0000-9999)");
            }
        }

        //Check if IP is empty
        if(!textfieldValidator.isTextfieldNotEmpty(view.getTfIpJoin())) {
            validation = false;
            view.getTfIpJoin().getStyleClass().add("text-field-error");
            view.getLblJoinIp().setText("Geben sie eine IP-Adresse ein");
        } else {
            //Check if IP has correct format
            if(!textfieldValidator.textfieldContainsIP(view.getTfIpJoin())) {
                validation = false;
                view.getTfIpJoin().getStyleClass().add("text-field-error");
                view.getLblJoinIp().setText("Geben sie eine korrekte IP-Adresse ein (Bsp.: 192.168.178.123)");
            }
        }

        return validation;
    }
}

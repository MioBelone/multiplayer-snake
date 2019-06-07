package validation;

import javafx.scene.control.TextField;

/**
 * This class contains all different types of text field validations which can be called by other classes.
 *
 * @author Maximilian Gräfe
 */
public class textfieldValidator {

    /**
     * This method checks if the input text field is empty or not.
     *
     * @param tf the text field which must be checked
     * @return if the text field is empty (false) or not (true) as boolean
     */
    public static boolean isTextfieldNotEmpty(TextField tf) {
        if(!tf.getText().equals("") && tf.getText() != null && !tf.getText().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method checks if the value of the input text field matches a port.
     *
     * @param tf the text field which must be checked
     * @return if the text field value matches a port as boolean
     */
    public static boolean textfieldContainsPort( TextField tf) {
        if(tf.getText().matches("[0-9]{1,5}")) {
            int port = Integer.parseInt(tf.getText());
            if(port >= 1024 && port <= 61000) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * This method checks if the value of the input text field matches an ip address.
     *
     * @param tf the text field which must be checked
     * @return if the text field value matches an ip address as boolean
     */
    public static boolean textfieldContainsIP (TextField tf) {
        if(tf.getText().matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}") || tf.getText().equals("localhost")) {
            return true;
        } else {
            return false;
        }
    }
}

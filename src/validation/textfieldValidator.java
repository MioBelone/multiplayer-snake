package validation;

import javafx.scene.control.TextField;

public class textfieldValidator {
    public static boolean isTextfieldNotEmpty(TextField tf) {
        if(!tf.getText().equals("") && tf.getText() != null && !tf.getText().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean textfieldContainsPort( TextField tf) {
        if(tf.getText().matches("[0-9]{1,4}")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean textfieldContainsIP (TextField tf) {
        if(tf.getText().matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}") || tf.getText().equals("localhost")) {
            return true;
        } else {
            return false;
        }
    }
}

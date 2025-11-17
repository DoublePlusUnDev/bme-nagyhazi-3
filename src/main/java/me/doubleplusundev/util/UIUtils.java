package me.doubleplusundev.util;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * A class that contains slef made helper methods to make working with UI easier.
 */
public class UIUtils {
    private UIUtils() {

    }

    /**
     * A default value can be set to a JTextField, which if the field is empty will be displayed in lightgrey color.
     * If the user enters a value that will be displayed.
     * Most UI libs allow for a something similar, however i could find no inbuilt solution for Swing, so i had to roll my own implementation.
     * @param field
     * @param placeHolderText
     */
    public static void addPlaceHolder(JTextField field, String placeHolderText) {
        Color placeHolderColor = Color.LIGHT_GRAY;
        field.setForeground(placeHolderColor);
        field.setText(placeHolderText);

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent event) {
                if (field.getForeground() == placeHolderColor) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent event) {
                if (field.getText().isEmpty()) {
                    field.setForeground(placeHolderColor);
                    field.setText(placeHolderText);
                }
            }
        });
    }
    
    /**
     * When an exception occurs, allows the exception to be displayed in a pop-up windows to the user.
     * @param exception
     */
    public static void showException(Exception exception) {
        SwingUtilities.invokeLater(() -> 
            JOptionPane.showMessageDialog(null, 
            "We run into an error: " + exception.getMessage() + "\n:(\nPlease contant your local system administrator!", "Error", JOptionPane.ERROR_MESSAGE)
        );
    }
}

package me.doubleplusundev.util;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class UIUtils {
    private UIUtils() {

    }

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
    
    public static void showException(Exception exception) {
        SwingUtilities.invokeLater(() -> 
            JOptionPane.showMessageDialog(null, 
            "We run into an error: " + exception.getMessage() + "\n:(\nPlease contant your local system administrator!", "Error", JOptionPane.ERROR_MESSAGE)
        );
    }
}

package me.doubleplusundev.ui;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ExceptionUI {
    private ExceptionUI() {

    }

    public static void showException(Exception exception) {
        SwingUtilities.invokeLater(() -> 
            JOptionPane.showMessageDialog(null, 
            "We run into an error: " + exception.getMessage() + "\n:(\nPlease contant your local system administrator!", "Error", JOptionPane.ERROR_MESSAGE)
        );
    }
}

import javafx.scene.control.Button;

/**
 * Store the layout of the buttons to be generated.
 */
public class Generate {
    /**
     * Generate the button of pre-defined layout
     * @param text text of the button
     * @param x x coordinate
     * @param y y coordinate
     * @param width width of button
     * @param height height of the button
     * @return the button generated
     */
    public static Button createButton(String text, double x, double y, double width, double height) {
        Button buttonUI = new Button();
        buttonUI.setText(text);
        buttonUI.setMinWidth(width);
        buttonUI.setMinHeight(height);
        buttonUI.prefWidth(150);
        buttonUI.maxWidth(150);
        buttonUI.minWidth(150);
        buttonUI.prefHeight(50);
        buttonUI.maxHeight(50);
        buttonUI.minHeight(50);
        return buttonUI;
    }
}

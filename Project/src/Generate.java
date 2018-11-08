import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Generate {
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

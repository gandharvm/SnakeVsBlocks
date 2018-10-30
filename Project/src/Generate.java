import javafx.scene.control.Button;

public class Generate {
    public static Button createButton(String text, double x, double y, double width, double height) {
        Button buttonUI = new Button();
        buttonUI.setText(text);
        buttonUI.setLayoutX(x);
        buttonUI.setLayoutY(y);
        buttonUI.setMinWidth(width);
        buttonUI.setMinHeight(height);

        return buttonUI;
    }
}

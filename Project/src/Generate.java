import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

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

    public static Scene createScene(Group root){
        Scene scene=new Scene(root);
        return scene;
    }
}

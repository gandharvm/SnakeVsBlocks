
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Snake {

	private Color color = Color.RED;
	private VBox vbox;
	
	public Snake() {
		
		vbox = new VBox();
		
		for(int i=0; i<5; i++) {
			Circle temp = new Circle(10);
			temp.setFill(color);
			vbox.getChildren().add(temp);
		}

		vbox.setLayoutX(300);
		vbox.setLayoutY(400);
		
	}
	
	public VBox getSnake() {
		return vbox;
	}
	
	
}

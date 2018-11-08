
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Snake {
	
	private int radius;
	private Color color = Color.RED;
	private VBox vbox;
	
	public Snake(int radius) {
		this.radius = radius;
		
		vbox = new VBox();
		
		for(int i=0; i<5; i++) {
			Circle temp = new Circle(radius);
			temp.setFill(color);
			vbox.getChildren().add(temp);
		}
		
	}
	
	public VBox getSnake() {
		return vbox;
	}
	
	
}

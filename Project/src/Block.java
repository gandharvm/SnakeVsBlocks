
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Block extends Rectangle {
		
	private int length;
	private int width;
	private Color mainColor;
	private Color strokeColor;
	
	public Block(int length, int width, Color colorValue) {
		this.length = length;
		this.width = width;
		this.mainColor = colorValue;
		this.strokeColor = Color.TRANSPARENT;
		this.setArcHeight(20);
		this.setArcWidth(20);
		
		setWidth(this.width);
		setHeight(this.length);
		setFill(this.mainColor);
		setStroke(this.strokeColor);

	}
	
	
}

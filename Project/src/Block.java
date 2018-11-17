
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Block extends Rectangle {
		
	private int length;
	private int width;
	private Color mainColor;
	private Color strokeColor;
	
	public Block( Color colorValue) {
		this.length = 75;
		this.width = 75;
		this.mainColor = colorValue;
		this.strokeColor = Color.TRANSPARENT;
		this.setArcHeight(20);
		this.setArcWidth(20);
		
		setWidth(this.width);
		setHeight(this.length);
		setFill(this.mainColor);
		setStroke(this.strokeColor);

	}


	public int getLength() {
		return length;
	}
}

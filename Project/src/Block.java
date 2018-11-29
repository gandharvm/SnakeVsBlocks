
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import java.io.Serializable;

public class Block extends Rectangle implements Serializable {
		
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

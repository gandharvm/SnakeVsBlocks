
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import java.io.Serializable;


/**
 * This class creates the individual blocks for the game
 */
public class Block extends Rectangle implements Serializable {

	/**
	 * Constructor for the class. Creates a block of length and breadth 75.
	 * @param colorValue The colour of this block
	 */
	public Block(Color colorValue) {
		this.setArcHeight(20);
		this.setArcWidth(20);
		
		setWidth(75);
		setHeight(75);
		setFill(colorValue);
		setStroke(Color.TRANSPARENT);

	}

}

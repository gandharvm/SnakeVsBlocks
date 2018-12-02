import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represent the wall in the game
 */
public class Wall extends Rectangle {
    /**
     * Length of the wall
     */
    private int length;

    /**
     * Constructor to initialize tha wall as per the length
     * @param length length of wall
     */
    public Wall(int length){
        this.length=length;
        this.setArcHeight(10);
        this.setArcWidth(10);

        setWidth(8);
        setHeight(this.length);
        setFill(Color.WHITE);
    }

    /**
     * Get the length of the wall
     * @return length of the wall
     */
    public int getLength() {
        return length;
    }


}

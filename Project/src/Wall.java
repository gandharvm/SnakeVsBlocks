import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall extends Rectangle {
    private int length;
    private int width;
    private Color mainColor;
    private Color strokeColor;

    public Wall(int length){
        this.length=length;
        this.width=8;
        this.mainColor= Color.WHITE;
        this.setArcHeight(10);
        this.setArcWidth(10);

        setWidth(this.width);
        setHeight(this.length);
        setFill(this.mainColor);
    }

    public int getLength() {
        return length;
    }
}

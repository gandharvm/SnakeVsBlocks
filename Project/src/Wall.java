import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

public class Wall extends Rectangle implements Serializable {
    private int length;
    private int width;
    private Color mainColor;
    private double xPos,yPos;

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

    public double getyPos() {
        return yPos;
    }

    public void updatePosition(){
        this.xPos=this.getLayoutX();
        this.yPos=this.getLayoutY();
    }
}

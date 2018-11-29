import javafx.scene.image.ImageView;

import java.io.Serializable;

public abstract class Tokens implements Serializable {
    private int xPos,yPos;

    public abstract void useToken();

    public void setPosition(int xPos,int yPos){
        this.xPos=xPos;
        this.yPos=yPos;
    }

    public abstract ImageView getView();
}

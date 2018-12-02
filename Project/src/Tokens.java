import javafx.scene.image.ImageView;

import java.io.Serializable;

public abstract class Tokens {
    protected ImageView imageView;

    public abstract void useToken();


    public ImageView getView() {
        return imageView;
    }
}

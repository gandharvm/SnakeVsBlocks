import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

public class Magnet extends Tokens implements Serializable {
    private final int TIME=5;


    public Magnet() {

    }

    public ImageView addMagnet(int xPos) throws FileNotFoundException{
        FileInputStream magnetStream = new FileInputStream ("src\\magnet.png");
        Image magnetImage = new Image(magnetStream,30,30,true,true);
        imageView = new ImageView(magnetImage);

        imageView.setLayoutX(xPos);
        imageView.setLayoutY(-100);
        return imageView;
    }

    @Override
    public void useToken() {

    }
}

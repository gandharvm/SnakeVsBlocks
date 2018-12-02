import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Shield extends Tokens{
    private final int TIME=5;


    public Shield() {

    }

    public ImageView addShield(int xPos) throws FileNotFoundException{
        FileInputStream shieldStream = new FileInputStream ("src\\shield.png");
        Image shieldImage = new Image(shieldStream,35,35,true,true);
        imageView = new ImageView(shieldImage);

        imageView.setLayoutX(xPos);
        imageView.setLayoutY(-100);
        return imageView;
    }

    @Override
    public void useToken() {

    }
}

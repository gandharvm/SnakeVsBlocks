import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Shield extends Tokens {
    private final int TIME=5;
    private ImageView shieldView;

    public Shield() {

    }

    public ImageView addShield(int xPos) throws FileNotFoundException{
        FileInputStream shieldStream = new FileInputStream ("src\\shield.png");
        Image shieldImage = new Image(shieldStream,35,35,true,true);
        shieldView = new ImageView(shieldImage);

        shieldView.setLayoutX(xPos);
        shieldView.setLayoutY(-100);
        return shieldView;
    }

    @Override
    public void useToken() {

    }
}

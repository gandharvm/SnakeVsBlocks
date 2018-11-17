import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Magnet extends Tokens {
    private final int TIME=5;
    private ImageView magnetView;

    public Magnet() {

    }

    public ImageView addMagnet(int xPos) throws FileNotFoundException{
        FileInputStream magnetStream = new FileInputStream ("src\\magnet.png");
        Image magnetImage = new Image(magnetStream,30,30,true,true);
        magnetView = new ImageView(magnetImage);

        magnetView.setLayoutX(xPos);
        magnetView.setLayoutY(-100);
        return magnetView;
    }

    @Override
    public void useToken() {

    }
}

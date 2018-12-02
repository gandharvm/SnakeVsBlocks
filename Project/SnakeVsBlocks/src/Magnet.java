import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Type of token to attract all the coins within a certain distance of the snake.
 */
public class Magnet extends Tokens{

    /**
     * Default Constructor
     */
    public Magnet() {

    }

    /**
     * Add the image to the image view for the magnet and set its layout.
     * @param xPos value of the x coordinate of the token
     * @return Image View of the icon for this token
     * @throws FileNotFoundException if location of image is not found
     */
    public ImageView addMagnet(int xPos) throws FileNotFoundException{
        FileInputStream magnetStream = new FileInputStream ("src\\magnet.png");
        Image magnetImage = new Image(magnetStream,30,30,true,true);
        imageView = new ImageView(magnetImage);

        imageView.setLayoutX(xPos);
        imageView.setLayoutY(-100);
        return imageView;
    }

}

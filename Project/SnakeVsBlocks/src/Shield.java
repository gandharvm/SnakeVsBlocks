import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * A type of token that is used to move the snake without reducing its length.
 */
public class Shield extends Tokens{

    /**
     * Default Constructor
     */
    public Shield() {

    }

    /**
     * Add the image to the image view for the destroy blocks and set its layout.
     * @param xPos value of the x coordinate of the token
     * @return Image View of the icon for this token
     * @throws FileNotFoundException if location of image is not found
     */
    public ImageView addShield(int xPos) throws FileNotFoundException{
        FileInputStream shieldStream = new FileInputStream ("src\\shield.png");
        Image shieldImage = new Image(shieldStream,35,35,true,true);
        imageView = new ImageView(shieldImage);

        imageView.setLayoutX(xPos);
        imageView.setLayoutY(-100);
        return imageView;
    }

}

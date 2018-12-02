import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * A type of token that is used to remove/destroy all the blocks on the screen.
 */
public class DestroyBlocks extends Tokens {

    /**
     * Default Constructor
     */
    public DestroyBlocks() {

    }

    /**
     * Add the image to the image view for the destroy blocks and set its layout.
     * @param xPos value of the x coordinate of the token
     * @return Image View of the icon for this token
     * @throws FileNotFoundException if location of image is not found
     */
    public ImageView addDestroyBlocks(int xPos) throws FileNotFoundException {
        FileInputStream destroyStream = new FileInputStream ("src\\Destroy.png");
        Image destroyImage = new Image(destroyStream,35,35,true,true);
        imageView = new ImageView(destroyImage);

        imageView.setLayoutX(xPos);
        imageView.setLayoutY(-100);
        return imageView;
    }

}

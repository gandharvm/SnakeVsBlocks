import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

public class DestroyBlocks extends Tokens implements Serializable {


    public DestroyBlocks() {

    }

    public ImageView addDestroyBlocks(int xPos) throws FileNotFoundException {
        FileInputStream destroyStream = new FileInputStream ("src\\Destroy.png");
        Image destroyImage = new Image(destroyStream,35,35,true,true);
        imageView = new ImageView(destroyImage);

        imageView.setLayoutX(xPos);
        imageView.setLayoutY(-100);
        return imageView;
    }

    @Override
    public void useToken() {

    }
}

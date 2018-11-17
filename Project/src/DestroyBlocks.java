import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DestroyBlocks extends Tokens {
    private ImageView destroyView;

    public DestroyBlocks() {

    }

    public ImageView addDestroyBlocks(int xPos) throws FileNotFoundException {
        FileInputStream destroyStream = new FileInputStream ("src\\Destroy.png");
        Image destroyImage = new Image(destroyStream,35,35,true,true);
        destroyView = new ImageView(destroyImage);

        destroyView.setLayoutX(xPos);
        destroyView.setLayoutY(-100);
        return destroyView;
    }

    @Override
    public void useToken() {

    }
}

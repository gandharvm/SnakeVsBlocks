import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * This class is for the properties of a coin which is a token. The coin is used to increase the length of the snake.
 */
public class Coin extends Tokens {
    /**
     * Stores the value of the coin.
     */
    private int value;

    /**
     * Stores the Group that encloses the image and value of the coin.
     */
    private Group coinGroup;

    /**
     * Get the coinGroup of the coin
     * @return coin group
     */
    public Group getCoinGroup() {
        return coinGroup;
    }

    /**
     * Constructor to assign the given value as the value of the coin
     * @param value value of the coin
     */
    public Coin(int value) {
        this.value = value;
    }

    /**
     * Get the value of this coin
     * @return Value of the coin
     */
    public int getValue() {
        return value;
    }

    /**
     * Add the image of the coin in the image view from the super class-token, create a text field for the value of the token and add these to the coinGroup
     * @param xPos the x coordinate of the coin.
     * @return The coinGroup after adding the image and the text fields.
     * @throws FileNotFoundException if location of image is not found
     */
    public Group addCoin(int xPos) throws FileNotFoundException {
        FileInputStream coinStream = new FileInputStream ("src\\coin.png");
        Image coinImage = new Image(coinStream,35,35,true,true);
        imageView = new ImageView(coinImage);

        imageView.setLayoutX(xPos);
        imageView.setLayoutY(-100);

        Text coinText=new Text(Integer.toString(this.value));
        coinText.setFill(Color.WHITE);
        coinText.setLayoutX(xPos+5);
        coinText.setLayoutY(-100);
        coinText.setFont(Font.font(16));

        coinGroup=new Group(coinText,imageView);
        return coinGroup;
    }

}

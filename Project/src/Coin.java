import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Coin extends Tokens {
    private int value;
    private ImageView coinView;
    private Group coinGroup;

    public Coin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Group addCoin(int xPos) throws FileNotFoundException {
        FileInputStream coinStream = new FileInputStream ("src\\coin.png");
        Image coinImage = new Image(coinStream,35,35,true,true);
        coinView = new ImageView(coinImage);

        coinView.setLayoutX(xPos);
        coinView.setLayoutY(-100);

        Text coinText=new Text(Integer.toString(this.value));
        coinText.setFill(Color.WHITE);
        coinText.setLayoutX(xPos+5);
        coinText.setLayoutY(-100);
        coinText.setFont(Font.font(16));

        coinGroup=new Group(coinText,coinView);
        return coinGroup;
    }

    @Override
    public void useToken(){

    }
}

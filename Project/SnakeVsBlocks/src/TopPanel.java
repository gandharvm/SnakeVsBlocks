import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The top panel contains the Text field for the current score of the player and the pause button
 */
public class TopPanel {
    /**
     * Text for the current score of the player
     */
    private Text score;
    /**
     * Horizontal box that contains the score and pause button
     */
    private HBox top;
    /**
     * Button to pause the game
     */
    private Button pause;
    /**
     * The data to be serialized in case the game is paused
     */
    private Data data;

    /**
     *  Get the Horizontal box that contains the score and pause button
     * @return Horizontal box that contains the score and pause button
     */
    public HBox getTop() {
        return top;
    }

    /**
     * Constructor to generate the top panel
     * @param sc current score of the player
     * @param game current instance of the game
     * @param sceneManager sceneManager to communicate between different scenes
     * @param data Data to be serialized in case pause butoon is pressed
     */
    TopPanel(Text sc, Game game, SceneManager sceneManager, Data data){
        top=new HBox();
        this.data=data;
        pause=Generate.createButton("",300.0,10.0,30,30);
        pause.setBackground(Background.EMPTY);
        top.setStyle("-fx-background-color: #000000;");

        try {
            FileInputStream inputStream=new FileInputStream ("src\\Pauseb.png");
            Image image=new Image(inputStream,30,30,true,true);
            ImageView imageView=new ImageView(image);
            pause.setGraphic(imageView);
        }

        catch (FileNotFoundException e){
            //Exception will not occur(Checked exception)
        }

        pause.setOnAction(e ->{
            try{
                Data.serialize(this.data);
            }
            catch (IOException ex){

            }
            game.getTimer().stop();
            sceneManager.Pause();
        });

        Region x=new Region();

        HBox.setHgrow(x, Priority.ALWAYS);

        top.setPrefWidth(600);

        score=sc;
        score.setFill(Color.WHITE);
        score.setFont(Font.font(16));

        top.getChildren().addAll(score,x,pause);


    }
}

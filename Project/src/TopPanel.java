import javafx.scene.Group;
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

public class TopPanel {
    private Text score;
    private HBox top;
    private Button pause;
    private Data data;

    public HBox getTop() {
        return top;
    }

    TopPanel(Text sc, Game game, SceneManager sceneManager, Data data){
        top=new HBox();
        this.data=data;
        pause=Generate.createButton("",300.0,10.0,30,30);
        pause.setBackground(Background.EMPTY);
        top.setStyle("-fx-background-color: #000000;");

        try {
            FileInputStream inputStream=new FileInputStream ("src\\Pauseb.png");
            javafx.scene.image.Image image=new javafx.scene.image.Image(inputStream,30,30,true,true);
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
        Region y=new Region();

        HBox.setHgrow(x, Priority.ALWAYS);
        HBox.setHgrow(y,Priority.ALWAYS);

        top.setPrefWidth(600);

        score=sc;
        score.setFill(Color.WHITE);
        score.setFont(Font.font(16));

        try {
            FileInputStream inputStream=new FileInputStream ("src\\coin.png");
            javafx.scene.image.Image image=new Image(inputStream,30,30,true,true);
            ImageView imageView=new ImageView(image);
            top.getChildren().addAll(score,x,imageView,y,pause);

        }

        catch (FileNotFoundException e){
            //Exception will not occur(Checked exception)
        }
    }
}

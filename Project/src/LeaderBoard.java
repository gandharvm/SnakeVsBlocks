import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class LeaderBoard {
    private SceneManager sceneManager;
    private GridPane root;
    private Scene scene;

    LeaderBoard(SceneManager sceneManager){
        this.sceneManager=sceneManager;

    }

    public Scene start(){
        root=new GridPane();
        root.setBackground(Background.EMPTY);
        scene=new Scene(root,600,600, Color.BLACK);
        root.setAlignment(Pos.TOP_CENTER);
        addButtons();
        return scene;
    }

    private void addButtons(){
        Button back=Generate.createButton("Back",300.0,400.0,50,20);
        back.setOnAction(e ->{
            sceneManager.showMainMenu();
        });


        try {
            FileInputStream inputStream=new FileInputStream ("src\\Leaderboard.png");
            Image image=new Image(inputStream,400,50,true,true);
            ImageView i=new ImageView(image);
            root.add(i,1,0);
            GridPane.setHalignment(i, HPos.CENTER);
            GridPane.setValignment(i, VPos.CENTER);
        }

        catch (FileNotFoundException e){

        }

        Text text=new Text();
        text.setText("Date");                   //add text here
        text.setFill(Color.WHITE);
        text.setFont(Font.font(20));
        root.add(text,0,1);

        Text text1=new Text();
        text1.setText("Score");                                           //add text here
        text1.setFill(Color.WHITE);
        text1.setFont(Font.font(20));
        root.add(text1,2,1);

        root.add(back,1,2);
        GridPane.setHalignment(back, HPos.CENTER);
        GridPane.setValignment(back, VPos.CENTER);
    }
}

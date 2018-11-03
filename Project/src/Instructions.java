import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Instructions {
    private SceneManager sceneManager;
    private GridPane root;
    private Scene scene;

    Instructions(SceneManager sceneManager){
        this.sceneManager=sceneManager;

    }

    public Scene start(){
        root=new GridPane();
        root.setBackground(Background.EMPTY);
        scene=new Scene(root,600,600, Color.BLACK);
        root.setAlignment(Pos.CENTER);
        addButtons();
        return scene;
    }

    private void addButtons(){
        Button back=Generate.createButton("Back",300.0,400.0,50,20);
        back.setOnAction(e ->{
            sceneManager.showMainMenu();
        });



        try {
            FileInputStream inputStream=new FileInputStream ("src\\Instructions.png");
            Image image=new Image(inputStream,400,50,true,true);
            ImageView i=new ImageView(image);
            root.add(i,0,0);
            GridPane.setHalignment(i, HPos.CENTER);
            GridPane.setValignment(i, VPos.CENTER);
        }

        catch (FileNotFoundException e){

        }

        Text text=new Text();
        text.setText("");                                           //add text here
        text.setFill(Color.WHITE);
        root.add(text,0,1);


        root.add(back,0,2);
        GridPane.setHalignment(back, HPos.CENTER);
        GridPane.setValignment(back, VPos.CENTER);
    }
}

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Pause {
    private SceneManager sceneManager;
    private GridPane root;
    private Scene scene;

    Pause(SceneManager sceneManager){
        this.sceneManager=sceneManager;

    }

    public Scene start(){
        root=new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(16);
        root.setVgap(10);
        root.setBackground(Background.EMPTY);

        scene=new Scene(root,600,600, Color.BLACK);
        addButtons();
        scene.getStylesheets().add("Pause.css");
        return scene;
    }

    private void addButtons(){
        try {
            FileInputStream inputStream=new FileInputStream ("src\\Paused.png");
            Image image=new Image(inputStream,400,80,true,true);
            ImageView i=new ImageView(image);
            root.add(i,0,0);
            GridPane.setHalignment(i, HPos.CENTER);
            GridPane.setValignment(i, VPos.CENTER);
        }

        catch (FileNotFoundException e){

        }

        VBox vBox = new VBox(20);
        vBox.setPrefWidth(170);

        GridPane.setHalignment(vBox, HPos.CENTER);
        GridPane.setValignment(vBox, VPos.CENTER);


        Button back=Generate.createButton("Main Menu",300.0,400.0,50,20);
        back.setOnAction(e ->{
            sceneManager.showMainMenu();
        });

        Button Restart=Generate.createButton("Restart Game",300.0,500.0,50,20);
        Restart.setOnAction(e ->{
            sceneManager.startGame();
        });

        Button Resume=Generate.createButton("Resume",300.0,600.0,50,20);
        Resume.setOnAction(e ->{
            sceneManager.restartGame();
        });

        Region x=new Region();
        x.setPrefHeight(20);
        root.add(x,0,1);

        back.setMinWidth(vBox.getPrefWidth());
        Restart.setMinWidth(vBox.getPrefWidth());
        Resume.setMinWidth(vBox.getPrefWidth());


        vBox.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(back,Restart,Resume);
        root.add(vBox,0,2);
    }
}

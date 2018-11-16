import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainMenu {
	private SceneManager sceneManager;
	private GridPane root;
	private Scene menu;

	MainMenu(SceneManager sceneManager){
	    this.sceneManager=sceneManager;

    }

    public Scene start(){
        root=new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(16);
        root.setVgap(10);
        root.setBackground(Background.EMPTY);
        menu=new Scene(root,600,600, Color.BLACK);
        addButtons();
        menu.getStylesheets().add("MainMenu.css");
        return menu;
    }

    public void addButtons(){

        try {
            FileInputStream inputStream=new FileInputStream ("src\\Logom8.png");
            Image image=new Image(inputStream,400,50,true,true);
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



        Button start=Generate.createButton("New Game",400.0,200.0,50,20);
        start.setOnAction(e ->{
            sceneManager.startGame();
        });
        Button resume=Generate.createButton("Resume Game",400.0,250.0,50,20);
//        resume.setOnAction(e ->{
//            sceneManager.startGame();
//        });
        Button leader=Generate.createButton("Leaderboard",400.0,300.0,50,20);
        leader.setOnAction(e ->{
            sceneManager.gotoLeaderBoard();
        });
        Button instructions=Generate.createButton("Instructions",400.0,350.0,50,20);
        instructions.setOnAction(e ->{
            sceneManager.gotoInstructions();
        });
        Button exit=Generate.createButton("Exit",400.0,350.0,50,20);
        exit.setOnAction(e ->{
            sceneManager.exit();
        });

        Text x=new Text();
        x.setText("abc");
        x.setVisible(false);
        x.fontProperty().setValue(Font.font(20));
        root.add(x,0,1);

        start.setMinWidth(vBox.getPrefWidth());
        leader.setMinWidth(vBox.getPrefWidth());
        resume.setMinWidth(vBox.getPrefWidth());
        instructions.setMinWidth(vBox.getPrefWidth());
        exit.setMinWidth(vBox.getPrefWidth());


        vBox.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(start,resume,leader,instructions,exit);
        root.add(vBox,0,2);
        

    }
}

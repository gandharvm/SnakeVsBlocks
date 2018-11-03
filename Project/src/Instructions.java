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
        		// This exception will never occur
        }

        Text text=new Text();
        text.setText("This is a classic Snake vs Blocks game. \n" + 
        			"You as a player will get the snake with initial length as 5. \n" +
        			"You can move the snake left or right using the Shift+Arrow keys. By default, your snake will move forward. \n" +
        			"Your snake will have to go through the randomly appearing walls on the screen. \n" + 
        			"Each block has a value, which represents the points made by you when the snake eats it. \n" + 
        			"After eating the block the length of the snake decreases by the value of the block \n" + 
        			"\n" + "But worry not! There will be atleast one block that will have a value less than the length of your snake !!! \n" + 
        			"You may encounter 4 types of tokens on the way: \n" + 
        			"a) Ball: A ball has a value written over it.\n The length of the snake increases by the" + 
        			"value of the ball if it eats the ball.\n" + 
        			"b) Destroy Blocks: Destroy all the blocks present on the screen. \n Value of each block" + 
        			"destroyed is added to the score.\n" + 
        			"c) Shield: A shield lets you eat any block without decreasing the snake's" + 
        			"length. \n Again, the value of the block eaten by snake is added to the total score. \n" + 
        			"The shield remains with snake for 5 seconds only.\n" + 
        			"d) Magnet: A magnet allows the snake to collect coins which are within a certain" + 
        			"distance from the head of the snake.\n" + 
        			"The speed of your snake will increase as the length increases so watch out !!! \n" + 
        			"You lose when you are not able to pass any block !!! ");  
        //add text here - OK added
        
        text.setTextOrigin(VPos.TOP);
//        text.layoutXProperty().bind(scene.widthProperty().subtract(text.prefWidth(-1)).divide(2));
//        text.layoutYProperty().bind(scene.heightProperty().subtract(text.prefHeight(-1)).divide(2));

        text.setFill(Color.WHITE);
        root.add(text,0,1);


        root.add(back,0,2);
        GridPane.setHalignment(back, HPos.CENTER);
        GridPane.setValignment(back, VPos.CENTER);
    }
}

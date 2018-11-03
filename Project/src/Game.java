import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Game {
    private SceneManager sceneManager;
    private Group root;
    private Scene scene;

    private static final int KEYBOARD_MOVEMENT_DELTA = 5;

    Game(SceneManager sceneManager){
        this.sceneManager=sceneManager;

    }

    public Scene start(){
        root=new Group();
        scene=new Scene(root,600,600, Color.BLACK);

        addSnake();
        addButtons();
        addIcons();
        addPane();
        
        return scene;
    }
    
    
    private void addPane() {
    	Panel P = new Panel(200);
		ArrayList<StackPane> stkpane = P.getPane();
		
		root.getChildren().addAll(stkpane);
    }
    
    
    private void addIcons() {

        try{
            // Coin Image
            FileInputStream coinStream = new FileInputStream ("src\\coin.png");
            Image coinImage = new Image(coinStream,35,35,true,true);
            ImageView coinView = new ImageView(coinImage);

            coinView.setLayoutX(50);
            coinView.setLayoutY(300);

            // Magnet Image
            FileInputStream magnetStream = new FileInputStream ("src\\magnet.png");
            Image magnetImage = new Image(magnetStream,30,30,true,true);
            ImageView magnetView = new ImageView(magnetImage);

            magnetView.setLayoutX(20);
            magnetView.setLayoutY(33);

            // Shield Image
            FileInputStream shieldStream = new FileInputStream ("src\\shield.png");
            Image shieldImage = new Image(shieldStream,35,35,true,true);
            ImageView shieldView = new ImageView(shieldImage);

            shieldView.setLayoutX(400);
            shieldView.setLayoutY(330);


            // DestroyBlocks Image
            FileInputStream destroyStream = new FileInputStream ("src\\Destroy.png");
            Image destroyImage = new Image(destroyStream,35,35,true,true);
            ImageView destroyView = new ImageView(destroyImage);

            destroyView.setLayoutX(400);
            destroyView.setLayoutY(400);

            root.getChildren().addAll(coinView, magnetView, shieldView,destroyView);
        }
    	catch (FileNotFoundException e){
    			// This exception will never occur
        }

    }
    
    
    private void addButtons(){
        HBox top=new HBox();
        Button back=Generate.createButton("Pause",300.0,10.0,50,20);

//        back.setStyle("-fx-background-image: url('src\\Pause.png')");

        back.setOnAction(e ->{
            sceneManager.Pause();
        });

        Region x=new Region();
        Region y=new Region();

        HBox.setHgrow(x, Priority.ALWAYS);
        HBox.setHgrow(y,Priority.ALWAYS);

        top.setPrefWidth(600);

        x.setPrefWidth(200);
        x.setPrefHeight(200);
        y.setPrefWidth(200);
        y.setPrefHeight(200);

        Label t=new Label("Score: 0");
        t.setTextFill(Color.WHITE);
        //System.out.println(System.getProperty("user.dir"));
        try {
            FileInputStream inputStream=new FileInputStream ("src\\coin.png");
            Image image=new Image(inputStream,20,20,true,true);
            ImageView i=new ImageView(image);
            top.getChildren().addAll(t,x,i,y,back);

        }

        catch (FileNotFoundException e){
            top.getChildren().addAll(t,back);
        }

        //top.spacingProperty().setValue(200);
        root.getChildren().addAll(top);
    }
    
    private void addSnake(){
    	Snake S = new Snake(10);
		VBox Snake = S.getSnake();
		Snake.setLayoutX(300);
		Snake.setLayoutY(400);
		
		root.getChildren().add(Snake);
		
		moveCircleOnKeyPress(Snake);
    }

    // Move the snake
    private void moveCircleOnKeyPress(VBox circle) {
        scene.setOnKeyPressed(event ->  {
            switch (event.getCode()) {
              case RIGHT: circle.setLayoutX(circle.getLayoutX() + KEYBOARD_MOVEMENT_DELTA); break;
	          case LEFT:  circle.setLayoutX(circle.getLayoutX() - KEYBOARD_MOVEMENT_DELTA); break;
            }
        });
    }
}

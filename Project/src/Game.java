import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;



public class Game {
    private SceneManager sceneManager;
    private Group root;
    private Scene scene;
    protected long stepCounter=0L;

    private static final int KEYBOARD_MOVEMENT_DELTA = 5;

    Game(SceneManager sceneManager){
        this.sceneManager=sceneManager;

    }

    public Scene start(){
        root=new Group();
        scene=new Scene(root,600,600, Color.BLACK);

        addSnake();
        addTopPanel();
        addIcons();

        return scene;
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


            Block wall=new Block(200);
            wall.setLayoutX(350);
            wall.setLayoutY(200);


            root.getChildren().addAll(coinView, magnetView, shieldView,destroyView,wall);
        }
    	catch (FileNotFoundException e){
    			// This exception will never occur(Checked exception)
        }



    }
    
    
    private void addTopPanel(){
        HBox top=new HBox();
        Button back=Generate.createButton("",300.0,10.0,30,30);
        back.setBackground(Background.EMPTY);
        top.setStyle("-fx-background-color: #000000;");

        try {
            FileInputStream inputStream=new FileInputStream ("src\\Pauseb.png");
            Image image=new Image(inputStream,30,30,true,true);
            ImageView imageView=new ImageView(image);
            back.setGraphic(imageView);
        }

        catch (FileNotFoundException e){
            //Exception will not occur(Checked exception)
        }

        back.setOnAction(e ->{
            sceneManager.Pause();
        });

        Region x=new Region();
        Region y=new Region();

        HBox.setHgrow(x, Priority.ALWAYS);
        HBox.setHgrow(y,Priority.ALWAYS);

        top.setPrefWidth(600);

        Text t=new Text("Score: 0");
        t.setFill(Color.WHITE);
        t.setFont(Font.font(16));

        try {
            FileInputStream inputStream=new FileInputStream ("src\\coin.png");
            Image image=new Image(inputStream,30,30,true,true);
            ImageView imageView=new ImageView(image);
            top.getChildren().addAll(t,x,imageView,y,back);

        }

        catch (FileNotFoundException e){
            //Exception will not occur(Checked exception)
        }
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
    private void moveCircleOnKeyPress(VBox snake) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event ->  {
            switch (event.getCode()) {
              case RIGHT: snake.setLayoutX(snake.getLayoutX() + KEYBOARD_MOVEMENT_DELTA); break;
	          case LEFT:  snake.setLayoutX(snake.getLayoutX() - KEYBOARD_MOVEMENT_DELTA); break;
            }
        });
    }

    private void startGame(){

        Panel P = new Panel(-100);
        ArrayList<StackPane> stkpane = P.getPane();

        root.getChildren().addAll(stkpane);


        for (int i=0;i<stkpane.size();i++){
            TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(4),stkpane.get(i));
            translateTransition.setByY(700);
            translateTransition.setInterpolator(Interpolator.LINEAR);
            translateTransition.play();
        }

    }

    public void step() {

        if (stepCounter % 3 == 0) {
            startGame();
        }

        stepCounter++;
    }
}

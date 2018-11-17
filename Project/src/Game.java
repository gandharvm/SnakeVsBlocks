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
import java.util.Random;


public class Game {
    private SceneManager sceneManager;
    private Group root;
    private Scene scene;
    private ArrayList<Panel> panels;
    private ArrayList<Tokens> tokens;
    private ArrayList<Wall> walls;
    private long stepCounter=0L;

    private static final int KEYBOARD_MOVEMENT_DELTA = 15;

    Game(SceneManager sceneManager){

        this.sceneManager=sceneManager;
        panels=new ArrayList<>();
        tokens=new ArrayList<>();
        walls=new ArrayList<>();

    }

    public Scene start(){
        root=new Group();
        scene=new Scene(root,600,600, Color.BLACK);

        addTopPanel();

        addPlayer();

       // System.out.println(System.getProperty("user.dir"));

        return scene;
    }
    
    
    private void addPowerups() {

        Random random =new Random();
        int choose=random.nextInt(3);

        int position=random.nextInt(550)+25;

        try{
            if(choose==0){
                // Magnet Image
                Magnet magnet=new Magnet();
                ImageView magnetView=magnet.addMagnet(position);
                root.getChildren().add(magnetView);
                tokens.add(magnet);

                TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(4),magnetView);
                translateTransition.setByY(700);
                translateTransition.setInterpolator(Interpolator.LINEAR);
                translateTransition.play();
            }

            else if (choose==1){
                // Shield Image
                Shield shield=new Shield();
                ImageView shieldView=shield.addShield(position);
                root.getChildren().add(shieldView);
                tokens.add(shield);

                TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(4),shieldView);
                translateTransition.setByY(700);
                translateTransition.setInterpolator(Interpolator.LINEAR);
                translateTransition.play();
            }


            else if(choose==2){
                // DestroyBlocks Image
                DestroyBlocks destroyBlocks=new DestroyBlocks();
                ImageView destroyView=destroyBlocks.addDestroyBlocks(position);
                root.getChildren().add(destroyView);
                tokens.add(destroyBlocks);

                TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(4),destroyView);
                translateTransition.setByY(700);
                translateTransition.setInterpolator(Interpolator.LINEAR);
                translateTransition.play();
            }

        }
    	catch (FileNotFoundException e){
    			// This exception will never occur (Checked exception)
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
    
    private void addPlayer(){
    	Player player=new Player();
		VBox snake = player.getSnake();
		
		root.getChildren().add(snake);
		
		moveCircleOnKeyPress(snake);
    }

    // Move the snake
    private void moveCircleOnKeyPress(VBox snake) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event ->  {
            if(event.getCode().toString() .equals("RIGHT")) {
            	if(snake.getLayoutX() < 575)
            		snake.setLayoutX(snake.getLayoutX() + KEYBOARD_MOVEMENT_DELTA);
            } else if(event.getCode().toString() .equals("LEFT")) {
            	if(snake.getLayoutX() > 3)
            		snake.setLayoutX(snake.getLayoutX() - KEYBOARD_MOVEMENT_DELTA); 
            }
        });
    }

    
    // Adds Panel and creates Animation
    private void addPanel(){

        Panel P = new Panel(-100);
        ArrayList<StackPane> stkpane = P.getPane();

        root.getChildren().addAll(stkpane);

        // Movement of the Panel 
        for (int i=0;i<stkpane.size();i++){
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(4),stkpane.get(i));
            translateTransition.setByY(700);
            translateTransition.setInterpolator(Interpolator.LINEAR);
            translateTransition.play();
        }

    }
    
    // spawn walls, coins and powerups after specific intervals
    public void step() {

        if (stepCounter % 3 == 0) {
            addPanel();
            //addWall();
            //addWall();
        }

        if (stepCounter % 5 == 0) {
            addPowerups();
        }

        addCoins();

        stepCounter++;
    }

    private void addCoins(){

        Random random =new Random();
        int position=random.nextInt(550)+25;
        int value=random.nextInt(5)+1;

        try{

            // Coin Image
            Coin coin=new Coin(value);
            Group coinGroup=coin.addCoin(position);
            root.getChildren().add(coinGroup);
            tokens.add(coin);

            TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(4),coinGroup);
            translateTransition.setByY(800);
            translateTransition.setInterpolator(Interpolator.LINEAR);
            translateTransition.play();
        }
        catch (FileNotFoundException e){
            // This exception will never occur(Checked exception)
        }

    }


    private void addWall(){
        Random random =new Random();
        int position=random.nextInt(550)+25;

        int length=random.nextInt(500);

        Wall wall=new Wall(length);
        wall.setLayoutX(position);
        wall.setLayoutY(-100-wall.getLength());
        root.getChildren().add(wall);

        TranslateTransition translateTransition=new TranslateTransition(Duration.seconds((2*wall.getLength()+800)/200),wall);
        translateTransition.setByY(700+wall.getLength());
        translateTransition.setInterpolator(Interpolator.LINEAR);
        translateTransition.play();

    }

}


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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;


public class Game implements Serializable {
    private SceneManager sceneManager;
    private Group root;
    private Scene scene;
    private ArrayList<Panel> panels;
    private ArrayList<Tokens> tokens;
    private ArrayList<Wall> walls;
    private ArrayList<Coin> coins;
    private Player player;
    private long stepCounter=0L;
    private int SPEED=3;

    private static final int KEYBOARD_MOVEMENT_DELTA = 15;

    Game(SceneManager sceneManager){

        player=new Player();
        this.sceneManager=sceneManager;
        panels=new ArrayList<>();
        tokens=new ArrayList<>();
        walls=new ArrayList<>();
        coins=new ArrayList<>();

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

            }

            else if (choose==1){
                // Shield Image
                Shield shield=new Shield();
                ImageView shieldView=shield.addShield(position);
                root.getChildren().add(shieldView);
                tokens.add(shield);

            }


            else if(choose==2){
                // DestroyBlocks Image
                DestroyBlocks destroyBlocks=new DestroyBlocks();
                ImageView destroyView=destroyBlocks.addDestroyBlocks(position);
                root.getChildren().add(destroyView);
                tokens.add(destroyBlocks);

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
            SceneManager.serialize(this);
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
        panels.add(P);

        root.getChildren().addAll(stkpane);

    }
    
    // spawn walls, coins and powerups after specific intervals
    public void step() {

        if (stepCounter % 200 == 0) {
            addPanel();
            addWall();
            addWall();
            addCoins();
        }

        if (stepCounter % 500 == 0) {
            addPowerups();
        }

        moveDown();

        stepCounter++;
    }

    private void moveDown(){
        for (int i=0;i<panels.size();i++){
            for (int j=0;j<panels.get(i).getPane().size();j++){
                panels.get(i).getPane().get(j).setLayoutY(panels.get(i).getPane().get(j).getLayoutY()+SPEED);
            }

        }

        for (int i=0;i<tokens.size();i++){
            tokens.get(i).getView().setLayoutY(tokens.get(i).getView().getLayoutY()+SPEED);
        }

        for (int i=0;i<coins.size();i++){
            coins.get(i).getCoinGroup().setLayoutY(coins.get(i).getCoinGroup().getLayoutY()+SPEED);
        }
        for (int i=0;i<walls.size();i++){
            walls.get(i).setLayoutY(walls.get(i).getLayoutY()+SPEED);
        }
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
            coins.add(coin);

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
        walls.add(wall);


    }

}


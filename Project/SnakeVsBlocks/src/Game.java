import javafx.animation.AnimationTimer;
import javafx.concurrent.Task;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Game contains the scene for the actual game and handles all the interaction between all GUI components
 */
public class Game {
    /**
     * Stores the sceneManager that called this class
     */
    private SceneManager sceneManager;
    /**
     * Stores the Group that contains all GUI components
     */
    private Group root;
    /**
     * The Scene for this class.
     */
    private Scene scene;
    /**
     * Arraylist for the panels containing the blocks
     */
    private ArrayList<Panel> panels;
    /**
     * Arraylist for the tokens containing the blocks
     */
    private ArrayList<Tokens> tokens;
    /**
     * Arraylist for the walls containing the blocks
     */
    private ArrayList<Wall> walls;
    /**
     * Arraylist for the coins containing the blocks
     */
    private ArrayList<Coin> coins;
    /**
     * Instance of the current player
     */
    private Player player;
    /**
     * Variable to help add the GUI components after specific intervals
     */
    private long stepCounter = 0L;
    /**
     * Variable to change the amount each component moves after each animation timer
     */
    private double SPEED;
    /**
     * Text field that contains current score of the player
     */
    private Text score;
    /**
     * Instance of the top panel of the game that contains pause button and score of player
     */
    private TopPanel topPanel;
    /**
     * Animation timer being currently used for animation
     */
    private AnimationTimer timer;
    /**
     * Data to be serialized in case user presses pause button or closes the window
     */
    private Data data;
    /**
     * Instance of the current stage being used by the scene
     */
    private Stage stage;
    /**
     * Variable to assist the intersect animation
     */
    private int loop1 = 6;
    /**
     * Variable to check if shield is active
     */
    private int shieldLoop=300;
    /**
     * Variable to check if magnet is active
     */
    private int magnet=300;

    /**
     * Variable to specify the movement of snake on each keypress.
     */
    private static final int KEYBOARD_MOVEMENT_DELTA = 15;

    /**
     * Constructor to Initialize the root and add all the elements in it.
     * @param SPEED Variable to change the amount each component moves after each animation timer
     * @param sc score of the player
     * @param length length of the snake
     * @param stage Current stage being used for the scene
     */
    Game(double SPEED, int sc, int length, Stage stage) {
        root = new Group();
        player = new Player();
        panels = new ArrayList<>();
        tokens = new ArrayList<>();
        walls = new ArrayList<>();
        coins = new ArrayList<>();
        player.setScore(sc);
        player.getSnake().updateLength(length);
        score = new Text("Score: " + Integer.toString(player.getScore()));
        scene = new Scene(root, 600, 600, Color.BLACK);
        this.SPEED = SPEED;
        data = new Data(sc, SPEED, length);
        this.stage = stage;
        this.stage.setOnHiding(e -> {
            try {
                Data.serialize(data);
            } catch (IOException ex) {

            }
        });
    }

    /**
     * Get the animation timer being used
     * @return the animation timer being used
     */
    public AnimationTimer getTimer() {
        return timer;
    }

    /**
     * Start the game by adding all GUI components in it.
     * @param sceneManager the sceneManager to communicate between different scenes.
     * @param timer timer being used for the game
     * @return the current scene
     */
    public Scene start(SceneManager sceneManager, AnimationTimer timer) {

        this.sceneManager = sceneManager;
        addTopPanel();
        addPlayer();
        this.timer = timer;
        // System.out.println(System.getProperty("user.dir"));

        return scene;
    }

    /**
     * Add different tokens to the scene
     */
    private void addPowerups() {

        Random random = new Random();
        int choose = random.nextInt(3);
        int position = random.nextInt(550) + 25;

        try {
            if (choose == 0) {
                // Magnet Image
                Magnet magnet = new Magnet();
                ImageView magnetView = magnet.addMagnet(position);
                root.getChildren().add(magnetView);
                tokens.add(magnet);

            } else if (choose == 1) {
                // Shield Image
                Shield shield = new Shield();
                ImageView shieldView = shield.addShield(position);
                root.getChildren().add(shieldView);
                tokens.add(shield);

            } else if (choose == 2) {
                // DestroyBlocks Image
                DestroyBlocks destroyBlocks = new DestroyBlocks();
                ImageView destroyView = destroyBlocks.addDestroyBlocks(position);
                root.getChildren().add(destroyView);
                tokens.add(destroyBlocks);

            }

        } catch (FileNotFoundException e) {
            // This exception will never occur (Checked exception)
        }

    }

    /**
     * Add the top panel to the scene
     */
    private void addTopPanel() {
        topPanel = new TopPanel(score, this, sceneManager, data);
        root.getChildren().add(topPanel.getTop());
    }

    /**
     * Add the player's snake to the scene
     */
    private void addPlayer() {

        VBox snake = player.getSnake().getSnake();

        root.getChildren().add(snake);

        moveCircleOnKeyPress(snake);
    }

    /**
     * To move the snake on keypress
     * @param snake vertical box containing the snake
     */
    private void moveCircleOnKeyPress(VBox snake) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().toString().equals("RIGHT")) {
                if (snake.getLayoutX() < 575) {
                    snake.setLayoutX(snake.getLayoutX() + KEYBOARD_MOVEMENT_DELTA);
                    if (!checkWall()) {
                        snake.setLayoutX(snake.getLayoutX() - KEYBOARD_MOVEMENT_DELTA);
                    }
                }
            } else if (event.getCode().toString().equals("LEFT")) {
                if (snake.getLayoutX() > 3) {
                    snake.setLayoutX(snake.getLayoutX() - KEYBOARD_MOVEMENT_DELTA);
                    if (!checkWall()) {
                        snake.setLayoutX(snake.getLayoutX() + KEYBOARD_MOVEMENT_DELTA);
                    }
                }

            }
        });
    }

    /**
     * Check intersection of walls with snake
     * @param b Bounds of the head of the snake
     * @return true if no collision and false otherwise
     */
    private boolean checkWall(Bounds b) {
        for (int i = 0; i < walls.size(); i++) {
            if (walls.get(i).getBoundsInParent().intersects(b)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check intersection of walls with snake
     * @return true if no collision and false otherwise
     */
    private boolean checkWall(){
        Bounds b = new Bounds(player.getSnake().getSnake().getBoundsInParent().getMinX(), 410, 0, 20, 20, 0) {
            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Point2D p) {
                return false;
            }

            @Override
            public boolean contains(Point3D p) {
                return false;
            }

            @Override
            public boolean contains(double x, double y) {
                return false;
            }

            @Override
            public boolean contains(double x, double y, double z) {
                return false;
            }

            @Override
            public boolean contains(Bounds b) {
                return false;
            }

            @Override
            public boolean contains(double x, double y, double w, double h) {
                return false;
            }

            @Override
            public boolean contains(double x, double y, double z, double w, double h, double d) {
                return false;
            }

            @Override
            public boolean intersects(Bounds b) {
                return false;
            }

            @Override
            public boolean intersects(double x, double y, double w, double h) {
                return false;
            }

            @Override
            public boolean intersects(double x, double y, double z, double w, double h, double d) {
                return false;
            }
        };
        for (int i = 0; i < walls.size(); i++) {
            if (walls.get(i).getBoundsInParent().intersects(b)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds Panel and creates Animation
     */
    private void addPanel() {

        Panel P = new Panel(-100, player.getSnake().getLength());
        ArrayList<StackPane> stkpane = P.getPane();
        panels.add(P);

        root.getChildren().addAll(stkpane);

    }

    /**
     * spawn walls, coins and powerups after specific intervals
     */

    public void step() {

        if (stepCounter % 150 == 0) {
            addPanel();

        }

        if ((stepCounter + 37) % 150 == 0) {
            addWall();
            addWall();
            addCoins();
        }

        if ((stepCounter + 43) % 400 == 0) {
            addPowerups();
        }

        if ((stepCounter + 57) % 300 == 0) {
            addCoins();
        }

        moveDown();
        Bounds b = new Bounds(player.getSnake().getSnake().getBoundsInParent().getMinX(), 410, 0, 20, 20, 0) {
            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Point2D p) {
                return false;
            }

            @Override
            public boolean contains(Point3D p) {
                return false;
            }

            @Override
            public boolean contains(double x, double y) {
                return false;
            }

            @Override
            public boolean contains(double x, double y, double z) {
                return false;
            }

            @Override
            public boolean contains(Bounds b) {
                return false;
            }

            @Override
            public boolean contains(double x, double y, double w, double h) {
                return false;
            }

            @Override
            public boolean contains(double x, double y, double z, double w, double h, double d) {
                return false;
            }

            @Override
            public boolean intersects(Bounds b) {
                return false;
            }

            @Override
            public boolean intersects(double x, double y, double w, double h) {
                return false;
            }

            @Override
            public boolean intersects(double x, double y, double z, double w, double h, double d) {
                return false;
            }
        };
        checkTokens(b);
        checkPanels(b);
        checkCoins(b);

        changeSpeed();
        loopIntersection();
        updateData();
        stepCounter++;
        if (shieldLoop<300){
            shieldLoop++;
        }
        if (magnet<300){
            magnet++;
        }
    }


    /**
     * Change speed of snake as its length increases
     */
    public void changeSpeed() {
        if (player.getSnake().getLength() > 5) {
            SPEED = 3.5;
        }
        if (player.getSnake().getLength() > 8) {
            SPEED = 4;
        }
        if (player.getSnake().getLength() > 11) {
            SPEED = 4.5;
        }
        if (player.getSnake().getLength() > 14) {
            SPEED = 5;
        }
        if (player.getSnake().getLength() > 17) {
            SPEED = 5.5;
        }
        if (player.getSnake().getLength() > 20) {
            SPEED = 6;
        }
        if (player.getSnake().getLength() > 23) {
            SPEED = 6.5;
        }
    }

    /**
     * Check intersection of snake with tokens
     * @param b Bounds of the snake
     */
    private void checkTokens(Bounds b) {
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).getView().getBoundsInParent().intersects(b)) {
                if (tokens.get(i).getClass() == DestroyBlocks.class) {
                    for (int j = panels.size()-1; j >=0; j--) {
                        for (int k = panels.get(j).getPane().size()-1; k >=0; k--) {
                            if (panels.get(j).getPane().get(k).getLayoutY() >= -10 && panels.get(j).getPane().get(k).getLayoutY() <= 610) {
                                panels.get(j).getPane().get(k).setVisible(false);
                                player.setScore(player.getScore() + panels.get(j).getValueOfBlock(k));
                                score.setText("Score: " + Integer.toString(player.getScore()));
                            }

                        }
                        panels.remove(j);
                    }
                }
                else if (tokens.get(i).getClass() == Shield.class){
                    shieldLoop=0;
                }
                else if(tokens.get(i).getClass() == Magnet.class){
                    magnet=0;
                }
                root.getChildren().remove(tokens.get(i));
                tokens.get(i).getView().setVisible(false);
                tokens.remove(i);
                intersectAnimation();
            }
        }

        for (int i = 0; i < walls.size(); i++) {
            if (!checkWall(b)) {
                player.getSnake().getSnake().setLayoutX(player.getSnake().getSnake().getLayoutX() + KEYBOARD_MOVEMENT_DELTA);
            }
        }

    }

    /**
     * Check intersection of snake with coins
     * @param b Bounds of the snake
     */
    private void checkCoins(Bounds b){
        if (magnet!=300){
            b=new Bounds(player.getSnake().getSnake().getBoundsInParent().getMinX()-150, 410, 0, 300, 20, 0) {
                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public boolean contains(Point2D p) {
                    return false;
                }

                @Override
                public boolean contains(Point3D p) {
                    return false;
                }

                @Override
                public boolean contains(double x, double y) {
                    return false;
                }

                @Override
                public boolean contains(double x, double y, double z) {
                    return false;
                }

                @Override
                public boolean contains(Bounds b) {
                    return false;
                }

                @Override
                public boolean contains(double x, double y, double w, double h) {
                    return false;
                }

                @Override
                public boolean contains(double x, double y, double z, double w, double h, double d) {
                    return false;
                }

                @Override
                public boolean intersects(Bounds b) {
                    return false;
                }

                @Override
                public boolean intersects(double x, double y, double w, double h) {
                    return false;
                }

                @Override
                public boolean intersects(double x, double y, double z, double w, double h, double d) {
                    return false;
                }
            };
        }
        for (int i = 0; i < coins.size(); i++) {
            if (coins.get(i).getCoinGroup().getBoundsInParent().intersects(b)) {
                player.getSnake().updateLength(player.getSnake().getLength() + coins.get(i).getValue());
                root.getChildren().remove(coins.get(i));
                coins.get(i).getCoinGroup().setVisible(false);
                coins.remove(i);
                intersectAnimation();
            }
        }
    }

    /**
     * Interaction of snake with blocks
     * @param b Bounds of the head of the snake
     */
    private void checkPanels(Bounds b){
        if (shieldLoop==300){
            for (int i = 0; i < panels.size(); i++) {
                for (int j = 0; j < panels.get(i).getPane().size(); j++) {
                    if (panels.get(i).getPane().get(j).getBoundsInParent().intersects(b)) {
                        if (player.getSnake().getLength() - panels.get(i).getValueOfBlock(j) >= 0) {
                            if (panels.get(i).getValueOfBlock(j) > 3) {
                                int x = panels.get(i).getValueOfBlock(j) * 50;
                                timer.stop();
                                Task<Void> sleeper = new Task<>() {
                                    @Override
                                    protected Void call() {
                                        try {
                                            Thread.sleep(x);
                                        }
                                        catch (InterruptedException e) {}
                                        return null;
                                    }
                                };
                                sleeper.setOnSucceeded(e -> {
                                    timer.start();
                                });
                                new Thread(sleeper).start();
                            }
                            player.setScore(player.getScore() + panels.get(i).getValueOfBlock(j));
                            score.setText("Score: " + Integer.toString(player.getScore()));
                            player.getSnake().updateLength(player.getSnake().getLength() - panels.get(i).getValueOfBlock(j));
                            panels.get(i).deleteBlock(j);
                        } else {
                            player.setScore(player.getScore() + player.getSnake().getLength());
                            score.setText("Score: " + Integer.toString(player.getScore()));
                            player.getSnake().updateLength(0);
                            timer.stop();
                            player.getSnake().setLength(-1);
                            sceneManager.gameOver(player.getScore());
                        }

                        intersectAnimation();
                    }
                }
            }
        }
        else {
            for (int i = 0; i < panels.size(); i++) {
                for (int j = 0; j < panels.get(i).getPane().size(); j++) {
                    if (panels.get(i).getPane().get(j).getBoundsInParent().intersects(b)) {
                        player.setScore(player.getScore() + panels.get(i).getValueOfBlock(j));
                        score.setText("Score: " + Integer.toString(player.getScore()));
                        panels.get(i).deleteBlock(j);

                        intersectAnimation();
                    }
                }
            }
        }
    }

    /**
     * Circles for intersect animation
     */
    private Circle[] circles;

    /**
     * Create intersect animation
     */
    private void intersectAnimation() {

        if (circles != null) {
            circles[0].setVisible(false);
            circles[1].setVisible(false);
            circles[2].setVisible(false);
            circles[3].setVisible(false);
        }
        circles = new Circle[4];
        circles[0] = new Circle(player.getSnake().getSnake().getBoundsInParent().getMinX() + 10, 410, 3, Color.BLUE);
        circles[1] = new Circle(player.getSnake().getSnake().getBoundsInParent().getMinX() + 10, 410, 3, Color.YELLOW);
        circles[2] = new Circle(player.getSnake().getSnake().getBoundsInParent().getMinX() + 10, 410, 3, Color.GREEN);
        circles[3] = new Circle(player.getSnake().getSnake().getBoundsInParent().getMinX() + 10, 410, 3, Color.ORANGE);
        root.getChildren().addAll(circles[0], circles[1], circles[2], circles[3]);
        loop1 = 0;
        loopIntersection();
    }


    /**
     * Complete intersect animation
     */
    private void loopIntersection(){
        if (loop1<5){
            circles[0].setLayoutX(circles[0].getLayoutX()+2);
            circles[0].setLayoutY(circles[0].getLayoutY()+2);
            circles[1].setLayoutX(circles[1].getLayoutX()+2);
            circles[1].setLayoutY(circles[1].getLayoutY()-2);
            circles[2].setLayoutX(circles[2].getLayoutX()-2);
            circles[2].setLayoutY(circles[2].getLayoutY()-2);
            circles[3].setLayoutX(circles[3].getLayoutX()-2);
            circles[3].setLayoutY(circles[3].getLayoutY()+2);
            loop1++;
        }
        else if (loop1==5){
            circles[0].setVisible(false);
            circles[1].setVisible(false);
            circles[2].setVisible(false);
            circles[3].setVisible(false);
            root.getChildren().remove(circles[0]);
            root.getChildren().remove(circles[1]);
            root.getChildren().remove(circles[2]);
            root.getChildren().remove(circles[3]);
        }
    }

    /**
     * Move all GUI components down after each animation timer
     */
    private void moveDown(){
        for (int i=0;i<panels.size();i++){
            for (int j=0;j<panels.get(i).getPane().size();j++){
                if (panels.get(i).getPane().get(j).getLayoutY()<1000){
                    panels.get(i).getPane().get(j).setLayoutY(panels.get(i).getPane().get(j).getLayoutY()+SPEED);
                }
                else {
                    panels.get(i).getPane().remove(j);
                }
            }
            if (panels.get(i).getPane().size()==0){
                root.getChildren().remove(panels.get(i));
                panels.remove(i);
            }
        }

        for (int i=0;i<tokens.size();i++){
            if (tokens.get(i).getView().getLayoutY()<1000){
                tokens.get(i).getView().setLayoutY(tokens.get(i).getView().getLayoutY()+SPEED);
            }
            else{
                root.getChildren().remove(tokens.get(i));
                tokens.remove(i);
            }

        }

        for (int i=0;i<coins.size();i++){
            if(coins.get(i).getCoinGroup().getLayoutY()<1000){
                coins.get(i).getCoinGroup().setLayoutY(coins.get(i).getCoinGroup().getLayoutY()+SPEED);
            }
            else {
                root.getChildren().remove(coins.get(i));
                coins.remove(i);
            }
        }

        for (int i=0;i<walls.size();i++){
            if (walls.get(i).getLayoutY()<1000){
                walls.get(i).setLayoutY(walls.get(i).getLayoutY()+SPEED);
            }
            else{
                root.getChildren().remove(walls.get(i));
                walls.remove(i);
            }
        }

    }

    /**
     * Add Coins to the scene
     */
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


    /**
     * Add walls in the scene
     */
    private void addWall(){
        Random random =new Random();
        int position=random.nextInt(550)+25;

        int length=random.nextInt(200)+70;

        Wall wall=new Wall(length);
        wall.setLayoutX(position);
        wall.setLayoutY(-100);
        root.getChildren().add(wall);
        walls.add(wall);

    }


    /**
     * Get the value of current scene
     * @return the current scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Update data for serialization
     */
    public void updateData(){
        data.setLength(player.getSnake().getLength());
        data.setSPEED(SPEED);
        data.setScore(player.getScore());
    }

}


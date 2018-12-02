import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

/**
 * Class that acts as an interface between different scenes and is used to switch and communicate between different scenes.
 */
public class SceneManager{

    /**
     * Stage of the game
     */
    private Stage stage;
    /**
     * New Game variable
     */
    private Game game;
    /**
     * Saved game variable
     */
    private Game prevGame;

    /**
     * Constructor to set the value of stage
     * @param s stage for the game
     */
    SceneManager(Stage s) {
        this.stage=s;
        s.resizableProperty().setValue(Boolean.FALSE);
    }

    /**
     * Go to main menu
     */
    public void showMainMenu(){

        Scene scene=new MainMenu(this).start();

        stage.setScene(scene);

    }

    /**
     * Start a new game
     */
    public void startGame(){

        game=new Game(3,0,7,stage);
        AnimationTimer timer=setGameLoop(game);

        Scene scene=game.start(this,timer);

        stage.setScene(scene);

    }

    /**
     * Go to pause screen
     */
    public void Pause(){
        Scene scene=new Pause(this).start();

        stage.setScene(scene);
    }

    /**
     * Show leaderBoard
     */
    public void gotoLeaderBoard(){

        Scene scene=new LeaderBoard(this).start();

        stage.setScene(scene);
    }

    /**
     * Go to instructions scene
     */
    public void gotoInstructions(){
        Scene scene=new Instructions(this).start();

        stage.setScene(scene);
    }

    /**
     * Close the game
     */
    public void exit(){
        stage.close();
    }

    /**
     * set the game loop by initializing animation timer
     * @param g Game variable for which timer should be initialized
     * @return the timer
     */
    private AnimationTimer setGameLoop(Game g) {
        AnimationTimer timer=new AnimationTimer() {
            @Override
            public void handle(long now) {
                g.step();
            }
        };
        timer.start();
        return timer;
    }

    /**
     * Load the saved game from memory
     */
    public void restartGame(){
        Data data=null;
        try {
            data=Data.deserialize();
        }
        catch (IOException e){
            prevGame=new Game(3,0,7,stage);
        }
        if (data!=null){
            if (data.getLength()>=0){
                prevGame =new Game(data.getSPEED(),data.getScore(),data.getLength(),stage);
            }
            else {
                prevGame=new Game(3,0,7,stage);
            }
        }
        else {
            prevGame=new Game(3,0,7,stage);
        }

        //Scene scene=prevGame.start();
        Scene scene=prevGame.getScene();
        AnimationTimer timer=setGameLoop(prevGame);
        prevGame.start(this,timer);
        stage.setScene(scene);

    }

    /**
     * Go to game over screen
     * @param score
     */
    public void gameOver(int score){
        Scene scene=new GameOver(this).start(score);
        stage.setScene(scene);
    }
}

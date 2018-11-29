import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;


public class SceneManager implements Serializable{

    private Stage stage;
    private Timeline animation;
    private Game game;

    SceneManager(Stage s) {
        this.stage=s;
        s.resizableProperty().setValue(Boolean.FALSE);
    }


    public void showMainMenu(){

        Scene scene=new MainMenu(this).start();

        stage.setScene(scene);

    }

    public void startGame(){

        game=desreialize();
        if (game==null){
            game=new Game(this);
        }

        Scene scene=game.start();

        stage.setScene(scene);
        setGameLoop();
    }

    public void Pause(){
        Scene scene=new Pause(this).start();

        stage.setScene(scene);
    }

    public void gotoLeaderBoard(){

        Scene scene=new LeaderBoard(this).start();

        stage.setScene(scene);
    }

    public void gotoInstructions(){
        Scene scene=new Instructions(this).start();

        stage.setScene(scene);
    }
    public void exit(){
        stage.close();
    }

    private void setGameLoop() {
        AnimationTimer timer=new AnimationTimer() {
            @Override
            public void handle(long now) {
                game.step();
            }
        };
        timer.start();
    }

    public static void serialize(Game game){
        ObjectOutputStream out =null;
        try{
            out=new ObjectOutputStream(new FileOutputStream("out.txt"));
            out.writeObject(game);
            out.close();
        }catch ( IOException e){

        }

    }

    public static Game desreialize(){
        ObjectInputStream in =null;
        Game game=null;
        try{
            in=new ObjectInputStream(new FileInputStream("out.txt"));
            game=(Game) in.readObject();
            in.close();
        }
        catch (ClassNotFoundException | IOException e){

        }
        return game;
    }

}

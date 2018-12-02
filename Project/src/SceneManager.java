import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;


public class SceneManager{

    private Stage stage;
    private Game game;
    private Game prevGame;

    SceneManager(Stage s) {
        this.stage=s;
        s.resizableProperty().setValue(Boolean.FALSE);
    }


    public void showMainMenu(){

        Scene scene=new MainMenu(this).start();

        stage.setScene(scene);

    }

    public void startGame(){

        game=new Game(3,0,7,stage);
        AnimationTimer timer=setGameLoop(game);

        Scene scene=game.start(this,timer);

        stage.setScene(scene);

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

    public void gameOver(int score){
        Scene scene=new GameOver(this).start(score);
        stage.setScene(scene);
    }
}

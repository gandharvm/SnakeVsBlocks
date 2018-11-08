import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;


public class SceneManager {

    private Stage stage;
    private Timeline animation;

    SceneManager(Stage s) {
        this.stage=s;
        s.resizableProperty().setValue(Boolean.FALSE);
    }


    public void showMainMenu(){

        Scene scene=new MainMenu(this).start();

        stage.setScene(scene);

    }

    public void startGame(){

        Game game=new Game(this);

        Scene scene=game.start();

        stage.setScene(scene);

        KeyFrame frame = new KeyFrame(Duration.millis(1000), e -> game.step());
        setGameLoop(frame);
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

    private void setGameLoop(KeyFrame frame) {
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

}

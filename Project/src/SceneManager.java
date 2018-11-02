import javafx.scene.Scene;
import javafx.stage.Stage;



public class SceneManager {

    private Stage stage;

    SceneManager(Stage s) {
        this.stage=s;
        s.resizableProperty().setValue(Boolean.FALSE);
    }


    public void showMainMenu(){

        Scene scene=new MainMenu(this).start();

        stage.setScene(scene);

    }

    public void startGame(){

        Scene scene=new Game(this).start();

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

}

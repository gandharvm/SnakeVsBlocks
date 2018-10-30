import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;


public class SceneManager {

    private Stage stage;

    SceneManager(Stage s) {
        this.stage=s;

    }


    public void showMainMenu(){

        Scene scene=new MainMenu(this).start();

        stage.setScene(scene);

    }
//
//    public void startGame() throws Exception{
//        AnchorPane root=FXMLLoader.load(getClass().getResource());
//        Scene scene=new Scene(root);
//
//        stage.setScene(scene);
//    }
//
//    public void Pause() throws Exception{
//        AnchorPane root=FXMLLoader.load(getClass().getResource());
//        Scene scene=new Scene(root);
//
//        stage.setScene(scene);
//    }
//
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

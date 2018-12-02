import javafx.animation.*;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;


/**
 * Generates the loading screen for the game
 */
public class SplashS extends Application {
    /**
     * Launches the application
     * @param args command line arguments
     */
    public static void main(String[] args){
        launch(args);
    }

    /**
     * Image View for the loading circle
     */
    @FXML
    private ImageView loadingCircle;

    /**
     * Start the game  by first playng the loading screen animation and then going to the main menu
     * @param s Stage for the game
     * @throws IOException if fxml file for loading screen not found
     */
    @Override
    public void start(Stage s) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Splash.fxml"));
        loader.setController(this);

        Pane root=loader.load();

        RotateTransition rt = new RotateTransition(Duration.millis(2500), loadingCircle);
        rt.setByAngle(360);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();

        SceneManager sceneManager=new SceneManager(s);
        Task<Void> sleeper = new Task<>() {
            @Override
            protected Void call() {
                try {
                    Thread.sleep((int)(Math.random()*500)+1000);
                } catch (InterruptedException e) {}
                return null;
            }
        };
        sleeper.setOnSucceeded(e->{
                rt.stop();
                sceneManager.showMainMenu();
        });


        s.setTitle("Snake Vs Blocks");
        s.setScene(new Scene(root));
        s.show();

        new Thread(sleeper).start();

        
    }


}

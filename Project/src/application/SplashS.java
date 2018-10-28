import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class SplashS extends Application {
    public static void main(String[] args){
        launch(args);
    }


    @FXML
    private ImageView loadingCircle;

    @Override
    public void start(Stage s) throws Exception{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Splash.fxml"));
        loader.setController(this);

        Pane root=loader.load();


        RotateTransition rt = new RotateTransition(Duration.millis(2500), loadingCircle);
        rt.setByAngle(360);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();

        s.setScene(new Scene(root));
        s.show();

        
    }


}

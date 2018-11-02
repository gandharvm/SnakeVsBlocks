import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Game {
    private SceneManager sceneManager;
    private Group root;
    private Scene scene;

    private static final int KEYBOARD_MOVEMENT_DELTA = 5;
    private static final Duration TRANSLATE_DURATION = Duration.seconds(0.25);

    Game(SceneManager sceneManager){
        this.sceneManager=sceneManager;

    }

    public Scene start(){
        root=new Group();
        scene=new Scene(root,600,600, Color.BLACK);

        addSnake();
        addButtons();
        return scene;
    }

    private void addButtons(){
        HBox top=new HBox();
        Button back=Generate.createButton("Pause",300.0,10.0,50,20);
        back.setOnAction(e ->{
            sceneManager.Pause();
        });

        Label t=new Label("Score: 0");
        t.setTextFill(Color.WHITE);
        //System.out.println(System.getProperty("user.dir"));
        try {
            FileInputStream inputStream=new FileInputStream ("src\\coin.png");
            Image image=new Image(inputStream,20,20,true,true);
            ImageView i=new ImageView(image);
            top.getChildren().addAll(t,i,back);
            top.setAlignment(Pos.TOP_CENTER);
        }

        catch (FileNotFoundException e){
            top.getChildren().addAll(t,back);
        }





        top.spacingProperty().setValue(200);
        root.getChildren().addAll(top);
    }

    private void addSnake(){
        Circle head = new Circle(300,580,10,Color.RED);

        moveCircleOnKeyPress(head);
        root.getChildren().addAll(head);
    }

//    private TranslateTransition createTranslateTransition(Circle circle) {
//        final TranslateTransition transition = new TranslateTransition(TRANSLATE_DURATION, circle);
//        transition.setOnFinished(e-> {
//            circle.setCenterX(circle.getTranslateX() + circle.getCenterX());
//            circle.setCenterY(circle.getTranslateY() + circle.getCenterY());
//            circle.setTranslateX(0);
//            circle.setTranslateY(0);
//        });
//        return transition;
//    }

    private void moveCircleOnKeyPress(Circle circle) {
        scene.setOnKeyPressed(event ->  {
            switch (event.getCode()) {
                case UP:    circle.setCenterY(circle.getCenterY() - KEYBOARD_MOVEMENT_DELTA); break;
                case RIGHT: circle.setCenterX(circle.getCenterX() + KEYBOARD_MOVEMENT_DELTA); break;
                case DOWN:  circle.setCenterY(circle.getCenterY() + KEYBOARD_MOVEMENT_DELTA); break;
                case LEFT:  circle.setCenterX(circle.getCenterX() - KEYBOARD_MOVEMENT_DELTA); break;
            }
        });
    }
}

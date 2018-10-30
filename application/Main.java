package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class Main extends Application {
	
	  private static final int      KEYBOARD_MOVEMENT_DELTA = 5;
	  private static final Duration TRANSLATE_DURATION      = Duration.seconds(0.25);
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Circle head = new Circle(300,580,10,Color.RED);
			
			Pane root = new Pane();
			
			root.getChildren().add(head);
			
			Scene scene = new Scene(root,600,600);
					
			moveCircleOnKeyPress(scene,head);
			
			primaryStage.setTitle("Snakes Vs Blocks");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Create transition
	private TranslateTransition createTranslateTransition(Circle circle) {
	    final TranslateTransition transition = new TranslateTransition(TRANSLATE_DURATION, circle);
	    transition.setOnFinished(new EventHandler<ActionEvent>() {
	      @Override public void handle(ActionEvent t) {
	        circle.setCenterX(circle.getTranslateX() + circle.getCenterX());
	        circle.setCenterY(circle.getTranslateY() + circle.getCenterY());
	        circle.setTranslateX(0);
	        circle.setTranslateY(0);
	      }
	    });
	    return transition;
	  }
	
	 private void moveCircleOnKeyPress(Scene scene, Circle circle) {
		    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		      @Override 
		      public void handle(KeyEvent event) {
		        switch (event.getCode()) {
		          case UP:    circle.setCenterY(circle.getCenterY() - KEYBOARD_MOVEMENT_DELTA); break;
		          case RIGHT: circle.setCenterX(circle.getCenterX() + KEYBOARD_MOVEMENT_DELTA); break;
		          case DOWN:  circle.setCenterY(circle.getCenterY() + KEYBOARD_MOVEMENT_DELTA); break;
		          case LEFT:  circle.setCenterX(circle.getCenterX() - KEYBOARD_MOVEMENT_DELTA); break;
		        }
		      }
		    });
		  }
	
	public static void main(String[] args) {
		launch(args);
	}
}

package application;

import java.util.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;


public class Main extends Application {
	
	static AnchorPane root;
	static List<GridPane> grid = new ArrayList<GridPane>();
	
	private static int index = 0;
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("Anchor.fxml"));
			
			grid.add((GridPane)FXMLLoader.load(getClass().getResource("MainMenu.fxml")));
			grid.add((GridPane)FXMLLoader.load(getClass().getResource("Instructions.fxml")));
			grid.add((GridPane)FXMLLoader.load(getClass().getResource("LeaderBoard.fxml")));

			
			Scene scene = new Scene(root,600,600);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			
			e.printStackTrace();
		
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

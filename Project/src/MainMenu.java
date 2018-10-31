import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class MainMenu {
	private SceneManager sceneManager;
	private Group root;
	private Scene menu;

	MainMenu(SceneManager sceneManager){
	    this.sceneManager=sceneManager;

    }

    public Scene start(){
        root=new Group();
        root.maxHeight(800.0);
        root.maxWidth(600.0);
        menu=new Scene(root,800,600, Color.BLACK);
        addButtons();
        menu.getStylesheets().add("MainMenu.css");
        return menu;
    }

    public void addButtons(){
        Button start=Generate.createButton("New Game",400.0,200.0,50,20);
        start.setOnAction(e ->{
            sceneManager.startGame();
        });
        Button leader=Generate.createButton("Leaderboard",400.0,250.0,50,20);
        leader.setOnAction(e ->{
            sceneManager.gotoLeaderBoard();
        });
        Button instructions=Generate.createButton("Instructions",400.0,300.0,50,20);
        instructions.setOnAction(e ->{
            sceneManager.gotoInstructions();
        });
        Button exit=Generate.createButton("Exit",400.0,350.0,50,20);
        exit.setOnAction(e ->{
            sceneManager.exit();
        });
        root.getChildren().addAll(start,leader,instructions,exit);
    }
}

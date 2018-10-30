import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class MainMenu {
	private SceneManager sceneManager;
	private Group root;
	private Scene menu;

	MainMenu(SceneManager sceneManager){
	    this.sceneManager=sceneManager;

    }

    public Scene start(){
        root=new Group();
        menu=new Scene(root);
        addButtons();
        return menu;
    }

    public void addButtons(){
        Button start=Generate.createButton("New Game",300.0,200.0,50,20);
//        start.setOnAction(e ->{
//            sceneManager.g
//        });
        Button leader=Generate.createButton("Leaderboard",300.0,300.0,50,20);
        leader.setOnAction(e ->{
            sceneManager.gotoLeaderBoard();
        });
        Button instructions=Generate.createButton("Instructions",300.0,400.0,50,20);
        instructions.setOnAction(e ->{
            sceneManager.gotoInstructions();
        });
        Button exit=Generate.createButton("Exit",300.0,500.0,50,20);
        exit.setOnAction(e ->{
            sceneManager.exit();
        });
        root.getChildren().addAll(start,leader,instructions,exit);
    }
}

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class Pause {
    private SceneManager sceneManager;
    private Group root;
    private Scene scene;

    Pause(SceneManager sceneManager){
        this.sceneManager=sceneManager;

    }

    public Scene start(){
        root=new Group();
        scene=new Scene(root);
        addButtons();
        return scene;
    }

    private void addButtons(){
        Button back=Generate.createButton("Main Menu",300.0,400.0,50,20);
        back.setOnAction(e ->{
            sceneManager.showMainMenu();
        });

        Button Restart=Generate.createButton("Restart Game",300.0,500.0,50,20);
        Restart.setOnAction(e ->{
            sceneManager.startGame();
        });

        Button Resume=Generate.createButton("Resume",300.0,600.0,50,20);
//        Resume.setOnAction(e ->{
//            sceneManager.showMainMenu();
//        });

        root.getChildren().addAll(back,Restart,Resume);
    }
}

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class Instructions {
    private SceneManager sceneManager;
    private Group root;
    private Scene scene;

    Instructions(SceneManager sceneManager){
        this.sceneManager=sceneManager;

    }

    public Scene start(){
        root=new Group();
        scene=new Scene(root,600,600);
        addButtons();
        return scene;
    }

    private void addButtons(){
        Button back=Generate.createButton("Back",300.0,400.0,50,20);
        back.setOnAction(e ->{
            sceneManager.showMainMenu();
        });
        root.getChildren().addAll(back);
    }
}

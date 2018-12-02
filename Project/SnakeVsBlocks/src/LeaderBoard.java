import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Leader Board Screen of the game
 */
public class LeaderBoard {
    /**
     * Stores the sceneManager that called this class
     */
    private SceneManager sceneManager;
    /**
     * Stores the Gridpane that contains all GUI components
     */
    private GridPane root;
    /**
     * The Scene for this class.
     */
    private Scene scene;
    /**
     * Variable that stores deserialized ArrayList that contains all previous high scores
     */
    private LeaderboardStore store;

    /**
     * Constructor to set value of the sceneManager, and store by deserializing the previous high scores.
     * @param sceneManager the sceneManager to communicate between different scenes.
     */
    LeaderBoard(SceneManager sceneManager) {
        this.sceneManager=sceneManager;
        store=null;
        try{
            store=LeaderboardStore.deserialize();
        }
        catch (IOException e){

        }
        if (store==null){
            store=new LeaderboardStore(new ArrayList<>());
        }
    }

    /**
     * Initialize the root and add all the elements in it.
     * @return the current scene
     */
    public Scene start(){
        root=new GridPane();
        root.setBackground(Background.EMPTY);
        scene=new Scene(root,600,600, Color.BLACK);
        root.setAlignment(Pos.CENTER);
        scene.getStylesheets().add("leader.css");
        addButtons();
        return scene;
    }

    /**
     * To add all the high scores and GUI components to the scene.
     */
    private void addButtons(){
        Button back=Generate.createButton("Back",300.0,400.0,50,20);
        back.setOnAction(e ->{
            sceneManager.showMainMenu();
        });

        try {
            FileInputStream inputStream=new FileInputStream ("src\\Leaderboard.png");
            Image image=new Image(inputStream,400,60,true,true);
            ImageView i=new ImageView(image);
            root.add(i,1,0);
            GridPane.setHalignment(i, HPos.CENTER);
            GridPane.setValignment(i, VPos.CENTER);
        }

        catch (FileNotFoundException e){

        }

        Text text=new Text();
        text.setText("Date");                   //add text here
        text.setFill(Color.ORANGE);
        text.setFont(Font.font(20));
        root.add(text,0,1);

        Text text1=new Text();
        text1.setText("Score");                                           //add text here
        text1.setFill(Color.ORANGE);
        text1.setFont(Font.font(20));
        root.add(text1,2,1);

        for (int i=0;i<store.getScoreInfos().size();i++){
            Text textt=new Text();
            textt.setText(store.getScoreInfos().get(i).getDate().toString());                   //add text here
            textt.setFill(Color.WHITE);
            textt.setFont(Font.font(15));
            root.add(textt,0,i+2);

            Text text1t=new Text();
            text1t.setText(Integer.toString(store.getScoreInfos().get(i).getScore()));                                           //add text here
            text1t.setFill(Color.WHITE);
            text1t.setFont(Font.font(15));
            root.add(text1t,2,i+2);
        }

        back.setMinWidth(100);

        root.add(back,1,store.getScoreInfos().size()+2);
        GridPane.setHalignment(back, HPos.CENTER);
        GridPane.setValignment(back, VPos.CENTER);
    }
}

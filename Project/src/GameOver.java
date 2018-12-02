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
import java.util.Date;
import java.util.GregorianCalendar;


public class GameOver {
    private SceneManager sceneManager;
    private GridPane root;
    private Scene over;
    private GregorianCalendar date;

    public GameOver(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public Scene start(int score){
        root=new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(16);
        root.setVgap(30);
        root.setBackground(Background.EMPTY);
        over=new Scene(root,600,600, Color.BLACK);
        addElements(score);
        over.getStylesheets().add("over.css");
        addToLeader(score);
        return over;

    }

    private void addToLeader(int score){
        LeaderboardStore leaderboard=null;
        try {
            leaderboard=LeaderboardStore.deserialize();
        }
        catch (IOException e){

        }
        if(leaderboard==null){
            ArrayList<ScoreInfo> sc=new ArrayList<>();
            leaderboard=new LeaderboardStore(sc);
        }
        leaderboard.add(new ScoreInfo(new Date(),score));
        try{
            LeaderboardStore.serialize(leaderboard);
        }
        catch (IOException e){

        }
    }

    private void addElements(int score){
        try {
            FileInputStream inputStream=new FileInputStream ("src\\gameover.png");
            Image image=new Image(inputStream,500,100,true,true);
            ImageView i=new ImageView(image);
            root.add(i,0,0);
            GridPane.setHalignment(i, HPos.CENTER);
            GridPane.setValignment(i, VPos.CENTER);
        }

        catch (FileNotFoundException e){

        }
        Text t= new Text("Your Score: "+score);
        t.setFill(Color.WHITE);
        t.setFont(Font.font(25));
        root.add(t,0,1);

        Button menu=Generate.createButton("Main Menu",400.0,200.0,50,20);
        menu.setOnAction(e ->{
            sceneManager.showMainMenu();
        });
        GridPane.setHalignment(t, HPos.CENTER);
        GridPane.setValignment(t, VPos.CENTER);

        GridPane.setHalignment(menu, HPos.CENTER);
        GridPane.setValignment(menu, VPos.CENTER);
        root.add(menu,0,2);
    }
}

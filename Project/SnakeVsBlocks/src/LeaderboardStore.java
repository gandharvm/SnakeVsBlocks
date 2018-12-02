import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * Class to store the highscores of the player
 */
public class LeaderboardStore implements Serializable {
    /**
     * to check if the class has been changed before deserializing
     */
    private static long serialVersionUID=42L;
    /**
     * Arraylist of high scores
     */
    private ArrayList<ScoreInfo> scoreInfos;

    /**
     * Get arraylist containing top 10 highscores
     * @return arraylist containing top 10 highscores
     */
    public ArrayList<ScoreInfo> getScoreInfos() {
        return scoreInfos;
    }

    /**
     * Constructor to save value of highscores
     * @param scoreInfos arraylist of top10 hgh scores.
     */
    public LeaderboardStore(ArrayList<ScoreInfo> scoreInfos) {
        this.scoreInfos = scoreInfos;
    }

    /**
     * Deserializing the previous 10 high scores of the player
     * @return the object containing previous 10 high scores of the player
     * @throws IOException
     */
    public static LeaderboardStore deserialize()throws IOException {
        ObjectInputStream in =null;
        LeaderboardStore leaderboard=null;
        try{
            in=new ObjectInputStream(new FileInputStream("scores.txt"));
            leaderboard=(LeaderboardStore) in.readObject();
        }
        catch (ClassNotFoundException | IOException e){

        }
        finally {
            if (in!=null){
                in.close();
            }
        }
        return leaderboard;
    }

    /**
     * Serialize/save top 10 high scores of the player
     * @param leaderboard object to be serialized
     * @throws IOException
     */
    public static void serialize(LeaderboardStore leaderboard)throws IOException{
        ObjectOutputStream out =null;
        try{
            out=new ObjectOutputStream(new FileOutputStream("scores.txt"));
            out.writeObject(leaderboard);

        }catch ( IOException e){

        }
        finally {
            if (out!=null){
                out.close();
            }
        }

    }

    /**
     * Add the current score of the player and check if it should be in the top 10 hig scores
     * @param scoreInfo score of player in the game that just ended
     */
    public void add(ScoreInfo scoreInfo){
        scoreInfos.add(scoreInfo);
        Collections.sort(scoreInfos, new Comparator<ScoreInfo>() {
            @Override
            public int compare(ScoreInfo o1, ScoreInfo o2) {
                if (o1.getScore()<o2.getScore())return 1;
                else if (o1.getScore()==o2.getScore())return 0;
                else return -1;
            }
        });
        if (scoreInfos.size()>10)scoreInfos.remove(10);
    }
}

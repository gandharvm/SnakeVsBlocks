import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LeaderboardStore implements Serializable {
    private static long serialVersionUID=42L;
    private ArrayList<ScoreInfo> scoreInfos;

    public ArrayList<ScoreInfo> getScoreInfos() {
        return scoreInfos;
    }

    public LeaderboardStore(ArrayList<ScoreInfo> scoreInfos) {
        this.scoreInfos = scoreInfos;
    }

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

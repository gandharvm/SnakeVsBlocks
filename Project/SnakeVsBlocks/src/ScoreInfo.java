import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * To serialize the highscore and associate date together
 */
public class ScoreInfo implements Serializable {
    /**
     * Date of the score
     */
    private Date date;
    /**
     * the high score
     */
    private int score;

    /**
     * Get the high score
     * @return the high score
     */
    public int getScore() {
        return score;
    }

    /**
     * Get the date
     * @return Date of the score
     */
    public String getDate() {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd");
        return ft.format(date);
    }

    /**
     * Constructor to store the high score and date
     * @param date Date of the score
     * @param score Value of the score
     */
    public ScoreInfo(Date date, int score) {
        this.date = date;
        this.score = score;
    }
}

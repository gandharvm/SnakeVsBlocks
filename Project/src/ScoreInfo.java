import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class ScoreInfo implements Serializable {
    private Date date;
    private int score;

    public int getScore() {
        return score;
    }

    public String getDate() {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd");
        return ft.format(date);
    }

    public ScoreInfo(Date date, int score) {
        this.date = date;
        this.score = score;
    }
}

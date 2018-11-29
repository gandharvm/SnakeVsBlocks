import javafx.scene.layout.VBox;

import java.io.Serializable;

public class Player implements Serializable {
    private int score;
    private Snake snake;

    public Player() {
        this.score = 0;
        this.snake=new Snake();
    }

    public VBox getSnake(){
        return snake.getSnake();
    }
}

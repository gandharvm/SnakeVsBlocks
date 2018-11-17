import javafx.scene.layout.VBox;

public class Player {
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

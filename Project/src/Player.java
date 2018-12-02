public class Player {
    private int score;
    private Snake snake;

    public Player() {
        this.score = 0;
        this.snake=new Snake(5);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Snake getSnake(){
        return snake;
    }


}

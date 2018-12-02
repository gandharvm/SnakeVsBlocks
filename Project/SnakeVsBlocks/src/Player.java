/**
 * Stores info about the player of this game. Contains the snake class.
 */
public class Player {
    /**
     * Current score of the player
     */
    private int score;
    /**
     *Instance of the snake for the player
     */
    private Snake snake;

    /**
     * Contructor to initialize score to 0 and length of snake to 7.
     */
    public Player() {
        this.score = 0;
        this.snake=new Snake(7);
    }

    /**
     * Get current score of player
     * @return current score of player
     */
    public int getScore() {
        return score;
    }

    /**
     * Change the score of the player
     * @param score new score of player
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Get instance of snake of the player
     * @return instance of snake of the player
     */
    public Snake getSnake(){
        return snake;
    }


}

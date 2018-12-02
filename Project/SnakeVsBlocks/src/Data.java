import java.io.*;

/**
 * Class is used for serialisation of the game in case a game is not completed. This is made because javafx elements donot by default support serialisation.
 */
public class Data implements Serializable {
    /**
     * Score of the user at the time of quitting the game.
     */
    private int score;

    /**
     * Length of the snake at the time of quitting the game.
     */
    private int length;

    /**
     * The value of the variable SPEED in Game class at the time of quitting the game.
     */
    private double SPEED;

    /**
     * Construtor for the class to store the variables.
     * @param score Score of the user
     * @param SPEED The value of the variable SPEED in Game class
     * @param length Length of the snake
     */
    public Data(int score, double SPEED, int length) {
        this.score = score;
        this.SPEED = SPEED;
        this.length = length;
    }

    /**
     * To change the value of the score to be saved.
     * @param score Score of the user
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * To change the value of the SPEED to be saved.
     * @param SPEED The value of the variable SPEED in Game class
     */
    public void setSPEED(double SPEED) {
        this.SPEED = SPEED;
    }

    /**
     * To change the value of the length to be saved
     * @param length Length of the snake
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Get the value of score of player
     * @return score of the player
     */
    public int getScore() {
        return score;
    }

    /**
     * Get the SPEED
     * @return value of SPEED
     */
    public double getSPEED() {
        return SPEED;
    }

    /**
     * Get the length
     * @return Length of the snake
     */
    public int getLength() {
        return length;
    }

    /**
     * Deserialize the data while resuming the game.
     * @return data that was saved
     * @throws IOException
     */
    public static Data deserialize()throws IOException {
        ObjectInputStream in =null;
        Data data=null;
        try{
            in=new ObjectInputStream(new FileInputStream("out.txt"));
            data=(Data) in.readObject();
        }
        catch (ClassNotFoundException | IOException e){

        }
        finally {
            if (in!=null){
                in.close();
            }
        }
        return data;
    }

    /**
     * Save/Serialize the data currently in the object.
     * @param data Data to be serialized
     * @throws IOException
     */
    public static void serialize(Data data)throws IOException{
        ObjectOutputStream out =null;
        try{
            out=new ObjectOutputStream(new FileOutputStream("out.txt"));
            out.writeObject(data);

        }catch ( IOException e){

        }
        finally {
            if (out!=null){
                out.close();
            }
        }

    }
}

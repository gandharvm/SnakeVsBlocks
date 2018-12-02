import java.io.*;

public class Data implements Serializable {
    private int score,length;
    private double SPEED;

    public Data(int score, double SPEED, int length) {
        this.score = score;
        this.SPEED = SPEED;
        this.length = length;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setSPEED(double SPEED) {
        this.SPEED = SPEED;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getScore() {
        return score;
    }

    public double getSPEED() {
        return SPEED;
    }

    public int getLength() {
        return length;
    }

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

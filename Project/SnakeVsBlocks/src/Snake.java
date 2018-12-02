import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * The snake for the player. Eats the blocks. Its speed increases with increase in length.
 */
public class Snake{

    /**
     * The vertical box containing the snake and the text field of its length
     */
    private VBox vbox;
    /**
     * Length of the snake
     */
	private int length;
    /**
     * The first circle in the snake
     */
	private Circle head;
    /**
     * Text for the length of the snake
     */
	private Text l;

    /**
     * Get the length of the snake
     * @return length of the snake
     */
    public int getLength() {
        return length;
    }

    /**
     * Constructor for making the snake of the desired length
     * @param length length of the snake
     */
    public Snake(int length) {
		
		vbox = new VBox();

        this.length=0;
        l=new Text (Integer.toString(length));
        l.setFont(Font.font(16));
        l.setFill(Color.WHITE);
        l.setLayoutX(305);
        l.setLayoutY(395);
        vbox.getChildren().add(l);

		head= new Circle(10);
		head.setFill(Color.RED);
		head.setLayoutX(300);
		head.setLayoutY(400);
		vbox.getChildren().add(head);

		vbox.setLayoutX(300);
		vbox.setLayoutY(400);

		updateLength(length);

	}

    /**
     * Return reference to the first circle of snake
     * @return the first circle
     */
    public Circle getHead() {
        return head;
    }

    /**
     * Get the vertical box containing all balls of the snake
     * @return vertical box containing all balls of the snake
     */
    public VBox getSnake() {
		return vbox;
	}

    /**
     * Set the length of the snake
     * @param length the length of the snake
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Change the length of the snake in GUI
     * @param length the length of the snake
     */
    public void updateLength(int length){
	    if (this.length<=length){
            for (int i=this.length;i<length;i++) {
                Circle temp = new Circle(10);
                temp.setFill(Color.RED);
                vbox.getChildren().add(temp);
            }
        }

        else {
            for (int i=this.length;i>length;i--){
                vbox.getChildren().remove(i);
            }

        }
        this.length=length;
        l.setText(Integer.toString(length));
    }
	
}

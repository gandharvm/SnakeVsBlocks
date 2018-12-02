import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Snake{

	private Color color = Color.RED;
	private VBox vbox;
	private int length;
	private Circle head;
	private Text l;

    public int getLength() {
        return length;
    }

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
		head.setFill(color);
		head.setLayoutX(300);
		head.setLayoutY(400);
		vbox.getChildren().add(head);

		vbox.setLayoutX(300);
		vbox.setLayoutY(400);

		updateLength(length);

	}

    public Circle getHead() {
        return head;
    }

    public VBox getSnake() {
		return vbox;
	}

    public void setLength(int length) {
        this.length = length;
    }

    public void updateLength(int length){
	    if (this.length<=length){
            for (int i=this.length;i<length;i++) {
                Circle temp = new Circle(10);
                temp.setFill(color);
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


import java.util.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Panel {

	private ArrayList<StackPane> Blk = new ArrayList<>(8);
	
	private int getValue() {
		Random rand = new Random();
		return rand.nextInt(10) + 1;
	}
	
	private Color getColor() {
		Color[] C = new Color[16];
		
		C[0]=Color.AQUA;
		C[1]=Color.YELLOW;
		C[2]=Color.BLUE;
		C[3]=Color.DEEPPINK;
		C[4]=Color.LAWNGREEN;
		C[5]=Color.LIME;
		C[5]=Color.ORANGERED;
		C[6]=Color.TOMATO;
		C[7]=Color.SPRINGGREEN;
		C[8]=Color.STEELBLUE;
		C[9]=Color.ORANGE;
		C[10]=Color.MEDIUMORCHID;
		C[11]=Color.INDIANRED;
		C[12]=Color.DODGERBLUE;
		C[13]=Color.DARKORANGE;
		C[14]=Color.CRIMSON;
		C[15]=Color.TURQUOISE;

		
		Random randC = new Random();
		int index = randC.nextInt(16);
		
		return C[index];
	}
	
	public Panel(int yPos,int length) {
		int counter = 75;
		Random rand = new Random();
		int pos=rand.nextInt(8);
		for(int i=0; i < 8; i++) {
			Text value;
			if (i!=pos){
				int points = this.getValue();
				value = new Text(Integer.toString(points));
			}
			else {
				if (length>0){
					value=new Text(Integer.toString(length-1));
				}
				else {
					value=new Text(Integer.toString(0));
				}
			}
			value.setFont(Font.font(15));
			Color colorValue = this.getColor();
			Block temp = new Block(colorValue);
			StackPane stkpane = new StackPane();
			stkpane.getChildren().addAll(temp, value);	
			stkpane.setLayoutX(i*counter); stkpane.setLayoutY(yPos);
			
			Blk.add(stkpane);
		}
	}


	public ArrayList<StackPane> getPane(){
		return Blk;
	}

	public void deleteBlock(int index){
	    Blk.get(index).setVisible(false);
	    Blk.remove(index);

    }

    public int getValueOfBlock(int index){
	    Text t=(Text) Blk.get(index).getChildren().get(1);
	    return Integer.parseInt(t.getText());
    }
}

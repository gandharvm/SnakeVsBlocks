
import java.util.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Panel {
	
	private ArrayList<StackPane> Blk = new ArrayList<StackPane>(8);
	
	private int getValue() {
		Random rand = new Random();
		return rand.nextInt(50) + 1; 
	}
	
	private Color getColor() {
		Color[] C = new Color[16];
		
		C[0] = Color.AQUA;
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
	
	public Panel(int yPos) {
		int counter = 75;
		for(int i=0; i < 8; i++) {
			
			int points = this.getValue();
			Text value = new Text(Integer.toString(points));
			
			Color colorValue = this.getColor();
			Block temp = new Block(75, 75, colorValue);

			StackPane stkpane = new StackPane();
			stkpane.getChildren().addAll(temp, value);	
			stkpane.setLayoutX(i*counter); stkpane.setLayoutY(yPos);
			
			Blk.add(stkpane);
		}
	}

	public Panel(int xPos,int yPos){

	}
	
	public ArrayList<StackPane> getPane(){
		return Blk;
	}
	
	
	
}

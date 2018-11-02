package application;

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
		Color[] C = new Color[10];
		
		C[0] = Color.AQUA;
		C[1] = Color.ANTIQUEWHITE;
		C[2] = Color.BLUEVIOLET;
		C[3] = Color.DARKORANGE;
		C[4] = Color.GOLD;
		C[5] = Color.GREEN;
		C[6] = Color.HOTPINK;
		C[7] = Color.YELLOW;
		C[8] = Color.BEIGE;
		C[9] = Color.ORCHID;
		
		Random randC = new Random();
		int index = randC.nextInt(10); 
		
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
	
	public ArrayList<StackPane> getPane(){
		return Blk;
	}
	
	
	
}

import javax.swing.*;
import ui.Game;


public class TankerMan {
	
	static JFrame frame = new JFrame("TankerMan");
	
	public TankerMan(){
		new TankerMan();
	}
	
	public static void main(String[] args){
		Game game = new Game(frame);
		game.start(frame);
	}
	
}

package project;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Renderer extends JPanel     // in the main pong class we used **jframe.add(renderer);** and the controls comes here in search of a component to be added in the JFrame Container which in this case is the JPanel
{
	private static final long serialVersionUID = 1L;
	
	// protected means that we can only call paintComponent method in this current class and its sub classes . in other classes we cannot call paintComponent directly so we use paint() or repaint().
	protected void paintComponent(Graphics g)  // we need to use a paintComponent which is a built in class of java in " java.awt.Graphics " To PAINT OUR GAME
	{
		super.paintComponent(g);  // this line controls the painting and drawing of the components.
		Pong.pong.render((Graphics2D) g);  // The paintComponent Sends the control to the render method in the Pong Class.
	}
	
	
	/* In 2D gaming there is always a game loop that is going around doing everything but it is not like the loop used in the coding of java for console output .
	 * The Game Loop repeats itself again and again unless the game is finished .
	 * Every time the game loop goes through it updates the game and it repaints the objects or components of the game , clears the screen and repaints it again.
	 * It UPDATES , PAINTS , And clears
	 * But this process is so fast that we dont recognize it executing .
	*/
}
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import com.leapmotion.leap.*;

public class Gui extends JFrame  {
	
	static Controller control = new Controller();
	
	public static void main (String [] args){
		createWindow();
		
	}
	
	private static void createWindow() {
		JFrame frame = new JFrame("Sign Language Learning");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel textLabel = new JLabel("I'm a label in the window",SwingConstants.CENTER);
		frame.addKeyListener(new KeyListener(){

			
			public void keyTyped(KeyEvent e) {
				Frame frame = new controllerFrame();
				// TODO adding frame=controllerFrame();
				// checkAns(frame, letter);
					//checkans should return boolean, if true, change color of border to green
					// if false change to red
					// try again with same or new char
				
			}

			
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		
		textLabel.setPreferredSize(new Dimension(300, 100));
		frame.getContentPane().add(textLabel);
		frame.pack();
		frame.setVisible(true); 
	 }
	
	public boolean checkAnswer(Frame frame, char letter){
		
		return true;
	}


}


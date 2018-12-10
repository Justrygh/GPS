package GUI;
import java.awt.Color;
/**
 * Code taken from: https://javatutorial.net/display-text-and-graphics-java-jframe
 * 
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class JFrameGraphics extends JPanel{


	public void paint(Graphics g){
		 //Image image = Toolkit.getDefaultToolkit().getImage("example.jpg");
			int w = this.getWidth();
			int h = this.getHeight();
			 g.setColor(Color.blue);
			 g.fillOval(w/3, h/3, w/3, h/3);
			g.setColor(Color.red);
			String s = " ["+w+","+h+"]";
		    g.drawString(s, w/3, h/2);
		   
		    
	}
	
	public static void main(String[] args){
		JFrame frame= new JFrame("JavaTutorial.net");	
		frame.getContentPane().add(new JFrameGraphics());
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setName("JFrame example");
	}
}
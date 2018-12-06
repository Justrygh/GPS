package Structure;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Players.Fruit;
import Players.Pacman;

public class Map extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JMenuBar _MB;
	private int x, y;
	private boolean isPacman = false;
	private boolean isFruit = false;

	private ArrayList<Fruit> _Fruits = new ArrayList<Fruit>();
	private ArrayList<Pacman> _Pacmans = new ArrayList<Pacman>();

	private static void setMB(JMenuBar menu) {
		_MB = menu;
	}

	public static JMenuBar getMB() {
		return _MB;
	}

	public Map() {
		initGUI();
		this.addMouseListener(this); 
	}

	private void initGUI() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("File");
		JMenuItem newf = new JMenuItem("   New   ");
		JMenuItem open = new JMenuItem("   Open File...   ");
		JMenuItem save = new JMenuItem("   Save As...   ");
		JMenu menu2 = new JMenu("Edit");
		JMenuItem clear = new JMenuItem("   Clear   ");

		JMenuItem pacman = new JMenuItem("   Pacman   ", KeyEvent.VK_F);
		JMenuItem fruit = new JMenuItem("   Fruit   ", KeyEvent.VK_S);

		menuBar.add(menu1);
		menuBar.add(menu2);

		menu2.add(pacman);
		pacman.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isPacman = true;
				isFruit = false;
			}
		});
		menu2.add(fruit);
		fruit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isFruit = true;
				isPacman = false;
			}
		});

		menu1.add(newf);
		menu1.add(open);
		menu1.add(save);
		menu2.add(clear);
		setMB(menuBar);

	}

	public void paint(Graphics g){
		Image image = Toolkit.getDefaultToolkit().getImage("/home/eli/eclipse-workspace/OOP_EX2-EX4-master/newdata/Ariel1.png");
		int w = this.getWidth();
		int h = this.getHeight();
		g.drawImage(image, 0, 0, w, h, this);  
	}

	public static void main(String[] args){
		JFrame frame= new JFrame("Ariel Map");
		frame.getContentPane().add(new Map());
		frame.setJMenuBar(_MB);
		frame.setSize(1433, 642);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
	}

	protected void paintElement() {
		//	The method getGraphics is called to obtain a Graphics object
		Graphics g = getGraphics();
		Image Pacman = Toolkit.getDefaultToolkit().getImage("/home/eli/eclipse-workspace/OOP_EX2-EX4-master/newdata/Pacman.png");
		Image Apple = Toolkit.getDefaultToolkit().getImage("/home/eli/eclipse-workspace/OOP_EX2-EX4-master/newdata/Apple.png");
		if(isPacman == true){
			if(g.drawImage(Pacman, x, y, this) == true) {
				g.setFont(new Font("Monospaced", Font.PLAIN, 14));  
				g.setColor(Color.WHITE);
				g.drawString("("+Integer.toString(x)+","+Integer.toString(y)+")",x, y);
			}
		} 
		if(isFruit == true){
			if(g.drawImage(Apple, x, y, this) == true) {
				g.setFont(new Font("Monospaced", Font.PLAIN, 14));  
				g.setColor(Color.WHITE);
				g.drawString("("+Integer.toString(x)+","+Integer.toString(y)+")",x, y);
				_Fruits.add(new Fruit("Fruit", x+","+y+","+"0", "Apple"));
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x = e.getX()-16;
		y = e.getY()-16;  
		paintElement();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}
}

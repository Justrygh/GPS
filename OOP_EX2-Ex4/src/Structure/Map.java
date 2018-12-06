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
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Algorithms.ShortestPathAlgo;
import Players.Fruit;
import Players.Game;
import Players.Pacman;

public class Map extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	private static JMenuBar _MB;
	private int x, y;
	private boolean isPacman = false;
	private boolean isFruit = false;
	private File myFile = new File("/home/eli/eclipse-workspace/OOP_EX2-EX4-master/newdata/CoordsConvert.kml");

	private ArrayList<Fruit> _Fruits = new ArrayList<Fruit>();
	private ArrayList<Pacman> _Pacmans = new ArrayList<Pacman>();
	private ArrayList<Game> _List;

	private static void setMB(JMenuBar menu) {
		_MB = menu;
	}
	
	private void setList(ArrayList<Game> list) {
		this._List = list;
	}
	
	public ArrayList<Game> getList(){
		return this._List;
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
		
		JMenuItem save = new JMenuItem("   Save  ");
		menu1.add(save);
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isFruit = false;
				isPacman = false;
				frame.dispose();
				setList(ConvertPoints());
				ShortestPathAlgo it = new ShortestPathAlgo(getList());
				setList(it.Calculate());
				Game Test = new Game(myFile);
				Test.write(getList());
			}
		});
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
		frame = new JFrame("Ariel Map");
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
			if(g.drawImage(Pacman, x-16, y-16, this) == true) {
				g.setFont(new Font("Monospaced", Font.PLAIN, 14));  
				g.setColor(Color.WHITE);
				g.drawString("("+Integer.toString(x)+","+Integer.toString(y)+")",x, y);
				_Pacmans.add(new Pacman("Pacman", x+","+y+","+"0", "1", "1", "Pacman"));
			}
		} 
		if(isFruit == true){
			if(g.drawImage(Apple, x-16, y-16, this) == true) {
				g.setFont(new Font("Monospaced", Font.PLAIN, 14));  
				g.setColor(Color.WHITE);
				g.drawString("("+Integer.toString(x)+","+Integer.toString(y)+")",x, y);
				_Fruits.add(new Fruit("Fruit", x+","+y+","+"0", "Apple"));
			}
		}
	}
	
	public ArrayList<Game> ConvertPoints(){
		ArrayList<Game> _List = new ArrayList<Game>();
		for(int i=0; i<_Pacmans.size(); i++) {
			String[] arrP = (_Pacmans.get(i).getPoint().split(","));
			double newY = (32.10565 - (Double.parseDouble(arrP[1])/150000));
			double newX = (35.2022 + (Double.parseDouble(arrP[0])/125000));
			_Pacmans.get(i).setPoint(String.valueOf(newX)+","+String.valueOf(newY)+","+0);
			Game temp = new Game(_Pacmans.get(i));
			_List.add(temp);
		}
		for(int i=0; i<_Fruits.size(); i++) {
			String[] arrF = (_Fruits.get(i).getPoint().split(","));
			double newY = (32.10565 - (Double.parseDouble(arrF[1])/150000));
			double newX = (35.2022 + (Double.parseDouble(arrF[0])/125000));
			_Fruits.get(i).setPoint(String.valueOf(newX)+","+String.valueOf(newY)+","+0);
			Game temp = new Game(_Fruits.get(i));
			_List.add(temp);
		}
		return _List;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();  
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

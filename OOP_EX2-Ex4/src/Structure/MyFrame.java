Package Structure;

import java.awt.Color;


import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import Algorithms.ShortestPathAlgo;
import Declaration.Data;
import File_format.Path2kml;
import Geom.Point3D;
import Players.Block;
import Players.Fruit;
import Players.Game;
import Players.Ghost;
import Players.Pacman;
import Players.Player;

public class MyFrame extends JPanel implements MouseListener {

	/**
	 * @author Eli
	 * @author Qusai This class is the main Class that represents our GUI + Game.
	 */

	// **********Private Variables**********//

	private static final long serialVersionUID = 1L;
	private static MenuBar _MB;
	private int H = 642;
	private int y, x = 0;
	private int W = 1299;
	private int newH = 32;
	private int newW = 32;
	//	private int Bx1;
	//	private int By1;
	//	private int Bx2;
	//	private int By2;

	private boolean isPacman = false;
	private boolean isFruit = false;
	private boolean isSaved = false;
	private boolean isDemo = false;
	private boolean instructions = false;
	private boolean Stop = false;
	private boolean isPlayer = false;
	private boolean isClicked = false;
	private boolean isOnline = false;
	private boolean isTimer = false;
	//	private boolean isBlock = false;
	//	private boolean isGhost = false;

	private Thread threadobj;


	private int i = 0;
	private int j = 0;
	private int p = 0;
	private int ghostStop = 0;
	private final double MaxTime = 100.0;
	private int _gameID;
	//	private int k = 0;
	//	private int b = 0;

	private Map _Map;
	private Player _Player;
	private Path _Path;
	private boolean[][] _Mat;

	private ArrayList<Fruit> _Fruits;
	private ArrayList<Pacman> _Pacmans;
	private ArrayList<Game> _List;
	private ArrayList<Image> _Icons;
	private ArrayList<Image> _GhostsImg;
	private ArrayList<Path> pList;
	private ArrayList<Ghost> _Ghosts;
	private ArrayList<Block> _Blocks;
	private ArrayList<Long> _Ids;
	private ArrayList<Integer> _Helper;


	// **********Private Methods**********//

	private static void setMB(MenuBar menu) {
		_MB = menu;
	}

	public Player getPlayer() {
		return this._Player;
	}

	public boolean[][] getMat(){
		return this._Mat;
	}

	private void setMat(boolean[][] mat) {
		this._Mat = mat;
	}

	public ArrayList<Block> getBList(){
		return this._Blocks;
	}

	private void setBList(ArrayList<Block> blist) {
		this._Blocks = blist;
	}

	private void setList(ArrayList<Game> list) {
		this._List = list;
	}

	public ArrayList<Game> getList() {
		return this._List;
	}

	public void setPList(ArrayList<Pacman> pList) {
		this._Pacmans = pList;
	}

	public void setFList(ArrayList<Fruit> fList) {
		this._Fruits = fList;
	}

	public void setiDList(ArrayList<Long> ids) {
		this._Ids = ids;
	}

	public void setGList(ArrayList<Ghost> gList) {
		this._Ghosts = gList;
	}

	public ArrayList<Ghost> getGList() {
		return this._Ghosts;
	}

	public ArrayList<Pacman> getPList() {
		return this._Pacmans;
	}

	public ArrayList<Fruit> getFList() {
		return this._Fruits;
	}

	public static MenuBar getMB() {
		return _MB;
	}

	/**
	 * Default Constructor
	 */
	public MyFrame() {
		initGUI();
		this.addMouseListener(this);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Game GUI");
		frame.getContentPane().add(new MyFrame());
		frame.setMenuBar(_MB);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1299, 697);
		frame.setResizable(true);
		frame.setVisible(true);
	}

	/**
	 * This function creates our MenuBar. Each button in our MenuBar
	 * enables/disables functions that we create in other classes. You have
	 * instructions in the main GUI when you run the game for the first time.
	 */
	private void initGUI() {
		_Fruits = new ArrayList<Fruit>();
		_Pacmans = new ArrayList<Pacman>();
		_List = new ArrayList<Game>();
		_Icons = new ArrayList<Image>();
		_GhostsImg = new ArrayList<Image>();
		pList = new ArrayList<Path>();
		_Ghosts = new ArrayList<Ghost>();
		_Blocks = new ArrayList<Block>();
		_Ids = new ArrayList<Long>();
		_Player = new Player();
		_Map = new Map();
		_Path = new Path();
		_Mat = new boolean[_Map.getHeight()][_Map.getWidth()];

		MenuBar menuBar = new MenuBar();
		Menu menu1 = new Menu("File");
		MenuItem here = new MenuItem("   New   ");
		MenuItem open = new MenuItem("   Open File...   ");
		MenuItem edit = new MenuItem("   Open & Edit   ");
		MenuItem save = new MenuItem("   Save ");
		Menu menu2 = new Menu("Edit");
		MenuItem pacman = new MenuItem("   Pacman   ");
		MenuItem fruit = new MenuItem("   Fruit   ");
		MenuItem clear = new MenuItem("   Clear   ");
		Menu menu3 = new Menu("Demo");
		MenuItem run = new MenuItem("   Run   ");
		MenuItem demo = new MenuItem("   Demo  ");
		MenuItem space = new MenuItem("------------------------------");
		MenuItem start = new MenuItem("   Start   ");
		MenuItem stop = new MenuItem("   Stop   ");
		MenuItem space1 = new MenuItem("------------------------------");
		MenuItem kml = new MenuItem("   Convert to KML   ");
		Menu menu4 = new Menu("   Define   ");
		MenuItem speedbar = new MenuItem("----------SPEED----------");
		MenuItem speedall = new MenuItem("   Default Pacmans   ");
		MenuItem speedp = new MenuItem("   Single Pacman  ");
		MenuItem radiusbar = new MenuItem("----------RADIUS----------");
		MenuItem radiusall = new MenuItem("   Defualt Pacmans   ");
		MenuItem radiusp = new MenuItem("   Single Pacman   ");
		Menu menu5 = new Menu("   New Game   ");
		MenuItem opengame = new MenuItem("   Open File...   ");
		MenuItem cleargame = new MenuItem("   Clear   ");
		Menu menu6 = new Menu("   Adjust   ");
		MenuItem player = new MenuItem("   Player   ");
		MenuItem insertid = new MenuItem("   Insert ID   ");
		Menu menu8 = new Menu("   Play   ");
		MenuItem easybar = new MenuItem("----------Difficult: Easy----------");
		MenuItem play = new MenuItem("   Click & Play   ");
		MenuItem autoplay = new MenuItem("   Auto Play   ");
		MenuItem hardbar = new MenuItem("----------Difficult: Hard----------");
		MenuItem playhard = new MenuItem("   Click & Play   ");
		MenuItem autoplayhard = new MenuItem("   Auto Play   ");
		Menu menu7 = new Menu("   Switch   ");
		MenuItem online = new MenuItem("   Online   ");
		MenuItem offline = new MenuItem("   Offline   ");
		JPopupMenu popup = new JPopupMenu();

		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);
		menuBar.add(menu4);
		menuBar.setHelpMenu(menu7);

		menu1.add(here);
		here.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				instructions = true;
				repaint();
				isPacman = true;
				isFruit = true;
				paintElement();
				paintElement();
				isPacman = false;
				isFruit = false;
				open.setEnabled(true);
				edit.setEnabled(true);
				menu1.remove(here);
				menu2.setEnabled(true);
				menu7.setEnabled(true);
				menu7.remove(offline);
				helpUsNow();
			}
		});
		here.setEnabled(true);

		menu1.add(open);
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menu3.setEnabled(true);
				menu4.setEnabled(false);
				pacman.setEnabled(false);
				fruit.setEnabled(false);
				save.setEnabled(false);
				clearLists();
				JFileChooser chooser = new JFileChooser();
				int returnName = chooser.showOpenDialog(null);
				String path = "";
				if (returnName == JFileChooser.APPROVE_OPTION) {
					File f = chooser.getSelectedFile();
					if (f != null && f.getName().endsWith(".csv")) {
						path = f.getAbsolutePath();
					}
				}
				Game Try = new Game(new File(path));
				ShortestPathAlgo Name = new ShortestPathAlgo(Try.read());
				setListsSPA(Name);
				repaint();
			}
		});
		open.setEnabled(false);

		menu1.add(edit);
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menu3.setEnabled(false);
				menu4.setEnabled(true);
				menu2.setEnabled(true);
				pacman.setEnabled(true);
				fruit.setEnabled(true);
				save.setEnabled(true);
				clearLists();
				JFileChooser chooser = new JFileChooser();
				int returnName = chooser.showOpenDialog(null);
				String path = "";
				if (returnName == JFileChooser.APPROVE_OPTION) {
					File f = chooser.getSelectedFile();
					if (f != null && f.getName().endsWith(".csv")) {
						path = f.getAbsolutePath();
					}
				}
				Game Try = new Game(new File(path));
				ShortestPathAlgo Name = new ShortestPathAlgo(Try.read());
				setListsSPA(Name);
				i = getPList().size();
				j = getFList().size();
				repaint();
			}
		});
		edit.setEnabled(false);

		menu1.add(save);
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isPacman = false;
				isFruit = false;
				save.setEnabled(false);
				menu2.setEnabled(false);
				menu3.setEnabled(true);
				menu4.setEnabled(false);
				_List = new ArrayList<Game>();
				setList(saveList());
				JFileChooser chooser = new JFileChooser();
				int result = chooser.showSaveDialog(null);
				String path = "";
				if (result == JFileChooser.APPROVE_OPTION) {
					File f = new File(chooser.getSelectedFile() + ".csv");
					try {
						f.createNewFile();
						path = f.getAbsolutePath();
						print(f, _Map.ConvertPoints2GPS(getList()));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				clearLists();
				Game Try = new Game(new File(path));
				ShortestPathAlgo Name = new ShortestPathAlgo(Try.read());
				setListsSPA(Name);
				repaint();
			}
		});
		save.setEnabled(false);

		menu2.add(pacman);
		pacman.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isPacman = true;
				isFruit = false;
				menu3.setEnabled(false);
				menu4.setEnabled(true);
				save.setEnabled(true);
			}
		});

		menu2.add(fruit);
		fruit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isFruit = true;
				isPacman = false;
				menu3.setEnabled(false);
				save.setEnabled(true);
			}
		});

		menu2.add(clear);
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearLists();
				repaint();
				Stop = false;
				isDemo = false;
				isSaved = false;
				isPacman = false;
				isFruit = false;
				menu1.setEnabled(true);
				demo.setEnabled(true);
				kml.setEnabled(true);
				run.setEnabled(true);
				stop.setEnabled(false);
				start.setEnabled(false);
				menu4.setEnabled(false);
				save.setEnabled(false);
				menu3.setEnabled(false);
				pacman.setEnabled(true);
				fruit.setEnabled(true);
				menu7.setEnabled(true);
			}
		});

		menu3.add(run);
		run.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pacman.setEnabled(false);
				fruit.setEnabled(false);
				menu1.setEnabled(false);
				menu4.setEnabled(false);
				menu7.setEnabled(false);
				isSaved = true;
				paths(getGraphics());
				menu3.setEnabled(false);
				repaint();
			}
		});

		menu3.add(demo);
		demo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isDemo = true;
				isPacman = false;
				isFruit = false;
				isSaved = false;
				menu1.setEnabled(false);
				menu2.setEnabled(false);
				menu4.setEnabled(false);
				menu7.setEnabled(false);
				run.setEnabled(false);
				kml.setEnabled(false);
				stop.setEnabled(true);
				calculatePath();
				pList = _Path.Create(getList(), getPList());
				removeFruitIcon();
				Thread t1 = new Thread(new Runnable() {
					public void run() {
						pList = _Path.Print(pList);
						for (int i = 0; i < _Pacmans.size(); i++) {
							for (int j = 0; j < pList.size(); j++) {
								if (_Pacmans.get(i).getiD().equals(pList.get(j).getList().get(0).getiD())) {
									//									double angel = pList.get(j).getList().get(0).getAngel();
									//									while(angel<0) {
									//										angel+=360;
									//									}
									//									while(angel>360) {
									//										angel-=360;
									//									}
									Data pac = new Data(_Pacmans.get(i));
									Data fru = new Data(pList.get(i).getList().get(0));
									Point3D Pac = new Point3D(pac.getX(), pac.getY());
									Point3D Fru = new Point3D(fru.getX(), fru.getY());
									double angel = rotatePac(Pac, Fru);
									_Pacmans.get(i).setPoint(pList.get(j).getList().get(0).getPoint());
									_Pacmans.get(i).setAngel(angel);
								}
							}
						}
						removeFruitIcon();
						repaint();
						try {
							Thread.sleep(500);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						if (pList.size() > 0 && Stop == false)
							this.run();
					}

				});
				t1.start();
				demo.setEnabled(false);
			}
		});

		menu3.add(space);
		space.setEnabled(false);

		menu3.add(start);
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stop.setEnabled(true);
				start.setEnabled(false);
				Stop = false;
				Thread t1 = new Thread(new Runnable() {
					public void run() {
						pList = _Path.Print(pList);
						for (int i = 0; i < _Pacmans.size(); i++) {
							for (int j = 0; j < pList.size(); j++) {
								if (_Pacmans.get(i).getiD().equals(pList.get(j).getList().get(0).getiD())) {
									//									double angel = pList.get(j).getList().get(0).getAngel();
									//									while(angel<0) {
									//										angel+=360;
									//									}
									//									while(angel>360) {
									//										angel-=360;
									//									}
									Data pac = new Data(_Pacmans.get(i));
									Data fru = new Data(pList.get(i).getList().get(0));
									Point3D Pac = new Point3D(pac.getX(), pac.getY());
									Point3D Fru = new Point3D(fru.getX(), fru.getY());
									double angel = rotatePac(Pac, Fru);
									_Pacmans.get(i).setPoint(pList.get(j).getList().get(0).getPoint());
									_Pacmans.get(i).setAngel(angel);
								}
							}
						}
						removeFruitIcon();
						repaint();
						try {
							Thread.sleep(500);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						if (pList.size() > 0 && Stop == false)
							this.run();
					}
				});
				t1.start();
			}
		});
		start.setEnabled(false);

		menu3.add(stop);
		stop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Stop = true;
				menu2.setEnabled(true);
				pacman.setEnabled(false);
				fruit.setEnabled(false);
				clear.setEnabled(true);
				start.setEnabled(true);
				stop.setEnabled(false);
			}
		});
		stop.setEnabled(false);

		menu3.add(space1);
		space1.setEnabled(false);

		menu3.add(kml);
		kml.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int result = chooser.showSaveDialog(null);
				File f = new File(" ");
				if (result == JFileChooser.APPROVE_OPTION) {
					f = new File(chooser.getSelectedFile() + ".kml");
					try {
						f.createNewFile();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				Path2kml pk = new Path2kml(f);
				pk.print();
				setList(convertGPS());
				pk.write(getList(), _Map.ConvertPac(getPList()));
				pk.Close();
				System.exit(0);
			}
		});

		menu4.add(speedbar);
		speedbar.setEnabled(false);

		menu4.add(speedall);
		speedall.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f = new JFrame("Default Speed");
				JButton b = new JButton("Submit");
				b.setBounds(50, 50, 100, 20);
				JTextField textfield = new JTextField();
				textfield.setBounds(50, 25, 100, 20);
				f.add(textfield);
				f.add(b);
				f.setResizable(false);
				f.setBounds(W / 3, H / 2, 0, 0);
				f.setSize(200, 120);
				f.setLayout(null);
				f.setVisible(true);

				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (textfield.getText().length() > 1 || textfield.getText().length() < 1
								|| textfield.getText().length() == 1 && Integer.parseInt(textfield.getText()) <= 0)
							return;
						for(int i=0; i<textfield.getText().length(); i++) {
							if(textfield.getText().charAt(i) < 48 || textfield.getText().charAt(i) > 57)
								return;
						}
						for (int i = 0; i < _Pacmans.size(); i++) {
							_Pacmans.get(i).setSpeed(textfield.getText());
						}
						f.dispose();
					}
				});
			}
		});

		menu4.add(speedp);
		speedp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f = new JFrame("Set Speed");
				JButton b = new JButton("Submit");
				b.setBounds(70, 75, 100, 20);
				JLabel label1 = new JLabel();
				label1.setText("ID:");
				label1.setBounds(10, -15, 100, 100);
				JLabel label2 = new JLabel();
				label2.setText("SPEED:");
				label2.setBounds(10, 10, 100, 100);
				JTextField textfield1 = new JTextField();
				textfield1.setBounds(70, 25, 100, 20);
				JTextField textfield2 = new JTextField();
				textfield2.setBounds(70, 50, 100, 20);
				f.add(textfield1);
				f.add(textfield2);
				f.add(b);
				f.add(label1);
				f.add(label2);
				f.setBounds(W / 3, H / 2, 0, 0);
				f.setSize(200, 150);
				f.setLayout(null);
				f.setVisible(true);
				f.setResizable(false);

				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						for(int i=0; i<textfield1.getText().length(); i++) {
							if(textfield1.getText().charAt(i) < 48 || textfield1.getText().charAt(i) > 57)
								return;
						}
						int id = Integer.parseInt(textfield1.getText());
						if (id >= _Pacmans.size()) {
							return;
						}
						for(int i=0; i<textfield2.getText().length(); i++) {
							if(textfield2.getText().charAt(i) < 48 || textfield2.getText().charAt(i) > 57)
								return;
						}
						if (textfield2.getText().length() < 1
								|| Integer.parseInt(textfield2.getText()) <= 0)
							return;
						for (int i = 0; i < _Pacmans.size(); i++) {
							if (id == Integer.parseInt(_Pacmans.get(i).getiD()))
								_Pacmans.get(i).setSpeed(textfield2.getText());
						}
						f.dispose();
					}
				});
			}
		});

		menu4.add(radiusbar);
		radiusbar.setEnabled(false);

		menu4.add(radiusall);
		radiusall.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f = new JFrame("Default Radius");
				JButton b = new JButton("Submit");
				b.setBounds(50, 50, 100, 20);
				JTextField textfield = new JTextField();
				textfield.setBounds(50, 25, 100, 20);
				f.add(textfield);
				f.add(b);
				f.setResizable(false);
				f.setBounds(W / 3, H / 2, 0, 0);
				f.setSize(200, 120);
				f.setLayout(null);
				f.setVisible(true);

				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						for(int i=0; i<textfield.getText().length(); i++) {
							if(textfield.getText().charAt(i) < 48 || textfield.getText().charAt(i) > 57)
								return;
						}
						if (textfield.getText().length() > 1 || textfield.getText().length() < 1
								|| textfield.getText().length() == 1 && Integer.parseInt(textfield.getText()) <= 0)
							return;
						for (int i = 0; i < _Pacmans.size(); i++) {
							_Pacmans.get(i).setRadius(textfield.getText());
						}
						f.dispose();
					}
				});
			}
		});

		menu4.add(radiusp);
		radiusp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f = new JFrame("Set Radius");
				JButton b = new JButton("Submit");
				b.setBounds(70, 75, 100, 20);
				JLabel label1 = new JLabel();
				label1.setText("ID:");
				label1.setBounds(10, -15, 100, 100);
				JLabel label2 = new JLabel();
				label2.setText("RADIUS:");
				label2.setBounds(10, 10, 100, 100);
				JTextField textfield1 = new JTextField();
				textfield1.setBounds(70, 25, 100, 20);
				JTextField textfield2 = new JTextField();
				textfield2.setBounds(70, 50, 100, 20);
				f.add(textfield1);
				f.add(textfield2);
				f.add(b);
				f.add(label1);
				f.add(label2);
				f.setBounds(W / 3, H / 2, 0, 0);
				f.setSize(200, 150);
				f.setLayout(null);
				f.setVisible(true);
				f.setResizable(false);

				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						for(int i=0; i<textfield1.getText().length(); i++) {
							if(textfield1.getText().charAt(i) < 48 || textfield1.getText().charAt(i) > 57)
								return;
						}
						int id = Integer.parseInt(textfield1.getText());
						if (id >= _Pacmans.size()) {
							return;
						}
						for(int i=0; i<textfield2.getText().length(); i++) {
							if(textfield2.getText().charAt(i) < 48 || textfield2.getText().charAt(i) > 57)
								return;
						}
						if (textfield2.getText().length() > 1 || textfield2.getText().length() < 1
								|| textfield2.getText().length() == 1 && Integer.parseInt(textfield2.getText()) <= 0)
							return;
						for (int i = 0; i < _Pacmans.size(); i++) {
							if (id == Integer.parseInt(_Pacmans.get(i).getiD()))
								_Pacmans.get(i).setRadius(textfield2.getText());
						}
						f.dispose();
					}
				});
			}
		});

		menu5.add(opengame);
		opengame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cleargame.setEnabled(true);
				menu6.setEnabled(true);
				menu7.setEnabled(true);
				menu8.setEnabled(false);
				menu7.remove(online);
				menuBar.remove(menu1);
				menuBar.remove(menu2);
				menuBar.remove(menu3);
				menuBar.remove(menu4);
				repaint();
				JFileChooser chooser = new JFileChooser();
				int returnName = chooser.showOpenDialog(null);
				String path = "";
				String name = "";
				if (returnName == JFileChooser.APPROVE_OPTION) {
					File f = chooser.getSelectedFile();
					if (f != null && f.getName().endsWith(".csv")) {
						path = f.getAbsolutePath();
						name = f.getName().substring(f.getName().length()-5, f.getName().length()-4);
					}
				}
				_gameID = _Helper.get(Integer.valueOf(name));
				ArrayList<Play> temp = new ArrayList<Play>();
				Play convert = new Play(new File(path));
				temp = _Map.ConvertList2Pixel(convert.read());
				setLists(temp);
				repaint();
			}
		});

		menu5.add(cleargame);
		cleargame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isDemo = false;
				isPlayer = false;
				isTimer = false;
				menu6.setEnabled(false);
				menu8.setEnabled(false);
				cleargame.setEnabled(false);
				menu7.setEnabled(true);
				opengame.setEnabled(true);
				clearLists();
				repaint();
			}
		});
		cleargame.setEnabled(false);

		menu6.add(insertid);
		insertid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(_Ids.size() == 0) {
					JFrame f = new JFrame("Insert ID");
					JButton b = new JButton("Submit");
					b.setBounds(90, 100, 100, 20);
					JLabel label1 = new JLabel();
					label1.setText("First ID:");
					label1.setBounds(10, -15, 100, 100);
					JLabel label2 = new JLabel();
					label2.setText("Second ID:");
					label2.setBounds(10, 10, 100, 100);
					JLabel label3 = new JLabel();
					label3.setText("Third ID:");
					label3.setBounds(10, 35, 100, 100);
					JTextField textfield1 = new JTextField();
					textfield1.setBounds(90, 25, 100, 20);
					JTextField textfield2 = new JTextField();
					textfield2.setBounds(90, 50, 100, 20);
					JTextField textfield3 = new JTextField();
					textfield3.setBounds(90, 75, 100, 20);
					f.add(textfield1);
					f.add(textfield2);
					f.add(textfield3);
					f.add(b);
					f.add(label1);
					f.add(label2);
					f.add(label3);
					f.setBounds(_Map.getWidth() / 2, _Map.getHeight() / 2, 0, 0);
					f.setSize(220, 180);
					f.setLayout(null);
					f.setVisible(true);
					f.setResizable(false);

					b.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							if (textfield1.getText().length() != 0
									&& textfield1.getText().length() != 9){
								return;
							}
							if (textfield3.getText().length() != 0
									&& textfield3.getText().length() != 9) {
								return;
							}
							if (textfield3.getText().length() != 0
									&& textfield3.getText().length() != 9) {
								return;
							}
							for(int i=0; i<textfield1.getText().length(); i++) {
								if(textfield1.getText().charAt(i) < 48 || textfield1.getText().charAt(i) > 57)
									return;
							}
							for(int i=0; i<textfield2.getText().length(); i++) {
								if(textfield2.getText().charAt(i) < 48 || textfield2.getText().charAt(i) > 57)
									return;
							}
							for(int i=0; i<textfield3.getText().length(); i++) {
								if(textfield3.getText().charAt(i) < 48 || textfield3.getText().charAt(i) > 57)
									return;
							}
							if (textfield1.getText().length() == 0){
								textfield1.setText("0");
							}
							if (textfield2.getText().length() == 0){
								textfield2.setText("0");
							}
							if (textfield3.getText().length() == 0){
								textfield3.setText("0");
							}
							_Ids.add(Long.parseLong(textfield1.getText()));
							_Ids.add(Long.parseLong(textfield2.getText()));
							_Ids.add(Long.parseLong(textfield3.getText()));
							f.dispose();
							player.setEnabled(true);
							insertid.setEnabled(false);
						}
					});
				}
				else {
					player.setEnabled(true);
					insertid.setEnabled(false);
				}
			}
		});

		menu6.add(player);
		player.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isPlayer = true;
				paintElement();
				menu8.setEnabled(true);
				menu6.setEnabled(false);
			}
		});
		player.setEnabled(false);

		menu8.add(easybar);
		easybar.setEnabled(false);

		menu8.add(play);
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isDemo = true;
				menu5.setEnabled(false);
				menu8.setEnabled(false);
				menu7.setEnabled(false);
				opengame.setEnabled(false);
				calculatePath();
				pList = _Path.Create(getList(), getPList());
				removeFruitIcon();
				Thread t1 = new Thread(new Runnable() {
					public void run() {
						pList = _Path.Print(pList);
						for (int i = 0; i < _Pacmans.size(); i++) {
							for (int j = 0; j < pList.size(); j++) {
								if (_Pacmans.get(i).getiD().equals(pList.get(j).getList().get(0).getiD())) {
									Data pac = new Data(_Pacmans.get(i));
									Data fru = new Data(pList.get(i).getList().get(0));
									Point3D Pac = new Point3D(pac.getX(), pac.getY());
									Point3D Fru = new Point3D(fru.getX(), fru.getY());
									double angel = rotatePac(Pac, Fru);
									_Pacmans.get(i).setPoint(pList.get(j).getList().get(0).getPoint());
									_Pacmans.get(i).setAngel(angel);
								}
							}
						}
						if(ghostStop == 0 && _Ghosts.size() > 0) {
							ArrayList<Ghost> temp = new ArrayList<Ghost>(_Path.chasePlayer(_Ghosts, _Player));
							for(int i=0; i<temp.size(); i++) {
								_Ghosts.get(i).setPoint(temp.get(i).getPoint());
								Data ghost = new Data(_Ghosts.get(i));
								Data player = new Data(_Player);
								if (player.getiX() == ghost.getiX() && 
										player.getiY() == ghost.getiY() && ghostStop == 0) {
									ghostStop = 3;
									_Player.ghostKill();
									_Player.setScore(-20);
								}
							}
						}
						else if(ghostStop > 0)
							ghostStop--;
						synchronized(threadobj) {
							while(isClicked == false) {
								try {
									Thread.sleep(1);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							isClicked = false;
						}
						removeFruitIcon();
						isPlayerAteFruit();
						isPlayerAtePacman();
						repaint();
						if (_Fruits.size() > 0 && _Player.getTime() < MaxTime)
							threadobj.run();
						else {
							double score = Double.parseDouble(new DecimalFormat("##.#").format(MaxTime - _Player.getTime()));
							_Player.setScore(score);
							popup.show(createPopup(), 0 , 0);
							sendReport();
							menu5.setEnabled(true);
						}
					}
				});
				threadobj = new Thread(t1);
				threadobj.start();
			}
		});

		menu8.add(autoplay);
		autoplay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isClicked = true;
				isDemo = true;
				menu5.setEnabled(false);
				menu8.setEnabled(false);
				menu7.setEnabled(false);
				opengame.setEnabled(false);
				calculatePath();
				pList = _Path.Create(getList(), getPList());
				removeFruitIcon();
				Thread t1 = new Thread(new Runnable() {
					public void run() {
						pList = _Path.Print(pList);
						for (int i = 0; i < _Pacmans.size(); i++) {
							for (int j = 0; j < pList.size(); j++) {
								if (_Pacmans.get(i).getiD().equals(pList.get(j).getList().get(0).getiD())) {
									Data pac = new Data(_Pacmans.get(i));
									Data fru = new Data(pList.get(i).getList().get(0));
									Point3D Pac = new Point3D(pac.getX(), pac.getY());
									Point3D Fru = new Point3D(fru.getX(), fru.getY());
									double angel = rotatePac(Pac, Fru);
									_Pacmans.get(i).setPoint(pList.get(j).getList().get(0).getPoint());
									_Pacmans.get(i).setAngel(angel);
								}
							}
						}
						if(ghostStop == 0 && _Ghosts.size() > 0) {
							ArrayList<Ghost> temp = new ArrayList<Ghost>(_Path.chasePlayer(_Ghosts, _Player));
							for(int i=0; i<temp.size(); i++) {
								_Ghosts.get(i).setPoint(temp.get(i).getPoint());
								Data ghost = new Data(_Ghosts.get(i));
								Data player = new Data(_Player);
								if (player.getiX() == ghost.getiX() && 
										player.getiY() == ghost.getiY() && ghostStop == 0) {
									ghostStop = 3;
									_Player.ghostKill();
									_Player.setScore(-20);
								}
							}
						}
						else if(ghostStop > 0)
							ghostStop--;
						if(_Pacmans.size() > 0) {
							_Path.movePlayer2Pacman(_Pacmans, _Player);
						}
						else if(_Fruits.size() > 0) {
							_Path.movePlayer2Fruit(_Fruits, _Player);
						}
						removeFruitIcon();
						isPlayerAteFruit();
						isPlayerAtePacman();
						repaint();
						_Player.Time();
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (_Fruits.size() > 0 && _Player.getTime() < MaxTime)
							threadobj.run();
						else {
							double score = Double.parseDouble(new DecimalFormat("##.#").format(MaxTime - _Player.getTime()));
							_Player.setScore(score);
							popup.show(createPopup(), 0 , 0);
							sendReport();
							menu5.setEnabled(true);
						}
					}
				});
				threadobj = new Thread(t1);
				threadobj.start();
			}
		});

		menu8.add(hardbar);
		hardbar.setEnabled(false);

		menu8.add(playhard);
		playhard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isDemo = true;
				menu5.setEnabled(false);
				menu8.setEnabled(false);
				menu7.setEnabled(false);
				opengame.setEnabled(false);
				calculatePath();
				pList = _Path.Create(getList(), getPList());
				removeFruitIcon();
				Thread t1 = new Thread(new Runnable() {
					public void run() {
						pList = _Path.Print(pList);
						for (int i = 0; i < _Pacmans.size(); i++) {
							for (int j = 0; j < pList.size(); j++) {
								if (_Pacmans.get(i).getiD().equals(pList.get(j).getList().get(0).getiD())) {
									Data pac = new Data(_Pacmans.get(i));
									Data fru = new Data(pList.get(i).getList().get(0));
									Point3D Pac = new Point3D(pac.getX(), pac.getY());
									Point3D Fru = new Point3D(fru.getX(), fru.getY());
									double angel = rotatePac(Pac, Fru);
									_Pacmans.get(i).setPoint(pList.get(j).getList().get(0).getPoint());
									_Pacmans.get(i).setAngel(angel);
								}
							}
						}
						synchronized(threadobj) {
							while(isClicked == false) {
								try {
									Thread.sleep(1);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							isClicked = false;
						}
						if(ghostStop == 0 && _Ghosts.size() > 0) {
							ArrayList<Ghost> temp = new ArrayList<Ghost>(_Path.chasePlayer(_Ghosts, _Player));
							for(int i=0; i<temp.size(); i++) {
								_Ghosts.get(i).setPoint(temp.get(i).getPoint());
								Data ghost = new Data(_Ghosts.get(i));
								Data player = new Data(_Player);
								if (player.getiX() == ghost.getiX() && 
										player.getiY() == ghost.getiY() && ghostStop == 0) {
									ghostStop = 3;
									_Player.ghostKill();
									_Player.setScore(-20);
								}
							}
						}
						else if(ghostStop > 0)
							ghostStop--;
						removeFruitIcon();
						isPlayerAteFruit();
						isPlayerAtePacman();
						repaint();
						_Player.Time();
						if (_Fruits.size() > 0 && _Player.getTime() < MaxTime)
							threadobj.run();
						else {
							double score = Double.parseDouble(new DecimalFormat("##.#").format(MaxTime - _Player.getTime()));
							_Player.setScore(score);
							popup.show(createPopup(), 0 , 0);
							sendReport();
							menu5.setEnabled(true);
						}
					}
				});
				threadobj = new Thread(t1);
				threadobj.start();
			}
		});

		menu8.add(autoplayhard);
		autoplayhard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isDemo = true;
				menu5.setEnabled(false);
				menu8.setEnabled(false);
				menu7.setEnabled(false);
				opengame.setEnabled(false);
				calculatePath();
				pList = _Path.Create(getList(), getPList());
				removeFruitIcon();
				Thread t1 = new Thread(new Runnable() {
					public void run() {
						pList = _Path.Print(pList);
						for (int i = 0; i < _Pacmans.size(); i++) {
							for (int j = 0; j < pList.size(); j++) {
								if (_Pacmans.get(i).getiD().equals(pList.get(j).getList().get(0).getiD())) {
									Data pac = new Data(_Pacmans.get(i));
									Data fru = new Data(pList.get(i).getList().get(0));
									Point3D Pac = new Point3D(pac.getX(), pac.getY());
									Point3D Fru = new Point3D(fru.getX(), fru.getY());
									double angel = rotatePac(Pac, Fru);
									_Pacmans.get(i).setPoint(pList.get(j).getList().get(0).getPoint());
									_Pacmans.get(i).setAngel(angel);
								}
							}
						}
						if(_Pacmans.size() > 0) {
							_Path.movePlayer2Pacman(_Pacmans, _Player);
						}
						else if(_Fruits.size() > 0) {
							_Path.movePlayer2Fruit(_Fruits, _Player);
						}
						if(ghostStop == 0 && _Ghosts.size() > 0) {
							ArrayList<Ghost> temp = new ArrayList<Ghost>(_Path.chasePlayer(_Ghosts, _Player));
							for(int i=0; i<temp.size(); i++) {
								_Ghosts.get(i).setPoint(temp.get(i).getPoint());
								Data ghost = new Data(_Ghosts.get(i));
								Data player = new Data(_Player);
								if (player.getiX() == ghost.getiX() && 
										player.getiY() == ghost.getiY() && ghostStop == 0) {
									ghostStop = 3;
									_Player.ghostKill();
									_Player.setScore(-20);
								}
							}
						}
						else if(ghostStop > 0)
							ghostStop--;
						removeFruitIcon();
						isPlayerAteFruit();
						isPlayerAtePacman();
						repaint();
						_Player.Time();
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (_Fruits.size() > 0 && _Player.getTime() < MaxTime)
							threadobj.run();
						else {
							double score = Double.parseDouble(new DecimalFormat("##.#").format(MaxTime - _Player.getTime()));
							_Player.setScore(score);
							popup.show(createPopup(), 0 , 0);
							sendReport();
							menu5.setEnabled(true);
						}
					}
				});
				threadobj = new Thread(t1);
				threadobj.start();
			}
		});

		menu7.add(online);
		online.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearLists();
				repaint();
				isPacman = false;
				isFruit = false;
				isOnline = true;
				menuBar.remove(menu1);
				menuBar.remove(menu2);
				menuBar.remove(menu3);
				menuBar.remove(menu4);
				menuBar.add(menu5);
				menuBar.add(menu6);
				menuBar.add(menu8);
				menu5.setEnabled(true);
				menu6.setEnabled(false);
				menu8.setEnabled(false);
				menu7.remove(online);
				menu7.add(offline);
			}
		});

		menu7.add(offline);
		offline.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearLists();
				repaint();
				isPlayer = false;
				isOnline = false;
				menuBar.remove(menu5);
				menuBar.remove(menu6);
				menuBar.remove(menu8);
				menuBar.add(menu1);
				menuBar.add(menu2);
				menuBar.add(menu3);
				menuBar.add(menu4);
				menu1.setEnabled(true);
				menu2.setEnabled(true);
				menu3.setEnabled(false);
				menu4.setEnabled(false);
				menu7.add(online);
				menu7.remove(offline);
			}
		});

		menu2.setEnabled(false);
		menu3.setEnabled(false);
		menu4.setEnabled(false);
		menu5.setEnabled(false);
		menu6.setEnabled(false);
		menu7.setEnabled(false);

		setMB(menuBar);
	}

	/**
	 * This function saves the lists after we read the data from the CSV file.
	 * @param temp - ArrayList of Play contains all the data from the CSV file.
	 */

	public void setLists(ArrayList<Play> temp) {
		Play convert = new Play(temp);
		setPList(convert.getPList());
		setFList(convert.getFList());
		setGList(convert.getGList());
		setBList(convert.getBList());
		setList(saveList());
	}

	/**
	 * This function rotates the pacmans mouth depends on the next fruit location.
	 * @param pac - Current Pacman location in Point3D
	 * @param fru - Current Fruit location in Point3D
	 * @return The angel we need to rotate the pacman.
	 */

	public double rotatePac(Point3D pac, Point3D fru) {
		double res = 0;
		if (pac.x() < fru.x()) {
			res = 0;
		} else if (pac.x() > fru.x()) {
			res = 180;
		}
		return res;
	}

	/**
	 * Clears all the privates variables
	 */
	public void clearLists() {
		_Fruits = new ArrayList<Fruit>();
		_Pacmans = new ArrayList<Pacman>();
		_List = new ArrayList<Game>();
		_Icons = new ArrayList<Image>();
		_GhostsImg  = new ArrayList<Image>();
		_Ghosts = new ArrayList<Ghost>();
		_Blocks = new ArrayList<Block>();
		_Player = new Player();
		ghostStop = 0;
		i = 0;
		j = 0;
		p = 0;
		x = 0;
		y = 0;
		//		k = 0;
		//		b = 0;
	}

	public JFrame createPopup() {
		JFrame f = new JFrame("Score");
		JLabel label1 = new JLabel();
		label1.setText("Your Score: " + _Player.getScore());
		label1.setBounds(60, -15, 150, 100);
		JLabel label2 = new JLabel();
		label2.setText("Pacmans eaten: " + _Player.getPacmansEaten());
		label2.setBounds(60, 10, 150, 100);
		JLabel label3 = new JLabel();
		label3.setText("Fruits eaten: " + _Player.getFruitsEaten());
		label3.setBounds(60, 35, 150, 100);
		JLabel label4 = new JLabel();
		label4.setText("Ghosts killed: " + _Player.getGhostKill());
		label4.setBounds(60, 60, 150, 100);
		JLabel label5 = new JLabel();
		label5.setText("_Blocks Passed: " + _Player.getWrongLocation());
		label5.setBounds(60, 85, 150, 100);
		f.add(label1);
		f.add(label2);
		f.add(label3);
		f.add(label4);
		f.add(label5);
		f.setBounds(W / 3, H / 2, 250, 200);
		f.setLayout(null);
		f.setVisible(true);
		f.setResizable(false);
		return f;
	}

	private void sendReport() { String jdbcUrl = "jdbc:mysql://ariel-oop.xyz:3306/oop?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";
	String jdbcUser = "boaz";
	String jdbcPassword = "9125";
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = 
				DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
		Statement statement = connection.createStatement();

		String id = _Ids.get(0) + "," + _Ids.get(1) + "," + _Ids.get(2);
		String insertSQL = "INSERT INTO logs (FirstID, SecondID, ThirdID, LogTime,Point, SomeDouble)\r\nVALUES (" + 
				id + ", CURRENT_TIMESTAMP," + _Player.getScore() + "," + _gameID + ");";
		statement.executeUpdate(insertSQL);
		statement.close();
		connection.close();
	}
	catch (SQLException sqle) {
		System.out.println("SQLException: " + sqle.getMessage());
		System.out.println("Vendor Error: " + sqle.getErrorCode());
	}
	catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	}

	/**
	 * This function prints the current game into a csv file.
	 * 
	 * @param csv  File - A file you want to save the current game.
	 * @param list - The current Game you created and you want to save.
	 */

	public void print(File csv, ArrayList<Game> list) {
		PrintWriter print = null;
		try {
			print = new PrintWriter(csv);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		StringBuilder Builder = new StringBuilder();
		Builder.append("Type,id,Lat,Lon,Alt,Speed/Weight,Radius," + _Pacmans.size() + "," + _Fruits.size() + "\n");

		for (int i = 0; i < list.size(); i++) {
			Game temp = list.get(i);
			String[] Data = temp.getPoint().split(",");
			Builder.append(temp.getType().charAt(0) + "," + temp.getiD() + "," + Data[1] + "," + Data[0] + "," + Data[2]
					+ "," + temp.getSpeed() + "," + temp.getRadius() + "," + "," + "\n");
		}
		print.write(Builder.toString());
		print.close();
	}

	/**
	 * This function Calculates the Path using the ShortestPathAlgo, Converts the
	 * points to GPS and updates the last Pacmans locations.
	 * 
	 * @return ArrayList of Game converted from Pixel to GPS points.
	 */

	public ArrayList<Game> convertGPS() {
		calculatePath();
		ArrayList<Game> res = new ArrayList<Game>();
		Game temp = new Game();
		for (int i = 0; i < _Pacmans.size(); i++) {
			for (int j = 0; j < getList().size(); j++) {
				if (_Pacmans.get(i).getiD().equals(getList().get(j).getiD())
						&& _Pacmans.get(i).getType().equals(getList().get(j).getType())) {
					temp = getList().get(j);
					temp.setPoint(getList().get(j + 1).getPoint());
				}
			}
			res.add(temp);
		}
		ShortestPathAlgo test = new ShortestPathAlgo(res);
		setPList(test.getPList());
		return _Map.ConvertPoints2GPS(getList());
	}

	/**
	 * Changes the Fruits Icon to (/) When the Pacmans reaches the fruits.
	 */

	public void changeFruitIcon() {
		for (int i = 0; i < _Pacmans.size(); i++) {
			for (int j = 0; j < _Fruits.size(); j++) {
				Data pacData = new Data(_Pacmans.get(i));
				Data fruData = new Data(_Fruits.get(j));
				int pX = pacData.getiX() * this.getWidth() / W;
				int pY = pacData.getiY() * this.getHeight() / H;

				int fX = fruData.getiX() * this.getWidth() / W;
				int fY = fruData.getiY() * this.getHeight() / H;
				if (pX == fX && pY == fY) {
					_Fruits.get(j).setPicture("Done");
				}
			}
		}
	}

	/**
	 * Removes the fruits eaten from the ArrayList.
	 */

	public void removeFruitIcon() {
		for (int i = 0; i < _Pacmans.size(); i++) {
			for (int j = 0; j < _Fruits.size(); j++) {
				Data pacData = new Data(_Pacmans.get(i));
				Data fruData = new Data(_Fruits.get(j));
				int pX = pacData.getiX() * this.getWidth() / W;
				int pY = pacData.getiY() * this.getHeight() / H;

				int fX = fruData.getiX() * this.getWidth() / W;
				int fY = fruData.getiY() * this.getHeight() / H;
				if (pX == fX && pY == fY) {
					_Fruits.remove(j);
					_Icons.remove(j);
				}
			}
		}
	}

	/**
	 * Removes the Fruits that the Player ate, and calculates the new path for the Pacmans (if they are any left)
	 */
	public void isPlayerAteFruit() {
		for (int j = 0; j < _Fruits.size(); j++) {
			Data playerData = new Data(_Player);
			Data fruData = new Data(_Fruits.get(j));
			int pX = playerData.getiX() * this.getWidth() / W;
			int pY = playerData.getiY() * this.getHeight() / H;

			int fX = fruData.getiX() * this.getWidth() / W;
			int fY = fruData.getiY() * this.getHeight() / H;

			double distance = Math.sqrt(Math.pow(fX-pX, 2) + Math.pow(fY-pY, 2)) 
					- Double.parseDouble(_Player.getRadius());
			if (distance <= Double.parseDouble(_Player.getSpeed())) {
				_Player.setScore((int)Double.parseDouble(_Fruits.get(j).getWeight()));
				_Player.FruitsEaten();
				_Fruits.remove(j);
				_Icons.remove(j);
				if(_Pacmans.size() > 0) {
					_List = new ArrayList<Game>();
					pList = new ArrayList<Path>();
					setList(saveList());
					calculatePath();
					pList = _Path.Create(getList(), getPList());
				}
			}
		}
	}

	/**
	 * Removes the Pacmans that the Player ate, and calculates the new path for the Pacmans (if they are any left)
	 */
	public void isPlayerAtePacman() {
		for (int i = 0; i < _Pacmans.size(); i++) {
			Data playerData = new Data(_Player);
			Data pacData = new Data(_Pacmans.get(i));
			int pX = playerData.getiX() * this.getWidth() / W;
			int pY = playerData.getiY() * this.getHeight() / H;

			int fX = pacData.getiX() * this.getWidth() / W;
			int fY = pacData.getiY() * this.getHeight() / H;

			double distance = Math.sqrt(Math.pow(fX-pX, 2) + Math.pow(fY-pY, 2)) 
					- Double.parseDouble(_Player.getRadius());
			if (distance <= Double.parseDouble(_Player.getSpeed())) {
				_Player.setScore((int)Double.parseDouble(_Pacmans.get(i).getSpeed()));
				_Player.PacmansEaten();
				_Pacmans.remove(i);
				if(_Pacmans.size() > 0) {
					_List = new ArrayList<Game>();
					pList = new ArrayList<Path>();
					setList(saveList());
					calculatePath();
					pList = _Path.Create(getList(), getPList());
				}
			}
		}
	}
	/**
	 * This function takes a CSV file points, Converts them to Pixels and updates
	 * each list. (Fruits + Pacmans + Game)
	 * 
	 * @param spa - ShortestPathAlgo
	 */

	public void setListsSPA(ShortestPathAlgo spa) {
		setPList(spa.getPList());
		setFList(spa.getFList());

		setList(saveList());
		setList(_Map.ConvertPoints2Pixel(getList()));

		ShortestPathAlgo test = new ShortestPathAlgo(getList());
		setPList(test.getPList());
		setFList(test.getFList());
	}

	/**
	 * Calculates the Path for all the Pacmans and updates the current ArrayList of
	 * Game.
	 */
	public void calculatePath() {
		ShortestPathAlgo Test = new ShortestPathAlgo(getList());
		ArrayList<Game> it = Test.calculatePath();
		setList(it);
	}

	/**
	 * Creates the Lines (DrawLine) that represents each Pacman's path.
	 * 
	 * @param g - Graphics.
	 */

	public void paths(Graphics g) {
		calculatePath();
		ArrayList<Game> it = getList();
		g.setColor(Color.WHITE);
		for (int i = 0; i < it.size(); i += 2) {
			Data Data1 = new Data(it.get(i));
			Data Data2 = new Data(it.get(i + 1));
			int x1 = Data1.getiX() * this.getWidth() / W;
			int y1 = Data1.getiY() * this.getHeight() / H;
			int x2 = Data2.getiX() * this.getWidth() / W;
			int y2 = Data2.getiY() * this.getHeight() / H;
			Image img = Toolkit.getDefaultToolkit().getImage("newdata/Done.png");
			g.drawImage(img, x2 - 16, y2 - 16, null);
			g.drawLine(x1, y1, x2, y2);
		}
	}

	/**
	 * This method paints our Pacmans/Fruits/Ghosts/_Blocks in our GUI. instructions - Boolean that
	 * represents when you first press New and want to create a new game gui and
	 * make the instructions disappear. isSaved - If you pressed the RUN button, it
	 * will print the Lines even after you rescale the map. isDemo - When you
	 * simulate the Pacmans in real time, updates the Fruits eaten image.
	 */

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (instructions == true) {
			g.drawImage(_Map.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
		} 
		else {
			Image start = Toolkit.getDefaultToolkit().getImage("newdata/Instructions.jpeg");
			g.drawImage(start, 0, 0, this.getWidth(), this.getHeight(), this);
		}

		if(isOnline == true && isTimer == true) {
			g.setColor(Color.BLACK);
			g.fillRect(5, 0, 205, 25);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Monospaced", Font.BOLD, 20));
			g.drawString("Time Left: " + Double.parseDouble(new DecimalFormat("##.#").format(MaxTime - _Player.getTime())), 10, 20);
		}

		Image Pacman = Toolkit.getDefaultToolkit().getImage("newdata/Pacman.png");
		Image img = Toolkit.getDefaultToolkit().getImage("newdata/Done.png");

		if (_Icons.size() < _Fruits.size()) {
			createFruitsIconList(_Fruits.size());
		}

		if (_GhostsImg.size() < _Ghosts.size()) {
			createGhostsIconList(_Ghosts.size());
		}

		setMat(_Map.image2Matrix(_Map.matImg(this.getHeight(), this.getWidth(), _Blocks)));
		
		newW = 32*this.getWidth()/W;
		newH = 32*this.getHeight()/H;

		Data Player = new Data(_Player);
		if(Player.getiX() != 0 && Player.getiY() != 0) {
			int x1 = Player.getiX() * this.getWidth() / W;
			int y1 = Player.getiY() * this.getHeight() / H;
			g.drawImage(_Player.getImage(), x1-newW/2, y1-newH/2, newW, newH, null);
			g.setFont(new Font("Monospaced", Font.BOLD, 14));
			g.setColor(Color.WHITE);
			g.drawString("(" + String.valueOf(x1) + "," + String.valueOf(y1) + ")", x1+newW/3, y1+newH/2);
			if(isDemo == true) {
				if(_Mat[y1][x1] == false) {
					_Player.setScore(-1);
					_Player.wrongLocation();
				}
			}
		}

		for (int i = 0; i < _Fruits.size(); i++) {
			Data fruData = new Data(_Fruits.get(i));
			int x1 = fruData.getiX() * this.getWidth() / W;
			int y1 = fruData.getiY() * this.getHeight() / H;
			g.drawImage(_Icons.get(i), x1-newW/2, y1-newH/2, newW, newH, this);
			g.setFont(new Font("Monospaced", Font.BOLD, 14));
			g.setColor(Color.WHITE);
			g.drawString("(" + String.valueOf(x1) + "," + String.valueOf(y1) + ")", x1+newW/3, y1+newH/2);
		}
		for (int i = 0; i < _Ghosts.size(); i++) {
			Data ghoData = new Data(_Ghosts.get(i));
			int x1 = ghoData.getiX() * this.getWidth() / W;
			int y1 = ghoData.getiY() * this.getHeight() / H;
			g.drawImage(_GhostsImg.get(i), x1-newW/2, y1-newH/2, newW, newH, this);
			g.setFont(new Font("Monospaced", Font.BOLD, 14));
			g.setColor(Color.WHITE);
			g.drawString("(" + String.valueOf(x1) + "," + String.valueOf(y1) + ")", x1+newW/3, y1+newH/2);
		}
		for (int i = 0; i < _Blocks.size(); i++) {
			Data bloData = new Data(_Blocks.get(i));
			int x1 = bloData.getiX() * this.getWidth() / W;
			int y1 = bloData.getiY() * this.getHeight() / H;
			int blockWidth = _Blocks.get(i).getWidth() * this.getWidth() / W;
			int blockHeight = _Blocks.get(i).getHeight() * this.getHeight() / H;
			g.setColor(Color.BLACK);
			g.fillRect(x1, y1, blockWidth, blockHeight);
		}
		if (isDemo == false) {
			for (int i = 0; i < _Pacmans.size(); i++) {
				Data pacData = new Data(_Pacmans.get(i));
				int x1 = pacData.getiX() * this.getWidth() / W;
				int y1 = pacData.getiY() * this.getHeight() / H;
				g.drawImage(Pacman, x1-newW/2, y1-newH/2, newW, newH, this);
				g.setFont(new Font("Monospaced", Font.BOLD, 14));
				g.setColor(Color.WHITE);
				g.drawString("(" + String.valueOf(x1) + "," + String.valueOf(y1) + ")", x1+newW/3, y1+newH/2);
				g.setFont(new Font("Monospaced", Font.BOLD, 18));
				g.setColor(Color.ORANGE);
				g.drawString("ID: " + _Pacmans.get(i).getiD(), x1-newW/2, y1-newH/2);
			}
		}

		if (isSaved == true) {
			for (int i = 0; i < _List.size(); i += 2) {
				g.setColor(Color.WHITE);
				Data Data1 = new Data(_List.get(i));
				Data Data2 = new Data(_List.get(i + 1));
				int x1 = Data1.getiX() * this.getWidth() / W;
				int y1 = Data1.getiY() * this.getHeight() / H;
				int x2 = Data2.getiX() * this.getWidth() / W;
				int y2 = Data2.getiY() * this.getHeight() / H;
				g.drawImage(img, x2-newW/2, y2-newH/2, newW, newH, null);
				g.drawLine(x1, y1, x2, y2);
			}
			for (int i = 0; i < _Pacmans.size(); i++) {
				Data pacData = new Data(_Pacmans.get(i));
				int x1 = pacData.getiX() * this.getWidth() / W;
				int y1 = pacData.getiY() * this.getHeight() / H;
				g.drawImage(Pacman, x1-newW/2, y1-newH/2, newW, newH, this);
				g.setFont(new Font("Monospaced", Font.BOLD, 14));
				g.setColor(Color.WHITE);
				g.drawString("(" + String.valueOf(x1) + "," + String.valueOf(y1) + ")", x1+newW/3, y1+newH/2);
				g.setFont(new Font("Monospaced", Font.BOLD, 18));
				g.setColor(Color.ORANGE);
				g.drawString("ID: " + _Pacmans.get(i).getiD(), x1-newW/2, y1-newH/2);
			}
		}
		if (isDemo == true) {
			for (int i = 0; i < _Pacmans.size(); i++) {
				Data pacData = new Data(_Pacmans.get(i));
				int x1 = pacData.getiX() * this.getWidth() / W;
				int y1 = pacData.getiY() * this.getHeight() / H;
				// Draw our image like normal
				g.drawImage(pacmanIcon(_Pacmans.get(i).getAngel()), x1-newW/2, y1-newH/2, newW, newH, this);
				g.setFont(new Font("Monospaced", Font.BOLD, 14));
				g.setColor(Color.WHITE);
				g.drawString("(" + String.valueOf(x1) + "," + String.valueOf(y1) + ")", x1+newW/3, y1+newH/2);
				g.setFont(new Font("Monospaced", Font.BOLD, 18));
				g.setColor(Color.ORANGE);
				g.drawString("ID: " + _Pacmans.get(i).getiD(), x1-newW/2, y1-newH/2);
			}
			p++;
		}
	}

	/**
	 * This function generates a random icon for each fruit created.
	 * 
	 * @return Fruit Image
	 */

	public Image randomFruitsIcon() {
		Image Apple = Toolkit.getDefaultToolkit().getImage("newdata/Apple.png");
		Image Fruit = Toolkit.getDefaultToolkit().getImage("newdata/Fruit.png");
		Image[] Fruits = {Fruit, Apple};
		Random random = new Random();
		int Select = random.nextInt(Fruits.length);
		Image newImage = Fruits[Select];
		_Icons.add(newImage);
		return newImage;
	}

	/**
	 * This function generates a random icon for each ghost created.
	 * 
	 * @return Ghost Image
	 */

	public Image randomGhostsIcon() {
		Image redGhost = Toolkit.getDefaultToolkit().getImage("ourdata/RGhost.png");
		Image orangeGhost = Toolkit.getDefaultToolkit().getImage("ourdata/OGhost.png");
		Image blueGhost = Toolkit.getDefaultToolkit().getImage("ourdata/BGhost.png");
		Image pinkGhost = Toolkit.getDefaultToolkit().getImage("ourdata/PGhost.png");
		Image[] Ghosts = {redGhost, orangeGhost, blueGhost, pinkGhost};
		Random random = new Random();
		int Select = random.nextInt(Ghosts.length);
		Image newImage = Ghosts[Select];
		_GhostsImg.add(newImage);
		return newImage;
	}

	/**
	 * This function generates list of random fruits icons when you open a game
	 * 
	 * @param size of the ArrayList of fruits.
	 */

	public void createFruitsIconList(int size) {
		for (int i = 0; i < size; i++) {
			randomFruitsIcon();
		}
	}

	/**
	 * This function generates list of random ghosts icons when you open a game
	 * 
	 * @param size of the ArrayList of ghosts.
	 */

	public void createGhostsIconList(int size) {
		for (int i = 0; i < size; i++) {
			randomGhostsIcon();
		}
	}

	/**
	 * This function switches between 2 Pacmans icons, 1 with open mouth, the second
	 * with closed one and their mouth direction.
	 * @param angel - Angel in degrees.
	 * @return Pacman image
	 */

	public Image pacmanIcon(double angel) {
		Image test = null;
		Image Pacman = Toolkit.getDefaultToolkit().getImage("newdata/Pacman.png");
		Image Pacman1 = Toolkit.getDefaultToolkit().getImage("newdata/Pacman1.png");
		Image Pacman180 = Toolkit.getDefaultToolkit().getImage("newdata/Pacman180.png");
		Image Pacman1_180 = Toolkit.getDefaultToolkit().getImage("newdata/Pacman1-180.png");
		if (p % 2 == 1 && angel == 0) {
			test = Pacman1;
		} else if (p % 2 == 0 && angel == 0) {
			test = Pacman;
		} else if (p % 2 == 1 && angel == 180) {
			test = Pacman1_180;
		} else if (p % 2 == 0 && angel == 180) {
			test = Pacman180;
		}
		return test;
	}

	/**
	 * Paints the Pacman/Fruit/Ghost/Block when you click on the GUI (Depends on your choice).
	 * Adds each Pacman/Fruit/Ghost/Block that was created into ArrayList in order to repaint
	 * then all.
	 */
	protected void paintElement() {
		Graphics g = getGraphics();
		// The method getGraphics is called to obtain a Graphics object
		Image Pacman = Toolkit.getDefaultToolkit().getImage("newdata/Pacman.png");

		//Image Ghost = randomGhostsIcon();
		Image Fruit = randomFruitsIcon();

		newW = 32*this.getWidth()/W;
		newH = 32*this.getHeight()/H;

		x = (int) (x * W / this.getWidth());
		y = (int) (y * H / this.getHeight());

		int x1 = newW/2 * this.getWidth() / W;
		int y1 = newH/2 * this.getHeight() / H;

		if (isPacman == true && g.drawImage(Pacman, x-x1, y-y1 , newW, newH, this) == true) {
			g.setFont(new Font("Monospaced", Font.BOLD, 14));
			g.setColor(Color.WHITE);
			g.drawString("(" + Integer.toString(x) + "," + Integer.toString(y) + ")", x+newW/3, y+newH/2);
			g.setFont(new Font("Monospaced", Font.BOLD, 18));
			g.setColor(Color.ORANGE);
			g.drawString("ID: " + i, x-newW/2, y-newH/2);
			_Pacmans.add(new Pacman("Pacman", x + "," + y + "," + 0, "1", "1", String.valueOf(i)));
			i++;
		}
		if (isFruit == true && g.drawImage(Fruit, x-x1 ,y-y1 ,newW ,newH ,this) == true) {
			g.setFont(new Font("Monospaced", Font.BOLD, 14));
			g.setColor(Color.WHITE);
			g.drawString("(" + Integer.toString(x) + "," + Integer.toString(y) + ")", x+newW/3, y+newH/2);
			_Fruits.add(new Fruit("Fruit", x + "," + y + "," + 0, "1", String.valueOf(j)));
			j++;
		}
		if (isPlayer == true && g.drawImage(_Player.getImage(), x-x1, y-y1, newW, newH,this) == true) {
			g.setFont(new Font("Monospaced", Font.BOLD, 14));
			g.setColor(Color.WHITE);
			g.drawString("(" + Integer.toString(x) + "," + Integer.toString(y) + ")", x+newW/3, y+newH/2);
			_Player.setPoint(x + "," + y + "," + 0);
		}
		//		if (isGhost == true && g.drawImage(Ghost, x - 16, y - 16, this) == true) {
		//			g.setFont(new Font("Monospaced", Font.BOLD, 14));
		//			g.setColor(Color.WHITE);
		//			g.drawString("(" + Integer.toString(x) + "," + Integer.toString(y) + ")", x, y);
		//			_Ghosts.add(new Ghost("Ghost", x + "," + y + "," + 0,"1","1", String.valueOf(k)));
		//			k++;
		//		}
		//		if(isBlock == true) {
		//			Bx1 = Bx1 * W / this.getWidth();
		//			By1 = By1 * H / this.getHeight();
		//
		//			Bx2 = Bx2 * W / this.getWidth();
		//			By2 = By2 * H / this.getHeight();
		//
		//			g.setColor(Color.BLACK);
		//			if(Bx2>Bx1 && By2>By1) {
		//				g.fillRect(Bx1, By1, Bx2-Bx1, By2-By1);
		//				int blockWidth = Bx2-Bx1;
		//				int blockHeight = By2-By1;
		//				_Blocks.add(new Block("Block", Bx1+","+By1+","+0, blockWidth, blockHeight, String.valueOf(b)));
		//			}
		//			else if(Bx2>Bx1 && By1>By2) {
		//				g.fillRect(Bx1, By2, Bx2-Bx1, By1-By2);
		//				int blockWidth = Bx2-Bx1;
		//				int blockHeight = By1-By2;
		//				_Blocks.add(new Block("Block", Bx1+","+By2+","+0, blockWidth, blockHeight, String.valueOf(b)));
		//			}
		//			else if(Bx2<Bx1 && By2>By1) {
		//				g.fillRect(Bx2, By1, Bx1-Bx2, By2-By1);
		//				int blockWidth = Bx1-Bx2;
		//				int blockHeight = By2-By1;
		//				_Blocks.add(new Block("Block", Bx2+","+By1+","+0, blockWidth, blockHeight, String.valueOf(b)));
		//			}
		//			else if(Bx2<Bx1 && By1>By2) {
		//				g.fillRect(Bx2, By2, Bx1-Bx2, By1-By2);
		//				int blockWidth = Bx1-Bx2;
		//				int blockHeight = By1-By2;
		//				_Blocks.add(new Block("Block", Bx2+","+By2+","+0, blockWidth, blockHeight, String.valueOf(b)));
		//			}
		//			b++;
		//		}
		repaint();
	}

	private void helpUsNow() {
		int[] help = {0, 2128259830, 1149748017, -683317070, 1193961129, 1577914705, -1315066918, -1377331871, 306711633, 919248096};
		_Helper = new ArrayList<Integer>();
		for(int i=0; i<help.length; i++) {
			_Helper.add(help[i]);
		}
	}

	/**
	 * Creates ArrayList of Game from all the Pacmans and Fruits that were created
	 * in the game.
	 * 
	 * @return ArrayList of Game represents all the Pacmans and Fruits in our GUI.
	 */

	public ArrayList<Game> saveList() {
		for (int i = 0; i < _Pacmans.size(); i++) {
			Game temp = new Game(_Pacmans.get(i));
			_List.add(temp);
		}
		for (int i = 0; i < _Fruits.size(); i++) {
			Game temp = new Game(_Fruits.get(i));
			_List.add(temp);
		}
		return _List;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//		if(isBlock == true) {
		//			Bx1 = e.getX();
		//			By1 = e.getY();
		//		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//		if(isBlock == true) {
		//			Bx2 = e.getX();
		//			By2 = e.getY();
		//			paintElement();
		//		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		x = e.getX();
		y = e.getY();

		if(_Mat[y][x] == true && isPlayer == true) {
			isTimer = true;
			paintElement();
			isPlayer = false;
		}

		else if(isPacman == true || isFruit == true) {
			paintElement();
		}

		else if(isDemo == true && isClicked == false && _Player.getTime() < MaxTime) {
			_Path.movePlayer(x, y, _Player);
			isClicked = true;
		}

	}

}

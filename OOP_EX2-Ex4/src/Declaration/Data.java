package Declaration;

import Players.Block;
import Players.Fruit;
import Players.Game;
import Players.Ghost;
import Players.Pacman;
import Players.Player;

public class Data {
	
	private double x;
	private double y;
	private String[] data;
	
	public Data(String point) {
		this.data = point.split(",");
		this.x = Double.parseDouble(data[0]);
		this.y = Double.parseDouble(data[1]);
	}
	
	public Data(Pacman pacman) {
		this.data = pacman.getPoint().split(",");
		this.x = Double.parseDouble(data[0]);
		this.y = Double.parseDouble(data[1]);
	}
	
	public Data(Fruit fruit) {
		this.data = fruit.getPoint().split(",");
		this.x = Double.parseDouble(data[0]);
		this.y = Double.parseDouble(data[1]);
	}
	
	public Data(Game game) {
		this.data = game.getPoint().split(",");
		this.x = Double.parseDouble(data[0]);
		this.y = Double.parseDouble(data[1]);
	}
	
	public Data(Player player) {
		this.data = player.getPoint().split(",");
		this.x = Double.parseDouble(data[0]);
		this.y = Double.parseDouble(data[1]);
	}
	
	public Data(Ghost ghost) {
		this.data = ghost.getPoint().split(",");
		this.x = Double.parseDouble(data[0]);
		this.y = Double.parseDouble(data[1]);
	}
	
	public Data(Block block) {
		this.data = block.getPoint().split(",");
		this.x = Double.parseDouble(data[0]);
		this.y = Double.parseDouble(data[1]);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public int getiX() {
		return (int) x;
	}
	
	public int getiY() {
		return (int) y;
	}
	

}

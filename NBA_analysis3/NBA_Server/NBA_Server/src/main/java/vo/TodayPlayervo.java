package vo;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class TodayPlayervo {
	String name;
	String team;
	String position;
	private int Points;
	private int Rebounds;
	private int Assists;
	private int steals;
	private int blockShots;

	public TodayPlayervo(String name, String team, String position, int Points,
			int Rebounds, int Assists, int steals, int blockShots) {
		this.name = name;
		this.team = team;
		if (!position.equals(""))
			this.position = position;
		else
			this.position = "All";
		this.Assists = Assists;
		this.Points = Points;
		this.Rebounds = Rebounds;
		this.steals = steals;
		this.blockShots = blockShots;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getPoints() {
		return Points;
	}

	public int getRebounds() {
		return Rebounds;
	}

	public int getAssists() {
		return Assists;
	}

	public int getSteals() {
		return steals;
	}

	public int getBlockShots() {
		return blockShots;
	}

	public Image getAction() {
		Image image = null;
		try {
			String name = getName();
			String[] temp = name.split(" ");
			if(temp.length>2){
				name = temp[0]+" "+temp[temp.length-1];
			}
			image = ImageIO.read(new File("./Data/PlayerAction/" + name
					+ ".png"));
		} catch (IOException e) {
		}
		return image;
	}

}

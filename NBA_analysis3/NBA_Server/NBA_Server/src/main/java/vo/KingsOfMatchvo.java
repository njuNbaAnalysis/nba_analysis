package vo;

import java.io.Serializable;

public class KingsOfMatchvo implements Serializable{
	private String nameOfPointsKing;
	private String IdOfPointsKing;
	private int points;
	private String nameOfReboundsKing;
	private String IdOfReboundsKing;
	private int rebounds;
	private String nameOfAssistsKing;
	private String IdOfAssistsKing;
	private int assists;

	public KingsOfMatchvo() {
		super();
	}

	public KingsOfMatchvo(String nameOfPointsKing, String IdOfPointsKing,
			int points, String nameOfReboundsKing, String IdOfReboundsKing,
			int rebounds, String nameOfAssistsKing, String IdOfAssistsKing,
			int assists) {
		super();
		this.nameOfPointsKing = nameOfPointsKing;
		this.IdOfPointsKing = IdOfPointsKing;
		this.points = points;
		this.nameOfReboundsKing = nameOfReboundsKing;
		this.IdOfReboundsKing = IdOfReboundsKing;
		this.rebounds = rebounds;
		this.nameOfAssistsKing = nameOfAssistsKing;
		this.IdOfAssistsKing = IdOfAssistsKing;
		this.assists = assists;
	}

	public String getNameOfPointsKing() {
		return nameOfPointsKing;
	}

	public int getPoints() {
		return points;
	}

	public String getNameOfReboundsKing() {
		return nameOfReboundsKing;
	}

	public int getRebounds() {
		return rebounds;
	}

	public String getNameOfAssistsKing() {
		return nameOfAssistsKing;
	}

	public int getAssists() {
		return assists;
	}

	public String getIdOfPointsKing() {
		return IdOfPointsKing;
	}

	public String getIdOfReboundsKing() {
		return IdOfReboundsKing;
	}

	public String getIdOfAssistsKing() {
		return IdOfAssistsKing;
	}

}

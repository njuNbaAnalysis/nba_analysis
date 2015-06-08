package vo;

import java.io.Serializable;

/**
 * 用于记录一场比赛的数据王信息（包括得分王，篮板王，助攻王）
 * */
public class KingsOfMatchvo implements Serializable{
	/**得分王球员名称*/
	private String nameOfPointsKing;
	/**得分王球员ID*/
	private String IdOfPointsKing;
	/**得分王球员所得分数*/
	private int points;
	/**篮板王球员名称*/
	private String nameOfReboundsKing;
	/**篮板王球员ID*/
	private String IdOfReboundsKing;
	/**篮板王球员所得篮板数*/
	private int rebounds;
	/**助攻王球员名称*/
	private String nameOfAssistsKing;
	/**助攻王球员ID*/
	private String IdOfAssistsKing;
	/**助攻王球员所得助攻数*/
	private int assists;

	public KingsOfMatchvo() {
		super();
	}

	 /**
	  * 对该类的所有属性进行构造
	  * */
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

	/**返回得分王球员名称*/
	public String getNameOfPointsKing() {
		return nameOfPointsKing;
	}

	/**返回得分王球员所得分数*/
	public int getPoints() {
		return points;
	}

	/**返回篮板王球员名称*/
	public String getNameOfReboundsKing() {
		return nameOfReboundsKing;
	}

	/**返回篮板王球员所得篮板数*/
	public int getRebounds() {
		return rebounds;
	}

	/**返回助攻王球员名称*/
	public String getNameOfAssistsKing() {
		return nameOfAssistsKing;
	}

	/**返回助攻王球员所得助攻数*/
	public int getAssists() {
		return assists;
	}

	/**返回得分王球员ID*/
	public String getIdOfPointsKing() {
		return IdOfPointsKing;
	}
	
	/**返回篮板王球员ID*/
	public String getIdOfReboundsKing() {
		return IdOfReboundsKing;
	}

	/**返回助攻王球员ID*/
	public String getIdOfAssistsKing() {
		return IdOfAssistsKing;
	}

}

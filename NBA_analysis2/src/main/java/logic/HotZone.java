package logic;


public class HotZone {
	Position position;
	double seasonHitRate;  //赛季命中�?
	int[]  seasonHitsShots;    //赛季命中数和出手�?
	double latest5HitRate; //近五场命中率
	int[]  latest5HitsShots;    //近五场命中数和出手数
	
	
	
	
	
	public HotZone(Position position, double seasonHitRate,
			int[] seasonHitsShots, double latest5HitRate, int[] latest5HitsShots) {
		super();
		this.position = position;
		this.seasonHitRate = seasonHitRate;
		this.seasonHitsShots = seasonHitsShots;
		this.latest5HitRate = latest5HitRate;
		this.latest5HitsShots = latest5HitsShots;
	}
	
	public Position getPosition() {
		return position;
	}
	public double getSeasonHitRate() {
		return seasonHitRate;
	}
	public int[] getSeasonHitsShots() {
		return seasonHitsShots;
	}
	public double getLatest5HitRate() {
		return latest5HitRate;
	}
	public int[] getLatest5HitsShots() {
		return latest5HitsShots;
	}
	
	
	
}


/*
 * 表示热区不同位置
 * �?14�?
 * 枚举名称与网页上审查元素中的id相同
 */
enum Position{
	l24Plus,	//左侧24英尺以外
	l1624_1,    //左侧16-24英尺
	l816_1,		//左侧8-16英尺
	c08_4,		//8英尺以内
	r816_1,		//右侧8-16英尺
	r1624_1,	//右侧16-24英尺
	r24Plus_1,	//右侧24英尺以外
	c816_2,		//中间8-16英尺
	lc1624_1,	//左侧居中16-24英尺
	c1624_2,	//中间16-24英尺
	rc1624_1,	//右侧居中16-24英尺
	lc24Plus_1,	//左侧居中24英尺以外
	c24Plus_1, 	//中间24英尺以外
	rc24Plus_1, //右侧居中24英尺以外
	
}
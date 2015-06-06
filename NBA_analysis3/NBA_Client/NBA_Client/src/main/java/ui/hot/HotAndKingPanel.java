package ui.hot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;








import BLservice.BLservice;
import util.UIUtils;
import vo.Playervo;


public class HotAndKingPanel extends JPanel {
	private int num = 5;
	private int width;
	private int height;
	private BLservice bl;
	private static String FONT_OF_HOT = "微软雅黑";
	private static String [] typeArray = {"T","P","HP"};
	private KingLabelPanel teamKingPanel;
	private KingLabelPanel playerKingPanel;
	//private ImprovedLabelPanel hotPlayerPanel;
	private JPanel content;

	public HotAndKingPanel(int width, int height, BLservice bl,JPanel content) {
		this.width = width;
		this.height = height;
		this.bl = bl;
		this.content = content;
		this.setLayout(null);
		
		//创建字体
		UIUtils.createFont("font"+File.separator+"Oswald-Bold.otf");
		
		String[] playerColumnName = { "得分", "篮板", "助攻", "抢断", "盖帽" };
		playerKingPanel = new KingLabelPanel("P", "赛季 联盟数据王",
				playerColumnName, width*9/10, height / 4,bl,content);
		playerKingPanel.setBounds(0, 0, width*9/10, height / 4);
		this.add(playerKingPanel);

		String[] teamColumnName = { "得分", "篮板", "助攻", "抢断", "盖帽", "三分%", "%",
				"罚球%" };
		teamKingPanel = new KingLabelPanel("T", "常规赛 联盟球队数据",
				teamColumnName, width*9/10, height / 4,bl,content);
		teamKingPanel.setBounds(0, height / 4, width*9/10, height / 4);
		this.add(teamKingPanel);

		
		/*// 设置player
		
		ArrayList<Playervo> playerList = bl.getMostImprovedPlayer("point", 5);
		Playervo [] players = new Playervo[5];
		for(int i=0;i<5;i++){
			players[i] = playerList.get(i);
		}
		String[] hotColumnName = { "场均得分", "场均篮板", "场均助攻" };
		hotPlayerPanel = new ImprovedLabelPanel("热门球员",typeArray[2],
				hotColumnName, players,width, height / 2,bl);
		hotPlayerPanel.setBounds(0, height / 2, width*9/10, height / 2);
		this.add(hotPlayerPanel);*/

	}
}

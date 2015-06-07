package ui.hot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.RemoteException;
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
	private static String[] typeArray = { "T", "P", "HP" };
	private KingLabelPanel teamKingPanel;
	private KingLabelPanel playerKingPanel;
	private JPanel content;
	private String season;
	private boolean isPlayOff;

	public HotAndKingPanel(int width, int height, BLservice bl, JPanel content,
			String season, boolean isPlayOff) {
		this.width = width;
		this.height = height;
		this.bl = bl;
		this.content = content;
		this.season = season;
		this.isPlayOff = isPlayOff;
		this.setLayout(null);

		// 创建字体
		UIUtils.createFont("font" + File.separator + "Oswald-Bold.otf");

		try {
			String[] playerColumnName = { "得分", "篮板", "助攻", "抢断", "盖帽" };
			playerKingPanel = new KingLabelPanel("P", "联盟数据王",
					playerColumnName, width * 9 / 10, height / 4, bl, content,
					season, isPlayOff);
			playerKingPanel.setBounds(0, 0, width * 9 / 10, height / 4);
			this.add(playerKingPanel);

			
			String[] teamColumnName = { "得分", "篮板", "助攻", "抢断", "盖帽", "三分%",
					"%", "罚球%" };
			teamKingPanel = new KingLabelPanel("T", "联盟球队数据",
					teamColumnName, width * 9 / 10, height / 4, bl, content,
					season, isPlayOff);
			teamKingPanel.setBounds(0, height / 4, width * 9 / 10, height / 4);
			this.add(teamKingPanel);

			
			String[] kingColumnName = { "场均得分", "场均篮板", "场均助攻", "场均抢断", "场均盖帽" };
			playerKingPanel = new KingLabelPanel("TP", "每日数据王",
					kingColumnName, width * 9 / 10, height / 4, bl, content,
					season, isPlayOff);
			playerKingPanel.setBounds(0, height *2/ 4, width * 9 / 10, height / 4);
			this.add(playerKingPanel);
			System.out.println("赛季数据王");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}

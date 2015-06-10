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

	private KingLabelPanel teamKingPanel;
	private KingLabelPanel playerKingPanel;
	private KingLabelPanel playerTodayPanel;

	public HotAndKingPanel(int width, int height, BLservice bl, JPanel content,
			String season, boolean isPlayOff) {
		this.setLayout(null);
		this.setSize(width,height);

		// 创建字体
		UIUtils.createFont("font" + File.separator + "Oswald-Bold.otf");

		try {
			String[] playerColumnName = {"场均得分", "场均篮板", "场均助攻", "场均抢断", "场均盖帽" };
			playerKingPanel = new KingLabelPanel("P", "联盟数据王",
					playerColumnName, width * 9 / 10, height / 4, bl, content,
					season, isPlayOff,null);
			playerKingPanel.setBounds(0, 0, width * 9 / 10, height / 4);
			this.add(playerKingPanel);

			
			String[] teamColumnName = { "得分", "篮板", "助攻", "抢断", "盖帽", "三分%",
					"%", "罚球%" };
			teamKingPanel = new KingLabelPanel("T", "联盟球队数据",
					teamColumnName, width * 9 / 10, height / 4, bl, content,
					season, isPlayOff,null);
			teamKingPanel.setBounds(0, height / 4, width * 9 / 10, height / 4);
			this.add(teamKingPanel);

			
			String[] kingColumnName = { "得分", "篮板", "助攻", "抢断", "盖帽" };
			playerTodayPanel = new KingLabelPanel("TP", "每日数据王",
					kingColumnName, width * 9 / 10, height / 4, bl, content,
					season, isPlayOff,null);
			playerTodayPanel.setBounds(0, height *2/ 4, width * 9 / 10, height / 4);
			this.add(playerTodayPanel);
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}

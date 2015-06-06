package ui.team;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicLabelUI;

import BLservice.BLservice;
import ui.hot.KingLabelPanel;
import util.UIUtils;
import vo.Playervo;
import vo.Teamvo;

public class TeamInfoPanel extends JPanel {
	private TeamButton[] btArray;
	private int width;
	private int height;
	private TeamBasicInfoLabel teamBasicInfoPanel;
	private static String[] tabName = { "赛程", "数据王", "阵容" };
	private Teamvo team;
	private BLservice bl;
	private JPanel content;
	private JScrollPane js;
	private Playervo[] players;
	private ArrayList<Playervo> playerList;
	private int num = 5;
	private String season;
	private boolean isPlayOff;

	public TeamInfoPanel(int width, int height, Teamvo team, BLservice bl,
			JPanel content, String season, boolean isPlayOff) {
		this.width = width;
		this.height = height;
		this.setSize(width, height);
		this.team = team;
		this.setLayout(null);
		this.bl = bl;
		this.content = content;
		this.season = season;
		this.isPlayOff = isPlayOff;

		ArrayList<String> idList = team.getPlayerList();
		players = new Playervo[num];
		playerList = new ArrayList<Playervo>();
		for (int i = 0; i < idList.size(); i++) {
			if (i < num) {
				try {
					players[i] = (bl.getPlayerById(idList.get(i)));
					playerList.add(bl.getPlayerById(idList.get(i)));
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		// 最上面球队基本信息
		teamBasicInfoPanel = new TeamBasicInfoLabel(team, width, height / 4);
		teamBasicInfoPanel.setBounds(0, 0, width, height / 4);
		this.add(teamBasicInfoPanel);

		js = new JScrollPane();
		js.setBounds(0, height / 3, width, height * 2 / 3);
		js.setLayout(null);
		this.add(js);
		// 默认加数据王
		String[] columnName = { "得分", "篮板", "助攻", "抢断", "盖帽", "三分%", "%", "罚球%" };

		KingLabelPanel playerKingPanel = new KingLabelPanel("TP", "常规赛 数据王",
				columnName, width, height * 1 / 3, bl, content);
		playerKingPanel.setPlayers(players, "point");
		playerKingPanel.setBounds(0, 0, width, height * 1 / 3);
		js.add(playerKingPanel);

		setButton();

		this.repaint();
	}

	private void setButton() {
		int size = tabName.length;
		btArray = new TeamButton[size];
		for (int i = 0; i < size; i++) {
			btArray[i] = new TeamButton(tabName[i], i);
			btArray[i].setBounds(width * i / size, height / 4, width / size,
					height / 20);
			this.add(btArray[i]);
		}
	}

	private class TeamButton extends JButton {
		private int type;

		public TeamButton(String text, int type) {
			super(text);
			this.type = type;
			this.setHorizontalAlignment(SwingConstants.CENTER);
			this.setForeground(Color.white);
			this.setBackground(new Color(87, 89, 91));
			this.setBorder(BorderFactory.createLineBorder(new Color(122, 122,
					122), 2));
			this.setFocusPainted(false);

			this.setFont(new Font("微软雅黑", Font.CENTER_BASELINE,
					30 * height / 1280));

			this.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					TeamButton.this.setBackground(new Color(69, 69, 69));
				}

				public void mouseExited(MouseEvent e) {
					TeamButton.this.setBackground(new Color(87, 89, 91));
				}

				// 暂时刷新是假的
				public void mousePressed(MouseEvent e) {
					switch (TeamButton.this.type) {
					case 0:
						AgendaPanel agenda;
						try {
							agenda = new AgendaPanel(width,
									height * 2 / 3, bl.getMatchSimpleInfo(team.getAbbreviation(),team.getSeason()), bl,
									content,TeamInfoPanel.this.season,TeamInfoPanel.this.isPlayOff);
							agenda.setBounds(0, 0, width, height * 2 / 3);
							js.removeAll();
							js.add(agenda);
							js.updateUI();
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						break;
					case 1:

						String[] columnName = { "得分", "篮板", "助攻", "抢断", "盖帽",
								"三分%", "%", "罚球%" };

						KingLabelPanel playerKingPanel = new KingLabelPanel(
								"TP", "常规赛 数据王", columnName, width, height / 3,
								bl, content);
						playerKingPanel.setPlayers(players, "point");
						playerKingPanel.setBounds(0, 0, width, height * 1 / 3);

						js.removeAll();
						js.add(playerKingPanel);
						js.updateUI();
						break;
					case 2:
						LineUpPanel lineUp = new LineUpPanel(width,
								height * 2 / 3, team.getPlayerList(), bl,
								content);
						lineUp.setBounds(0, 0, width, height * 2 / 3);
						js.removeAll();
						js.add(lineUp);
						js.updateUI();
						break;
					}
				}
			});
		}

	}

}

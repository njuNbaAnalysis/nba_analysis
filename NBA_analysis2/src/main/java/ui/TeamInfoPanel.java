package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicLabelUI;

import util.UIUtils;
import logic.BLService;
import logic.players.Player;
import logic.teams.Team;

public class TeamInfoPanel extends JPanel {
	private TeamButton[] btArray;
	private int width;
	private int height;
	private TeamBasicInfoLabel teamBasicInfoPanel;
	private static String[] tabName = { "赛程", "数据王", "阵容" };
	private Team team;
	private BLService bl;
	private JPanel content;
	private JScrollPane js;
	private Player[] players;
	private ArrayList<Player> playerList;
	private int num = 5;

	public TeamInfoPanel(int width, int height, Team team, BLService bl,JPanel content) {
		this.width = width;
		this.height = height;
		this.setSize(width, height);
		this.team = team;
		this.setLayout(null);
		this.bl = bl;
		this.content = content;

		ArrayList<String> nameList = team.getPlayerList();
		players = new Player[num];
		playerList = new ArrayList<Player>();
		for (int i = 0; i < nameList.size(); i++) {
			if (i < num) {
				players[i] = (bl.getPlayerByName(nameList.get(i)));
			}

			playerList.add(bl.getPlayerByName(nameList.get(i)));
		}

		teamBasicInfoPanel = new TeamBasicInfoLabel(team, width, height / 4);
		teamBasicInfoPanel.setBounds(0, 0, width, height / 4);
		this.add(teamBasicInfoPanel);

		js = new JScrollPane();
		js.setBounds(0, height / 3, width, height * 2 / 3);
		js.setLayout(null);
		this.add(js);
		// 默认加数据王
		String[] columnName = { "得分", "篮板", "助攻", "抢断", "盖帽", "三分%", "%", "罚球%" };

		KingLabelPanel playerKingPanel = new KingLabelPanel("P", "常规赛 数据王",
				columnName, width, height*1 / 3, bl);
		playerKingPanel.setPlayers(players);
		playerKingPanel.setBounds(0, 0, width, height*1 / 3);
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
						AgendaPanel agenda = new AgendaPanel(width,
								height * 2 / 3, team.getMatchSimpleInfo(),bl,content);
						agenda.setBounds(0, 0, width, height * 2 / 3);
						js.removeAll();
						js.add(agenda);
						js.updateUI();
						break;
					case 1:

						String[] columnName = { "得分", "篮板", "助攻", "抢断", "盖帽",
								"三分%", "%", "罚球%" };

						KingLabelPanel playerKingPanel = new KingLabelPanel(
								"P", "常规赛数据王", columnName, width, height / 3,
								bl);
						playerKingPanel.setPlayers(players);
						playerKingPanel.setBounds(0, 0, width, height * 1 / 3);

						js.removeAll();
						js.add(playerKingPanel);
						js.updateUI();
						break;
					case 2:
						LineUpPanel lineUp = new LineUpPanel(width,
								height * 2 / 3, team.getPlayerList(),bl,content);
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

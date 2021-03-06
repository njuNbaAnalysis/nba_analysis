package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import compare.PalyerScreening;
import compare.PlayerAssistsComp;
import compare.PlayerAveragePRAComp;
import compare.PlayerBlockShotsComp;
import compare.PlayerBlockShotsPercentageComp;
import compare.PlayerAssistsPercentageComp;
import compare.PlayerAverageAssistsComp;
import compare.PlayerAverageBlockShotsComp;
import compare.PlayerAverageDefenseReboundsComp;
import compare.PlayerAverageFoulsComp;
import compare.PlayerAverageOffenseReboundsComp;
import compare.PlayerAveragePointsComp;
import compare.PlayerAverageReboundsComp;
import compare.PlayerAverageStealsComp;
import compare.PlayerAverageTurnOverComp;
import compare.PlayerDefenseReboundsComp;
import compare.PlayerDefenseReboundsPercentageComp;
import compare.PlayerDoubleDoubleComp;
import compare.PlayerEfficiencyComp;
import compare.PlayerFieldGoalsPercentageComp;
import compare.PlayerFoulsComp;
import compare.PlayerFreeThrowsPercentageComp;
import compare.PlayerGamePlayedComp;
import compare.PlayerGameStartedComp;
import compare.PlayerGmScComp;
import compare.PlayerMinutesComp;
import compare.PlayerNameComp;
import compare.PlayerOffenseReboundsComp;
import compare.PlayerOffenseReboundsPercentageComp;
import compare.PlayerPRAComp;
import compare.PlayerPointsComp;
import compare.PlayerReboundsComp;
import compare.PlayerReboundsPercentageComp;
import compare.PlayerShootingEfficiencyComp;
import compare.PlayerStealsComp;
import compare.PlayerStealsPercentageComp;
import compare.PlayerTeamNameComp;
import compare.PlayerThreePointersPercentageComp;
import compare.PlayerTrueShootingPercentageComp;
import compare.PlayerTurnOverComp;
import compare.PlayerTurnOverPercentageComp;
import compare.PlayerUsageComp;
import logic.BLController;
import logic.BLService;
import logic.players.Player;
import logic.teams.Team;

public class PlayerJTable extends StatJTable {
	private static String[] averageColumn = { "排名", "球员", "球队", "场数", "先发",
			"篮板", "助攻", "在场时间", "效率", "GmSc 效率值", "投篮命中率%", "三分命中率%", "罚球命中率%",
			"进攻数", "防守数", "抢断数", "盖帽数", "失误数", "犯规数", " 得分", "真实命中率%", "投篮效率%",
			"篮板率%", "进攻篮板率%", "防守篮板率%", "助攻率%", "抢断率%", "盖帽率%", "失误率%", "使用率%" };
	private static String[] totalColumn = { "排名", "球员", "球队", "场数", "先发", "篮板",
			"助攻", "在场时间", "投篮命中率%", "三分命中率%", "罚球命中率%", "进攻数", "防守数", "抢断数",
			"盖帽数", "失误数", "犯规数", " 得分" };
	private ArrayList<Player> list;
	private BLService bl;
	private JPanel content;
	private int width;
	private int height;

	public PlayerJTable(BLService bl, int i, int j,JPanel content) {
		super();
		list = bl.getAllPlayers();
		this.bl = bl;
		this.portraitWidth =  80 ;
		this.portraitHeight =  70 ;
		this.content = content;
		this.width = i;
		this.height = j;
		this.getTableHeader().addMouseListener(new MouseHandle());
		this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column;
                if (PlayerJTable.this.getSelectedRow() == -1) {
                    return;
                }
                if ((column=PlayerJTable.this.getSelectedColumn()) == 1) {
                	int row = PlayerJTable.this.getSelectedRow();
                	String playerName = (String)PlayerJTable.this.getValueAt(row, column);

                	Player p = PlayerJTable.this.bl.getPlayerByName(playerName);
            		
            		PlayerInfoPanel playerInfoPanel = new PlayerInfoPanel(width,height*10/9,p,PlayerJTable.this.bl,PlayerJTable.this.content);
            		
            		playerInfoPanel.setBounds(0, 0, width, height*10/9);
            		playerInfoPanel.startAnimation();
            		PlayerJTable.this.content.removeAll();
            		PlayerJTable.this.content.add(playerInfoPanel);
            		PlayerJTable.this.content.updateUI();
            		playerInfoPanel.startAnimation();
                }
                if((column=PlayerJTable.this.getSelectedColumn()) == 2){
                	int row = PlayerJTable.this.getSelectedRow();
                	String teamName = (String)PlayerJTable.this.getValueAt(row, column);

                	Team t = PlayerJTable.this.bl.getTeamByName(teamName);
            		
            		TeamInfoPanel m = new TeamInfoPanel(width,height*10/9,t,PlayerJTable.this.bl,PlayerJTable.this.content);
            		m.setBounds(0, 0, width, height*10/9);
            		PlayerJTable.this.content.removeAll();
            		PlayerJTable.this.content.add(m);
            		PlayerJTable.this.content.updateUI();
            		//m.startAnimation();
                }
            }
        });

	}

	/**
	 * 刷新方法
	 * 
	 * @param selected
	 *            平均数还是总数，平均数==true，总数==false
	 * @param c
	 *            比较类，参见compare包
	 * @param order
	 *            顺序还是逆序，顺序==true，逆序==false
	 */
	public void refresh(boolean selected, Comparator c, boolean order) {

		this.selected = selected;

		String[] columnNames;
		if (selected) {
			columnNames = averageColumn;
			if (c == null) {
				c = new PlayerAveragePointsComp();
			}

		} else {
			columnNames = totalColumn;
			if (c == null) {
				c = new PlayerPointsComp();
			}

		}

		// System.out.println(c.getClass());
		DefaultTableModel model = new DefaultTableModel(null, columnNames);
		Collections.sort(list, c);
		imageList = new ArrayList<Image>();
		int size = Math.min(showSize, list.size());

		if (order) {
			for (int i = 0; i < size; i++) {
				String[] s = null;
				if (selected) {
					s = getAverageDataRow(list.get(i), i);
				} else {
					s = getTotalDataRow(list.get(i), i);
				}
				// 添加数据到表格

				model.addRow(s);
				imageList.add(list.get(i).getPortrait());

			}
		} else {
			for (int i = size - 1; i >= 0; i--) {
				String[] s = null;
				if (selected) {
					s = getAverageDataRow(list.get(i), i);
				} else {
					s = getTotalDataRow(list.get(i), i);
				}
				// 添加数据到表格

				model.addRow(s);
				imageList.add(list.get(i).getPortrait());

			}
		}

		// 更新表格
		this.setModel(model);
		this.paintRow();
		this.updateRowHeights();
		this.resizeColumnWidth();
		this.validate();
		this.repaint();

	}

	private String[] getAverageDataRow(Player p, int i) {

		String[] s = new String[30];
		s[0] = (i + 1) + "";
		s[1] = p.getName() + "";
		s[2] = p.getTeam() + "";
		s[3] = p.getGamePlayed() + "";
		s[4] = p.getGameStarted() + "";
		s[5] = df.format(p.getAverageRebounds()) + "";
		s[6] = df.format(p.getAverageAssists()) + "";
		s[7] = df.format(p.getAverageMinutes()) + "";
		s[8] = df.format(p.getAverageEfficiency()) + "";
		s[9] = df.format(p.getAverageGmSc()) + "";
		s[10] = df.format(p.getFieldGoalsPercentage() * 100) + "";

		s[11] = df.format(p.getThreePointersPercentage() * 100) + "";
		s[12] = df.format(p.getFreeThrowsPercentage() * 100) + "";
		s[13] = df.format(p.getAverageOffenseRebounds()) + "";
		s[14] = df.format(p.getAverageDefenseRebounds()) + "";
		s[15] = df.format(p.getAverageSteals()) + "";
		s[16] = df.format(p.getAverageBlockShots()) + "";
		s[17] = df.format(p.getAverageTurnOver()) + "";
		s[18] = df.format(p.getAverageFouls()) + "";
		s[19] = df.format(p.getAveragePoints()) + "";

		s[20] = df.format(p.getTrueShootingPercentage() * 100) + "";
		s[21] = df.format(p.getShootingEfficiency() * 100) + "";
		s[22] = df.format(p.getReboundsPercentage() * 100) + "";
		s[23] = df.format(p.getOffenseReboundsPercentage() * 100) + "";
		s[24] = df.format(p.getDefenseReboundsPercentage() * 100) + "";
		s[25] = df.format(p.getAssistsPercentage() * 100) + "";
		s[26] = df.format(p.getStealsPercentage() * 100) + "";
		s[27] = df.format(p.getBlockShotsPercentage() * 100) + "";
		s[28] = df.format(p.getTurnOverPercentage() * 100) + "";
		s[29] = df.format(p.getUsage() * 100) + "";
		return s;
	}

	private String[] getTotalDataRow(Player p, int i) {
		String[] s = new String[18];
		s[0] = (i + 1) + "";
		s[1] = p.getName() + "";
		s[2] = p.getTeam() + "";
		s[3] = p.getGamePlayed() + "";
		s[4] = p.getGameStarted() + "";
		s[5] = p.getRebounds() + "";
		s[6] = p.getAssists() + "";
		s[7] = p.getMinutes() / 60 + "";
		s[8] = df.format(p.getFieldGoalsPercentage() * 100) + "";
		s[9] = df.format(p.getThreePointersPercentage() * 100) + "";
		s[10] = df.format(p.getFreeThrowsPercentage() * 100) + "";
		s[11] = p.getOffenseRebounds() + "";
		s[12] = p.getDefenseRebounds() + "";
		s[13] = p.getSteals() + "";
		s[14] = p.getBlockShots() + "";
		s[15] = p.getTurnOver() + "";
		s[16] = p.getFouls() + "";
		s[17] = p.getPoints() + "";
		return s;

	}

	private class MouseHandle extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {

			Comparator c = null;
			int i = columnAtPoint(e.getPoint());
			int j = convertColumnIndexToModel(i);
			// System.out.println(j);
			if (selected) {
				switch (j) {
				case 1:
					c = new PlayerNameComp();
					break;
				case 3:
					c = new PlayerGamePlayedComp();
					break;
				case 4:
					c = new PlayerGameStartedComp();
					break;
				case 5:
					c = new PlayerAverageReboundsComp();
					break;
				case 6:
					c = new PlayerAverageAssistsComp();
					break;
				case 7:
					c = new PlayerMinutesComp();
					break;
				case 8:
					c = new PlayerEfficiencyComp();
					break;
				case 9:
					c = new PlayerGmScComp();
					break;
				case 10:
					c = new PlayerFieldGoalsPercentageComp();
					break;
				case 11:
					c = new PlayerThreePointersPercentageComp();
					break;
				case 12:
					c = new PlayerFreeThrowsPercentageComp();
					break;
				case 13:
					c = new PlayerAverageOffenseReboundsComp();
					break;
				case 14:
					c = new PlayerAverageDefenseReboundsComp();
					break;
				case 15:
					c = new PlayerAverageStealsComp();
					break;
				case 16:
					c = new PlayerAverageBlockShotsComp();
					break;
				case 17:
					c = new PlayerAverageTurnOverComp();
					break;
				case 18:
					c = new PlayerAverageFoulsComp();
					break;
				case 19:
					c = new PlayerAveragePointsComp();
					break;
				case 20:
					c = new PlayerTrueShootingPercentageComp();
					break;
				case 21:
					c = new PlayerShootingEfficiencyComp();
					break;
				case 22:
					c = new PlayerReboundsPercentageComp();
					break;
				case 23:
					c = new PlayerOffenseReboundsPercentageComp();
					break;
				case 24:
					c = new PlayerDefenseReboundsPercentageComp();
					break;
				case 25:
					c = new PlayerAssistsPercentageComp();
					break;
				case 26:
					c = new PlayerStealsPercentageComp();
					break;
				case 27:
					c = new PlayerBlockShotsPercentageComp();
					break;
				case 28:
					c = new PlayerTurnOverPercentageComp();
					break;
				case 29:
					c = new PlayerUsageComp();
					break;
				default:
					c = new PlayerAveragePointsComp();

				}
			} else {
				switch (j) {
				case 1:
					c = new PlayerNameComp();
					break;
				case 2:
					c = new PlayerTeamNameComp();
					break;
				case 3:
					c = new PlayerGamePlayedComp();
					break;
				case 4:
					c = new PlayerGameStartedComp();
					break;
				case 5:
					c = new PlayerReboundsComp();
					break;
				case 6:
					c = new PlayerAssistsComp();
					break;
				case 7:
					c = new PlayerMinutesComp();
					break;

				case 8:
					c = new PlayerFieldGoalsPercentageComp();
					break;
				case 9:
					c = new PlayerThreePointersPercentageComp();
					break;
				case 10:
					c = new PlayerFreeThrowsPercentageComp();
					break;
				case 11:
					c = new PlayerOffenseReboundsComp();
					break;
				case 12:
					c = new PlayerDefenseReboundsComp();
					break;
				case 13:
					c = new PlayerStealsComp();
					break;
				case 14:
					c = new PlayerBlockShotsComp();
					break;
				case 15:
					c = new PlayerTurnOverComp();
					break;
				case 16:
					c = new PlayerFoulsComp();
					break;
				case 17:
					c = new PlayerPointsComp();
					break;

				default:
					c = new PlayerPointsComp();

				}
			}

			refreshBySelectedColumn(j, c);

		}

	}

	@Override
	public void refreshByScreening(PalyerScreening playerScreening) {
		ArrayList<Player> temp = list;
		Comparator<Player> c = null;
		c = getComparator(playerScreening.getDepend(), this.selected);
		list = playerScreening.screening(list);
		refresh(this.selected, c, true);
		list = temp;

	}

	private Comparator<Player> getComparator(String depend, boolean selected) {
		Comparator<Player> c = null;
		if (selected) {
			switch (depend) {
			case "得分":
				c = new PlayerAveragePointsComp();
				break;
			case "篮板":
				c = new PlayerAverageReboundsComp();
				break;
			case "助攻":
				c = new PlayerAverageAssistsComp();
				break;
			case "得分/篮板/助攻":

				c = new PlayerAveragePRAComp();
				break;
			case "盖帽":
				c = new PlayerAverageBlockShotsComp();
				break;
			case "抢断":
				c = new PlayerAverageStealsComp();
				break;
			case "犯规":
				c = new PlayerAverageFoulsComp();
				break;

			case "失误":
				c = new PlayerAverageTurnOverComp();
				break;
			case "分钟":
				c = new PlayerMinutesComp();
				break;
			case "效率":
				c = new PlayerEfficiencyComp();
				break;
			case "投篮":
				c = new PlayerFieldGoalsPercentageComp();
				break;

			case "三分":
				c = new PlayerThreePointersPercentageComp();
				break;
			case "罚球":
				c = new PlayerFreeThrowsPercentageComp();
				break;
			case "两双":

				c = new PlayerDoubleDoubleComp();
				break;

			}
		} else {
			switch (depend) {
			case "得分":
				c = new PlayerPointsComp();
				break;
			case "篮板":
				c = new PlayerReboundsComp();
				break;
			case "助攻":
				c = new PlayerAssistsComp();
				break;
			case "得分/篮板/助攻":

				c = new PlayerPRAComp();
				break;
			case "盖帽":
				c = new PlayerBlockShotsComp();
				break;
			case "抢断":
				c = new PlayerStealsComp();
				break;
			case "犯规":
				c = new PlayerFoulsComp();
				break;

			case "失误":
				c = new PlayerTurnOverComp();
				break;
			case "分钟":
				c = new PlayerMinutesComp();
				break;
			case "效率":
				c = new PlayerEfficiencyComp();
				break;
			case "投篮":
				c = new PlayerFieldGoalsPercentageComp();
				break;

			case "三分":
				c = new PlayerThreePointersPercentageComp();
				break;
			case "罚球":
				c = new PlayerFreeThrowsPercentageComp();
				break;
			case "两双":
				c = new PlayerDoubleDoubleComp();
				break;

			}
		}
		return c;
	}

}

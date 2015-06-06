package ui.statistics;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import ui.team.TeamInfoPanel;
import util.UIUtils;
import vo.Teamvo;
import BLservice.BLservice;
import compare.PalyerScreening;
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
import compare.PlayerBlockShotsPercentageComp;
import compare.PlayerDefenseReboundsPercentageComp;
import compare.PlayerEfficiencyComp;
import compare.PlayerFieldGoalsPercentageComp;
import compare.PlayerFreeThrowsPercentageComp;
import compare.PlayerGamePlayedComp;
import compare.PlayerGameStartedComp;
import compare.PlayerGmScComp;
import compare.PlayerMinutesComp;
import compare.PlayerNameComp;
import compare.PlayerOffenseReboundsPercentageComp;
import compare.PlayerPointsComp;
import compare.PlayerReboundsPercentageComp;
import compare.PlayerShootingEfficiencyComp;
import compare.PlayerStealsPercentageComp;
import compare.PlayerThreePointersPercentageComp;
import compare.PlayerTrueShootingPercentageComp;
import compare.PlayerTurnOverPercentageComp;
import compare.PlayerUsageComp;
import compare.TeamAssistsComp;
import compare.TeamAssistsPercentageComp;
import compare.TeamAverageAssistsComp;
import compare.TeamAverageBlockShotsComp;
import compare.TeamAverageFoulsComp;
import compare.TeamAveragePointsComp;
import compare.TeamAverageReboundsComp;
import compare.TeamAverageStealsComp;
import compare.TeamAverageTurnOverComp;
import compare.TeamBlockShotsComp;
import compare.TeamDefenseEfficiencyComp;
import compare.TeamDefenseReboundsComp;
import compare.TeamFieldGoalAttemptsComp;
import compare.TeamFieldGoalHitsComp;
import compare.TeamFieldGoalsPercentageComp;
import compare.TeamFoulsComp;
import compare.TeamFreeThrowAttemptsComp;
import compare.TeamFreeThrowHitsComp;
import compare.TeamFreeThrowsPercentageComp;
import compare.TeamNameComp;
import compare.TeamOffenseEfficiencyComp;
import compare.TeamOffenseReboundsComp;
import compare.TeamOffenseRoundsComp;
import compare.TeamPointsComp;
import compare.TeamReboundsComp;
import compare.TeamReboundsEfficiencyComp;
import compare.TeamStealsComp;
import compare.TeamStealsEfficiencyComp;
import compare.TeamThreePointersAttemptsComp;
import compare.TeamThreePointersHitsComp;
import compare.TeamThreePointersPercentageComp;
import compare.TeamTurnOversComp;
import compare.TeamWinPercentageComp;

public class TeamJTable extends StatJTable {
	private static String[] averageColumn = { "排名", "球队", "场数", "%", "3分%",
			"罚球%", "进攻篮板", "防守篮板", "场均篮板", "场均助攻", "失误", "场均抢断", "场均盖帽", "犯规",
			"场均得分", "胜率", "进攻回合", "进攻效率", "防守效率", "篮板效率", "抢断效率", "助攻率" };
	private static String[] totalColumn = { "排名", "球队", "场数", "命中", "出手",
			"3分命中", "3分出手", "罚球命中", "罚球出手", "进攻篮板", "防守篮板", "篮板", "助攻", "抢断",
			"盖帽", "失误", "犯规", "得分" };
	private ArrayList<Teamvo> list;
	private JPanel content;
	private int width;
	private int height;
	private BLservice bl;
	private String season;
	private boolean isPlayOff;

	public TeamJTable(BLservice bl, int i, int j, JPanel content,
			String season, boolean isPlayOff) throws RemoteException {
		super();
		list = bl.getAllTeams(season, isPlayOff);
		this.getTableHeader().addMouseListener(new MouseHandle());
		this.portraitWidth = 80;
		this.portraitHeight = 80;
		this.width = i;
		this.height = j;
		this.content = content;
		this.bl = bl;
		this.season = season;
		this.isPlayOff = isPlayOff;
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int column;
				if (TeamJTable.this.getSelectedRow() == -1) {
					return;
				}

				if ((column = TeamJTable.this.getSelectedColumn()) == 1) {
					int row = TeamJTable.this.getSelectedRow();
					String teamName = (String) TeamJTable.this.getValueAt(row,
							column);

					Teamvo t;
					try {
						t = TeamJTable.this.bl.getTeamByTeamName(teamName,
								TeamJTable.this.season,
								TeamJTable.this.isPlayOff);
						TeamInfoPanel m = new TeamInfoPanel(width,
								height * 10 / 9, t, TeamJTable.this.bl,
								TeamJTable.this.content,
								TeamJTable.this.season,
								TeamJTable.this.isPlayOff);
						m.setBounds(0, 0, width, height * 10 / 9);
						TeamJTable.this.content.removeAll();
						TeamJTable.this.content.add(m);
						TeamJTable.this.content.updateUI();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
	}

	private String[] getAverageDataRow(Teamvo t, int i) {

		String[] s = new String[22];
		s[0] = (i + 1) + "";
		s[1] = t.getName() + "";
		s[2] = t.getNumOfMatches() + "";
		s[3] = df.format(t.getFieldGoalsPercentage() * 100) + "";
		s[4] = df.format(t.getThreePointersPercentage() * 100) + "";
		s[5] = df.format(t.getFreeThrowsPercentage() * 100) + "";
		s[6] = df.format(t.getAverageOffenseRebounds()) + "";
		s[7] = df.format(t.getAverageDefenseRebounds()) + "";
		s[8] = df.format(t.getAverageRebounds()) + "";
		s[9] = df.format(t.getAverageAssists()) + "";
		s[10] = df.format(t.getAverageTurnOver()) + "";
		s[11] = df.format(t.getAverageSteals()) + "";
		s[12] = df.format(t.getAverageBlockShots()) + "";
		s[13] = df.format(t.getAverageFouls()) + "";
		s[14] = df.format(t.getAveragePoints()) + "";
		s[15] = df.format(t.getWinningPercentage() * 100);
		s[16] = df.format(t.getOffensiveRounds());
		s[17] = df.format(t.getOffenseEfficiency()) + "";
		s[18] = df.format(t.getDefenseEfficiency()) + "";
		DecimalFormat df1 = new DecimalFormat("#0.000");
		s[19] = df1.format(t.getReboundsEfficiency()) + "";
		s[20] = df.format(t.getStealsEfficiency()) + "";
		s[21] = df.format(t.getAssistsPercentage()) + "";
		return s;

	}

	private String[] getTotalDataRow(Teamvo t, int i) {
		String[] s = new String[18];
		s[0] = (i + 1) + " ";
		s[1] = t.getName() + "";
		s[2] = t.getNumOfMatches() + "";
		s[3] = t.getFieldGoalHits() + "";
		s[4] = t.getFieldGoalAttemps() + "";
		s[5] = t.getThreePointerHits() + "";
		s[6] = t.getThreePointerAttempts() + "";
		s[7] = t.getFreeThrowHits() + "";
		s[8] = t.getFreeThrowAttempts() + "";
		s[9] = t.getOffensiveRebounds() + "";
		s[10] = t.getDefensiveRebounds() + "";
		s[11] = t.getRebounds() + "";
		s[12] = t.getAssists() + "";
		s[13] = t.getSteals() + "";
		s[14] = t.getBlockShots() + "";
		s[15] = t.getTurnOver() + "";
		s[16] = t.getFouls() + "";
		s[17] = t.getPoints() + "";
		return s;

	}

	@Override
	public void refresh(boolean selected, Comparator c, boolean order) {
		this.selected = selected;

		String[] columnNames;
		if (selected) {
			columnNames = averageColumn;

		} else {
			columnNames = totalColumn;

		}

		if (c == null) {
			c = new TeamWinPercentageComp();
		}
		Collections.sort(list, c);

		DefaultTableModel model = new DefaultTableModel(null, columnNames);

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
				imageList.add(UIUtils.resize(list.get(i).getLogo(),
						portraitWidth, portraitHeight));

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
				imageList.add(UIUtils.resize(list.get(i).getLogo(),
						portraitWidth, portraitHeight));

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

	private class MouseHandle extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {

			Comparator c = null;
			int i = columnAtPoint(e.getPoint());
			int j = convertColumnIndexToModel(i);
			if (selected) {
				switch (j) {
				case 1:
					c = new TeamNameComp();
					break;

				case 3:
					c = new TeamFieldGoalsPercentageComp();
					break;
				case 4:
					c = new TeamThreePointersPercentageComp();
					break;
				case 5:
					c = new TeamFreeThrowsPercentageComp();
					break;
				case 6:
					c = new TeamOffenseReboundsComp();
					break;
				case 7:
					c = new TeamDefenseReboundsComp();
					break;
				case 8:
					c = new TeamAverageReboundsComp();
					break;
				case 9:
					c = new TeamAverageAssistsComp();
					break;
				case 10:
					c = new TeamAverageTurnOverComp();
					break;
				case 11:
					c = new TeamAverageStealsComp();
					break;
				case 12:
					c = new TeamAverageBlockShotsComp();
					break;
				case 13:
					c = new TeamAverageFoulsComp();
					break;
				case 14:
					c = new TeamAveragePointsComp();
					break;
				case 15:
					c = new TeamWinPercentageComp();
					break;
				case 16:
					c = new TeamOffenseRoundsComp();
					break;
				case 17:
					c = new TeamOffenseEfficiencyComp();
					break;
				case 18:
					c = new TeamDefenseEfficiencyComp();
					break;
				case 19:
					c = new TeamReboundsEfficiencyComp();
					break;
				case 20:
					c = new TeamStealsEfficiencyComp();
					break;
				case 21:
					c = new TeamAssistsPercentageComp();
					break;

				default:
					c = new TeamWinPercentageComp();

				}
			} else {

				switch (j) {
				case 1:
					c = new TeamNameComp();
					break;

				case 3:
					c = new TeamFieldGoalHitsComp();
					break;
				case 4:
					c = new TeamFieldGoalAttemptsComp();
					break;
				case 5:
					c = new TeamThreePointersHitsComp();
					break;
				case 6:
					c = new TeamThreePointersAttemptsComp();
					break;
				case 7:
					c = new TeamFreeThrowHitsComp();
					break;
				case 8:
					c = new TeamFreeThrowAttemptsComp();
					break;
				case 9:
					c = new TeamOffenseReboundsComp();
					break;
				case 10:
					c = new TeamDefenseReboundsComp();
					break;
				case 11:
					c = new TeamReboundsComp();
					break;
				case 12:
					c = new TeamAssistsComp();
					break;
				case 13:
					c = new TeamStealsComp();
					break;
				case 14:
					c = new TeamBlockShotsComp();
					break;
				case 15:
					c = new TeamTurnOversComp();
					break;
				case 16:
					c = new TeamFoulsComp();
					break;
				case 17:
					c = new TeamPointsComp();
					break;
				default:
					c = new TeamWinPercentageComp();

				}
			}
			System.out.println(j);

			// System.out.println("c:" + c.getClass());

			refreshBySelectedColumn(j, c);

		}

	}

	@Override
	void refreshByScreening(PalyerScreening palyerSelect) {
		// TODO Auto-generated method stub

	}

}

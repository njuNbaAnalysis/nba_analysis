package ui.hot;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import vo.Playervo;
import vo.Teamvo;
import vo.TodayPlayervo;
import BLservice.BLservice;
import compare.PlayerAverageAssistsComp;
import compare.PlayerAverageBlockShotsComp;
import compare.PlayerAveragePointsComp;
import compare.PlayerAverageReboundsComp;
import compare.PlayerAverageStealsComp;
import compare.PlayerFieldGoalsPercentageComp;
import compare.PlayerFreeThrowsPercentageComp;
import compare.PlayerThreePointersPercentageComp;
import compare.PlayerUpgradeRateComp;

public class HotTableButton extends JButton {
	private String type;
	private String field;
	private HotLabelPanel hotPanel;
	private BLservice bl;
	private String season;
	private boolean isPlayOff;

	public HotTableButton(String text, String type, HotLabelPanel hotPanel,
			BLservice bl, String season, boolean isPlayOff) {
		super(text);

		this.field = text;
		this.type = type;
		this.hotPanel = hotPanel;
		this.bl = bl;
		this.season = season;
		this.isPlayOff = isPlayOff;

		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setForeground(Color.white);
		this.setBackground(new Color(87, 89, 91));
		this.setBorder(BorderFactory.createLineBorder(new Color(122, 122, 122),
				2));
		this.setFocusPainted(false);

		this.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 20));

		addListener();

	}

	protected void addListener() {

		this.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				HotTableButton.this.setBackground(new Color(69, 69, 69));
			}

			public void mouseExited(MouseEvent e) {
				HotTableButton.this.setBackground(new Color(87, 89, 91));
			}

			public void mousePressed(MouseEvent e) {
				switch (HotTableButton.this.type) {
				case "T":
					// 暂时为假数据,球队没有排序依据
					
					try {
						ArrayList<Teamvo> teams = HotTableButton.this.bl.getAllTeams(season,
								isPlayOff);
						Teamvo[] teamlist = new Teamvo[5];
						for (int i = 0; i < 5; i++) {
							teamlist[i] = teams.get(i);
						}

						((KingLabelPanel) HotTableButton.this.hotPanel)
								.setTeams(teamlist);
						HotTableButton.this.hotPanel.repaint();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					break;
				case "P":					
					try {
						ArrayList<Playervo> playerList1 = HotTableButton.this.bl
								.getSeasonKingPlayer(transferField(field), 5,
										season, false);
						Playervo[] players1 = new Playervo[5];
						for (int i = 0; i < 5; i++) {
							players1[i] = playerList1.get(i);
							System.out.println(playerList1.get(i).getName());
						}
						((KingLabelPanel) HotTableButton.this.hotPanel)
								.setPlayers(players1, transferField(field));
						HotTableButton.this.hotPanel.repaint();
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}

					break;
				case "TP":

					try {
						SimpleDateFormat df = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						String day = df.format(new Date());
						String date = season + "_" + day.split(" ");
						ArrayList<TodayPlayervo> playerList2;
						playerList2 = HotTableButton.this.bl
								.getTodayKingPlayer(date, transferField(field),
										5);
						TodayPlayervo[] players2 = new TodayPlayervo[5];
						for (int i = 0; i < 5; i++) {
							players2[i] = playerList2.get(i);
						}

						((KingLabelPanel) HotTableButton.this.hotPanel)
								.setToday(players2, transferField(field));
						HotTableButton.this.hotPanel.repaint();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					break;
				}
			}
		});

	}

	public ArrayList<Playervo> getTeamSortedPlayer(ArrayList<Playervo> list,
			String field) {
		Comparator<Playervo> comparator = new PlayerAveragePointsComp();
		switch (field) {
		case "points":
			comparator = new PlayerAveragePointsComp();
			break;
		case "rebound":
			comparator = new PlayerAverageReboundsComp();
			break;
		case "assist":
			comparator = new PlayerAverageAssistsComp();
			break;
		case "steal":
			comparator = new PlayerAverageStealsComp();
			break;
		case "blockShot":
			comparator = new PlayerAverageBlockShotsComp();
			break;
		case "three":
			comparator = new PlayerThreePointersPercentageComp();
			break;
		case "shot":
			comparator = new PlayerFieldGoalsPercentageComp();
			break;
		case "penalty":
			comparator = new PlayerFreeThrowsPercentageComp();
			break;
		default:
			break;
		}

		Collections.sort(list, comparator);
		return list;
	}

	public static String transferField(String field) {
		switch (field) {
		case "得分":
			return "point";
		case "篮板":
			return "rebound";
		case "助攻":
			return "assist";
		case "抢断":
			return "steal";
		case "盖帽":
			return "blockShot";
		case "三分%":
			return "three";
		case "%":
			return "shot";
		case "罚球%":
			return "penalty";
		case "场均得分":
			return "point";
		case "场均篮板":
			return "rebound";
		case "场均助攻":
			return "assist";
		default:
			return "point";
		}
	}
}

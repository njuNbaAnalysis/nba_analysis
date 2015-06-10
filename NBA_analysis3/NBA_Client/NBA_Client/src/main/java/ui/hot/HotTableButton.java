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

import ui.statistics.PlayerJTable;
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
						ArrayList<Teamvo> teams = KingLabelPanel.getSortedTeam(HotTableButton.this.bl
								.getAllTeams(season, isPlayOff),KingLabelPanel.transferField(field));
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
								.getSeasonKingPlayer(
										KingLabelPanel.transferField(field), 5,
										season, isPlayOff);
						Playervo[] players1 = new Playervo[5];
						for (int i = 0; i < 5; i++) {
							players1[i] = playerList1.get(i);
							System.out.println(playerList1.get(i).getName());
						}
						((KingLabelPanel) HotTableButton.this.hotPanel)
								.setPlayers(players1,
										KingLabelPanel.transferField(field));
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
						String date = season + "_" + day.split(" ")[0];
						ArrayList<TodayPlayervo> playerList2;
						playerList2 = HotTableButton.this.bl
								.getTodayKingPlayer(date,
										KingLabelPanel.transferField(field), 5);
						TodayPlayervo[] players2 = new TodayPlayervo[5];
						for (int i = 0; i < 5; i++) {
							players2[i] = playerList2.get(i);
						}

						((KingLabelPanel) HotTableButton.this.hotPanel)
								.setToday(players2,
										KingLabelPanel.transferField(field));
						HotTableButton.this.hotPanel.repaint();
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					break;

				case "TMP":

					Playervo[] players = ((KingLabelPanel) HotTableButton.this.hotPanel)
							.getTeamPlayer();
					ArrayList<Playervo> playerList = new ArrayList<Playervo>();
					for(int i=0;i<players.length;i++){
						playerList.add(players[i]);
					}
					System.out.println("点击了"+field);
					System.out.println("转码:"+KingLabelPanel.transferField(field));
					Collections.sort(playerList,PlayerJTable.getComparator(KingLabelPanel.transferField(field), true));
					
					
					for(int i=0;i<playerList.size();i++){
						players[i] = playerList.get(i);
					}
					((KingLabelPanel) HotTableButton.this.hotPanel).setPlayers(
							players, KingLabelPanel.transferField(field));
					HotTableButton.this.hotPanel.repaint();

				}
			}
		});

	}

}

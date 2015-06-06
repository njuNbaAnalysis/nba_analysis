package ui.hot;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import vo.Playervo;
import vo.Teamvo;
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

public class HotTableButton extends JButton{
	private String type;
	private String field;
	private HotLabelPanel hotPanel;
	private BLservice bl;



	public HotTableButton(String text, String type,HotLabelPanel hotPanel,BLservice bl) {
		super(text);

		
		this.field = text;
		this.type = type;
		this.hotPanel = hotPanel;
		this.bl = bl;
		
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setForeground(Color.white);
		this.setBackground(new Color(87, 89, 91));
		this.setBorder(BorderFactory.createLineBorder(new Color(122, 122,
				122), 2));
		this.setFocusPainted(false);

		this.setFont(new Font("微软雅黑", Font.CENTER_BASELINE,
			 20 ));
		
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
				switch(HotTableButton.this.type){
				case "T":
					
					ArrayList<Teamvo> teamList = HotTableButton.this.bl.getSeasonKingTeam(transferField(field), 5);
					Teamvo [] teams = new Teamvo[5];
					for(int i=0;i<5;i++){
						teams[i] = teamList.get(i);
					}
					((KingLabelPanel)HotTableButton.this.hotPanel).setTeams(teams);
					HotTableButton.this.hotPanel.repaint();
					break;
				case "P":
					ArrayList<Playervo> playerList1 = HotTableButton.this.bl.getSeasonKingPlayer(transferField(field), 5);
					Playervo [] players1 = new Playervo[5];
					for(int i=0;i<5;i++){
						players1[i] = playerList1.get(i);
					}
					((KingLabelPanel)HotTableButton.this.hotPanel).setPlayers(players1,transferField(field));
					HotTableButton.this.hotPanel.repaint();
					break;
				case "HP":	
					ArrayList<Playervo> playerList2 = HotTableButton.this.bl.getMostImprovedPlayer(transferField(field), 5);
					Playervo [] players2 = new Playervo[5];
					for(int i=0;i<5;i++){
						players2[i] = playerList2.get(i);
					}
					((ImprovedLabelPanel)HotTableButton.this.hotPanel).setPlayers(players2,transferField(field));
					HotTableButton.this.hotPanel.repaint();
					break;
				case "TP":
					Playervo [] players = ((KingLabelPanel)HotTableButton.this.hotPanel).getPlayers();
					
					ArrayList<Playervo> playerList = new ArrayList<Playervo>();
					for(int i=0;i<players.length;i++){
						playerList.add(players[i]);
					}
					playerList = getTeamSortedPlayer(playerList,transferField(field));
					for(int i=0;i<players.length;i++){
						players[i] = playerList.get(i);
					}
					
					((KingLabelPanel)HotTableButton.this.hotPanel).setPlayers(players,transferField(field));
					HotTableButton.this.hotPanel.repaint();
					break;
				}
			}
		});
		
	}
	public ArrayList<Playervo> getTeamSortedPlayer(ArrayList<Playervo> list,String field) {
		Comparator<Playervo> comparator  = new PlayerAveragePointsComp();
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
	
	private String transferField(String field){
		switch(field){
		case  "得分":
			return "point";
		case  "篮板": 
			return "rebound";
		case  "助攻":
			return "assist";
		case  "抢断":
			return "steal";
		case  "盖帽":
			return "blockShot";
		case  "三分%":
			return "three";
		case  "%":
			return "shot";
		case  "罚球%":
			return "penalty";
		case  "场均得分":
			return "point";
		case  "场均篮板":
			return "rebound";
		case  "场均助攻":
			return "assist";
		default:
			return "point";
		}
	}
}

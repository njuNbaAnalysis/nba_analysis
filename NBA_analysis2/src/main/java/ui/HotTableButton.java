package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import logic.BLService;
import logic.players.Player;
import logic.players.todayPlayer;
import logic.teams.Team;

public class HotTableButton extends JButton{
	private String type;
	private String field;
	private HotLabelPanel hotPanel;
	private BLService bl;



	public HotTableButton(String text, String type,HotLabelPanel hotPanel,BLService bl) {
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
					
					ArrayList<Team> teamList = HotTableButton.this.bl.getSeasonKingTeam(transferField(field), 5);
					Team [] teams = new Team[5];
					for(int i=0;i<5;i++){
						teams[i] = teamList.get(i);
					}
					((KingLabelPanel)HotTableButton.this.hotPanel).setTeams(teams);
					HotTableButton.this.hotPanel.repaint();
					break;
				case "P":
					ArrayList<Player> playerList1 = HotTableButton.this.bl.getSeasonKingPlayer(transferField(field), 5);
					Player [] players1 = new Player[5];
					for(int i=0;i<5;i++){
						players1[i] = playerList1.get(i);
					}
					((KingLabelPanel)HotTableButton.this.hotPanel).setPlayers(players1);
					HotTableButton.this.hotPanel.repaint();
					break;
				case "HP":	
					ArrayList<Player> playerList2 = HotTableButton.this.bl.getMostImprovedPlayer(transferField(field), 5);
					Player [] players2 = new Player[5];
					for(int i=0;i<5;i++){
						players2[i] = playerList2.get(i);
					}
					((ImprovedLabelPanel)HotTableButton.this.hotPanel).setPlayers(players2,transferField(field));
					HotTableButton.this.hotPanel.repaint();
					break;
				}
			}
		});
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

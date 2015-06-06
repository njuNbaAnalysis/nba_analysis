package ui.team;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import BLservice.BLservice;
import vo.Teamvo;

public class AllTeamPanel extends JPanel {

	ArrayList<Teamvo> teams;
	int width;
	int height;
	String season;
	boolean isPlayOff;
	private JPanel content;
	BLservice bl;
	private TeamBasicInfoLabel teamBasicInfoPanel;

	public void paintComponent(Graphics g2) {
		Graphics2D g = (Graphics2D) g2.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		/*
		 * g.setColor(new Color(30, 81, 140)); g.fillRect(0, 0, width,
		 * height/10); g.setColor(Color.WHITE); g.setFont(new Font("default",
		 * Font.BOLD, 50)); g.drawString("球队列表", 30, 70);
		 */

		/*
		 * for(int i=0;i<3;i++){ for(int j = 0 ; j<10;j++){
		 * g.drawImage(teams.get(i*10+j).getLogo(96, 80), 100+150*j, 200+300*i,
		 * this); } }
		 */

	}

	public AllTeamPanel(final int width, final int height, final BLservice bl,
			JPanel content, String season, boolean isPlayOff)
			throws RemoteException {
		this.width = width;
		this.height = height;
		this.content = content;
		this.bl = bl;
		this.season = season;
		this.isPlayOff = isPlayOff;
		this.setBounds(0, 0, width, height);
		this.setLayout(null);
		setBackground(Color.white);

		teams = bl.getAllTeams(season, false);

		JPanel teamsMapsPanel = new TeamsMapsPanel(1366, 768, this);
		this.add(teamsMapsPanel);
		teamsMapsPanel.setSize(1366, 768);
		teamsMapsPanel.setLocation((width - 1366) / 2, 250);
		teamsMapsPanel.setVisible(true);
		teamsMapsPanel.setOpaque(true);
		teamBasicInfoPanel = new TeamBasicInfoLabel(teams.get(0), 0, 0,
				Color.black);
		this.add(teamBasicInfoPanel);

		/*
		 * for(int i = 0 ; i < 3 ; i++ ){ for(int j = 0 ; j<10;j++){ final int
		 * row = i; final int colum = j; JButton each = new
		 * JButton(teams.get(i*10+j).getName()); each.setBounds(100+150*j,
		 * 300+300*i, 96, 30); each.setBackground(new Color(30,81,140));
		 * each.setFocusable(false); each.setForeground(Color.white);
		 * each.setBorderPainted(false); each.addMouseListener(new
		 * MouseAdapter() {
		 * 
		 * @Override public void mouseClicked(MouseEvent e) {
		 * 
		 * TeamInfoPanel m = new
		 * TeamInfoPanel(width,height*10/9,teams.get(row*10
		 * +colum),bl,AllTeamPanel.this.content); m.setBounds(0, 0, width,
		 * height*10/9); AllTeamPanel.this.content.removeAll();
		 * AllTeamPanel.this.content.add(m);
		 * AllTeamPanel.this.content.updateUI();
		 * 
		 * } }); this.add(each); } }
		 */

	}

	public void enterTeam(String name) {
		for (Teamvo each : teams) {
			if (each.getAbbreviation().equals(name)) {
				TeamInfoPanel m = new TeamInfoPanel(width, height * 10 / 9,
						each, bl, AllTeamPanel.this.content,
						AllTeamPanel.this.season, AllTeamPanel.this.isPlayOff);
				m.setBounds(0, 0, width, height * 10 / 9);
				AllTeamPanel.this.content.removeAll();
				AllTeamPanel.this.content.add(m);
				AllTeamPanel.this.content.updateUI();
			}

		}

	}

	public void updateTopLabe(String name) {
		for (Teamvo each : teams) {
			if (each.getAbbreviation().equals(name)) {
				AllTeamPanel.this.remove(teamBasicInfoPanel);
				teamBasicInfoPanel = new TeamBasicInfoLabel(each, width,
						height / 4, Color.black);
				teamBasicInfoPanel.setBounds(0, 0, width, height / 4);
				AllTeamPanel.this.add(teamBasicInfoPanel);
				AllTeamPanel.this.content.updateUI();
			}

		}
		this.add(teamBasicInfoPanel);
	}
}

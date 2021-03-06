package ui.player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.team.TeamInfoPanel;
import vo.Playervo;
import vo.Teamvo;
import BLservice.BLservice;



public class PlayerBasicInfoLabel extends JLabel {
	private Playervo player;
	private int width;
	private int height;
	private JPanel content;
	private BLservice bl;
	String season;
	boolean isPlayOff;

	public void paintComponent(Graphics g2) {
		Graphics2D g = (Graphics2D) g2.create();
		g.setColor(Color.white);
		g.fillRect(0, 0, 1800, 300);
		g.setColor(new Color(190, 157, 83, 200));
		g.fillRect(0, 120, 1800, 40);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(player.getPortrait(), 0, 0, this);
		g.setColor(new Color(190, 157, 83));
		g.setFont(new Font("default", Font.BOLD, 50));
		String num[] = player.getNumber().split(",");
		g.drawString(num[num.length-1] + "", 490, 60);
		g.setColor(new Color(218, 218, 218));
		g.drawLine(550, 20, 550, 80);
		g.setColor(new Color(68, 68, 68));
		g.setFont(new Font("default", Font.ROMAN_BASELINE, 25));
		
		
		g.drawString(player.getName(), 555, 45);
		
		
		g.setFont(new Font("default", Font.PLAIN, 20));
		g.drawString(player.getPosition().split("-")[0]+"-", 555, 80);
		
		
		
		g.setColor(new Color(68, 68, 68));
		
		
		g.setFont(new Font("default", Font.PLAIN, 15));
		g.drawString("场均得分", 450, 118);
		g.drawString("场均篮板", 550, 118);
		g.drawString("场均助攻", 650, 118);
		g.setColor(new Color(68, 68, 68));
		g.setFont(new Font("default", Font.BOLD, 25));
		DecimalFormat df = new DecimalFormat("#0.00");
		g.drawString(df.format(player.getAveragePoints()), 455, 148);
		g.drawString(df.format(player.getAverageRebounds()), 555,
				148);
		g.drawString(df.format(player.getAverageAssists()), 655,
				148);

	}
	
	private void setLabel(){
		JLabel teamName = new JLabel(player.getTeam());
		teamName.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				
			}

			public void mouseExited(MouseEvent e) {
				
			}

			
			public void mousePressed(MouseEvent e) {
				

            	Teamvo t = null;
				try {
					t = PlayerBasicInfoLabel.this.bl.getTeamByPlayerId(player.getPid(),season,isPlayOff);
					TeamInfoPanel m = new TeamInfoPanel(width,height*4,t,PlayerBasicInfoLabel.this.bl,PlayerBasicInfoLabel.this.content,season,isPlayOff);
	        		m.setBounds(0, 0, width, height*4);
	        		PlayerBasicInfoLabel.this.content.removeAll();
	        		PlayerBasicInfoLabel.this.content.add(m);
	        		PlayerBasicInfoLabel.this.content.updateUI();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		teamName.setForeground(new Color(30, 81, 140));
		teamName.setFont(new Font("default", Font.PLAIN, 20));
		teamName.setBounds(598, 48, 100, 50);
		teamName.setOpaque(false);
		this.add(teamName);
	}

	public PlayerBasicInfoLabel(Playervo player, int width, int height,JPanel content,BLservice bl,String season,boolean isPlayOff) {
		super();
		this.bl = bl;
		this.player = player;
		this.height = height;
		this.width = width;
		this.content = content;
		this.season =season;
		this.isPlayOff = isPlayOff;
		this.setSize(width, height);
		setLabel();

	}

}

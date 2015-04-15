package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import util.UIUtils;
import logic.BLService;
import logic.players.Player;
import logic.players.todayPlayer;

public class ImprovedLabelPanel extends HotLabelPanel {
	private String type;
	private int num = 5;

	private todayPlayer[] players;

	public ImprovedLabelPanel(String headName, String type,
			String[] columnName, todayPlayer[] players, int hotWidth, int hotHeight,
			BLService bl) {
		super(headName, columnName, hotWidth, hotHeight, bl);
		this.type = type;

		setTableHeadLabel();
		setButton(type);
		setHotTableContentLabel();
		setPlayers(players);

	}
	
	protected void setTableHeadLabel() {
		tableHeadLabel = new HotTabelHeadLabel(headName,hotWidth,hotHeight/2);
		tableHeadLabel.setBounds(0, 0, hotWidth, hotHeight / 12);
		this.add(tableHeadLabel);

	}
	
	protected void setButton(String type) {
		int size = columnName.length;
		btArray = new HotTableButton[size];
		for (int i = 0; i < size; i++) {
			btArray[i] = new HotTableButton(columnName[i],type,this,bl);
			btArray[i].setBounds(hotWidth * i / size, hotHeight / 12,
					hotWidth / size, hotHeight / 16);
			this.add(btArray[i]);
		}
	}

	/*public void setTableContent(String type) {
		if (type.equals("HP")) {
			Object[] o = bl.getAllPlayers().subList(0, num).toArray();
			Player[] p = new Player[num];
			for (int i = 0; i < num; i++) {
				p[i] = (Player) o[i];
			}
			
			setHotTableContentLabel();
			setPlayers(p);
		}

	}*/

	private void setHotTableContentLabel() {
		tableContentLabel = new HotTableContentLabel(players, hotWidth,
				hotHeight * 5 / 6);
		tableContentLabel.setBounds(0, hotHeight / 6, hotWidth,
				hotHeight * 5 / 6);
		this.add(tableContentLabel);

	}


	public void setPlayers(todayPlayer[] players) {
		((HotTableContentLabel) tableContentLabel).setPlayers(players);

	}

	private class HotTableContentLabel extends JLabel {
		private todayPlayer[] players;
		private int contentWidth;
		private int contentHeight;

		public HotTableContentLabel(todayPlayer[] players, int contentWidth,
				int contentHeight) {
			this.players = players;
			this.contentWidth = contentWidth;
			this.contentHeight = contentHeight;
		}

		public void setPlayers(todayPlayer[] players) {
			this.players = players;
			this.repaint();
		}

		public void paintComponent(Graphics g2) {
			// 背景
			Graphics2D g = (Graphics2D) g2.create();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(Color.white);
			g.fillRect(0, 0, contentWidth, contentHeight);
			// 头像
			BufferedImage portrait = UIUtils.resize(players[0].getPortrait(),
					contentWidth / 10, contentHeight * 3 / 10);
			g.drawImage(portrait, 0, 0, this);
			// 排名
			g.setColor(new Color(190, 157, 83));
			g.setFont(new Font("微软雅黑", Font.BOLD, 50));
			g.drawString(1 + "", contentWidth / 3, contentHeight * 1 / 5);
			// 姓名
			g.setColor(new Color(68, 68, 68));
			g.setFont(new Font("微软雅黑", Font.ROMAN_BASELINE, 25));
			g.drawString(players[0].getName(), contentWidth / 2,
					contentHeight * 1 / 10);

			// 位置，球队
			g.setColor(new Color(68, 68, 68));
			g.setFont(new Font("微软雅黑", Font.PLAIN, 20));
			g.drawString(players[0].getPosition() + "/" + players[0].getTeam(),
					contentWidth / 2, contentHeight * 1 / 5);

			// 最近五场/提升率
			g.setColor(new Color(68, 68, 68));
			g.setFont(new Font("微软雅黑", Font.PLAIN, 20));
			g.drawString("最近五场" + "/" + "提升率", contentWidth / 2,
					contentHeight * 3 / 10);
			// 数据、球队图标暂无

			g.setColor(new Color(158, 158, 158));
			g.fillRect(0, contentHeight * 2 / 5, contentWidth,
					contentHeight * 1 / 20);

			// 排名
			g.setColor(new Color(68, 68, 68));
			g.setFont(new Font("微软雅黑", Font.PLAIN, 15));
			g.drawString("排名", contentWidth / 13, contentHeight * 7 / 16);
			g.drawString("球队", contentWidth / 6, contentHeight * 7 / 16);
			g.drawString("最近五场/提升率", contentWidth * 7 / 12,
					contentHeight * 7 / 16);

			for (int i = 2; i <= num; i++) {
				int posh = contentHeight / 2 + contentHeight * (i - 2) / 8
						- contentHeight / 20;

				g.setColor(new Color(246, 246, 246));
				g.fillRect(0, contentHeight / 2 + contentHeight * (i - 2) / 8
						- contentHeight / 20, contentWidth / 6,
						contentHeight / 10);

				// 排名
				g.setColor(new Color(158, 158, 158));
				g.setFont(new Font("Oswald-Bold", Font.PLAIN, 20));
				g.drawString(i + "", contentWidth / 12, posh + contentHeight
						/ 16);
				// 头像
				BufferedImage image = UIUtils.resize(
						players[i - 1].getPortrait(), contentWidth / 25,
						contentHeight / 10);

				g.drawImage(image, contentWidth / 6, posh, this);
				// 名字
				g.setColor(Color.black);
				g.setFont(new Font("Oswald-Bold", Font.PLAIN, 15));
				g.drawString(players[i - 1].getName(), contentWidth * 9 / 40,
						posh + contentHeight / 30);

				// 球队
				String str = Integer.toString(players[i - 1].getNumber()) + " "
						+ players[i - 1].getPosition() + "/"
						+ players[i - 1].getTeam();
				g.drawString(str, contentWidth * 9 / 40, posh + contentHeight
						* 7 / 80);

				// 最近五场/提升率
				g.setColor(Color.black);
				g.setFont(new Font("Oswald-Bold", Font.PLAIN, 30));
				g.drawString("20.0", contentWidth * 7 / 12, contentHeight / 2
						+ contentHeight * (i - 2) / 8 + contentHeight / 40);

				g.setColor(new Color(57, 167, 229));
				g.setFont(new Font("Oswald-Bold", Font.PLAIN, 15));
				g.drawString("/     97.5%", contentWidth * 7 / 11,
						contentHeight / 2 + contentHeight * (i - 2) / 8
								+ contentHeight / 40);
			}

		}
	}

}
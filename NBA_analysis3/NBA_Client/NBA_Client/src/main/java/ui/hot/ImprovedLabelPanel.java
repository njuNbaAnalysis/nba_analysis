//package ui.hot;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
//import java.awt.image.BufferedImage;
//import java.text.DecimalFormat;
//
//import javax.swing.JLabel;
//
//import BLservice.BLservice;
//import util.UIUtils;
//import vo.Playervo;
//
//public class ImprovedLabelPanel extends HotLabelPanel {
//	private String type;
//	private String field;
//	private int num = 5;
//
//	private Playervo[] players;
//
//	public ImprovedLabelPanel(String headName, String type,
//			String[] columnName,Playervo[] players, int hotWidth, int hotHeight,
//			BLservice bl) {
//		super(headName, columnName, hotWidth, hotHeight, bl);
//		this.type = type;
//
//		setTableHeadLabel();
//		setButton(type);
//		setHotTableContentLabel();
//		setPlayers(players,"point");
//
//	}
//	
//	protected void setTableHeadLabel() {
//		tableHeadLabel = new HotTabelHeadLabel(headName,hotWidth,hotHeight/2);
//		tableHeadLabel.setBounds(0, 0, hotWidth, hotHeight / 12);
//		this.add(tableHeadLabel);
//
//	}
//	
//	protected void setButton(String type) {
//		int size = columnName.length;
//		btArray = new HotTableButton[size];
//		for (int i = 0; i < size; i++) {
//			btArray[i] = new HotTableButton(columnName[i],type,this,bl);
//			btArray[i].setBounds(hotWidth * i / size, hotHeight / 12,
//					hotWidth / size, hotHeight / 16);
//			this.add(btArray[i]);
//		}
//	}
//
//	private void setHotTableContentLabel() {
//		tableContentLabel = new HotTableContentLabel(players, hotWidth,
//				hotHeight * 5 / 6,"point");
//		tableContentLabel.setBounds(0, hotHeight / 6, hotWidth,
//				hotHeight * 5 / 6);
//		this.add(tableContentLabel);
//
//	}
//
//
//	public void setPlayers(Playervo[] players,String field) {
//		((HotTableContentLabel) tableContentLabel).setPlayers(players);
//		((HotTableContentLabel) tableContentLabel).setType(field);
//		this.field = field;
//
//	}
//
//	private class HotTableContentLabel extends JLabel {
//		private Playervo[] players;
//		private int contentWidth;
//		private int contentHeight;
//		private String field;
//		protected DecimalFormat df = new DecimalFormat("#0.00");
//
//		public HotTableContentLabel(Playervo[] players, int contentWidth,
//				int contentHeight,String field) {
//			this.players = players;
//			this.contentWidth = contentWidth;
//			this.contentHeight = contentHeight;
//			this.field = field;
//		}
//
//		public void setType(String field) {
//			this.field = field;
//			
//		}
//
//		public void setPlayers(Playervo[] players) {
//			this.players = players;
//			this.repaint();
//		}
//
//		public void paintComponent(Graphics g2) {
//			// 背景
//			Graphics2D g = (Graphics2D) g2.create();
//			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//					RenderingHints.VALUE_ANTIALIAS_ON);
//			g.setColor(Color.white);
//			g.fillRect(0, 0, contentWidth, contentHeight);
//			// 头像
//			
//			BufferedImage portrait = UIUtils.resize(players[0].getPortrait(),
//					contentWidth / 10, contentHeight * 3 / 10);
//			g.drawImage(portrait, 0, 0, this);
//			// 排名
//			g.setColor(new Color(190, 157, 83));
//			g.setFont(new Font("微软雅黑", Font.BOLD, 50));
//			g.drawString(1 + "", contentWidth / 3, contentHeight * 1 / 5);
//			// 姓名
//			g.setColor(new Color(68, 68, 68));
//			g.setFont(new Font("微软雅黑", Font.ROMAN_BASELINE, 25));
//			g.drawString(players[0].getName(), contentWidth / 2,
//					contentHeight * 1 / 10);
//
//			// 位置，球队
//			g.setColor(new Color(68, 68, 68));
//			g.setFont(new Font("微软雅黑", Font.PLAIN, 20));
//			g.drawString(players[0].getPosition() + "/" + players[0].getTeam(),
//					contentWidth / 2, contentHeight * 1 / 5);
//
//			// 最近五场/提升率
//			g.setColor(new Color(68, 68, 68));
//			g.setFont(new Font("微软雅黑", Font.PLAIN, 20));
//			g.drawString("最近五场" + "/" + "提升率", contentWidth / 2,
//					contentHeight * 3 / 10);
//			double result1 = 0;
//			double result2 = 0;
//			switch(field){
//			case "point":
//			case "场均得分":
//				result1 = players[0].FivePoints();
//				result2 = players[0].getUpgradeRate()[0];
//				break;
//			case "rebound":
//			case "场均篮板":
//				result1 = players[0].FiveRebounds();
//				result2 = players[0].getUpgradeRate()[1];
//				break;
//			case "assist":
//			case "场均助攻":
//				result1 = players[0].FiveAssists();
//				result2 = players[0].getUpgradeRate()[2];
//				break;
//			default:
//				System.out.println("Error in ImprovefLabelPanel.paintComponent()!!!"+field);	
//			}
//			// 最近五场/提升率
//						g.setColor(new Color(68, 68, 68));
//						g.setFont(new Font("微软雅黑", Font.PLAIN, 20));
//						g.drawString(Double.toString(result1) + "/" + (df.format(result2)), contentWidth / 2,
//								contentHeight * 4 / 10);
//			// 数据、球队图标暂无
//
//			g.setColor(new Color(158, 158, 158));
//			g.fillRect(0, contentHeight * 2 / 5, contentWidth,
//					contentHeight * 1 / 20);
//
//			// 排名
//			g.setColor(new Color(68, 68, 68));
//			g.setFont(new Font("微软雅黑", Font.PLAIN, 15));
//			g.drawString("排名", contentWidth / 13, contentHeight * 7 / 16);
//			g.drawString("球队", contentWidth / 6, contentHeight * 7 / 16);
//			g.drawString("最近五场/提升率", contentWidth * 7 / 12,
//					contentHeight * 7 / 16);
//
//			for (int i = 2; i <= num; i++) {
//				int posh = contentHeight / 2 + contentHeight * (i - 2) / 8
//						- contentHeight / 20;
//
//				g.setColor(new Color(246, 246, 246));
//				g.fillRect(0, contentHeight / 2 + contentHeight * (i - 2) / 8
//						- contentHeight / 20, contentWidth / 6,
//						contentHeight / 10);
//
//				// 排名
//				g.setColor(new Color(158, 158, 158));
//				g.setFont(new Font("Oswald-Bold", Font.PLAIN, 20));
//				g.drawString(i + "", contentWidth / 12, posh + contentHeight
//						/ 16);
//				// 头像
//				Player player = bl.getPlayerByName(players[i-1].getName());
//				BufferedImage image = UIUtils.resize(
//						player.getPortrait(), contentWidth / 25,
//						contentHeight / 10);
//
//				g.drawImage(image, contentWidth / 6, posh, this);
//				// 名字
//				g.setColor(Color.black);
//				g.setFont(new Font("Oswald-Bold", Font.PLAIN, 15));
//				g.drawString(players[i - 1].getName(), contentWidth * 9 / 40,
//						posh + contentHeight / 30);
//
//				// 球队
//				String str = Integer.toString(player.getNumber()) + " "
//						+ players[i - 1].getPosition() + "/"
//						+ players[i - 1].getTeam();
//				g.drawString(str, contentWidth * 9 / 40, posh + contentHeight
//						* 7 / 80);
//
//				// 最近五场/提升率
//				g.setColor(Color.black);
//				g.setFont(new Font("Oswald-Bold", Font.PLAIN, 30));
//				result1 = 0;
//				result2 = 0;
//				//System.out.println(field);
//				switch(field){
//				case "point":
//				case "场均得分":
//					result1 = players[i-1].FivePoints();
//					result2 = players[i-1].getUpgradeRate()[0];
//					break;
//				case "rebound":
//				case "场均篮板":
//					result1 = players[i-1].FiveRebounds();
//					result2 = players[i-1].getUpgradeRate()[1];
//					break;
//				case "assist":
//				case "场均助攻":
//					result1 = players[i-1].FiveAssists();
//					result2 = players[i-1].getUpgradeRate()[2];
//					break;
//				default:
//					System.out.println("Error in ImprovefLabelPanel.paintComponent()!!!"+field);	
//				}
//				g.drawString(Double.toString(result1), contentWidth * 7 / 12, contentHeight / 2
//						+ contentHeight * (i - 2) / 8 + contentHeight / 40);
//
//				g.setColor(new Color(57, 167, 229));
//				g.setFont(new Font("Oswald-Bold", Font.PLAIN, 15));
//				g.drawString((df.format(result2)), contentWidth * 7 / 11,
//						contentHeight / 2 + contentHeight * (i - 2) / 8
//								+ contentHeight / 40);
//			}
//
//		}
//	}
//
//}

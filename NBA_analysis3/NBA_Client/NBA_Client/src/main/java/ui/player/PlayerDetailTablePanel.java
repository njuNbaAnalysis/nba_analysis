package ui.player;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import BLservice.BLservice;
import ui.statistics.BaseJTable;
import vo.Playervo;
import vo.RecordOfPlayervo;

public class PlayerDetailTablePanel extends JPanel {
	private Playervo player;
	private int width;
	private int height;
	private SeasonJTable seasonTable;
	private LatestJTable latestTable;
	private JScrollPane seasonJspane;
	private JScrollPane latestJspane;
	private BLservice bl;
	private String season;
	private DecimalFormat df = new DecimalFormat("#0.0");

	public PlayerDetailTablePanel(Playervo player, int width, int height,BLservice bl,String season) throws RemoteException {
		this.player = player;
		this.width = width;
		this.height = height;
		this.bl = bl;
		this.season = season;
		this.setLayout(null);

		setTable();
	}

	private void setTable() throws RemoteException {
		seasonJspane = new JScrollPane();
		seasonJspane.setBounds(0, height / 10, width, height / 4);
		seasonTable = new SeasonJTable(bl.getPlayerById(player.getPid(), season, false),season);
		seasonJspane.setViewportView(seasonTable);
		this.add(seasonJspane);
		//seasonTable.refreshElement();
		
		
		//迭代三中没有近五场数据
		latestJspane = new JScrollPane();
		latestJspane.setBounds(0, height * 1 / 2, width, height * 35 / 80);
		latestTable = new LatestJTable(bl.getRecordOfPlayerById(player.getPid()));
		latestJspane.setViewportView(latestTable);

		this.add(latestJspane);
		latestTable.refreshElement();

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(30, 81, 140));
		g.fillRect(0, 0, width, height / 10);
		g.setColor(Color.white);
		g.setFont(new Font("微软雅黑", Font.ROMAN_BASELINE, 20));
		g.drawString("赛季数据", width / 20, height / 15);

		g.setColor(new Color(246, 246, 246));
		g.fillRect(0, 7 * height / 20, width, height / 8);

		g.setColor(new Color(30, 81, 140));
		g.fillRect(0, 2 * height / 5, width, height / 10);
		g.setColor(Color.white);
		g.setFont(new Font("微软雅黑", Font.ROMAN_BASELINE, 20));
		g.drawString("最近5场比赛", width / 20, height * 7 / 15);

	}

	private abstract class DetailJTable extends BaseJTable {

		public DetailJTable() {
			super();
			this.setShowGrid(false);
			// this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			this.setFont(new Font("微软雅黑", Font.ROMAN_BASELINE, 15));
			adjustHeader();

		}

		protected abstract void refreshElement();

		protected void updateRowHeights() {
			try {

				for (int row = 0; row < this.getRowCount(); row++) {
					int rowHeight = this.getRowHeight();

					for (int column = 0; column < this.getColumnCount(); column++) {
						Component comp = this.prepareRenderer(
								this.getCellRenderer(row, column), row, column);
						rowHeight = Math.max(rowHeight,
								comp.getPreferredSize().height);
						rowHeight = Math.max(height / 23, rowHeight);
					}

					this.setRowHeight(row, rowHeight);
				}
			} catch (ClassCastException e) {
			}
		}

	}

	private class SeasonJTable extends DetailJTable {
		Playervo player;
		String season;
		private String[] columnName = { "年度", "球队", "场数", "先发", "分钟", "%",
				"三分%", "进攻", "防守", "篮板", "助攻", "抢断", "盖帽", "失误", "犯规", "得分" };

		public SeasonJTable(Playervo player,String season) {
			super();
			this.player = player;
			this.season = season;
			this.setModel(new DefaultTableModel(null, columnName));
			refreshElement();
		}

		protected void paintRow() {
			TableColumnModel tcm = this.getColumnModel();
			for (int i = 0, n = tcm.getColumnCount(); i < n; i++) {
				TableColumn tc = tcm.getColumn(i);
				tc.setCellRenderer(new RowRenderer());
			}
		}

		protected class RowRenderer extends DefaultTableCellRenderer {

			public Component getTableCellRendererComponent(JTable t,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {

				setHorizontalAlignment(SwingConstants.CENTER);

				// 设置奇偶行的背景色
				if (row == 0 || row == 2) {
					setBackground(new Color(218, 218, 218));
				} else if (row % 2 == 0) {
					setBackground(new Color(255, 255, 255));
				} else {
					setBackground(new Color(246, 246, 246));
				}

				setSize(width / 16, height / 10);

				return super.getTableCellRendererComponent(t, value,
						isSelected, hasFocus, row, column);

			}
		}

		@Override
		public void refreshElement() {
			DefaultTableModel model = (DefaultTableModel) this.getModel();
			//String[] data = getSeasonData(player);
			String[] row = null;
			for (int i = 0; i < 4; i++) {
				row = new String[16];
				if (i == 0) {
					row[0] = "赛季平均";

				} else if (i == 2) {
					row[0] = "赛季总计";

				} else if (i == 1) {
					row = getSeasonAverageData(player);

				} else {
					row = getSeasonTotalData(player);
				}
				model.addRow(row);
			}

			this.setModel(model);
			this.updateRowHeights();
			this.paintRow();
			this.repaint();

		}

		//没有分赛季展现
		private String[] getSeasonAverageData(Playervo player) {
			String[] result = new String[16];
			result[0] = season;
			result[1] = player.getTeam();
			result[2] = player.getGamePlayed()+"";
			
			result[3] = player.getGameStarted()+"";
			result[4] = df.format(player.getAverageMinutes());
			result[5] = df.format(player.getFieldGoalsPercentage()*100);		
			result[6] = df.format(player.getThreePointersPercentage()*100);

			result[7] = df.format(player.getAverageOffenseRebounds());
			result[8] = df.format(player.getAverageDefenseRebounds());
			result[9] = df.format(player.getAverageRebounds());
			result[10] = df.format(player.getAverageAssists());
			
			result[11] = df.format(player.getAverageSteals());
			result[12] = df.format(player.getAverageBlockShots());
			result[13] = df.format(player.getAverageFouls());
			result[14] = df.format(player.getAverageTurnOver());
			
			result[15] = df.format(player.getAveragePoints());
			return result;
		}
		
		//没有分赛季展现
		private String[] getSeasonTotalData(Playervo player) {
			String[] result = new String[16];
			result[0] = season;
			result[1] = player.getTeam();
			result[2] = player.getGamePlayed()+"";
			
			result[3] = player.getGameStarted()+"";
			result[4] = df.format(player.getMinutes()/60);
			result[5] = df.format(player.getFieldGoalsPercentage()*100);		
			result[6] = df.format(player.getThreePointersPercentage()*100);

			result[7] = player.getOffenseRebounds()+"";
			result[8] = player.getDefenseRebounds()+"";
			result[9] = player.getRebounds()+"";
			result[10] = player.getAssists()+"";
			
			result[11] = player.getSteals()+"";
			result[12] = player.getBlockShots()+"";
			result[13] = player.getFouls()+"";
			result[14] = player.getTurnOver()+"";
			
			result[15] = player.getPoints()+"";
			return result;
		}
	}

	private class LatestJTable extends DetailJTable {
		protected DecimalFormat df = new DecimalFormat("#0");
		private String[] columnName = { "日期", "对手", "得分", "篮板",
				"助攻",  "抢断", "盖帽" };
		
		private ArrayList<RecordOfPlayervo> arrayListOfPlayerData;
		public LatestJTable(ArrayList<RecordOfPlayervo> arrayList) {
			this.setModel(new DefaultTableModel(null, columnName));
			arrayListOfPlayerData = arrayList;
			System.out.println("最近五场："+arrayListOfPlayerData.size());
			refreshElement();
		}

		protected void paintRow() {
			TableColumnModel tcm = this.getColumnModel();
			for (int i = 0, n = tcm.getColumnCount(); i < n; i++) {
				TableColumn tc = tcm.getColumn(i);
				tc.setCellRenderer(new RowRenderer());
			}
		}

		protected void updateRowHeights() {
			try {

				for (int row = 0; row < this.getRowCount(); row++) {
					int rowHeight = this.getRowHeight();

					for (int column = 0; column < this.getColumnCount(); column++) {
						Component comp = this.prepareRenderer(
								this.getCellRenderer(row, column), row, column);
						rowHeight = Math.max(rowHeight,
								comp.getPreferredSize().height);
						rowHeight = Math.max(height / 24, rowHeight);
					}

					this.setRowHeight(row, rowHeight);
				}
			} catch (ClassCastException e) {
			}
		}

		protected class RowRenderer extends DefaultTableCellRenderer {
			

			public Component getTableCellRendererComponent(JTable t,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {

				setHorizontalAlignment(SwingConstants.CENTER);

				// 设置奇偶行的背景色
				if (row == 5 || row == 7) {
					setBackground(new Color(218, 218, 218));
				} else if (row % 2 == 0) {
					setBackground(new Color(255, 255, 255));
				} else {
					setBackground(new Color(246, 246, 246));
				}

				setSize(width / 16, height / 10);

				return super.getTableCellRendererComponent(t, value,
						isSelected, hasFocus, row, column);

			}
		}

		@Override
		protected void refreshElement() {
			DefaultTableModel model = (DefaultTableModel) this.getModel();
			
			String[] row = null;
			for (int i = 0; i < 8; i++) {
				row = new String[22];
				if (i == 5) {
					row[0] = "平均";

				} else if (i == 7) {
					row[0] = "总计";

				} else if(i<=4){
					row = getSeasonData(i);
				}else{
					row = getAverageData();
				}
				model.addRow(row);
			}

			this.setModel(model);
			this.updateRowHeights();
			this.paintRow();
			this.repaint();

		}

		private String[] getSeasonData(int numberOfMatches) {
			String[] result = new String[7];
			result[0] = arrayListOfPlayerData.get(numberOfMatches).getDate();
			result[1] = arrayListOfPlayerData.get(numberOfMatches).getAway_Team();
			result[2] = df.format(arrayListOfPlayerData.get(numberOfMatches).getPoints());
			result[3] = df.format(arrayListOfPlayerData.get(numberOfMatches).getRebounds());
			result[4] = df.format(arrayListOfPlayerData.get(numberOfMatches).getAssists());
			result[5] = df.format(arrayListOfPlayerData.get(numberOfMatches).getSteals());
			result[6] = df.format(arrayListOfPlayerData.get(numberOfMatches).getBlocks());
			return result;
		}
		private String[] getAverageData() {
			String[] result = new String[7];
			result[0] = "";
			result[1] = "";
			double points = 0;
			double rebounds = 0;
			double assists = 0;
			double steal = 0;
			double block = 0;
			for(int i=0;i<arrayListOfPlayerData.size();i++){
				points+=arrayListOfPlayerData.get(i).getPoints();
				rebounds+=arrayListOfPlayerData.get(i).getRebounds();
				assists+=arrayListOfPlayerData.get(i).getAssists();
				steal+=arrayListOfPlayerData.get(i).getSteals();
				block+=arrayListOfPlayerData.get(i).getBlocks();
			}
			result[2] = Double.toString(points/5.0);
			result[3] = Double.toString(rebounds/5.0);
			result[4] = Double.toString(assists/5.0);
			result[5] = Double.toString(steal/5.0);
			result[6] = Double.toString(block/5.0);
			return result;
		}
	}

}

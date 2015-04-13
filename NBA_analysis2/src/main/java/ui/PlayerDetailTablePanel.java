package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
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

import logic.players.Player;

public class PlayerDetailTablePanel extends JPanel {
	private Player player;
	private int width;
	private int height;
	private SeasonJTable seasonTable;
	private LatestJTable latestTable;
	private JScrollPane seasonJspane;
	private JScrollPane latestJspane;

	public PlayerDetailTablePanel(Player player, int width, int height) {
		this.player = player;
		this.width = width;
		this.height = height;
		this.setLayout(null);

		setTable();
	}

	private void setTable() {
		seasonJspane = new JScrollPane();
		seasonJspane.setBounds(0, height / 10, width, height / 4);
		seasonTable = new SeasonJTable();
		seasonJspane.setViewportView(seasonTable);
		this.add(seasonJspane);
		seasonTable.refreshElement();

		latestJspane = new JScrollPane();
		latestJspane.setBounds(0, height * 1 / 2, width, height * 35 / 80);
		latestTable = new LatestJTable();
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
			refreshElement();

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
		private String[] columnName = { "年度", "球队", "场数", "先发", "分钟", "%",
				"三分%", "进攻", "防守", "篮板", "助攻", "抢断", "盖帽", "失误", "犯规", "得分" };

		public SeasonJTable() {
			super();
			this.setModel(new DefaultTableModel(null, columnName));
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
			String[] data = getSeasonData();
			String[] row = null;
			for (int i = 0; i < 4; i++) {
				row = new String[16];
				if (i == 0) {
					row[0] = "赛季平均";

				} else if (i == 2) {
					row[0] = "赛季总计";

				} else {
					row = getSeasonData();

				}
				model.addRow(row);
			}

			this.setModel(model);
			this.updateRowHeights();
			this.paintRow();
			this.repaint();

		}

		// 这个方法没有实现
		private String[] getSeasonData() {
			String[] result = new String[16];
			for (int i = 0; i < 16; i++) {
				result[i] = Integer.toString(i);
			}
			return result;
		}

	}

	private class LatestJTable extends DetailJTable {
		private String[] columnName = { "日期", "对手", "分钟", "%", "命中", "出手",
				"三分%", "三分命中", "三分出手", "罚球%", "罚球命中", "罚球出手", "进攻", "防守", "篮板",
				"助攻", "犯规", "抢断", "盖帽", "失误", "盖帽", "得分" };

		public LatestJTable() {
			this.setModel(new DefaultTableModel(null, columnName));
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
			String[] data = getSeasonData();
			String[] row = null;
			for (int i = 0; i < 8; i++) {
				row = new String[22];
				if (i == 5) {
					row[0] = "平均";

				} else if (i == 7) {
					row[0] = "总计";

				} else {
					row = data;
				}
				model.addRow(row);
			}

			this.setModel(model);
			this.updateRowHeights();
			this.paintRow();
			this.repaint();

		}

		// 这个方法没有实现
		private String[] getSeasonData() {
			String[] result = new String[22];
			for (int i = 0; i < 22; i++) {
				result[i] = Integer.toString(i);
			}
			return result;
		}
	}

}

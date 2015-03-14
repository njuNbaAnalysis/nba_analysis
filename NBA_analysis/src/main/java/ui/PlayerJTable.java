package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import logic.players.Player;

public class PlayerJTable extends JTable {
	private DecimalFormat df = new DecimalFormat("#0.0");
	private ArrayList<Image> portraitsList;
	// 设置头像大小
	private static int portraitWidth = 70;
	private static int portraitHeigft = 60;

	public PlayerJTable() {
		super();
		this.portraitsList = new ArrayList<Image>();
		this.setShowGrid(false);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		

		// 获得表头
		JTableHeader tableH = null;

		tableH = this.getTableHeader();
		// 设置表头的背景色
		tableH.setBackground(new Color(158, 158, 158));
		// 设置表头的文字颜色
		tableH.setForeground(new Color(255, 255, 255));
		// this.refresh();
		Dimension size = tableH.getPreferredSize();
		size.height = 40;
		tableH.setPreferredSize(size);
		tableH.setReorderingAllowed(false);
		
		this.setTableHeaderColor(new Color(158,158,158));
	}

	public void setImageList(ArrayList<Image> portraitsList) {
		this.portraitsList = portraitsList;
	}

	public void refresh(ArrayList<Player> list, boolean selected) {
		DefaultTableModel tableModel = (DefaultTableModel) this.getModel();
		tableModel.setRowCount(0);// 清除原有行
		portraitsList = new ArrayList<Image>();

		for (int i = 0; i < list.size(); i++) {
			String[] s = null;
			if (selected) {
				s = getAverageDataRow(list.get(i), i);
			} else {
				s = getTotalDataRow(list.get(i), i);
			}
			// 添加数据到表格

			tableModel.addRow(s);
			portraitsList.add(list.get(i).getPortrait());

		}

		// 更新表格
		this.setModel(tableModel);
		this.paintRow();
		this.updateRowHeights();
		this.resizeColumnWidth();
		this.validate();
		this.repaint();

	}

	private String[] getAverageDataRow(Player p, int i) {

		String[] s = new String[30];
		s[0] = (i+1) + "";
		s[1] = p.getName() + "";
		s[2] = p.getTeam() + "";
		s[3] = p.getGamePlayed() + "";
		s[4] = p.getGameStarted() + "";
		s[5] = df.format(p.getAverageRebounds()) + "";
		s[6] = df.format(p.getAverageAssists()) + "";
		s[7] = df.format(p.getAverageMinutes()) + "";
		s[8] = df.format(p.getAverageEfficiency()) + "";
		s[9] = df.format(p.getAverageGmSc()) + "";
		s[10] = df.format(p.getFieldGoalsPercentage()*100) + "";

		s[11] = df.format(p.getThreePointersPercentage()*100) + "";
		s[12] = df.format(p.getFreeThrowsPercentage()*100) + "";
		s[13] = df.format(p.getAverageOffenseRebounds()) + "";
		s[14] = df.format(p.getAverageDefenseRebounds()) + "";
		s[15] = df.format(p.getAverageSteals()) + "";
		s[16] = df.format(p.getAverageBlockShots()) + "";
		s[17] = df.format(p.getAverageTurnOver()) + "";
		s[18] = df.format(p.getAverageFouls()) + "";
		s[19] = df.format(p.getAveragePoints()) + "";

		s[20] = df.format(p.getTrueShootingPercentage()*100) + "";
		s[21] = df.format(p.getShootingEfficiency()*100) + "";
		s[22] = df.format(p.getReboundsPercentage()*100) + "";
		s[23] = df.format(p.getOffenseReboundsPercentage()*100) + "";
		s[24] = df.format(p.getDefenseReboundsPercentage()*100) + "";
		s[25] = df.format(p.getAssistsPercentage()*100) + "";
		s[26] = df.format(p.getStealsPercentage()*100) + "";
		s[27] = df.format(p.getBlockShotsPercentage()*100) + "";
		s[28] = df.format(p.getTurnOverPercentage()*100) + "";
		s[29] = df.format(p.getUsage()*100) + "";
		return s;
	}

	private String[] getTotalDataRow(Player p, int i) {
		String[] s = new String[18];
		s[0] = (i+1) + "";
		s[1] = p.getName() + "";
		s[2] = p.getTeam() + "";
		s[3] = p.getGamePlayed() + "";
		s[4] = p.getGameStarted() + "";
		s[5] = p.getRebounds() + "";
		s[6] = p.getAssists() + "";
		s[7] = p.getMinutes() + "";
		s[8] = df.format(p.getFieldGoalsPercentage()*100) + "";
		s[9] = df.format(p.getThreePointersPercentage()*100) + "";
		s[10] = df.format(p.getFreeThrowsPercentage()*100) + "";
		s[11] = p.getOffenseRebounds() + "";
		s[12] = p.getDefenseRebounds() + "";
		s[13] = p.getSteals() + "";
		s[14] = p.getRebounds() + "";
		s[15] = p.getTurnOver() + "";
		s[16] = p.getFouls() + "";
		s[17] = p.getPoints() + "";
		return s;

	}

	// 重写行
	public void paintRow() {
		TableColumnModel tcm = this.getColumnModel();
		for (int i = 0, n = tcm.getColumnCount(); i < n; i++) {
			TableColumn tc = tcm.getColumn(i);
			tc.setCellRenderer(new RowRenderer());
		}
	}

	// 自动调整行高
	private void updateRowHeights() {
		try {

			for (int row = 0; row < this.getRowCount(); row++) {
				int rowHeight = this.getRowHeight();

				for (int column = 0; column < this.getColumnCount(); column++) {
					Component comp = this.prepareRenderer(
							this.getCellRenderer(row, column), row, column);
					rowHeight = Math.max(rowHeight,
							comp.getPreferredSize().height);
				}

				this.setRowHeight(row, rowHeight);
			}
		} catch (ClassCastException e) {
		}
	}

	// 自动调整列宽
	public void resizeColumnWidth() {

		for (int column = 0; column < this.getColumnCount(); column++) {
			int width = 100; // Min width
			for (int row = 0; row < this.getRowCount(); row++) {
				TableCellRenderer renderer = this.getCellRenderer(row, column);
				Component comp = this.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width, width);
			}
			columnModel.getColumn(column).setPreferredWidth(width);
		}
	}

	// 重写行方法(paintRow())具体对应的类
	private class RowRenderer extends DefaultTableCellRenderer {

		public Component getTableCellRendererComponent(JTable t, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {

			// 设置内容居中
			if(column==1){
				setHorizontalAlignment(SwingConstants.LEFT);
			}else{
				setHorizontalAlignment(SwingConstants.CENTER);
			}
			

			// 设置奇偶行的背景色，可在此根据需要进行修改
			if (row % 2 == 0)
				setBackground(new Color(255, 255, 255));
			else
				setBackground(new Color(246, 246, 246));

			// 设置图片
			if (column == 1) {

				ImageIcon icon = new ImageIcon(resize(portraitsList.get(row),
						portraitWidth, portraitHeigft));
				setIcon(icon);

			}

			return super.getTableCellRendererComponent(t, value, isSelected,
					hasFocus, row, column);

		}

		public BufferedImage resize(Image image, int width, int height) {
			/*if (image instanceof BufferedImage) {
				return (BufferedImage) image;
			}*/

			BufferedImage bi = new BufferedImage(width, height,
					BufferedImage.TRANSLUCENT);
			Graphics2D g2d = (Graphics2D) bi.createGraphics();
			g2d.addRenderingHints(new RenderingHints(
					RenderingHints.KEY_RENDERING,
					RenderingHints.VALUE_RENDER_QUALITY));
			g2d.drawImage(image, 0, 0, width, height, null);
			g2d.dispose();
			return bi;
		}

	}

	public boolean isCellEditable(int row, int column) {
		return false;
	}

	public void setTableHeaderColor(Color c) {

		TableColumn column;
		for (int columnIndex = 0; columnIndex < this.getColumnCount(); columnIndex++) {
			column = this.getTableHeader().getColumnModel()
					.getColumn(columnIndex);
			DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
			cellRenderer.setBackground(c);
			column.setHeaderRenderer(cellRenderer);
		}

	}

}

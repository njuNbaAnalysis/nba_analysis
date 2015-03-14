package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

public class PlayerJTable extends JTable {
	private ArrayList<File> portraitsList;
	// 设置头像大小
	private static int portraitWidth = 70;
	private static int portraitHeigft = 60;

	public PlayerJTable() {
		super();

		this.setShowGrid(false);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

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
		this.setFont(new Font("微软雅黑", Font.PLAIN, 16));
	
	}

	public void setImageList(ArrayList<File> portraitsList) {
		this.portraitsList = portraitsList;
	}

	public void refresh(Object[] o) {
		DefaultTableModel tableModel = (DefaultTableModel) this.getModel();
		tableModel.setRowCount(0);// 清除原有行
		Object[] s = o;

		for (int i = 0; i < o.length; i++) {
			// 添加数据到表格
			s[0] = i + "";
			s[1] = i + "";
			s[2] = i + "";
			s[3] = i + "";

			tableModel.addRow(s);

		}

		// 更新表格
		this.setModel(tableModel);
		this.paintRow();
		this.updateRowHeights();
		this.resizeColumnWidth();
		this.validate();
		this.repaint();

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
			setHorizontalAlignment(SwingConstants.CENTER);

			// 设置奇偶行的背景色，可在此根据需要进行修改
			if (row % 2 == 0)
				setBackground(new Color(255, 255, 255));
			else
				setBackground(new Color(246, 246, 246));

			// 设置图片
			if (column == 1) {
				BufferedImage bi;
				try {
					bi = ImageIO.read(portraitsList.get(row));
					ImageIcon icon = new ImageIcon(resize(bi, portraitWidth,
							portraitHeigft));
					setIcon(icon);
					// setText("String");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			return super.getTableCellRendererComponent(t, value, isSelected,
					hasFocus, row, column);

		}

		public  BufferedImage resize(BufferedImage image, int width, int height) {
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

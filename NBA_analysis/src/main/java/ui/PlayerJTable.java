package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class PlayerJTable extends JTable {
	private ArrayList<File> portraitsList;
	//设置头像大小
	private static int portraitWidth = 70;
	private static int portraitHeigft = 60;

	public PlayerJTable() {
		super();
		// this.portraitsList = portraitsList;
		// this.setDefaultRenderer(Object.class, simpleTableRender);
		// t.getTableHeader().setDefaultRenderer(headerRenderer);
		// 设置单元格边框
		this.setShowGrid(false);

		//this.setShowHorizontalLines(false);
		//this.setShowVerticalLines(false);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		// 获得表头
		JTableHeader tableH = null;

		tableH = this.getTableHeader();
		// 设置表头的背景色
		tableH.setBackground(new Color(158, 158, 158));
		// 设置表头的文字颜色
		tableH.setForeground(new Color(255, 255, 255));
		// this.refresh();

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
			s[0] = "a";
			// JLabel a = new JLabel(new
			// ImageIcon("C:\\Users\\zgw\\Desktop\\CSEIII\\CSEData\\players\\portrait\\Aaron Brooks.png"));
			// a.setText("brooks");
			s[1] = "b";
			s[2] = "c";
			s[3] = "d";

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

	public void resizeColumnWidth() {
//		TableCellRenderer rend = yourTable.getTableHeader().getDefaultRenderer();
//		TableColumnModel tcm = yourTable.getColumnModel();
//		for (int j=0; j < tcm.getColumnCount(); j+=1) {
//		TableColumn tc = tcm.getColumn(j);
//		TableCellRenderer rendCol = tc.getHeaderRenderer(); // likely null
//		if (rendCol == null) rendCol = rend;
//		Component c = rendCol.getTableCellRendererComponent(yourTable, tc.getHeaderValue(), false, false, 0, j); 
//		tc.setPreferredWidth(c.getPreferredSize().width);
//		}
		for (int column = 0; column < this.getColumnCount(); column++) {
			int width = 50; // Min width
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

		public BufferedImage resize(BufferedImage image, int width, int height) {
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

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 1200, 800);
		frame.setVisible(true);

		/* PlayerJTable jTable1 = new PlayerJTable(list); */
		/*
		 * jTable1.setModel(new javax.swing.table.DefaultTableModel( new
		 * Object[][] { { null, null, null, null }, }, new String[] { "单据类型",
		 * "单据ID", "操作人员", "单据制定时间" }));
		 */
		/*
		 * String [] a = new String [4]; jTable1.refresh(a); JScrollPane
		 * ScrollPane1 = new JScrollPane();
		 * ScrollPane1.setViewportView(jTable1);
		 */

		// ScrollPane1.setBounds(100, 100, 800, 600);
		// frame.getContentPane().add(ScrollPane1);
		// ScrollPane1.repaint();
		// frame.repaint();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}

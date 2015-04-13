package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;





import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import ui.StatJTable.RowRenderer;
import logic.BLService;
import logic.matches.Match;

public class MatchTablePanel extends JPanel{
	protected Match match;
	protected MatchJTable matchTable;
	private int width;
	private int height;
	
	public MatchTablePanel(int width,int height,Match match) {
		this.width = width;
		this.height = height;
		this.match = match;
		this.setLayout(null);
		
		MatchJTable firstTeamRecord = new MatchJTable();
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height/20);
		//第一个球队姓名
		g.setColor(Color.BLACK);
		g.setFont(new Font("微软雅黑", Font.PLAIN, height/20));
		g.drawString(match.getTeams()[0] , width,
				height/20);
	}
	private class MatchJTable extends BaseJTable{
		public MatchJTable(){
			this.setShowGrid(false);
			this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			this.setFont(new Font("微软雅黑", Font.PLAIN, 16));
			this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		}
		protected void paintRow() {
			TableColumnModel tcm = this.getColumnModel();
			for (int i = 0, n = tcm.getColumnCount(); i < n; i++) {
				TableColumn tc = tcm.getColumn(i);
				tc.setCellRenderer(new RowRenderer());
			}
		}

		/*public void setImageList(ArrayList<Image> portraitsList) {
			this.imageList = portraitsList;
		}*/

		// 重写行方法(paintRow())具体对应的类
		protected class RowRenderer extends DefaultTableCellRenderer {

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

					//ImageIcon icon = new ImageIcon(resize(imageList.get(row),
					//		portraitWidth, portraitHeight));
					//setIcon(icon);

				}

				return super.getTableCellRendererComponent(t, value, isSelected,
						hasFocus, row, column);

			}

			public BufferedImage resize(Image image, int width, int height) {

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
	}
}

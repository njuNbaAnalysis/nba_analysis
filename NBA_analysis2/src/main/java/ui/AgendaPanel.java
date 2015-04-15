package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import logic.BLController;
import logic.BLService;
import logic.players.Player;
import logic.teams.MatchSimpleInfo;
import logic.teams.Team;
import util.UIUtils;

public class AgendaPanel extends JPanel{
	private int width;
	private int height;
	private ArrayList<MatchSimpleInfo> matches;
	private JScrollPane js;

	AgendaPanel(int width, int height, ArrayList<MatchSimpleInfo> matches) {
		this.width = width;
		this.height = height;
		this.matches = matches;
		this.setLayout(null);
		setTabelPanel();

	}

	private void setTabelPanel() {
		AgendaTable table = new AgendaTable(matches);
		js = new JScrollPane(table);
		js.setBounds(0, height * 1 / 20, width, height * 19 / 20);
		this.add(js);
	}

	

	public void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		Graphics2D g = (Graphics2D) g2.create();
		g.setColor(new Color(30, 81, 140));
		g.fillRect(0, 0, width, height / 20);

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.white);
		g.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, height / 30));
		g.drawString("球队赛程", width / 20, height / 30);
	}


	private class AgendaTable extends BaseJTable {
		
		private ArrayList<Image> imageList;
		private String[] agendaColumnName = { "日期", "对手", "结果", "比分", "比赛链接"};

		// type :0表示信息，1表示数据
		public AgendaTable(ArrayList<MatchSimpleInfo> matchList) {
			
			this.setShowGrid(false);
			this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			this.setFont(new Font("微软雅黑", Font.PLAIN, 16));
			this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			adjustHeader();
			this.setTableHeaderColor(new Color(158,158,158));

			
			
			DefaultTableModel model = new DefaultTableModel(null, agendaColumnName);
			int size = matchList.size();
			System.out.println("球员列表"+size);
			//imageList = new ArrayList<Image>();
			
			for(int i=0;i<12;i++){
				ArrayList<MatchSimpleInfo> subList = new ArrayList<MatchSimpleInfo>();
				for(MatchSimpleInfo info:matchList){
					if(info.getDate().getMonth()==i){
						subList.add(info);
					}
				}
				
				if(subList.size()!=0){
					String[] s = new String[4];
					s[0] = (i+1)+"月";
					model.addRow(s);
					//subList.sort();
					for(MatchSimpleInfo info:subList){
						s = getInfoRow(info);
						model.addRow(s);
					}
				}
				
				
				
				
			}
			
			
			this.setModel(model);
			this.resizeColumnWidth();	
			this.paintRow();
				
			this.updateRowHeights();
			this.repaint();
		}

		private String[] getInfoRow(MatchSimpleInfo info) {
			String[] s = new String[5];
			s[0] = (info.getDate().getMonth()+1)+"月"+(info.getDate().getDay()+1)+"日";
			if(info.isAtHome()){
				s[1] = "vs "+info.getNameOfRival();
			}else{
				s[1] = "@ "+info.getNameOfRival();
			}
			if(info.isWin()){
				s[2] = "是";
			}else{
				s[2] = "否";
			}

			s[3] = info.getPoints()[0]+"-"+info.getPoints()[1];
			s[4] = "技术统计";

			return s;
		}

		

		protected void paintRow() {
			TableColumnModel tcm = this.getColumnModel();
			for (int i = 0, n = tcm.getColumnCount(); i < n; i++) {
				TableColumn tc = tcm.getColumn(i);
				tc.setCellRenderer(new RowRenderer());
			}
		}

		// 重写行方法(paintRow())具体对应的类
		protected class RowRenderer extends DefaultTableCellRenderer {
			public Component getTableCellRendererComponent(JTable t,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {

				// 设置内容居中
				
				setHorizontalAlignment(SwingConstants.CENTER);
				

				// 设置奇偶行的背景色，可在此根据需要进行修改
				if (row % 2 == 0)
					setBackground(new Color(255, 255, 255));
				else
					setBackground(new Color(246, 246, 246));


				return super.getTableCellRendererComponent(t, value,
						isSelected, hasFocus, row, column);

			}
		}

	}

	public static void main(String[] args) {
		BLService bl = BLController.getInstance();
		bl.init();
		while(bl.getProgress()<9){
			System.out.println(bl.getProgress());
		}
		Team t = bl.getAllTeams().get(0);
		ArrayList<MatchSimpleInfo> matchList = t.getMatchSimpleInfo();
		AgendaPanel m = new AgendaPanel(1920,1280,matchList);
		m.setBounds(0, 0, 1920,1280);
		JFrame f = new JFrame();
		f.add(m);
		f.setSize(1920,1280);
		f.setVisible(true);
	}
}

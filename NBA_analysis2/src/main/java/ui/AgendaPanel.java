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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
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
	private BLService bl;
	private JPanel content;

	AgendaPanel(int width, int height, ArrayList<MatchSimpleInfo> matches,BLService bl,JPanel content) {
		this.width = width;
		this.height = height;
		this.matches = matches;
		this.bl = bl;
		this.content = content;
		this.setLayout(null);
		setTabelPanel();

	}

	private void setTabelPanel() {
		AgendaTable table = new AgendaTable(matches,bl,content);
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
		private String[] agendaColumnName = { "日期", "对手", "结果", "比分"};
		private BLService bl;
		private JPanel content;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM月dd日");

		// type :0表示信息，1表示数据
		public AgendaTable(ArrayList<MatchSimpleInfo> matchList,BLService bl,JPanel content) {
			this.bl = bl;
			this.content = content;
			this.setShowGrid(false);
			this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			this.setFont(new Font("微软雅黑", Font.PLAIN, 16));
			this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			adjustHeader();
			this.setTableHeaderColor(new Color(158,158,158));
			
			
			this.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                int column;
	                if (AgendaTable.this.getSelectedRow() == -1) {
	                    return;
	                }
	                if ((column=AgendaTable.this.getSelectedColumn()) == 1) {
	                	int row = AgendaTable.this.getSelectedRow();
	                	String teamName = (String)AgendaTable.this.getValueAt(row, column);
	                	teamName = teamName.split(" ")[1];//空格在1位
	                	Team t = AgendaTable.this.bl.getTeamByName(teamName);
	            		
	            		TeamInfoPanel m = new TeamInfoPanel(width,height*3/2,t,AgendaTable.this.bl,AgendaTable.this.content);
	            		m.setBounds(0, 0, width, height*3/2);
	            		AgendaTable.this.content.removeAll();
	            		AgendaTable.this.content.add(m);
	            		AgendaTable.this.content.updateUI();
	                }
	                //暂时这样
	               /* if ((column=AgendaTable.this.getSelectedColumn()) == 4) {
	                	int row = AgendaTable.this.getSelectedRow();
	                	String day = (String)AgendaTable.this.getValueAt(row, 0);
	                	day = day.trim();
	                	day = "2014-"+day;
	                	Date date = null;
	                	try {
							date = df.parse(day);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                	
	                	String name = (String)AgendaTable.this.getValueAt(row, 1);
	                	String atHome = name.split(" ")[0];
	                	String teamName = name.split(" ")[1];//空格在1位
	                	String [] result = new String [2];
	                	if(atHome.equals("vs")){
	                		result[0] = matchList.get(0).get
	                	}
	                	
	                	System.out.println("球队名字"+teamName);
	                	Team t = AgendaTable.this.bl.getTeamByName(teamName);
	            		
	            		TeamInfoPanel m = new TeamInfoPanel(width,height*3/2,t,AgendaTable.this.bl,AgendaTable.this.content);
	            		m.setBounds(0, 0, width, height*3/2);
	            		AgendaTable.this.content.removeAll();
	            		AgendaTable.this.content.add(m);
	            		AgendaTable.this.content.updateUI();
	                }*/
	               
	            }
	        });
			
			
			DefaultTableModel model = new DefaultTableModel(null, agendaColumnName);
			int size = matchList.size();
		//	System.out.println("球员列表"+size);
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
		protected void resizeColumnWidth() {

			for (int column = 0; column < this.getColumnCount(); column++) {
				int width = 340; // Min width
				for (int row = 0; row < this.getRowCount(); row++) {
					TableCellRenderer renderer = this.getCellRenderer(row, column);
					Component comp = this.prepareRenderer(renderer, row, column);
					width = Math.max(comp.getPreferredSize().width, width);
				}
				columnModel.getColumn(column).setPreferredWidth(width);
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


}

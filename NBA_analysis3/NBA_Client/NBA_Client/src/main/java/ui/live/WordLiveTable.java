package ui.live;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import BLservice.BLservice;
import ui.player.PlayerInfoPanel;
import ui.statistics.BaseJTable;
import ui.statistics.PlayerJTable;
import ui.team.TeamInfoPanel;
import util.UIUtils;
import vo.EventVo;
import vo.Playervo;
import vo.Teamvo;

public class WordLiveTable extends BaseJTable{

	private ArrayList<EventVo> eventList;
	private String [] columnNames;
	private int portraitWidth = 70;
	private int portraitHeight = 60;
	private int logoWidth = 70;
	private int logoHeight = 60;
	private int width;
	private int height;
	private BLservice bl;
	private JPanel content;
	private String season;
	private boolean isPlayOff;
	WordLiveTable(int width,int height,String [] columnName,ArrayList<EventVo> eventList,BLservice bl,JPanel content,String season,boolean isPlayOff){
		this.eventList = eventList;	
		this.columnNames = columnName;
		this.width = width;
		this.height = height;
		this.bl = bl;
		this.content = content;
		this.season = season;
		this.isPlayOff = isPlayOff;
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int column;
				if (WordLiveTable.this.getSelectedRow() == -1) {
					return;
				}
				if ((column = WordLiveTable.this.getSelectedColumn()) == 2) {
					int row = WordLiveTable.this.getSelectedRow();
					String playerName = (String) WordLiveTable.this.getValueAt(
							row, column);
					Playervo p;
					try {
						//System.out.println((WordLiveTable.this.bl==null)+"");
						p = WordLiveTable.this.bl.getPlayerById(WordLiveTable.this.bl.getPlayerByNameAndTeam(
								playerName).getPid(), WordLiveTable.this.season, WordLiveTable.this.isPlayOff);
						PlayerInfoPanel playerInfoPanel = new PlayerInfoPanel(
								WordLiveTable.this.width, WordLiveTable.this.height * 10 / 9*(20/13)*7/4, p,
								WordLiveTable.this.bl,
								WordLiveTable.this.content,
								WordLiveTable.this.season,
								WordLiveTable.this.isPlayOff);

						playerInfoPanel.setBounds(0, 0, WordLiveTable.this.width, WordLiveTable.this.height * 10 / 9*(20/13)*7/4);
						playerInfoPanel.startAnimation();
						WordLiveTable.this.content.removeAll();
						WordLiveTable.this.content.add(playerInfoPanel);
						WordLiveTable.this.content.updateUI();
						playerInfoPanel.startAnimation();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				
			}
		});
		
		adjustHeader();
		updateRowHeights();
		resizeColumnWidth();
		refreshTabelModel();
		paintRow();

	}
	protected void paintRow() {
		double [] widthCoefficient = {1.0/20,1.0/20,1.0/4,12.0/20,1.0/20};
		TableColumnModel tcm = this.getColumnModel();
		for (int i = 0, n = tcm.getColumnCount(); i < n; i++) {
			TableColumn tc = tcm.getColumn(i);
			tc.setCellRenderer(new RowRenderer());
			tc.setPreferredWidth((int)(width*widthCoefficient[i]));
		}
	}

	private void refreshTabelModel(){
		DefaultTableModel model = new DefaultTableModel(null, columnNames);
		int size = eventList.size();
	
			for (int i = 0; i <size; i++) {
				String[] s = getRowData(eventList.get(i));
				
				// 添加数据到表格
				model.addRow(s);
			}

		// 更新表格
		this.setModel(model);
		this.paintRow();
		this.updateRowHeights();
		this.resizeColumnWidth();
		this.validate();
		this.repaint();
	}
	
	// 重写行方法(paintRow())具体对应的类
	protected class RowRenderer extends DefaultTableCellRenderer {
		
		
		public Component getTableCellRendererComponent(JTable t, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
			
			// 设置内容居中
			if(column==2){
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
			if (column == 2) {

				ImageIcon icon = new ImageIcon(UIUtils.resize(eventList.get(row).getPlayerImage(),
						portraitWidth, portraitHeight));
				setIcon(icon);

			}
			
			if (column == 4) {

				ImageIcon icon = new ImageIcon(UIUtils.resize(eventList.get(row).getTeamImage(),
						logoWidth, logoHeight));
				setIcon(icon);

			}
			setBorder(BorderFactory.createEmptyBorder());
			
			return super.getTableCellRendererComponent(t, value, isSelected,
					hasFocus, row, column);

		}

		

	}
	private String[] getRowData(EventVo event) {
		String[] s = new String[5];
		s[0] = event.getTime();
		s[1] = event.getPoints();
		s[2] = event.getPlayerName();
		s[3] = event.getDescription();
		s[4] = "";
		
		return s;
		
	}
}
	

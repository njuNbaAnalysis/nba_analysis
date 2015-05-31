package ui.live;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import ui.BaseJTable;
import util.UIUtils;
import vo.EventVo;

public class WordLiveTable extends BaseJTable{

	private ArrayList<EventVo> eventList;
	private String [] columnNames;
	private int portraitWidth = 40;
	private int portraitHeight = 40;
	private int logoWidth = 20;
	private int logoHeight = 20;
	private int width;
	WordLiveTable(int width,String [] columnName,ArrayList<EventVo> eventList){
		this.eventList = eventList;	
		this.columnNames = columnName;
		this.width = width;
		//this.setColumnModel(getColumn(this, width));
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
	
	//外部事件出现，更新表格
	public void refresh(EventVo event){
		eventList.add(event);
		refreshTabelModel();
	}

	private void refreshTabelModel(){
		DefaultTableModel model = new DefaultTableModel(null, columnNames);
		int size = eventList.size();
	
			for (int i = size-1; i >=0; i--) {
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

				ImageIcon icon = new ImageIcon(UIUtils.resize(eventList.get(row).getPlayerPortrait(),
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
	

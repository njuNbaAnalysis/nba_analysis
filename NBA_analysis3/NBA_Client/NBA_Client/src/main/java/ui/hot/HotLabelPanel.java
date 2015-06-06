package ui.hot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicLabelUI;

import BLservice.BLservice;


public class HotLabelPanel extends JPanel{
	protected static String FONT_OF_HOT = "微软雅黑";
	protected String headName;
	protected String[] columnName;
	protected HotTableButton[] btArray;
	protected HotTabelHeadLabel tableHeadLabel;
	protected JLabel tableContentLabel;
	protected int hotWidth;
	protected int hotHeight;
	protected BLservice bl;
	HotLabelPanel(String headName, String[] columnName,
			int kingWidth, int kingHeight,BLservice bl) {
		this.headName = headName;
		this.columnName = columnName;
		this.hotWidth = kingWidth;
		this.hotHeight = kingHeight;
		this.bl = bl;
		this.setLayout(null);
		
	}
	protected void setTableHeadLabel() {
		tableHeadLabel = new HotTabelHeadLabel(headName,hotWidth,hotHeight);
		tableHeadLabel.setBounds(0, 0, hotWidth, hotHeight / 6);
		this.add(tableHeadLabel);

	}

	protected void setButton(String type) {
		int size = columnName.length;
		btArray = new HotTableButton[size];
		for (int i = 0; i < size; i++) {
			btArray[i] = new HotTableButton(columnName[i],type,this,bl);
			btArray[i].setBounds(hotWidth * i / size, hotHeight / 6,
					hotWidth / size, hotHeight / 8);
			btArray[i].setText(columnName[i]);
			this.add(btArray[i]);
		}
	}
	
}

package ui.live;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import ui.statistics.PlayerJTable;
import util.Tools;
import vo.EventVo;

public class WordLiveTablePanel extends JPanel {
	private String[] columnNames;
	private int width;
	private int height;
	private int sectionSize;// 表示有几节
	private SectionButton[] btArray;
	private int selected = 0;
	private ArrayList<EventVo> eventList;
	
	private JScrollPane jspane;

	public WordLiveTablePanel(String[] columnNames,
			int width, int height, ArrayList<EventVo> eventList) {
		this.setLayout(null);
		this.setSize(width, height);
		this.columnNames = columnNames;
		this.width = width;
		this.height = height;
		this.eventList = eventList;
		setButton();

		jspane = new JScrollPane();
		jspane.setBounds(0, height / 10, width, height * 9 / 10);
		
		
		
		WordLiveTable eventTable = new WordLiveTable(width, columnNames, Tools.reverse(eventList));

		jspane.setViewportView(eventTable);
		this.add(jspane);
	}

	public void paintComponent(Graphics g) {
		g.setColor(new Color(87, 89, 91));
		g.fillRect(0, 0, width, height / 10);
	}
	
    
	private void setButton() {
		sectionSize = Tools.getSectionNum(eventList);
		if (sectionSize > 0) {
			btArray = new SectionButton[sectionSize];
			for (int i = 0; i < sectionSize; i++) {
				btArray[i] = new SectionButton(Tools.getSectionInChinese(i+1), i);
				btArray[i].setBounds(width * i / 8, 0, width / 8, height / 10);
				SectionButtonListener l = new SectionButtonListener(i);
				btArray[i].addActionListener(l);
				this.add(btArray[i]);

			}
		}

	}

	

	private ArrayList<EventVo> getSectionEvent(int sectionNum) {
		ArrayList<EventVo> list = new ArrayList<EventVo>();
		
		for (EventVo event : eventList) {
			if (event.getSection() == sectionNum) {
				list.add(event);
			}
		}
		return list;
	}

	private class SectionButtonListener implements ActionListener {
		int type;

		SectionButtonListener(int i) {
			type = i;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (selected != type) {
				selected = type;
				jspane.removeAll();				
				WordLiveTable eventTable = new WordLiveTable(width, columnNames, Tools.reverse(getSectionEvent(type+1)));

				jspane.setViewportView(eventTable);
				WordLiveTablePanel.this.updateUI();
				WordLiveTablePanel.this.repaint();
			}

		}

	}

}

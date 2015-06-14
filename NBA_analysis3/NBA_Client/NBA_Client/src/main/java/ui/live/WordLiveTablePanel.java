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

import BLservice.BLservice;
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
	private JPanel content;
	private JPanel con;
	private String season;
	private boolean isPlayOff;
	private BLservice bl;

	public WordLiveTablePanel(String[] columnNames, int width, int height,
			ArrayList<EventVo> eventList,BLservice bl,JPanel con,String season,boolean isPlayOff) {
		this.setLayout(null);
		this.setSize(width, height);
		this.columnNames = columnNames;
		this.width = width;
		this.height = height;
		this.eventList = eventList;
		this.con = con;
		this.season = season;
		this.isPlayOff = isPlayOff;
		this.bl = bl;
		setButton();

		content = new JPanel();
		content.setBounds(0, height / 10, width, height * 9 / 10);
		content.setLayout(null);

		JScrollPane jspane = new JScrollPane();
		jspane.setBounds(0,0, width, height * 9 / 10);

		WordLiveTable eventTable = new WordLiveTable(width,height * 9 / 10, columnNames,
				Tools.reverse(getSectionEvent(Tools.getSectionNum(eventList))),bl,con,season,isPlayOff);

		jspane.setViewportView(eventTable);
		content.add(jspane);
		this.add(content);
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
				btArray[i] = new SectionButton(
						Tools.getSectionInChinese(i + 1), i);
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
				//System.out.println(event.getTeamName() + event.getPoints());
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
				JScrollPane jspane = new JScrollPane();

				jspane.setBounds(0, 0, width, height * 9 / 10);
				WordLiveTable eventTable = new WordLiveTable(width,height,
						columnNames, Tools.reverse(getSectionEvent(type + 1)),bl,con,season,isPlayOff);

				jspane.setViewportView(eventTable);

				content.removeAll();
				content.updateUI();

				content.add(jspane);
				repaint();
			}

		}

	}

}

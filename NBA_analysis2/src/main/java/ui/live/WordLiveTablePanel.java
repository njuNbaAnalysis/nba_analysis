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

import ui.PlayerJTable;

public class WordLiveTablePanel extends JPanel {
	private String[] columnNames;
	private String[] buttonNames;
	private int width;
	private int height;
	private int sectionSize;// 表示有几节
	private SectionButton[] btArray;
	private WordLiveTable eventTable;
	private JScrollPane jspane;
	private int selected = 0;
	private ArrayList<EventVo> eventList;

	public WordLiveTablePanel(String[] columnNames, String[] buttonNames,
			int width, int height, ArrayList<EventVo> eventList) {
		this.setLayout(null);
		this.setSize(width, height);
		this.columnNames = columnNames;
		this.buttonNames = buttonNames;
		this.width = width;
		this.height = height;
		this.sectionSize = buttonNames.length;
		this.eventList = eventList;
		setButton();

		jspane = new JScrollPane();
		jspane.setBounds(0, height / 10, width, height * 9 / 10);

		eventTable = new WordLiveTable(width, columnNames, eventList, selected);

		jspane.setViewportView(eventTable);
		this.add(jspane);
	}

	public void paintComponent(Graphics g) {
		g.setColor(new Color(87, 89, 91));
		g.fillRect(0, 0, width, height / 10);
	}

	private void setButton() {
		sectionSize = getSectionNum();
		btArray = new SectionButton[sectionSize];
		for (int i = 0; i < sectionSize; i++) {
			btArray[i] = new SectionButton(buttonNames[i], i);
			btArray[i].setBounds(width * i / 8, 0, width / 8, height / 10);
			this.add(btArray[i]);

		}
	}

	private int getSectionNum() {
		int section = -1;
		for (EventVo event : eventList) {
			if (event.getSection() > section) {
				section = event.getSection();
			}
		}
		return section;
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
				eventTable = new WordLiveTable(width, columnNames, getSectionEvent(selected+1),
						selected);
				jspane.removeAll();
				jspane.setViewportView(eventTable);
				WordLiveTablePanel.this.add(jspane);
				jspane.updateUI();
				jspane.repaint();
			}

		}

	}

}

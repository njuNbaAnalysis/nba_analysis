package ui.live;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import util.UIUtils;
import vo.EventVo;

public class WordLivePanel extends JPanel {
	private JButton tableButton;
	private JButton chartButton;
	private int width;
	private int height;
	private int selectedNumber = 1;//表示选择哪个按钮,0表示折线图表格，1表示直播表格
	private JScrollPane content;
	ImageIcon tableIcon;
	ImageIcon tableIconD;
	ImageIcon tableIconB;
	ImageIcon chartIcon;
	ImageIcon chartIconD;
	ImageIcon chartIconB;
	private ArrayList<EventVo> eventList = new ArrayList<EventVo>();
	
	WordLivePanel(int width,int height,ArrayList<EventVo> eventList) {
		this.setLayout(null);
		this.setSize(width, height);
		this.width = width;
		this.height = height;
		this.eventList = eventList;
		
		loadImage();
		setButton();
		
		
		String [] columnNames = {"时间","比分","参与球员","文字直播","球队"};
		String [] btNames = {"第一节","第二节","第三节","第四节"};//暂时没有加时
		WordLiveTablePanel wordLivePanel = new WordLiveTablePanel(columnNames,btNames,width,height*9/10,eventList);
		wordLivePanel.setLocation(0, 0);
		
		this.content = new JScrollPane();
		content.setBounds(0, height/15, width, height*9/10);
		content.setLayout(null);
		content.add(wordLivePanel);
		this.add(content);
		
		
	}
	
	public void loadImage() {
		
		BufferedImage bufferTable = null;
		BufferedImage bufferTableB = null;
		BufferedImage bufferTableD = null;
		BufferedImage bufferChart = null;
		BufferedImage bufferChartB = null;
		BufferedImage bufferChartD = null;
		try {
			
			bufferTable = ImageIO.read(new File("image" + File.separator
					+ "table.png"));
			bufferTable = UIUtils.resize(bufferTable,width/15,height/15);
			bufferTableB = ImageIO.read(new File("image" + File.separator
					+ "table_b.png"));
			bufferTableB = UIUtils.resize(bufferTableB,width/15,height/15);
			bufferTableD = ImageIO.read(new File("image" + File.separator
					+ "table_d.png"));
			bufferTableD = UIUtils.resize(bufferTableD,width/15,height/15);
			
			bufferChart = ImageIO.read(new File("image" + File.separator
					+ "chart.png"));
			bufferChart = UIUtils.resize(bufferChart,width/15,height/15);
			bufferChartB = ImageIO.read(new File("image" + File.separator
					+ "chart_b.png"));
			bufferChartB = UIUtils.resize(bufferChartB,width/15,height/15);
			bufferChartD = ImageIO.read(new File("image" + File.separator
					+ "chart_d.png"));
			bufferChartD = UIUtils.resize(bufferChartD,width/15,height/15);

		} catch (IOException e) {
			e.printStackTrace();
		}

		
		tableIcon = new ImageIcon(bufferTable);
		tableIconB = new ImageIcon(bufferTableB);
		tableIconD = new ImageIcon(bufferTableD);
		
		chartIcon = new ImageIcon(bufferChart);
		chartIconB = new ImageIcon(bufferChartB);
		chartIconD = new ImageIcon(bufferChartD);
	}

	public void paintComponent(Graphics g2) {
		Graphics2D g = (Graphics2D) g2;
		g.setColor(new Color(30, 81, 140));
		g.fillRect(0, 0, width, height / 15);
		
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.WHITE);
		g.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, height / 30));
		g.drawString("文字直播",width/40 , height/20);
	}

	private void setButton() {
		
		

		chartButton = new JButton();
		chartButton.setBackground(new Color(42, 108, 182));
		chartButton.setBounds(width * 11 / 15, height/80, width * 1 / 15,
				height * 1 / 20);
		chartButton.setContentAreaFilled(false);
		chartButton.setBorderPainted(false);
		chartButton.setIcon(chartIcon);
		MouseHandle chartListener = new MouseHandle(chartIconD, chartIcon,
				chartIconB, 0);
		chartButton.addMouseListener(chartListener);
		this.add(chartButton);
		
		tableButton = new JButton();
		tableButton.setBackground(new Color(42, 108, 182));
		
		tableButton.setBounds(width * 12 / 15, height/80, width * 1 / 15,
				height * 1 / 20);
		tableButton.setContentAreaFilled(false);
		tableButton.setBorderPainted(false);
		tableButton.setIcon(tableIcon);
		MouseHandle tableListener = new MouseHandle(tableIconD, tableIcon,
				tableIconB, 1);
		tableButton.addMouseListener(tableListener);
		this.add(tableButton);

	}

	class MouseHandle extends MouseAdapter {

		ImageIcon newIcon;
		ImageIcon oldIcon;
		ImageIcon selIcon;
		int type;

		public MouseHandle(ImageIcon newIm, ImageIcon oldIm,
				ImageIcon selectIm, int x) {
			newIcon = newIm;
			oldIcon = oldIm;
			selIcon = selectIm;
			type = x;

		}

		@Override
		public void mousePressed(MouseEvent e) {
			if ((selectedNumber != type)) {
				((JButton) e.getSource()).setIcon(selIcon);
				clearImage(selectedNumber);
				selectedNumber = type;
			}
			if (type == 0) {
				String [] btNames = {"全部","第一节","第二节","第三节","第四节"};
				WordLiveLineChartPanel chartPanel = new WordLiveLineChartPanel(btNames,width,height*9/10,eventList);
				chartPanel.setLocation(0, 0);
				content.removeAll();
				content.add(chartPanel);
				content.updateUI();
			}

			if (type == 1) {
				String [] columnNames = {"时间","比分","参与球员","文字直播","球队"};
				String [] btNames = {"第一节","第二节","第三节","第四节"};//暂时没有加时
				WordLiveTablePanel wordLivePanel = new WordLiveTablePanel(columnNames,btNames,width,height*9/10,eventList);
				wordLivePanel.setLocation(0, 0);
				content.removeAll();
				content.add(wordLivePanel);
				content.updateUI();

			}

		}

		public void mouseEntered(MouseEvent e) {

			if (selectedNumber != type) {
				((JButton) e.getSource()).setIcon(newIcon);
			}
		}

		public void mouseExited(MouseEvent e) {

			if (selectedNumber != type) {
				((JButton) e.getSource()).setIcon(oldIcon);
			}
		}

		public void mouseClicked(MouseEvent e) {

		}
	}
	private void clearImage(int type) {
		switch (type) {
		case 0:
			tableButton.setIcon(tableIcon);
			break;
		case 1:
			chartButton.setIcon(chartIcon);
			break;
		}

	}

	public void refresh(ArrayList<EventVo> eventList) {
		if(selectedNumber==0){
			this.eventList = eventList; 
		}
		
		if(selectedNumber==1){
			String [] columnNames = {"时间","比分","参与球员","文字直播","球队"};
			String [] btNames = {"第一节","第二节","第三节","第四节"};//暂时没有加时
			WordLiveTablePanel wordLivePanel = new WordLiveTablePanel(columnNames,btNames,width,height*9/10,eventList);
			wordLivePanel.setLocation(0, 0);
			content.removeAll();
			content.add(wordLivePanel);
			content.updateUI();
		}
		
		
	}
}

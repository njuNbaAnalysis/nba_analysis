package ui.live;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import logic.matches.Match;
import logic.teams.Team;
import util.UIUtils;

public class TeamComparePanel extends JPanel {
	private boolean[] attribute;
	private String[] attributeNames;
	private double[] cof;
	private int width;
	private int height;
	private int defauleNum = 4;// 默认显示的选项数量

	private JButton setting;
	ImageIcon settingIcon;
	ImageIcon settingIconD;
	ImageIcon settingIconB;

	TeamComparePanel(int width, int height, String[] attributeNames,Match m) {
		this.width = width;
		this.height = height;
		this.attributeNames = attributeNames;
		this.setLayout(null);
		this.setSize(width,height);
		init();
		setButton();
		CompareBarChartPanel chartPanel = new CompareBarChartPanel(width,height*9/10,attribute,attributeNames,getTeamValue(m),getRivalValue(m),cof);
		chartPanel.setLocation(0,height/10);
		this.add(chartPanel);
		repaint();
	}
	private double[] getTeamValue(Match m){
		double [] teamData = new double[attributeNames.length];
		for(int i=0;i<attributeNames.length;i++){
			teamData[i] = i*1024%100;
		}
		return teamData;
	}
	private double[] getRivalValue(Match m){
		double [] teamData = new double[attributeNames.length];
		for(int i=0;i<attributeNames.length;i++){
			teamData[i] = (i+3)*1024%100;
		}
		return teamData;
		
	}
	
	private void init() {
		int size = attributeNames.length;
		attribute = new boolean[size];
		cof = new double[size];
		for (int i = 0; i < size; i++) {
			if (i <= defauleNum) {
				attribute[i] = true;
			} else {
				attribute[i] = false;
			}
			cof[i] = 100.0;
		}

	}
	
	public void loadImage() {

		BufferedImage bufferTable = null;
		BufferedImage bufferTableB = null;
		BufferedImage bufferTableD = null;

		try {

			bufferTable = ImageIO.read(new File("image" + File.separator
					+ "setting.png"));
			bufferTable = UIUtils.resize(bufferTable, width / 15, height / 15);
			bufferTableB = ImageIO.read(new File("image" + File.separator
					+ "setting_b.png"));
			bufferTableB = UIUtils
					.resize(bufferTableB, width / 15, height / 15);
			bufferTableD = ImageIO.read(new File("image" + File.separator
					+ "setting_d.png"));
			bufferTableD = UIUtils
					.resize(bufferTableD, width / 15, height / 15);

		} catch (IOException e) {
			e.printStackTrace();
		}

		settingIcon = new ImageIcon(bufferTable);
		settingIconB = new ImageIcon(bufferTableB);
		settingIconD = new ImageIcon(bufferTableD);

	}

	private void setButton() {
		setting = new JButton();
		MouseHandle mouseHandler = new MouseHandle(settingIconB, settingIcon,
				settingIconD);
		setting.setContentAreaFilled(false);
		setting.setBorderPainted(false);
		setting.setIcon(settingIcon);
		setting.addMouseListener(mouseHandler);
		setting.setBounds(width * 19 / 20, 0, width / 20, height / 20);
		this.add(setting);

	}

	class MouseHandle extends MouseAdapter {

		ImageIcon newIcon;
		ImageIcon oldIcon;
		ImageIcon selIcon;

		public MouseHandle(ImageIcon newIm, ImageIcon oldIm, ImageIcon selectIm) {
			newIcon = newIm;
			oldIcon = oldIm;
			selIcon = selectIm;

		}

		@Override
		public void mousePressed(MouseEvent e) {

			setting.setIcon(selIcon);
			

		}

		public void mouseEntered(MouseEvent e) {

			setting.setIcon(newIcon);

		}

		public void mouseExited(MouseEvent e) {

			setting.setIcon(oldIcon);
		}

		public void mouseClicked(MouseEvent e) {

		}
	}

	

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(new Color(218, 218, 218));
		g2.fillRect(0, 0, width, height / 20);
		// 球队对比
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("微软雅黑", Font.PLAIN, height / 30));
		g2.drawString("球队对比", width*9 / 20, height / 40);
	}

	private class RadioButtonPanel {
		RadioButtonPanel() {

		}
	}

	private class CompareBarChartPanel extends JPanel{
		private int width;
		private int height;

		boolean[] attr;
		String[] attr_name;
		private double[] a_value;
		private double[] b_value;
		private double[] coefficient;
		private Color[] colorArray = {new Color(158,158,158),new Color(0,103,178)};
		
		public CompareBarChartPanel(int width, int height, boolean[] attr,
				String[] attr_name, double[] a_value, double[] b_value,
				double[] coefficient) {
			super();
			this.width = width;
			this.height = height;
			this.attr = attr;
			this.attr_name = attr_name;
			this.a_value = a_value;
			this.b_value = b_value;
			this.coefficient = coefficient;
			this.setSize(width,height);
		}

		public void paintComponent(Graphics g){
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  
	                RenderingHints.VALUE_ANTIALIAS_ON); 
			
			int showNum = 0;
			int half = 9*width/20;

			int barHeight = height/20;
			int barHeightSeparator = height/80;
			for(int i=0;i<attr_name.length;i++){
				if(attr[i]){
					
					Color a;
					Color b;
					
					
					if(a_value[i]>b_value[i]){
						a = colorArray[0];
						b = colorArray[1];
					}else{
						a = colorArray[1];
						b = colorArray[0];
					}
					//左长方形
					int leftBarWidth = (int)(a_value[i]/coefficient[i]*half);
					
					g2.setFont(new Font("微软雅黑", Font.PLAIN, height / 30));
					g2.drawString(a_value[i]+"",half-leftBarWidth-width/20,barHeight*i+barHeightSeparator*(i+1));
					
					g2.setColor(a);
					g2.fillRect(half-leftBarWidth, barHeight*i+barHeightSeparator*(i+1), leftBarWidth, barHeight);
					
					//右长方形
					int rightBarWidth = (int)(b_value[i]/coefficient[i]*half);
					g2.setColor(b);
					g2.fillRect(width-half, barHeight*i+barHeightSeparator*(i+1), rightBarWidth, barHeight);
					
					g2.setFont(new Font("微软雅黑", Font.PLAIN, height / 30));
					g2.drawString(b_value[i]+"",width-half+rightBarWidth,barHeight*i+barHeightSeparator*(i+1));
					
					
					
					showNum++;
				}
			}
			
			g2.setColor(new Color(246,246,246));
			g2.fillRect(width*9/20, 0, width/10, height);
			
			g2.setColor(Color.black);
			for(int i=0;i<attr_name.length;i++){
				//中间属性名
				g2.setFont(new Font("微软雅黑", Font.PLAIN, height / 30));
				g2.drawString(attr_name[i],width*9 / 20, barHeight*i+barHeightSeparator*(i+1));
			}
			
			
			
			
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("微软雅黑", Font.PLAIN, height / 30));
		}

	}
}

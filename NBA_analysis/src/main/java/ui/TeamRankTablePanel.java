package ui;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class TeamRankTablePanel extends JPanel implements MouseListener {

	private static Image headBar;
	private static Image filter;
	
	private JLabel headLabel;
	private JLabel filertLabel;
	private PlayerJTable playerTable;
	private DefaultTableModel model;

	public void paintComponent(Graphics g) {
		g.setColor(new Color(30, 81,140));
		g.fillRect(0, 0, 2000, 50);
		g.setColor(new Color(87, 89,91));
	    g.fillRect(0, 50, 2000, 66);
		g.drawImage(headBar, 0, 0, this);
        g.drawImage(filter, 0, 50, this);
      
       
        g.setFont(new Font("STHUPO", Font.PLAIN, 40*2/3));
        
        
	}

	public void setLookAndFeel() {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {

		} catch (InstantiationException ex) {

		} catch (IllegalAccessException ex) {

		} catch (javax.swing.UnsupportedLookAndFeelException ex) {

		}
	}

	public TeamRankTablePanel() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//setLookAndFeel();

		BufferedImage bufferHeadBar = null;
		BufferedImage bufferedFilter = null;

		try {
			bufferHeadBar = ImageIO.read(new File("image" + File.separator
					+ "headBar.png"));
			bufferedFilter = ImageIO.read(new File("image" + File.separator
					+ "filter.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		headBar = bufferHeadBar;
		filter = bufferedFilter;

		
		/*
		 * headLabel.setSize(800, 50); headLabel.setVisible(true);
		 * this.add(headLabel); filertLabel.setSize(800, 66);
		 * filertLabel.setVisible(true); this.add(filertLabel);
		 */

		JPanel tableP = new JPanel();
		tableP.setSize(800, 600);
		tableP.setLayout(new BoxLayout(tableP, BoxLayout.Y_AXIS));
		this.add(Box.createVerticalStrut(116));
		
		
		
				
		Object[][] cellData = { { "", "", "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "", "", "", "" } };

		/*
		 * String[] columnNames = { "排名", "球队", "场数", "%", "3分%", "罚球%", "进攻篮板",
		 * "防守篮板", "场均助攻" };
		 */
		String[] columnNames = { "排名", "球员", "球队", "参赛场数", "篮板数", "助攻数",
				"在场时间", "投篮命中率", "三分命中率", "罚球命中率", "进攻数", "防守数", "抢断数", "盖帽数",
				"失误数", "犯规数", " 得分", "效率", "GmSc 效率值", "真实命中率", "投篮效率", "篮板率",
				"进攻篮板率", "防守篮板率", "助攻率", "抢断率", "盖帽率", "失误率", "使用率" };
		model = new DefaultTableModel(cellData, columnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		playerTable = new PlayerJTable();
		playerTable.setModel(model);
		
		
		//传给playertable图片文件数组
		ArrayList<File> list = new ArrayList<File>();
		File f1 = new File("Data\\players\\portrait\\Aaron Brooks.png");
		File f2 = new File("Data\\players\\portrait\\Aaron Gray.png");
		File f3 = new File("Data\\players\\portrait\\Adonis Thomas.png");
		File f4 = new File("Data\\players\\portrait\\Al Harrington.png");
		list.add(f1);
		list.add(f2);
		list.add(f3);
		list.add(f4);	
		playerTable.setImageList(list);	
		
		
		
		//刷新数据
		String [] a = new String [4];
		playerTable.refresh(a);
		
		//playerTable.setSize(1200, 1000);
		playerTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane jspane = new JScrollPane();
		jspane.setViewportView(playerTable);
		//this.add(playerTable);
		// tableP.add(jspane);

		// this.add(Box.createVerticalStrut(10));
		this.add(tableP);
		this.add(jspane);

	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO 自动生成的方法存根

	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO 自动生成的方法存根

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO 自动生成的方法存根

	}

	public void mousePressed(MouseEvent arg0) {
		// TODO 自动生成的方法存根

	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO 自动生成的方法存根

	}

}

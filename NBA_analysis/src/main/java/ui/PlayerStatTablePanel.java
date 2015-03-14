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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import compare.PlayerAveragePointComparator;
import compare.PlayerPointComparator;
import logic.BLService;
import logic.players.Player;

public class PlayerStatTablePanel extends JPanel implements MouseListener {

	private static Image headBar;
	private static Image filter;
	private int width;
	private int height;
	

	private PlayerJTable playerTable;
	private DefaultTableModel model;
	private SelectPanel selectPanel;
	private HeadPanel headPanel;

	private BLService bl;

	private static String[] averageColumn = { "排名", "球员", "球队", "场数", "先发",
			"篮板", "助攻", "在场时间", "效率", "GmSc 效率值", "投篮命中率%", "三分命中率%", "罚球命中率%",
			"进攻数", "防守数", "抢断数", "盖帽数", "失误数", "犯规数", " 得分", "真实命中率%", "投篮效率%",
			"篮板率%", "进攻篮板率%", "防守篮板率%", "助攻率%", "抢断率%", "盖帽率%", "失误率%", "使用率%" };
	private static String[] totalColumn = { "排名", "球员", "球队", "场数", "先发", "篮板",
			"助攻", "在场时间", "投篮命中率%", "三分命中率%", "罚球命中率%", "进攻数", "防守数", "抢断数",
			"盖帽数", "失误数", "犯规数", " 得分" };
	
	public void paintComponent(Graphics g) {
		g.setColor(new Color(30, 81, 140));
		g.fillRect(0, 0, 2000, 50 * height / (1080));
		g.setColor(new Color(87, 89, 91));
		g.fillRect(0, 50 * height / (1080), 2000, 66 * height / (1080));
		g.drawImage(headBar, 0, 0, this);
		g.drawImage(filter, 0, 50 * height / (1080), this);

		g.setFont(new Font("STHUPO", Font.PLAIN, 40 * 2 / 3));

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

	public PlayerStatTablePanel(int width, int height, BLService bl) {
		this.width = width;
		this.height = height;
		this.bl = bl;
		setLayout(null);
		// setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		// setLookAndFeel();

		BufferedImage bufferHeadBar = null;
		BufferedImage bufferedFilter = null;

		try {
			bufferHeadBar = ImageIO.read(new File("image" + File.separator
					+ "headBar.png"));
			System.out.println(bufferHeadBar.getHeight() + " sss"
					+ bufferHeadBar.getWidth());
			bufferHeadBar = MenuPanel.resize(bufferHeadBar,
					width * 800 / (1920), height * 50 / (1080));
			System.out.println(bufferHeadBar.getHeight() + " sss"
					+ bufferHeadBar.getWidth());
			bufferedFilter = ImageIO.read(new File("image" + File.separator
					+ "filter.png"));
			bufferedFilter = MenuPanel.resize(bufferedFilter,
					width * 800 / (1920), height * 66 / (1080));
		} catch (IOException e) {
			e.printStackTrace();
		}

		headBar = bufferHeadBar;
		filter = bufferedFilter;

		this.add(Box.createVerticalStrut(116 * height / (1080)));


		headPanel = new HeadPanel(width, 50, this);
		headPanel.setBounds(0, 0, width, 50 * height / (1080));
		this.add(headPanel);

		selectPanel = new SelectPanel(width, 66 * height / (1080), this);
		selectPanel.setBounds(0, 50 * height / (1080), width,
				66 * height / (1080));
		this.add(selectPanel);

		/*
		 * String[] columnNames = { "排名", "球队", "场数", "%", "3分%", "罚球%", "进攻篮板",
		 * "防守篮板", "场均助攻" };
		 */

		playerTable = new PlayerJTable();
		
		
		JScrollPane jspane = new JScrollPane();
		jspane.setViewportView(playerTable);
		jspane.setBounds(0, 116 * height / (1080), width*9/10, height - 116 * height
				/ (1080));
		this.add(jspane);
		refresh();
	}

	public void refresh() {

		String[] columnNames;
		Comparator c;
		
		if (headPanel.getSelected()) {
			columnNames = averageColumn;
			c = new PlayerPointComparator();
			
		} else {
			columnNames = totalColumn;
			c = new PlayerAveragePointComparator();
		}
		model = new DefaultTableModel(null, columnNames);
		playerTable.setModel(model);

		playerTable.setRowSorter(new TableRowSorter<TableModel>(model));

		// 刷新数据
		ArrayList<Player> pList = bl.getAllPlayers();
		//pList.sort(c);
		playerTable.refresh(pList, headPanel.getSelected());
	}

	public void mouseClicked(MouseEvent arg0) {

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

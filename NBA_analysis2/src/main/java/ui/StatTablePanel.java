package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import logic.BLService;


public class StatTablePanel extends JPanel{
	protected static Image headBar;
	protected static Image filter;
	protected int width;
	protected int height;

	protected StatJTable statTable;
	protected DefaultTableModel model;
	protected HeadPanel headPanel;
	protected JScrollPane jspane;

	protected BLService bl;
	protected int type;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(30, 81, 140));
		g.fillRect(0, 0, 2000, 50 * height / (1080));
		g.setColor(new Color(87, 89, 91));
		g.fillRect(0, 50 * height / (1080), 2000, 66 * height / (1080));
		g.drawImage(headBar, 0, 0, this);
		g.drawImage(filter, 0, 50 * height / (1080), this);
		g.setFont(new Font("STHUPO", Font.PLAIN, 40 * 2 / 3));
	}

	public StatTablePanel(int width, int height, BLService bl) {
		this.width = width;
		this.height = height;
		this.bl = bl;
		this.type = 1;
		setLayout(null);

		BufferedImage bufferHeadBar = null;
		BufferedImage bufferedFilter = null;

		try {
			bufferHeadBar = ImageIO.read(new File("image" + File.separator
					+ "headBar.png"));
			bufferHeadBar = MenuPanel.resize(bufferHeadBar,
					width * 800 / (1920), height * 50 / (1080));
			bufferedFilter = ImageIO.read(new File("image" + File.separator
					+ "filter.png"));
			bufferedFilter = MenuPanel.resize(bufferedFilter,
					width * 800 / (1920), height * 66 / (1080));
		} catch (IOException e) {
			e.printStackTrace();
		}

		headBar = bufferHeadBar;
		filter = bufferedFilter;

		headPanel = new HeadPanel(width, 50 * height / (1080), this);
		headPanel.setBounds(0, 0, width, 50 * height / (1080));
		this.add(headPanel);
		
		
	}

	public void refresh() {
		statTable.refresh(headPanel.getSelected(), null, true);
	}

}

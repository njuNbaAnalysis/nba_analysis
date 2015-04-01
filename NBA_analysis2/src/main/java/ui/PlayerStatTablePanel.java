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
import java.util.Collections;
import java.util.Comparator;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import compare.PalyerScreening;
import compare.PlayerAveragePointsComp;
import compare.PlayerPointsComp;
import logic.BLService;
import logic.players.Player;

public class PlayerStatTablePanel extends JPanel {

	private static Image headBar;
	private static Image filter;
	private int width;
	private int height;

	private StatJTable statTable;
	private DefaultTableModel model;
	private SelectPanel selectPanel;
	private HeadPanel headPanel;
	private PlayerInfoPanel playerInfoPanel;
	private JScrollPane jspane;

	private BLService bl;
	private int type;

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

		

		refreshTablePanel(type);

		

	}

	public void refreshTablePanel(int t) {
		
		
		//此处逻辑有问题
		if ((t == 1 && type != t && statTable != null) || statTable == null) {
			if(playerInfoPanel!=null){
				playerInfoPanel.setVisible(false);
			}
			jspane = new JScrollPane();
			selectPanel = new SelectPanel(width, 66 * height / (1080), this);
			selectPanel.setBounds(0, 50 * height / (1080), width,
					66 * height / (1080));
			//this.add(selectPanel);

			jspane.setBounds(0, 116 * height / (1080), width * 9 / 10, height
					- 116 * height / (1080));

			statTable = new PlayerJTable(bl, 800, 600);
			statTable.setBounds(200, 200, 800, 600);
			jspane.setViewportView(statTable);
			refresh();
			this.add(jspane);

		} else if (t == 2 && type != t) {
			if(playerInfoPanel!=null){
				playerInfoPanel.setVisible(false);
			}
			
			selectPanel.setVisible(false);
			//selectPanel = null;
			jspane.setBounds(0, 50 * height / (1080), width * 9 / 10, height
					- 50 * height / (1080));

			statTable = new TeamJTable(bl, 800, 600);
			statTable.setBounds(200, 200, 800, 600);
			jspane.setViewportView(statTable);
			refresh();
			this.add(jspane);

		} else if(t==3&&type != t){
			jspane.setVisible(false);
			//jspane =null;
			selectPanel.setVisible(false);
			//selectPanel = null;
			
			PlayerInfoPanel playerInfoPanel = new PlayerInfoPanel(width-width/50,height
					- 50 * height / (1080),bl);
			playerInfoPanel.setBounds(0, 50 * height / (1080), width, height
					- 50 * height / (1080));
			playerInfoPanel.startAnimation();
			this.add(playerInfoPanel);
		}

		
		type = t;
		
		this.validate();
		this.repaint();

	}

	public void refresh() {
		statTable.refresh(headPanel.getSelected(), null, true);
	}

	public void refreshBySelect(PalyerScreening palyerSelect) {
		statTable.refreshByScreening(palyerSelect);

	}

}

package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

	private static ImageIcon headBar;
	private static ImageIcon filter;

	private JLabel headLabel;
	private JLabel filertLabel;
	private JTable table;
	private DefaultTableModel model;

	public void paintComponent(Graphics g) {

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
		setLookAndFeel();
		
		
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

		headBar = new ImageIcon(bufferHeadBar);
		filter = new ImageIcon(bufferedFilter);

		headLabel = new JLabel(headBar);
		filertLabel = new JLabel(filter);
		
		/*headLabel.setSize(800, 50);
		headLabel.setVisible(true);
		this.add(headLabel);
		filertLabel.setSize(800, 66);
		filertLabel.setVisible(true);
		this.add(filertLabel);*/
		
		JPanel tableP = new JPanel();
		tableP.setSize(800, 600);
		tableP.setLayout(new BoxLayout(tableP, BoxLayout.Y_AXIS));
		tableP.add(headLabel);
		tableP.add(filertLabel);
		
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

		String[] columnNames = { "排名", "球队", "场数", "%", "3分%", "罚球%", "进攻篮板",
				"防守篮板", "场均助攻" };
		model = new DefaultTableModel(cellData, columnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(model);
		table.setSize(800, 600);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane jspane = new JScrollPane();
		jspane.setViewportView(table);
		this.add(table);
		//tableP.add(jspane);
		
		// this.add(Box.createVerticalStrut(10)); 
		this.add(tableP);
		

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

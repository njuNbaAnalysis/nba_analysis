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

public class PlayerStatTablePanel extends StatTablePanel {

	private SelectPanel selectPanel;

	public PlayerStatTablePanel(int width, int height, BLService bl,JPanel content) {
		super(width, height, bl);

		
		selectPanel = new SelectPanel(width, 66 * height / (1080), this);
		selectPanel.setBounds(0, 50 * height / (1080), width,
				66 * height / (1080));
		this.add(selectPanel);
		
		
		jspane = new JScrollPane();
		jspane.setBounds(0, 116 * height / (1080), width * 9 / 10, height - 116
				* height / (1080));

		statTable = new PlayerJTable(bl, width * 9 / 10, height - 116
				* height / (1080),content);
		statTable.setBounds(200, 200, 800, 600);
		jspane.setViewportView(statTable);
		refresh();
		
		this.add(jspane);
		
		this.validate();
		this.repaint();
	}

	public void refreshBySelect(PalyerScreening palyerSelect) {
		statTable.refreshByScreening(palyerSelect);

	}

}

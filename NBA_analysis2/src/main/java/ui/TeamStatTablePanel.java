package ui;

import javax.swing.JScrollPane;

import logic.BLService;

public class TeamStatTablePanel extends StatTablePanel {

	public TeamStatTablePanel(int width, int height, BLService bl) {
		super(width, height, bl);

		jspane = new JScrollPane();
		jspane.setBounds(0, 50 * height / (1080), width * 9 / 10, height - 50
				* height / (1080));

		statTable = new TeamJTable(bl, 800, 600);
		statTable.setBounds(200, 200, 800, 600);
		jspane.setViewportView(statTable);

		refresh();
		this.add(jspane);

		this.validate();
		this.repaint();

	}

}
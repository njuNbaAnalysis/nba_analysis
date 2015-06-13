package ui.statistics;

import java.rmi.RemoteException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import BLservice.BLservice;


public class TeamStatTablePanel extends StatTablePanel {

	public TeamStatTablePanel(int width, int height, BLservice bl,JPanel content,String season,boolean isPlayOff) {
		super(width, height, bl,season,isPlayOff);

		jspane = new JScrollPane();
		jspane.setBounds(0, 50 * height / (1080), width * 9 / 10, height - 50
				* height / (1080));

		try {
			statTable = new TeamJTable(bl, width * 9 / 10, height - 50
					* height / (1080),content,season,isPlayOff);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//statTable.setBounds(200, 200, 800, 600);
		jspane.setViewportView(statTable);

		refresh();
		this.add(jspane);

		this.validate();
		this.repaint();

	}

}
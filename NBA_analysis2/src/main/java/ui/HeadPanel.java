package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class HeadPanel extends JPanel {
	StatTablePanel statTablePanel;
	JButton average;
	JButton total;
	boolean selected = true;
	int width;
	int height;
	
	public HeadPanel(int width, int height, StatTablePanel statTablePanel) {
		this.setOpaque(false);
		this.setLayout(null);
		this.statTablePanel = statTablePanel;
		this.width = width;
		this.height = height;
		setLabel();
		setButton();
		setComboBox();
	}

	private void setLabel() {

	}

	private void setButton() {
		average = new JButton();
		average.setBackground(new Color(42, 108, 182));
		average.setText("平均");
		average.setBounds(width * 3 / 5, height * 1 / 8, width * 1 / 20,
				height * 6 / 8);
		average.setBorder(null);
		average.setFocusPainted(false);
		average.setForeground(Color.white);
		average.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 15*width/1920));
		average.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				selected = true;
				ChangeActionPerformed(e);

			}

		});
		this.add(average);

		total = new JButton();
		total.setBackground(new Color(26, 71, 123));
		total.setText("总数");
		total.setBounds(width * 13 / 20, height * 1 / 8, width * 1 / 20,
				height * 6 / 8);
		total.setBorder(null);
		total.setFocusPainted(false);
		total.setForeground(Color.white);
		total.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 15));
		total.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				selected = false;
				ChangeActionPerformed(e);

			}

		});
		this.add(total);

	}

	private void setComboBox() {

	}

	private void ChangeActionPerformed(ActionEvent e) {

		if ((JButton) e.getSource() == (average)) {
			average.setBackground(new Color(42, 108, 182));
			total.setBackground(new Color(26, 71, 123));
			statTablePanel.refresh();

		} else if ((JButton) e.getSource() == (total)) {
			total.setBackground(new Color(42, 108, 182));
			average.setBackground(new Color(26, 71, 123));
			statTablePanel.refresh();
		}
		this.validate();
		this.repaint();

	}
	public boolean getSelected(){
		return selected;
	}

}

package ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class HeadPanel extends JPanel {
	JButton average;
	JButton total;
	boolean selected = true;
	int width;
	int height;

	public HeadPanel(int width, int height) {
		this.setOpaque(false);
		this.setLayout(null);
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

		if (e.getSource().equals(average)) {
			total.setBackground(new Color(42, 108, 182));
			average.setBackground(new Color(26, 71, 123));

		} else if ((JButton) e.getSource() == (average)) {
			average.setBackground(new Color(42, 108, 182));
			total.setBackground(new Color(26, 71, 123));
		}
		this.validate();
		this.repaint();

	}

}

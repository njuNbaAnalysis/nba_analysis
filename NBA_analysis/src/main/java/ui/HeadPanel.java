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
	JButton submit;
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
		JButton average = new JButton();
		average.setBackground(new Color(42, 108, 182));
		average.setText("平均");
		average.setBounds(width*3/5, height*1/8,width*1/20, height*6/8);
		average.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ChangeActionPerformed(e);

			}

		});
		this.add(average);
		
		
		JButton total = new JButton();
		total.setBackground(new Color(26, 71, 123));		
		total.setText("总数");
		total.setBounds(width*13/20, height*1/8,width*1/20, height*6/8);
		total.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ChangeActionPerformed(e);

			}

		});
		this.add(total);
		
	}

	private void setComboBox() {
		
	}

	private void ChangeActionPerformed(ActionEvent e) {
		if (e.getSource() == average) {
			total.setBackground(new Color(42, 108, 182));
			average.setBackground(new Color(26, 71, 123));
		} else if (e.getSource() == total) {
			average.setBackground(new Color(42, 108, 182));
			total.setBackground(new Color(26, 71, 123));
		}
		this.validate();
		this.repaint();

	}

}

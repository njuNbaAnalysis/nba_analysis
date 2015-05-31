package ui.live;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class SectionButton extends JButton{
	private String field;
	private int num;
	SectionButton(String text,int num){
		this.field = text;
		this.num = num;
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setForeground(Color.white);
		this.setBackground(new Color(87, 89, 91));
		this.setBorder(null);
		this.setFocusPainted(false);

		this.setFont(new Font("微软雅黑", Font.CENTER_BASELINE,
			 20 ));
		this.setText(text);
	}
}

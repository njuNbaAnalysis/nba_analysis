package ui.hot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicLabelUI;

public class HotTabelHeadLabel extends JLabel {

	private String text;
	private int height;//这是外部容器的长宽。不是本身的长宽
	private int width;

	public HotTabelHeadLabel() {
		this.setForeground(Color.WHITE);
		this.setOpaque(true);

		this.setBackground(new Color(30, 81, 140));
		this.setUI(new LabelUI());
	}

	public HotTabelHeadLabel(String text,int width,int height) {
		this();
		this.text = text;
		this.height = height;
		this.width = width;
		// this.setText(text);
	}

	public void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		Graphics2D g = (Graphics2D) g2.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.white);
		g.setFont(new Font("微软雅黑", Font.CENTER_BASELINE,  height /10));
		g.drawString(text, width / 80, height / 8);
	}

	private class LabelUI extends BasicLabelUI {

		@Override
		public void paint(Graphics g, JComponent c) {
			// TODO Auto-generated method stub

			int y = (int) c.getSize().getHeight();
			int x = (int) c.getSize().getWidth();

			g.setColor(new Color(190, 157, 83));
			g.drawLine(0, y - 1, x - 1, y - 1);

			super.paint(g, c);
		}
	}

}

package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer.UIResource;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.metal.MetalComboBoxButton;

public class SelectPanel extends JPanel {
	JButton submit;
	SelectJComboBox<String> location;
	SelectJComboBox<String> union;
	SelectJComboBox<String> depend;
	int width;
	int height;

	public SelectPanel(int width, int height) {
		this.setOpaque(false);
		this.setLayout(null);
		this.width = width;
		this.height = height;
		setLabel();
		setComboBox();
		setButton();

	}

	private void setLabel() {

	}

	private void setButton() {

		submit = new JButton();
		submit.setText("提交");
		submit.setBounds(width * 11 / 16, height * 11 / 20, width * 1 / 6,
				height * 7 / 20);
		submit.setForeground(Color.white);
		submit.setContentAreaFilled(false);
		submit.setBorder(BorderFactory.createLineBorder(new Color(69, 69, 69),
				2));
		submit.setFocusPainted(false);
		submit.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 15));
		submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				SubmitActionPerformed(e);

			}

		});
		submit.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				submit.setContentAreaFilled(true);
				submit.setBackground(new Color(148, 148, 148));
			}

			public void mouseExited(MouseEvent e) {
				submit.setContentAreaFilled(false);
			}
		});
		this.add(submit);
	}

	private void setComboBox() {
		location = new SelectJComboBox<String>();
		location.addItem("全部位置");
		location.addItem("中锋");
		location.addItem("后卫");
		location.addItem("前锋");
		location.setBounds(width / 4, height / 10, width / 8, height * 7 / 20);
		location.setBackground(new Color(69, 69, 69));
		// location.setUI(new BasicComboBoxUI());
		location.setUI(ColorArrowUI.createUI(location));
		this.add(location);

		union = new SelectJComboBox<String>();
		union.addItem("全部联盟");
		union.addItem("东部");
		union.addItem("西部");
		union.setBounds(width / 2, height / 10, width / 8, height * 7 / 20);
		union.setBackground(new Color(69, 69, 69));
		// union.setUI(new BasicComboBoxUI());
		union.setUI(ColorArrowUI.createUI(union));
		this.add(union);

		depend = new SelectJComboBox<String>();
		depend.addItem("得分");
		depend.addItem("篮板");
		depend.addItem("助攻");
		depend.addItem("得分/篮板/助攻");
		depend.addItem("盖帽");
		depend.addItem("抢断");
		depend.addItem("犯规");
		depend.addItem("失误");
		depend.addItem("分钟");
		depend.addItem("效率");
		depend.addItem("投篮");
		depend.addItem("三分");
		depend.addItem("罚球");
		depend.addItem("两双");
		depend.setBounds(width * 3 / 4, height / 10, width / 8, height * 7 / 20);
		depend.setMaximumRowCount(15);
		depend.setUI(ColorArrowUI.createUI(depend));
		this.add(depend);
	}

	private void SubmitActionPerformed(ActionEvent e) {
		// 更新table
		this.validate();
		this.repaint();

	}

	private class SelectJComboBox<T> extends JComboBox<T> {
		SelectJComboBox() {
			this.setBackground(new Color(69, 69, 69));
			this.setForeground(Color.white);
			this.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 15));

		}

	}

}

// 重写JComboBox的UI，createUI()方法为静态方法，写成内部类报错，故这样书写
class ColorArrowUI extends BasicComboBoxUI {
	static int width;
	static int height;

	public static ComboBoxUI createUI(JComponent c) {
		width = c.getWidth();
		height = c.getHeight();
		return new ColorArrowUI();
	}

	@Override
	protected JButton createArrowButton() {
		JButton btn = new JButton();
		btn.setBorder(BorderFactory.createLineBorder(new Color(69, 69, 69)));
		try {
			btn.setIcon(new ImageIcon(resize(
					ImageIO.read(new File("image/arrowbutton.png")),
					width / 10, height)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return btn;
	}

	// 调整图片大小
	public BufferedImage resize(BufferedImage image, int width, int height) {
		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TRANSLUCENT);
		Graphics2D g2d = (Graphics2D) bi.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(image, 0, 0, width, height, null);
		g2d.dispose();
		return bi;
	}

}

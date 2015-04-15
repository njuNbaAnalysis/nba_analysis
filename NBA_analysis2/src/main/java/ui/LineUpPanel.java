package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicLabelUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import util.UIUtils;
import logic.BLController;
import logic.BLService;
import logic.matches.RecordOfPlayer;
import logic.players.Player;

public class LineUpPanel extends JPanel {
	private int width;
	private int height;
	private ArrayList<Player> players;
	private JButton info;
	private JButton data;
	private int state = 0;
	private JScrollPane js;

	LineUpPanel(int width, int height, ArrayList<Player> players) {
		this.width = width;
		this.height = height;
		this.players = players;
		this.setLayout(null);
		setButton();
		setTabelPanel();

	}

	private void setTabelPanel() {
		LineUpTable table = new LineUpTable(players, 0);
		js = new JScrollPane(table);
		js.setBounds(0, height * 1 / 20, width, height * 19 / 20);
		this.add(js);
	}

	private void setButton() {
		info = new JButton();
		info.setBackground(new Color(42, 108, 182));
		info.setText("信息");
		info.setBounds(width * 3 / 5, height * 1 / 160, width * 1 / 20,
				height * 3 / 80);
		info.setBorder(null);
		info.setFocusPainted(false);
		info.setForeground(Color.white);
		info.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 20 * width / 1920));
		info.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				ChangeActionPerformed(e);
				state = 0;

			}

		});
		this.add(info);

		data = new JButton();
		data.setBackground(new Color(26, 71, 123));
		data.setText("数据");
		data.setBounds(width * 13 / 20, height * 1 / 160, width * 1 / 20,
				height * 3 / 80);
		data.setBorder(null);
		data.setFocusPainted(false);
		data.setForeground(Color.white);
		data.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 20 * width / 1920));
		data.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				ChangeActionPerformed(e);
				state = 1;

			}

		});
		this.add(data);

	}

	public void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		Graphics2D g = (Graphics2D) g2.create();
		g.setColor(new Color(30, 81, 140));
		g.fillRect(0, 0, width, height / 20);

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.white);
		g.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, height / 30));
		g.drawString("阵容", width / 20, height / 30);
	}

	private void ChangeActionPerformed(ActionEvent e) {

		if ((JButton) e.getSource() == (info)) {
			info.setBackground(new Color(42, 108, 182));
			data.setBackground(new Color(26, 71, 123));
			if (state != 0) {
				//js.removeAll();
				LineUpTable table = new LineUpTable(players, 0);
				table.setBounds(0, 0, width, height*9/10);
				js.setViewportView(table);
				js.updateUI();

			}

		} else if ((JButton) e.getSource() == (data)) {
			data.setBackground(new Color(42, 108, 182));
			info.setBackground(new Color(26, 71, 123));

			if (state != 1) {
				//js.removeAll();
				LineUpTable table = new LineUpTable(players, 1);
				table.setBounds(0, 0, width, height*9/10);
				js.setViewportView(table);
				js.updateUI();

			}
		}
		this.validate();
		this.repaint();

	}

	private class LineUpTable extends BaseJTable {
		
		private ArrayList<Image> imageList;
		private String[] infoColumnName = { "姓名", "位置", "体重", "身高", "球号", "生日",
				"经验", "进入NBA之前" };
		private String[] dataColumnName = { "球员", "场数", "先发", "分钟", "%", "三分%",
				"罚球%", "进攻", "防守", "场均篮板", "场均助攻", "场均抢断", "场均盖帽", "失误", "犯规",
				"场均得分" };
		protected DecimalFormat df = new DecimalFormat("#0.0");
		private int portraitWidth = 70;
		private int portraitHeight = 60;

		// type :0表示信息，1表示数据
		public LineUpTable(ArrayList<Player> playerList, int type) {
			
			this.setShowGrid(false);
			this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			this.setFont(new Font("微软雅黑", Font.PLAIN, 16));
			this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			adjustHeader();
			this.setTableHeaderColor(new Color(158,158,158));

			String[] columnName = null;
			if (type == 0) {
				columnName = infoColumnName;
			} else {
				columnName = dataColumnName;
			}
			DefaultTableModel model = new DefaultTableModel(null, columnName);
			int size = playerList.size();
			System.out.println("球员列表"+size);
			imageList = new ArrayList<Image>();
			//存疑
			for (int i = 0; i < size-5; i++) {
				String[] s = null;
				if (type == 0) {
					s = getInfoRow(playerList.get(i));
				} else {
					s = getDataRow(playerList.get(i));
				}

				imageList.add(playerList.get(i).getPortrait());
				model.addRow(s);

			}
			
			this.setModel(model);
				
			
			this.resizeColumnWidth();
			this.paintRow();
			this.updateRowHeights();
			this.repaint();
		}

		private String[] getInfoRow(Player p) {
			String[] s = new String[9];
			s[0] = p.getName();
			System.out.println(p.getName());
			s[1] = p.getPosition() + "";
			s[2] = p.getHeight()[0] + "尺" + p.getHeight()[1] + "寸";
			s[3] = p.getWeight() + "";
			s[4] = p.getNumber() + "";
			s[5] = p.getBirthday() + "";
			s[6] = p.getExperience() + "年";
			s[7] = p.getSchool();

			return s;
		}

		private String[] getDataRow(Player p) {

			String[] s = new String[16];
			s[0] = p.getName() + "";
			s[1] = p.getGamePlayed() + "";
			s[2] = p.getGameStarted() + "";
			s[3] = df.format(p.getAverageMinutes()) + "";
			s[4] = df.format(p.getFieldGoalsPercentage() * 100) + "";
			s[5] = df.format(p.getThreePointersPercentage() * 100) + "";
			s[6] = df.format(p.getFreeThrowsPercentage() * 100) + "";
			s[7] = df.format(p.getAverageOffenseRebounds()) + "";
			s[8] = df.format(p.getAverageDefenseRebounds()) + "";
			s[9] = df.format(p.getAverageRebounds()) + "";
			s[10] = df.format(p.getAverageAssists()) + "";
			s[11] = df.format(p.getAverageSteals()) + "";
			s[12] = df.format(p.getAverageBlockShots()) + "";
			s[13] = df.format(p.getAverageTurnOver()) + "";
			s[14] = df.format(p.getAverageFouls()) + "";
			s[15] = df.format(p.getAveragePoints()) + "";

			return s;
		}

		protected void paintRow() {
			TableColumnModel tcm = this.getColumnModel();
			for (int i = 0, n = tcm.getColumnCount(); i < n; i++) {
				TableColumn tc = tcm.getColumn(i);
				tc.setCellRenderer(new RowRenderer());
			}
		}

		// 重写行方法(paintRow())具体对应的类
		protected class RowRenderer extends DefaultTableCellRenderer {
			public Component getTableCellRendererComponent(JTable t,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {

				// 设置内容居中
				
				setHorizontalAlignment(SwingConstants.CENTER);
				

				// 设置奇偶行的背景色，可在此根据需要进行修改
				if (row % 2 == 0)
					setBackground(new Color(255, 255, 255));
				else
					setBackground(new Color(246, 246, 246));

				// 设置图片
				if (column == 0) {

					ImageIcon icon = new ImageIcon(UIUtils.resize(
							imageList.get(row), portraitWidth, portraitHeight));
					setIcon(icon);

				}

				return super.getTableCellRendererComponent(t, value,
						isSelected, hasFocus, row, column);

			}
		}

	}

	public static void main(String[] args) {
		BLService bl = BLController.getInstance();
		bl.init();
		while(bl.getProgress()<9){
			System.out.println(bl.getProgress());
		}
		ArrayList<String> nameList = bl.getAllTeams().get(0).getPlayerList();
		ArrayList<Player> playerList1 = new ArrayList<Player>();
		for (int i = 0; i < nameList.size(); i++) {
			playerList1.add((bl.getPlayerByName(nameList
					.get(i))));
		}
		LineUpPanel m = new LineUpPanel(1920,1280,playerList1);
		m.setBounds(0, 0, 1920,1280);
		JFrame f = new JFrame();
		f.add(m);
		f.setSize(1920,1280);
		f.setVisible(true);
	}
}

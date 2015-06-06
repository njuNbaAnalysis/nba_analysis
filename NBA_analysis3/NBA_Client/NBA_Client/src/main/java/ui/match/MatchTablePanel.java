package ui.match;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import BLservice.BLservice;
import ui.statistics.BaseJTable;
import vo.Matchvo;
import vo.RecordOfPlayervo;

public class MatchTablePanel extends JPanel {
	protected Matchvo match;
	protected MatchJTable matchTable;
	private BLservice bl;
	private int width;
	private int height;
	private static String[] columnName = { "姓名", "分钟", "%", "命中", "出手",
			"三分%", "三分命中", "三分出手", "罚球%", "罚球命中", "罚球出手", "+/-", "进攻", "防守",
			"篮板", "助攻", "犯规", "抢断", "失误", "盖帽", "得分" };

	public MatchTablePanel(int width, int height, Matchvo match, BLservice bl) {
		this.width = width;
		this.height = height;
		this.match = match;
		this.bl = bl;
		this.setLayout(null);

		MatchJTable firstTeamRecord = new MatchJTable(
				match.getFirstRecordList());
		// firstTeamRecord.setBounds(0, height / 20, width, height*8/20);
		JScrollPane js = new JScrollPane(firstTeamRecord);
		js.setBounds(0, height / 20, width, height * 8 / 20);
		this.add(js);

		MatchJTable secondTeamRecord = new MatchJTable(
				match.getSecondRecordList());
		// secondTeamPanel.setBounds(0, height*11 / 20, width, height*8/20);
		JScrollPane secondTeamPanel = new JScrollPane(secondTeamRecord);
		secondTeamPanel.setBounds(0, height * 11 / 20, width, height * 8 / 20);
		this.add(secondTeamPanel);

	}

	public void paintComponent(Graphics g) {
		g.setColor(new Color(238, 238, 238));
		g.fillRect(width / 20, 0, width * 9 / 10, height / 20);
		// 第一个球队姓名
		g.setColor(Color.BLACK);
		g.setFont(new Font("微软雅黑", Font.PLAIN, height / 20));
		/*g.drawString(match.getTeams()[0], width / 20, height / 20);

		Image teamImage1 = bl.getTeamByTeamName(match.getTeams()[0]).getLogo();
		g.drawImage(teamImage1, 0, 0, this);*/

		g.setColor(new Color(238, 238, 238));
		g.fillRect(width / 20, height * 9 / 20, width * 9 / 10, height * 2 / 20);
		// 第二个球队姓名
		g.setColor(Color.BLACK);
		g.setFont(new Font("微软雅黑", Font.PLAIN, height / 20));
	/*	g.drawString(match.getTeams()[1], width / 20, height * 11 / 20);

		Image teamImage2 = bl.getTeamByTeamName(match.getTeams()[1]).getLogo();
		g.drawImage(teamImage2, 0, height * 10 / 20, this);*/
	}

	private class MatchJTable extends BaseJTable {
		protected DecimalFormat df = new DecimalFormat("#0.0");
		private ArrayList<RecordOfPlayervo> recordList;

		public MatchJTable(ArrayList<RecordOfPlayervo> recordList) {
			this.recordList = recordList;

			this.setShowGrid(false);
			this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			this.setFont(new Font("微软雅黑", Font.PLAIN, 16));
			this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			adjustHeader();
			this.setTableHeaderColor(new Color(158, 158, 158));

			DefaultTableModel model = new DefaultTableModel(null, columnName);
			int size = recordList.size();
			for (int i = 0; i < size + 2; i++) {
				String[] s = new String[22];
				if (i == size) {
					s[0] = "总计";
				} else if (i == size + 1) {
					s = getTotalData();
				} else {
					s = getIndividualDataRow(recordList.get(i));
				}

				model.addRow(s);

			}

			this.setModel(model);
			this.updateRowHeights();
			this.paintRow();
			this.resizeColumnWidth();
			this.repaint();
		}

		// 没有写
		private String[] getTotalData() {
			// TODO Auto-generated method stub
			return null;
		}
		//球员位置不要了
		private String[] getIndividualDataRow(RecordOfPlayervo recordOfPlayer) {

			String[] data = new String[22];
			data[0] = recordOfPlayer.getPlayerName();
			data[1] = df.format(recordOfPlayer.getMinutes() / 60);
			data[2] = df.format(recordOfPlayer.getFieldGoalPercentage() * 100);
			data[3] = Integer.toString(recordOfPlayer.getFieldGoalHits());
			data[4] = Integer.toString(recordOfPlayer.getFieldGoalAttempts());
			data[5] = df.format(recordOfPlayer.getThreePointPercentage() * 100);
			data[6] = Integer.toString(recordOfPlayer.getThreePointHits());
			data[7] = Integer.toString(recordOfPlayer.getThreePointAttemps());
			data[8] = df.format(recordOfPlayer.getFreeThrowPercentage() * 100);
			data[9] = Integer.toString(recordOfPlayer.getFreeThrowHits());
			data[10] = Integer.toString(recordOfPlayer.getFreeThrowAttemps());
			data[11] = df.format(recordOfPlayer.getEfficiency());
			data[12] = Integer.toString(recordOfPlayer.getOffensiveRebounds());
			data[13] = Integer.toString(recordOfPlayer.getDefensiveRebounds());
			data[14] = Integer.toString(recordOfPlayer.getRebounds());
			data[15] = Integer.toString(recordOfPlayer.getAssists());
			data[16] = Integer.toString(recordOfPlayer.getFauls());
			data[17] = Integer.toString(recordOfPlayer.getSteals());
			data[18] = Integer.toString(recordOfPlayer.getTurnOver());
			data[19] = Integer.toString(recordOfPlayer.getBlocks());
			data[20] = Integer.toString(recordOfPlayer.getPoints());
			return data;
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
				if (row == recordList.size())
					setBackground(new Color(218, 218, 218));
				else if (row % 2 == 0)
					setBackground(new Color(255, 255, 255));
				else
					setBackground(new Color(246, 246, 246));

				return super.getTableCellRendererComponent(t, value,
						isSelected, hasFocus, row, column);

			}

		}
	}
}
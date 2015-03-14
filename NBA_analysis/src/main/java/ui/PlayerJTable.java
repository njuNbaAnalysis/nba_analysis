package ui;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.table.DefaultTableModel;

import compare.PlayerAveragePointComparator;
import compare.PlayerPointComparator;
import logic.players.Player;

public class PlayerJTable extends StatJTable {
	private static String[] averageColumn = { "排名", "球员", "球队", "场数", "先发",
			"篮板", "助攻", "在场时间", "效率", "GmSc 效率值", "投篮命中率%", "三分命中率%", "罚球命中率%",
			"进攻数", "防守数", "抢断数", "盖帽数", "失误数", "犯规数", " 得分", "真实命中率%", "投篮效率%",
			"篮板率%", "进攻篮板率%", "防守篮板率%", "助攻率%", "抢断率%", "盖帽率%", "失误率%", "使用率%" };
	private static String[] totalColumn = { "排名", "球员", "球队", "场数", "先发", "篮板",
			"助攻", "在场时间", "投篮命中率%", "三分命中率%", "罚球命中率%", "进攻数", "防守数", "抢断数",
			"盖帽数", "失误数", "犯规数", " 得分" };

	public void refresh(ArrayList<Player> list, boolean selected) {
		
		Comparator c;
		
		
		String[] columnNames;
		if (selected) {
			columnNames = averageColumn;
			c = new PlayerAveragePointComparator();

		} else {
			columnNames = totalColumn;
			c = new PlayerPointComparator();
			//c = new PlayerAveragePointComparator();
		}
		DefaultTableModel model = new DefaultTableModel(null, columnNames);
		Collections.sort(list,c);
		//this.setModel(model);

		//DefaultTableModel tableModel = (DefaultTableModel) this.getModel();
		//tableModel.setRowCount(0);// 清除原有行
		imageList = new ArrayList<Image>();

		for (int i = 0; i < list.size(); i++) {
			String[] s = null;
			if (selected) {
				s = getAverageDataRow(list.get(i), i);
			} else {
				s = getTotalDataRow(list.get(i), i);
			}
			// 添加数据到表格

			model.addRow(s);
			imageList.add(list.get(i).getPortrait());

		}

		// 更新表格
		this.setModel(model);
		this.paintRow();
		this.updateRowHeights();
		this.resizeColumnWidth();
		this.validate();
		this.repaint();

	}

	private String[] getAverageDataRow(Player p, int i) {

		String[] s = new String[30];
		s[0] = (i + 1) + "";
		s[1] = p.getName() + "";
		s[2] = p.getTeam() + "";
		s[3] = p.getGamePlayed() + "";
		s[4] = p.getGameStarted() + "";
		s[5] = df.format(p.getAverageRebounds()) + "";
		s[6] = df.format(p.getAverageAssists()) + "";
		s[7] = df.format(p.getAverageMinutes()) + "";
		s[8] = df.format(p.getAverageEfficiency()) + "";
		s[9] = df.format(p.getAverageGmSc()) + "";
		s[10] = df.format(p.getFieldGoalsPercentage() * 100) + "";

		s[11] = df.format(p.getThreePointersPercentage() * 100) + "";
		s[12] = df.format(p.getFreeThrowsPercentage() * 100) + "";
		s[13] = df.format(p.getAverageOffenseRebounds()) + "";
		s[14] = df.format(p.getAverageDefenseRebounds()) + "";
		s[15] = df.format(p.getAverageSteals()) + "";
		s[16] = df.format(p.getAverageBlockShots()) + "";
		s[17] = df.format(p.getAverageTurnOver()) + "";
		s[18] = df.format(p.getAverageFouls()) + "";
		s[19] = df.format(p.getAveragePoints()) + "";

		s[20] = df.format(p.getTrueShootingPercentage() * 100) + "";
		s[21] = df.format(p.getShootingEfficiency() * 100) + "";
		s[22] = df.format(p.getReboundsPercentage() * 100) + "";
		s[23] = df.format(p.getOffenseReboundsPercentage() * 100) + "";
		s[24] = df.format(p.getDefenseReboundsPercentage() * 100) + "";
		s[25] = df.format(p.getAssistsPercentage() * 100) + "";
		s[26] = df.format(p.getStealsPercentage() * 100) + "";
		s[27] = df.format(p.getBlockShotsPercentage() * 100) + "";
		s[28] = df.format(p.getTurnOverPercentage() * 100) + "";
		s[29] = df.format(p.getUsage() * 100) + "";
		return s;
	}

	private String[] getTotalDataRow(Player p, int i) {
		String[] s = new String[18];
		s[0] = (i + 1) + "";
		s[1] = p.getName() + "";
		s[2] = p.getTeam() + "";
		s[3] = p.getGamePlayed() + "";
		s[4] = p.getGameStarted() + "";
		s[5] = p.getRebounds() + "";
		s[6] = p.getAssists() + "";
		s[7] = p.getMinutes() / 60 + "";
		s[8] = df.format(p.getFieldGoalsPercentage() * 100) + "";
		s[9] = df.format(p.getThreePointersPercentage() * 100) + "";
		s[10] = df.format(p.getFreeThrowsPercentage() * 100) + "";
		s[11] = p.getOffenseRebounds() + "";
		s[12] = p.getDefenseRebounds() + "";
		s[13] = p.getSteals() + "";
		s[14] = p.getRebounds() + "";
		s[15] = p.getTurnOver() + "";
		s[16] = p.getFouls() + "";
		s[17] = p.getPoints() + "";
		return s;

	}
}

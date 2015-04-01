package compare;

import java.util.ArrayList;
import java.util.Comparator;

import logic.BLParameter;
import logic.BLParameter.Sort;
import logic.players.Player;
import logic.teams.Team;

public class PlayerComparator implements Comparator<Player> {

	private ArrayList<Sort> sortList;
	private boolean isAvarage;

	public PlayerComparator(BLParameter parameter) {
		super();
		this.sortList = parameter.getSortList();
		this.isAvarage = parameter.isAvarage();
	}

	@Override
	public int compare(Player o1, Player o2) {
		for (Sort token : sortList) {
			int result = 0;
			// 默认升序排列，如果降序则取负
			switch (token.getField()) {
			case "point":
			case "score":
				if (isAvarage) {
					if (o1.getAveragePoints() < o2.getAveragePoints()) {
						result = -1;
					} else if (o1.getAveragePoints() > o2.getAveragePoints()) {
						result = 1;
					}
				} else {
					if (o1.getPoints() < o2.getPoints()) {
						result = -1;
					} else if (o1.getPoints() > o2.getPoints()) {
						result = 1;
					}
				}
				break;
			case "rebound":
				if (isAvarage) {
					if (o1.getAverageRebounds() < o2.getAverageRebounds()) {
						result = -1;
					} else if (o1.getAverageRebounds() > o2
							.getAverageRebounds()) {
						result = 1;
					}
				} else {
					if (o1.getRebounds() < o2.getRebounds()) {
						result = -1;
					} else if (o1.getRebounds() > o2.getRebounds()) {
						result = 1;
					}
				}
				break;
			case "assist":
				if (isAvarage) {
					if (o1.getAverageAssists() < o2.getAverageAssists()) {
						result = -1;
					} else if (o1.getAverageAssists() > o2.getAverageAssists()) {
						result = 1;
					}
				} else {
					if (o1.getAssists() < o2.getAssists()) {
						result = -1;
					} else if (o1.getAssists() > o2.getAssists()) {
						result = 1;
					}
				}
				break;
			case "blockShot":
				if (isAvarage) {
					if (o1.getAverageBlockShots() < o2.getAverageBlockShots()) {
						result = -1;
					} else if (o1.getAverageBlockShots() > o2
							.getAverageBlockShots()) {
						result = 1;
					}
				} else {
					if (o1.getBlockShots() < o2.getBlockShots()) {
						result = -1;
					} else if (o1.getBlockShots() > o2.getBlockShots()) {
						result = 1;
					}
				}
				break;
			case "steal":
				if (isAvarage) {
					if (o1.getAverageSteals() < o2.getAverageSteals()) {
						result = -1;
					} else if (o1.getAverageSteals() > o2.getAverageSteals()) {
						result = 1;
					}
				} else {
					if (o1.getSteals() < o2.getSteals()) {
						result = -1;
					} else if (o1.getSteals() > o2.getSteals()) {
						result = 1;
					}
				}
				break;
			case "foul":
				if (isAvarage) {
					if (o1.getAverageFouls() < o2.getAverageFouls()) {
						result = -1;
					} else if (o1.getAverageFouls() > o2.getAverageFouls()) {
						result = 1;
					}
				} else {
					if (o1.getFouls() < o2.getFouls()) {
						result = -1;
					} else if (o1.getFouls() > o2.getFouls()) {
						result = 1;
					}
				}
				break;
			case "fault":
				if (isAvarage) {
					if (o1.getAverageTurnOver() < o2.getAverageTurnOver()) {
						result = -1;
					} else if (o1.getAverageTurnOver() > o2
							.getAverageTurnOver()) {
						result = 1;
					}
				} else {
					if (o1.getTurnOver() < o2.getTurnOver()) {
						result = -1;
					} else if (o1.getTurnOver() > o2.getTurnOver()) {
						result = 1;
					}
				}
				break;
			case "minute":
				if (isAvarage) {
					if (o1.getAverageMinutes() < o2.getAverageMinutes()) {
						result = -1;
					} else if (o1.getAverageMinutes() > o2.getAverageMinutes()) {
						result = 1;
					}
				} else {
					if (o1.getMinutes() < o2.getMinutes()) {
						result = -1;
					} else if (o1.getMinutes() > o2.getMinutes()) {
						result = 1;
					}
				}
				break;
			case "efficient":
				if (isAvarage) {
					if (o1.getAverageEfficiency() < o2.getAverageEfficiency()) {
						result = -1;
					} else if (o1.getAverageEfficiency() > o2
							.getAverageEfficiency()) {
						result = 1;
					}
				} else {
					if (o1.getEfficiency() < o2.getEfficiency()) {
						result = -1;
					} else if (o1.getEfficiency() > o2.getEfficiency()) {
						result = 1;
					}
				}
				break;
			case "shot":
				if (o1.getFieldGoalsPercentage() < o2.getFieldGoalsPercentage()) {
					result = -1;
				} else if (o1.getFieldGoalsPercentage() > o2
						.getFieldGoalsPercentage()) {
					result = 1;
				}
				break;
			case "three":
				if (o1.getThreePointersPercentage() < o2
						.getThreePointersPercentage()) {
					result = -1;
				} else if (o1.getThreePointersPercentage() > o2
						.getThreePointersPercentage()) {
					result = 1;
				}
				break;
			case "penalty":
				if (o1.getFreeThrowsPercentage() < o2.getFreeThrowsPercentage()) {
					result = -1;
				} else if (o1.getFreeThrowsPercentage() > o2
						.getFreeThrowsPercentage()) {
					result = 1;
				}
				break;
			case "defendRebound":
				if (isAvarage) {
					if (o1.getAverageDefenseRebounds() < o2
							.getAverageDefenseRebounds()) {
						result = -1;
					} else if (o1.getAverageDefenseRebounds() > o2
							.getAverageDefenseRebounds()) {
						result = 1;
					}
				} else {
					if (o1.getDefenseRebounds() < o2.getDefenseRebounds()) {
						result = -1;
					} else if (o1.getDefenseRebounds() > o2
							.getDefenseRebounds()) {
						result = 1;
					}
				}
				break;
			case "offendRebound":
				if (isAvarage) {
					if (o1.getAverageOffenseRebounds() < o2
							.getAverageOffenseRebounds()) {
						result = -1;
					} else if (o1.getAverageOffenseRebounds() > o2
							.getAverageOffenseRebounds()) {
						result = 1;
					}
				} else {
					if (o1.getOffenseRebounds() < o2.getOffenseRebounds()) {
						result = -1;
					} else if (o1.getOffenseRebounds() > o2
							.getOffenseRebounds()) {
						result = 1;
					}
				}
				break;
			case "doubleTwo":
				if (isAvarage) {
					if (o1.getAverageDoubleTwo() < o2.getAverageDoubleTwo()) {
						result = -1;
					} else if (o1.getAverageDoubleTwo() > o2
							.getAverageDoubleTwo()) {
						result = 1;
					}
				} else {
					if (o1.getDoubledouble() < o2.getDoubledouble()) {
						result = -1;
					} else if (o1.getDoubledouble() > o2.getDoubledouble()) {
						result = 1;
					}
				}
				break;

			case "realShot":
				if (o1.getTrueShootingPercentage() < o2
						.getTrueShootingPercentage()) {
					result = -1;
				} else if (o1.getTrueShootingPercentage() > o2
						.getTrueShootingPercentage()) {
					result = 1;
				}
				break;
			case "GmSc":
				if (o1.getGmsc() < o2.getGmsc()) {
					result = -1;
				} else if (o1.getGmsc() > o2.getGmsc()) {
					result = 1;
				}
				break;
			case "shotEfficient":
				if (o1.getShootingEfficiency() < o2.getShootingEfficiency()) {
					result = -1;
				} else if (o1.getShootingEfficiency() > o2
						.getShootingEfficiency()) {
					result = 1;
				}
				break;
			case "offendReboundEfficient":
				if (o1.getOffenseReboundsPercentage() < o2
						.getOffenseReboundsPercentage()) {
					result = -1;
				} else if (o1.getOffenseReboundsPercentage() > o2
						.getOffenseReboundsPercentage()) {
					result = 1;
				}
				break;
			case "defendReboundEfficient":
				if (o1.getDefenseReboundsPercentage() < o2
						.getDefenseReboundsPercentage()) {
					result = -1;
				} else if (o1.getDefenseReboundsPercentage() > o2
						.getDefenseReboundsPercentage()) {
					result = 1;
				}
				break;
			case "stealEfficient":
				if (o1.getStealsPercentage() < o2.getStealsPercentage()) {
					result = -1;
				} else if (o1.getStealsPercentage() > o2.getStealsPercentage()) {
					result = 1;
				}
				break;
			case "assistEfficient":
				if (o1.getAssistsPercentage() < o2.getAssistsPercentage()) {
					result = -1;
				} else if (o1.getAssistsPercentage() > o2
						.getAssistsPercentage()) {
					result = 1;
				}
				break;
			case "blockShotEfficient":
				if (o1.getBlockShotsPercentage() < o2.getBlockShotsPercentage()) {
					result = -1;
				} else if (o1.getBlockShotsPercentage() > o2
						.getBlockShotsPercentage()) {
					result = 1;
				}
				break;
			case "faultEfficient":
				if (o1.getTurnOverPercentage() < o2.getTurnOverPercentage()) {
					result = -1;
				} else if (o1.getTurnOverPercentage() > o2
						.getTurnOverPercentage()) {
					result = 1;
				}
				break;
			case "frequency":
				if (o1.getUsage() < o2.getUsage()) {
					result = -1;
				} else if (o1.getUsage() > o2.getUsage()) {
					result = 1;
				}
				break;
			default:
				System.out.println("error in PlayerrComparator,unknown field: "
						+ token.getField());
			}

			if (!token.isAsc()) {
				result *= -1;
			}

			if (result != 0) {
				return result;
			}
		}

		return 0;
	}

}

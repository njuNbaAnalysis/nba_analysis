package compare;

import java.util.ArrayList;
import java.util.Comparator;

import logic.BLParameter;
import logic.BLParameter.Sort;
import logic.teams.Team;

public class TeamComparator implements Comparator<Team> {

	private ArrayList<Sort> sortList;
	private boolean isAvarage;

	public TeamComparator(BLParameter parameter) {
		super();
		this.sortList = parameter.getSortList();
		this.isAvarage = parameter.isAvarage();
	}

	@Override
	public int compare(Team o1, Team o2) {
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
					if (o1.getDefensiveRebounds() < o2.getDefensiveRebounds()) {
						result = -1;
					} else if (o1.getDefensiveRebounds() > o2
							.getDefensiveRebounds()) {
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
					if (o1.getOffensiveRebounds() < o2.getOffensiveRebounds()) {
						result = -1;
					} else if (o1.getOffensiveRebounds() > o2
							.getOffensiveRebounds()) {
						result = 1;
					}
				}
				break;

			case "winRate":
				if (o1.getWinningPercentage() < o2.getWinningPercentage()) {
					result = -1;
				} else if (o1.getWinningPercentage() > o2
						.getWinningPercentage()) {
					result = 1;
				}
				break;
			case "offendRound":
				if (isAvarage) {
					if (o1.getAverageOffendRounds() < o2
							.getAverageOffendRounds()) {
						result = -1;
					} else if (o1.getAverageOffendRounds() > o2
							.getAverageOffendRounds()) {
						result = 1;
					}
				} else {
					if (o1.getOffensiveRounds() < o2.getOffensiveRounds()) {
						result = -1;
					} else if (o1.getOffensiveRounds() > o2
							.getOffensiveRounds()) {
						result = 1;
					}
				}
				break;
			case "offendEfficient":
				if (o1.getOffenseEfficiency() < o2.getOffenseEfficiency()) {
					result = -1;
				} else if (o1.getOffenseEfficiency() > o2
						.getOffenseEfficiency()) {
					result = 1;
				}
				break;
			case "defendEfficient":
				if (o1.getDefenseEfficiency() < o2.getDefenseEfficiency()) {
					result = -1;
				} else if (o1.getDefenseEfficiency() > o2
						.getDefenseEfficiency()) {
					result = 1;
				}
				break;
			case "offendReboundEfficient":
				if (o1.getOffensiveReboundsEfficiency() < o2
						.getOffensiveReboundsEfficiency()) {
					result = -1;
				} else if (o1.getOffensiveReboundsEfficiency() > o2
						.getOffensiveReboundsEfficiency()) {
					result = 1;
				}
				break;
			case "defendReboundEfficient":
				if (o1.getDefensiveReboundsEfficiency() < o2
						.getDefensiveReboundsEfficiency()) {
					result = -1;
				} else if (o1.getDefensiveReboundsEfficiency() > o2
						.getDefensiveReboundsEfficiency()) {
					result = 1;
				}
				break;
			case "stealEfficient":
				if (o1.getStealsEfficiency() < o2.getStealsEfficiency()) {
					result = -1;
				} else if (o1.getStealsEfficiency() > o2.getStealsEfficiency()) {
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

			default:
				System.out.println("error in TeamComparator,unknown field: "
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

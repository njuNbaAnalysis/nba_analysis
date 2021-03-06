package logic;

import java.util.ArrayList;

import util.Tools;

/**
 * 此类旨在简化BL层对命令输入的解析过程，将team与player已整合在一起
 * 界面层可以使用String[]进行类的构造，也可以构造空类，然后对属性项进行逐一设置
 * 对于mode和filter，请直接set,不要get到默认的之后在set回去
 */
public class BLParameter {

	private boolean isPlayer;// player or team

	private Mode mode = new Mode("all", null, false);// 可以不初始化
	private ArrayList<Filter> filterList = new ArrayList<Filter>(); // 可以不初始化
	private ArrayList<Sort> sortList = new ArrayList<Sort>();// 如果没有则为空，可以不初始化

	// 独立，可选
	private int number = 50;// 前多少条信息，默认最大
	private boolean isHigh = false;// 是否为高阶数据
	private boolean isAverage = true;// 是否为平均数，默认是

	public class Mode {
		private String mode = "all"; // all or hot or king，默认all
		private String field; // score or rebound or assist
		private boolean isDaily; // season or daily

		public Mode() {
			super();
		}

		public Mode(String mode, String field, boolean isDaily) {
			super();
			this.mode = mode;
			this.field = field;
			this.isDaily = isDaily;
		}

		public String getMode() {
			return mode;
		}

		public void setMode(String mode) {
			this.mode = mode;
		}

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public boolean isDaily() {
			return isDaily;
		}

		public void setDaily(boolean isDaily) {
			this.isDaily = isDaily;
		}

	}

	// 只有player适用，可选
	public class Filter {
		private String filterName;// position or league or age
		private String filterValue;
		private int[] range; // 2位整数数组，将会用到Integer.MAX,MIN

		public Filter() {
			super();
			range = new int[] { Integer.MIN_VALUE, Integer.MAX_VALUE };
			filterValue = "All";
		}

		public String getFilterName() {
			return filterName;
		}

		public void setFilterName(String filterName) {
			this.filterName = filterName;
		}

		public String getFilterValue() {
			return filterValue;
		}

		public void setFilterValue(String filterValue) {
			this.filterValue = filterValue;
		}

		public int[] getRange() {
			return range;
		}

		public void setRange(int[] range) {
			this.range = range;
		}

	}

	// 可选
	public class Sort {
		private String field;
		private boolean isAsc;// 是否升序

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public boolean isAsc() {
			return isAsc;
		}

		public void setAsc(boolean isAsc) {
			this.isAsc = isAsc;
		}

		public Sort(String field, boolean isAsc) {
			super();
			this.field = field;
			this.isAsc = isAsc;
		}

		public Sort() {
			super();
		}

	}

	public BLParameter(String[] args) {
		// 采用从前往后理解的方式进行读取命令
		ArrayList<String> input = Tools.toArrayList(args);

		int debugTimes = 20;
		while (input.size() != 0) {
			debugTimes--;
			if (debugTimes == 0)
				break;

			switch (input.get(0)) {
			case "-player":
				this.setPlayer(true);
				input.remove(0);
				break;
			case "-team":
				this.setPlayer(false);
				input.remove(0);
				break;

			case "-avg":
				input.remove(0);
				break;
			case "-total":
				this.setAvarage(false);
				input.remove(0);
				break;

			case "-high":
				this.setHigh(true);
				input.remove(0);
				break;

			case "-n":
				int number = Integer.parseInt(input.get(1));
				this.setNumber(number);
				input.remove(1);
				input.remove(0);
				break;

			case "-all":
				input.remove(0);
				break;
			case "-hot":
				String field = input.get(1);
				BLParameter.Mode mode = new BLParameter.Mode();
				mode.setMode("hot");
				mode.setField(field);
				this.addSort(new Sort(field, false));
				this.setMode(mode);
				input.remove(1);
				input.remove(0);
				break;
			case "-king":
				String fieldKing = input.get(1);
				String during = input.get(2);
				BLParameter.Mode modeKing = new BLParameter.Mode();
				modeKing.setMode("king");
				modeKing.setField(fieldKing);
				this.addSort(new Sort(fieldKing, false));
				if (during.equals("-season")) {
					modeKing.setDaily(false);
				} else {
					modeKing.setDaily(true);
				}
				this.setMode(modeKing);
				input.remove(2);
				input.remove(1);
				input.remove(0);
				break;

			case "-filter":
				ArrayList<Filter> filterList = new ArrayList<Filter>();
				StringBuffer filterParameterBuffer = new StringBuffer("");
				String[] listFilter = null;
				int deleteNumber = 1; // 要删掉数据的数目
				// 将filter参数压缩成一个stringBuffer
				for (int i = 1; i < input.size(); i++) {
					if (input.get(i).contains("-")) {
						break;
					}

					else {
						filterParameterBuffer.append(input.get(i));
						deleteNumber++;
					}
				}

				// 将string解压成若干个string
				String filterParameter = filterParameterBuffer.toString();
				listFilter = filterParameter.split(",");

				for (String token : listFilter) {
					token = token.trim();
					String[] pair = token.split("\\.");
					BLParameter.Filter filter = new BLParameter.Filter();
					filter.setFilterName(pair[0]);
					if (filter.getFilterName().equals("age")) {
						// 处理此处为age的情况
						int[] range = new int[2];
						if (pair[1].equals("All")) {
							range[0] = Integer.MIN_VALUE;
							range[1] = Integer.MAX_VALUE;
						} else if (pair[1].contains("X")) {
							range[0] = Integer.parseInt(pair[1].split("<")[0]);
							range[1] = Integer.parseInt(pair[1].split("<=")[1]);
						} else if (pair[1].contains("<=")) {
							range[0] = Integer.MIN_VALUE;
							range[1] = Integer.parseInt(pair[1].split("<=")[1]);
						} else if (pair[1].contains(">")) {
							range[0] = Integer.parseInt(pair[1].split(">")[1]);
							range[1] = Integer.MAX_VALUE;
						} else {
							System.out.println("error in parse -filter: "
									+ pair[0] + " " + pair[1]);
						}

						filter.setRange(range);
					} else {
						filter.setFilterValue(pair[1]);
					}

					filterList.add(filter);
				}
				this.setFilterList(filterList);

				// remove,删掉deleteNumber个数据
				for (int i = deleteNumber; i >= 1; i--) {
					input.remove(0);
				}
				break;

			case "-sort":
				String[] listSort = input.get(1).split(",");
				for (String token : listSort) {
					String[] pair = token.split("\\.");
					BLParameter.Sort sort = new BLParameter.Sort();
					sort.setField(pair[0]);
					if (pair[1].equals("asc")) {
						sort.setAsc(true);
					} else {
						sort.setAsc(false);
					}
					this.addSort(sort);
				}
				input.remove(1);
				input.remove(0);
				break;

			default:
				System.out.println("BLParameter.constructor: " + input.get(0));
				break;
			}

		}

		// 如果sort并没有加任何东西，则对sort进行默认初始化
		if (this.sortList.size() == 0) {
			if (this.isPlayer) {
				if (!this.isHigh) {
					this.addSort(new Sort("point", false));
				} else {
					this.addSort(new Sort("realShot", false));
				}
			} else {
				if (!this.isHigh) {
					this.addSort(new Sort("point", false));
				} else {
					this.addSort(new Sort("winRate", false));
				}
			}
		}
	}

	public BLParameter() {
		super();
	}

	public boolean isPlayer() {
		return isPlayer;
	}

	public void setPlayer(boolean isPlayer) {
		this.isPlayer = isPlayer;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public ArrayList<Filter> getFilterList() {
		return filterList;
	}

	public void addFilter(Filter filter) {
		this.filterList.add(filter);
	}

	public void setFilterList(ArrayList<Filter> filterList) {
		this.filterList = filterList;
	}

	public void addSort(Sort sort) {
		this.sortList.add(sort);
	}

	public ArrayList<Sort> getSortList() {
		return sortList;
	}

	public void setSortList(ArrayList<Sort> sortList) {
		this.sortList = sortList;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isHigh() {
		return isHigh;
	}

	public void setHigh(boolean isHigh) {
		this.isHigh = isHigh;
	}

	public boolean isAvarage() {
		return isAverage;
	}

	public void setAvarage(boolean isAvarage) {
		this.isAverage = isAvarage;
	}

}

package compare;

import java.util.ArrayList;
import java.util.Comparator;

import vo.Playervo;


public class PalyerScreening {
	String location;
	String union;
	String depend;

	public PalyerScreening(String location, String union, String depend) {
		this.location = location;
		this.union = union;
		this.depend = depend;
	}

	public ArrayList<Playervo> screening(ArrayList<Playervo> list) {

		String div = null;
		char conference = 0;
		char position = 0;
		switch (location) {
		case "全部位置":
			break;

		case "中锋":
			position = 'C';
			break;
		case "后卫":
			position = 'G';
			break;
		case "前锋":
			position = 'F';
			break;

		}

		switch (union) {
		case "全部联盟":
			break;
		case "东部":
			conference = 'E';
			break;
		case "西部":
			conference = 'W';
			break;
		case "东南分区":
			div = "Southeast";
			break;
		case "中央分区":
			div = "Central";
			break;
		case "大西洋分区":
			div = "Atlantic";
			break;
		case "太平洋分区":
			div = "Pacific";
			break;
		case "西北分区":
			div = "Northwest";
			break;
		case "西南分区":
			div = "Southwest";
			break;
		}

		return screening(list, div,conference, position);

	}

	private ArrayList<Playervo> screening(ArrayList<Playervo> list, String div,
			char conference, char position) {
		ArrayList<Playervo> result = new ArrayList<Playervo>();
		for (Playervo p : list) {

			if (div != null && !(p.getDivision()==null)&&!p.getDivision().contains(div)) {
				continue;
			}
			if (conference != 0 && !(p.getConference().equals(conference))) {
				continue;
			}
			if (position != 0&&!(p.getPosition()==null)
					&& !(p.getPosition().contains(Character.toString(position)))) {
				continue;
			}
			result.add(p);
		}
		return result;

	}

	public String getDepend() {
		// TODO Auto-generated method stub
		return depend;
	}

}

package compare;

import java.util.ArrayList;
import java.util.Comparator;

import vo.Playervo;

public class PlayerScreening {
	String location;
	String union;
	String depend;

	public PlayerScreening(String location, String union, String depend) {
		this.location = location;
		this.union = union;
		this.depend = depend;
	}

	public ArrayList<Playervo> screening(ArrayList<Playervo> list) {

		String div = null;
		String conference = null;
		switch (union) {
		case "全部联盟":
			break;
		case "东部":
			conference = "eastern";
			break;
		case "西部":
			conference = "western";
			break;
		case "东南分区":
			div = "southeast";
			break;
		case "中央分区":
			div = "central";
			break;
		case "大西洋分区":
			div = "atlantic";
			break;
		case "太平洋分区":
			div = "pacific";
			break;
		case "西北分区":
			div = "northwest";
			break;
		case "西南分区":
			div = "southwest";
			break;
		}
		

		return screening(list, div,conference, location);

	}

	private ArrayList<Playervo> screening(ArrayList<Playervo> list, String div,
			String conference, String location) {
		ArrayList<Playervo> result = new ArrayList<Playervo>();
		for (Playervo p : list) {
			if (div != null && !(p.getDivision() == null)
					&& !p.getDivision().contains(div)) {
				continue;
			}
			if (conference != null && !(p.getConference().equals(conference))) {
				continue;
			}
			
			if(location.equals("全部位置")){
				result.add(p);
				continue;
			}
			
			if (location != null && !(p.getPosition() == null)
					&& !(p.getPosition().contains(location))) {
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

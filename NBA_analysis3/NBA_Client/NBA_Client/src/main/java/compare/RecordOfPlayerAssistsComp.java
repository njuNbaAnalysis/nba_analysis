package compare;

import java.util.Comparator;

import vo.RecordOfPlayervo;



public class RecordOfPlayerAssistsComp implements Comparator<RecordOfPlayervo> {

	@Override
	public int compare(RecordOfPlayervo o1, RecordOfPlayervo o2) {
		// TODO Auto-generated method stub
		return (int)(o2.getAssists() - o1.getAssists());
	}

}

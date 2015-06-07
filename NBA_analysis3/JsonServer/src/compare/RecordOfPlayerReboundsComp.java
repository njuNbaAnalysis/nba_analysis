package compare;

import java.util.Comparator;

import vo.RecordOfPlayervo;


public class RecordOfPlayerReboundsComp implements Comparator<RecordOfPlayervo> {

	public int compare(RecordOfPlayervo o1, RecordOfPlayervo o2) {
		// TODO Auto-generated method stub
		return (int)(o2.getRebounds() - o1.getRebounds());
	}

}

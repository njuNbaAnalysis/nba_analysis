package compare;

import java.util.Comparator;

import vo.PlayerVo;

public class PlayerPointComparator implements Comparator {

	public int compare(Object op1, Object op2) {
		PlayerVo p1 = (PlayerVo) op1;
		PlayerVo p2 = (PlayerVo) op2;

		// 按年龄排序
		return (p1.getPoint() - p2.getPoint());
	}

}

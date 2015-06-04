package po;

public class NBALiveTeam {

	private String Mid;
	private String Cname;
	private String Ename;
	
	public NBALiveTeam(String mid, String cname, String ename) {
		super();
		Mid = mid;
		Cname = cname;
		Ename = ename;
	}

	public String getMid() {
		return Mid;
	}

	public void setMid(String mid) {
		Mid = mid;
	}

	public String getCname() {
		return Cname;
	}

	public void setCname(String cname) {
		Cname = cname;
	}

	public String getEname() {
		return Ename;
	}

	public void setEname(String ename) {
		Ename = ename;
	}
	
}

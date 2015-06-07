package po;

public class PlayerName {
	private String Pid;
	private String Cname;
	private String Ename;
	
	public PlayerName(String pid, String cname, String ename) {
		super();
		Pid = pid;
		Cname = cname;
		Ename = ename;
	}

	public String getPid() {
		return Pid;
	}

	public void setPid(String pid) {
		Pid = pid;
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

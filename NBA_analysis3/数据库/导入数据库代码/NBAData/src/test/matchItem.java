package test;

public class matchItem {
	private String Mid;
	private Boolean ishome;
	private String[] num;
	public matchItem(String mid, Boolean ishome, String[] num) {
		super();
		Mid = mid;
		this.ishome = ishome;
		this.num = num;
	}
	public String getMid() {
		return Mid;
	}
	public void setMid(String mid) {
		Mid = mid;
	}
	public Boolean getIshome() {
		return ishome;
	}
	public void setIshome(Boolean ishome) {
		this.ishome = ishome;
	}
	public String[] getNum() {
		return num;
	}
	public void setNum(String[] num) {
		this.num = num;
	}
	
}

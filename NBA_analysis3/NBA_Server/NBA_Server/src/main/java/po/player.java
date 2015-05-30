package po;

public class player {
	private String Pid;
	private String name;
	private String position;
	private double height;
	private double weight;
	private String birthday;
	private String city;
	private String highschool;
	private String university;
	private String number;
	private String selected; // ѡ�����
	private String salary;
	public player(String Pid, String name, String position, double height,
			double weight, String birthday, String city, String highschool,
			String university, String number, String selected,String salary) {
		this.Pid = Pid;
		this.name = name;
		this.position = position;
		this.height = height;
		this.weight = weight;
		this.birthday = birthday;
		this.city = city;
		this.highschool = highschool;
		this.university = university;
		this.number = number;
		this.selected = selected;
		this.salary = salary;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getPid() {
		return Pid;
	}

	public void setPid(String pid) {
		Pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHighschool() {
		return highschool;
	}

	public void setHighschool(String highschool) {
		this.highschool = highschool;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}
	
	
}

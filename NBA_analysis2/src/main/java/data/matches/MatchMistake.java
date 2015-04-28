package data.matches;

public class MatchMistake {
	String name = null;
	Kind kind = Kind.NULL;

	public enum Kind {
		NULL, FIELD_GOAL // 出手错误：即出手命中次数大于出手次数
		, THREE_POINTER // 三分错误：即三分命中次数大于三分出手次数
		, FREE_THROW // 罚球错误：即罚球命中次数大于罚球出手次数
		, POINT // 得分错误：即原个人得分与出手命中数计算出的得分不同：
		, REBOUNDS // 篮板错误
		, TIME // 上场时间时间错误
	}

	public MatchMistake(String name, Kind kind) {
		this.name = name;
		this.kind = kind;
	}

	public String getName() {
		return name;
	}

	public Kind getKind() {
		return kind;
	}
}

package po;

public class TeamCode {
    private String abbr;
    private String code;
    public TeamCode() {
        super();
    }
    public TeamCode(String abbr, String code) {
        super();
        this.abbr = abbr;
        this.code = code;
    }
    public String getAbbr() {
        return abbr;
    }
    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    
    public String toString(){
        return "abbr:" + this.getAbbr() + " code:" + this.getCode(); 
    }
    
}

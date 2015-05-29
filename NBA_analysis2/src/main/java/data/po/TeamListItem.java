package data.po;

public class TeamListItem {
    private String teamNameEn;
    private String teamNameZh;
    private String conference;
    private String division;
    
    public TeamListItem() {
        super();
    }
    public TeamListItem(String teamNameEn, String teamNameZh, String conference, String division) {
        super();
        this.teamNameEn = teamNameEn;
        this.teamNameZh = teamNameZh;
        this.conference = conference;
        this.division = division;
    }
    public String getTeamNameEn() {
        return teamNameEn;
    }
    public void setTeamNameEn(String teamNameEn) {
        this.teamNameEn = teamNameEn;
    }
    public String getTeamNameZh() {
        return teamNameZh;
    }
    public void setTeamNameZh(String teamNameZh) {
        this.teamNameZh = teamNameZh;
    }
    public String getConference() {
        return conference;
    }
    public void setConference(String conference) {
        this.conference = conference;
    }
    public String getDivision() {
        return division;
    }
    public void setDivision(String division) {
        this.division = division;
    }
    
    
}

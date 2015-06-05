package po;

public class TeamListItem {
    private String teamNameEn;
    private String teamNameZh;
    private String conference;//例"eastern","western"
    private String division;//例"atlantic","southeast"
    private String teamNameZnAbbr;//例“子弹”
    private String homecourt;
    
    public TeamListItem(String teamNameEn, String teamNameZh, String conference, String division,
            String teamNameZnAbbr, String homecourt) {
        super();
        this.teamNameEn = teamNameEn;
        this.teamNameZh = teamNameZh;
        this.conference = conference;
        this.division = division;
        this.teamNameZnAbbr = teamNameZnAbbr;
        this.homecourt = homecourt;
    }
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
    public String getTeamNameZnAbbr() {
        return teamNameZnAbbr;
    }
    public void setTeamNameZnAbbr(String teamNameZnAbbr) {
        this.teamNameZnAbbr = teamNameZnAbbr;
    }
    public String getHomecourt() {
        return homecourt;
    }
    public void setHomecourt(String homecourt) {
        this.homecourt = homecourt;
    }
    
    
}

package po;

public class TeamListItem {
    private String teamNameEn;
    private String teamNameZh;
    private String conference;//例"eastern","western"
    private String division;//例"atlantic","southeast"
    private String teamNameZhAbbr;//例“子弹”
    private String homecourt;
    
    public TeamListItem(String teamNameEn, String teamNameZh, String conference, String division,
            String teamNameZhAbbr, String homecourt) {
        super();
        this.teamNameEn = teamNameEn;
        this.teamNameZh = teamNameZh;
        this.conference = conference;
        this.division = division;
        this.teamNameZhAbbr = teamNameZhAbbr;
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
    public String getTeamNameZhAbbr() {
        return teamNameZhAbbr;
    }
    public void setTeamNameZhAbbr(String teamNameZhAbbr) {
        this.teamNameZhAbbr = teamNameZhAbbr;
    }
    public String getHomecourt() {
        return homecourt;
    }
    public void setHomecourt(String homecourt) {
        this.homecourt = homecourt;
    }
    @Override
    public String toString() {
        return "TeamListItem [teamNameEn=" + teamNameEn + ", teamNameZh=" + teamNameZh + ", conference=" + conference
                + ", division=" + division + ", teamNameZnAbbr=" + teamNameZhAbbr + ", homecourt=" + homecourt + "]";
    }
    
    
}

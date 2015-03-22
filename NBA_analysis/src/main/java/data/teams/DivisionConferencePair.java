package data.teams;

import logic.teams.Team;

//用于球队赛区的修正
public class DivisionConferencePair {
    private String division = null;
    private char conference = '\0';
    private int number = 0;
    
    public DivisionConferencePair(String division, char conference,int number) {
        super();
        this.division = division;
        this.conference = conference;
        this.number = number;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public char getConference() {
        return conference;
    }

    public void setConference(char conference) {
        this.conference = conference;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    
    
    //赛区，分区对和team中的一样则返回true
    public boolean isRelatedTo(Team team){
        return team.getDivision().equals(this.getDivision()) && team.getConference() == this.getConference();
    }
    
    //for debug only
    public String toString(){
        return this.getDivision() + "-" + this.getConference() + ": " + this.getNumber();
    }
}

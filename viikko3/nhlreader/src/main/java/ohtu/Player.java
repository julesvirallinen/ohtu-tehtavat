
package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private int goals;
    private int assists;
    private String team;
    private String nationality;
    private String birthdate;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getGoals() {
        return goals;
    }

    public int getPoints() {
        return goals + assists;
    }

    @Override
    public int compareTo(Player p) {
        return ((Player) p).getPoints() - this.getPoints();
    }


    @Override
    public String toString() {
        String padding = new String(new char[20-name.length()]).replace("\0", " ");
        return name + padding + team + "  " + goals + " + " + assists + " = " + getPoints();
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }
}

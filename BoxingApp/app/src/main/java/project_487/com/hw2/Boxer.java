package project_487.com.hw2;

public class Boxer {
    String name;
    int wins;
    String titles;
    int losses;
    String divisions;
    int division;
    String imgId;

    public String getDivisions() {
        return divisions;
    }

    public Boxer(String name, int wins, String titles, int losses, String division, int divisions, String imgId) {
        this.name = name;
        this.wins = wins;
        this.titles = titles;
        this.losses = losses;
        this.division=divisions;
        this.divisions = division;
        this.imgId = imgId;

    }

    public void setDivisions(String divisions) {
        this.divisions = divisions;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getDivision() {
        return division;
    }

    public void setDivision(int division) {
        this.division = division;
    }
}

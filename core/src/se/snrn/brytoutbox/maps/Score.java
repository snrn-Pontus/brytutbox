package se.snrn.brytoutbox.maps;


import java.util.Date;


public class Score {


    private long id;

    private int score;

    private String user;


    private Map map;

    private Date date;

    public Score() {
    }

    public Score(int score, String user, Date date) {
        this.score = score;
        this.user = user;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}

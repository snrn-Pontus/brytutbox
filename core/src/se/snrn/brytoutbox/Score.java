package se.snrn.brytoutbox;


public class Score {

    private int score;

    public Score() {
        score = 0;
    }

    public void addScore(int scoreToAdd) {
        score += scoreToAdd;
    }

    public int getScore() {
        return score;
    }
}

package se.snrn.brytoutbox;


public class ScoreState {

    private int score;
    private int multiplier;

    public ScoreState() {
        score = 0;
        multiplier = 0;
    }

    public void addScore(int scoreToAdd) {
        score += scoreToAdd*multiplier;
    }

    public int getScore() {
        return score;
    }

    public void increaseMultiplier(int additional){
        multiplier += additional;
    }

    public void resetMultiplier(){
        multiplier = 0;
    }

    public int getMultiplier() {
        return multiplier;
    }
}

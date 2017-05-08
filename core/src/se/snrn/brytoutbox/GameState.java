package se.snrn.brytoutbox;

public class GameState {

    private int ballsLeft;
    private Score score;
    private States state;
    private int bricksLeft;
    private int level;

    public GameState() {
        score = new Score();
        ballsLeft = 3;
    }

    public int getBallsLeft() {
        return ballsLeft;
    }

    public Score getScore() {
        return score;
    }

    public void decreaseBallsLeft() {
        ballsLeft--;
    }

    public void setState(States state) {
        this.state = state;
    }

    public void setBricksLeft(int bricksLeft) {
        this.bricksLeft = bricksLeft;
    }

    public int getBricksLeft() {
        return bricksLeft;
    }

    public States getState() {
        return state;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

package se.snrn.brytoutbox;

public class GameState {

    private int ballsLeft;
    private Score score;
    private States state;

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
}

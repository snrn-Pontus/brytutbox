package se.snrn.brytoutbox;

public class GameState implements Updateable {

    private int ballsLeft;
    private ScoreState scoreState;
    private States state;
    private int bricksLeft;
    private int level;
    private BrytUtBox brytUtBox;

    public GameState(BrytUtBox brytUtBox) {
        this.brytUtBox = brytUtBox;
        scoreState = new ScoreState();
        ballsLeft = Settings.lives;
        state = States.LOADING;
    }

    public int getBallsLeft() {
        return ballsLeft;
    }

    public ScoreState getScoreState() {
        return scoreState;
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

    @Override
    public void update(float deltaTime) {
        if(state == States.PLAYING) {
            if (ballsLeft == 0 || bricksLeft == 0) {
                System.out.println("out of balls");
                state = States.GAME_OVER;
                brytUtBox.gameOver(getLevel());
            }
        }
    }



}

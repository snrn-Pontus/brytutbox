package se.snrn.brytoutbox;

public class GameState implements Updateable {

    private int ballsLeft;
    private Score score;
    private States state;
    private int bricksLeft;
    private int level;
    private boolean started;

    public GameState() {
        score = new Score();
        ballsLeft = Settings.lives;
        setStarted(false);
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

    @Override
    public void update(float deltaTime) {
        if(state == States.PLAYING) {
            if (ballsLeft == 0 && started) {
                System.out.println("out of balls");
                state = States.GAME_OVER;
            }
            if (bricksLeft == 0 && started) {
                System.out.println("all bricks destroyed");
                state = States.GAME_OVER;
            }
        }
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
}

package se.snrn.brytoutbox.powerups;

import se.snrn.brytoutbox.GameBoard;
import se.snrn.brytoutbox.paddle.Paddle;

public class SmallerPaddle implements PowerUpEffect {

    private Paddle paddle;

    public SmallerPaddle() {
        this.paddle = GameBoard.paddle;
    }

    @Override
    public void start() {
        paddle.decreaseSize();
    }

    @Override
    public void stop() {

    }

    @Override
    public void tick(float delta) {

    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public PowerUpType getType() {
        return PowerUpType.SMALL_PADDLE;
    }

    @Override
    public void setFinished(boolean finished) {

    }
}

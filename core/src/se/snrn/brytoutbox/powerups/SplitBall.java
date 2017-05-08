package se.snrn.brytoutbox.powerups;


import se.snrn.brytoutbox.GameBoard;
import se.snrn.brytoutbox.ball.Ball;
import se.snrn.brytoutbox.ball.BallManager;

import static se.snrn.brytoutbox.ball.BallType.SMALL;

public class SplitBall implements PowerUpEffect {


    private BallManager ballManager;


    Ball smallBall;
    Ball smallBall2;
    private boolean finished;

    public SplitBall() {
        this.ballManager = GameBoard.ballManager;

    }

    @Override
    public void start() {


        smallBall = new Ball(SMALL, ballManager.getBall().body.getTransform().getPosition().x, ballManager.getBall().body.getTransform().getPosition().y);
        smallBall2 = new Ball(SMALL, ballManager.getBall().body.getTransform().getPosition().x, ballManager.getBall().body.getTransform().getPosition().y);
        ballManager.addBall(smallBall);
        ballManager.addBall(smallBall2);
        smallBall.body.setLinearVelocity(5, 5);
        smallBall2.body.setLinearVelocity(-5, 5);



        ballManager.getBall().setLost(true);
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
        return PowerUpType.SPLIT_BALL;
    }

    @Override
    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}

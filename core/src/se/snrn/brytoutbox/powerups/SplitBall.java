package se.snrn.brytoutbox.powerups;


import se.snrn.brytoutbox.ball.Ball;
import se.snrn.brytoutbox.ball.BallManager;

import static se.snrn.brytoutbox.ball.BallType.SMALL;

public class SplitBall implements PowerUp {


    private BallManager ballManager;

    public SplitBall(BallManager ballManager) {
        this.ballManager = ballManager;


        Ball smallBall = new Ball(SMALL);
        smallBall.body.getPosition().set(ballManager.getBall().body.getTransform().getPosition());
        smallBall.body.setLinearVelocity(5,5);
        Ball smallBall2 = new Ball(SMALL);
        smallBall2.body.getPosition().set(ballManager.getBall().body.getTransform().getPosition());
        smallBall2.body.setLinearVelocity(-5,5);

        ballManager.addBall(smallBall);
        ballManager.addBall(smallBall2);

        ballManager.getBall().setLost(true);



    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void tick(float delta) {

    }
}
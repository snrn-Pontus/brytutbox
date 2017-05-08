package se.snrn.brytoutbox.ball;


import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.brytoutbox.*;

import java.util.ArrayList;

import static se.snrn.brytoutbox.ball.BallType.NORMAL;
import static se.snrn.brytoutbox.ball.BallType.SMALL;

public class BallManager implements Updateable, Renderable {

    private ArrayList<Ball> balls;
    private ArrayList<Ball> ballsToRemove;
    private ArrayList<Ball> ballsToAdd;


    public BallManager() {
        balls = new ArrayList<>();
        ballsToRemove = new ArrayList<>();
        ballsToAdd = new ArrayList<>();
    }

    public void addBall(Ball ball) {
        ballsToAdd.add(ball);
    }

    public void spawnNewBall(){
        Ball ball = new Ball(NORMAL);
        addBall(ball);

    }

    @Override
    public void update(float delta) {

        balls.addAll(ballsToAdd);
        ballsToAdd.clear();
        balls.removeAll(ballsToRemove);
        ballsToRemove.clear();
        for (Ball ball : balls) {
            ball.update(delta);
            if (ball.isLost()) {
                ballsToRemove.add(ball);
                GameBoard.world.destroyBody(ball.body);
            }
        }
        if (balls.isEmpty()) {
            if (BrytUtBox.gameState.getBallsLeft() > 0) {
                spawnNewBall();
                BrytUtBox.gameState.decreaseBallsLeft();
            } else {
                BrytUtBox.gameState.setState(States.GAME_OVER);
            }
        }
    }

    @Override
    public void render(Batch batch) {
        for (Ball ball : balls) {
            ball.render(batch);
        }
    }

    public Ball getBall() {
        return balls.get(0);
    }

}

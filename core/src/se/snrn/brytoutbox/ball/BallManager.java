package se.snrn.brytoutbox.ball;


import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.brytoutbox.BrytUtBox;
import se.snrn.brytoutbox.Renderable;
import se.snrn.brytoutbox.States;
import se.snrn.brytoutbox.Updateable;

import java.util.ArrayList;

import static se.snrn.brytoutbox.GameBoard.paddle;

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
        Ball ball = new Ball();
        addBall(ball);

    }

    @Override
    public void update(float delta) {
        if (balls.isEmpty()) {
            if (BrytUtBox.gameState.getBallsLeft() > 0) {
                spawnNewBall();
                BrytUtBox.gameState.decreaseBallsLeft();
            } else {
                BrytUtBox.gameState.setState(States.GAME_OVER);
            }
        }
        balls.addAll(ballsToAdd);
        ballsToAdd.clear();
        balls.removeAll(ballsToRemove);
        ballsToRemove.clear();
        for (Ball ball : balls) {
            ball.update(delta);
            if (ball.isLost()) {
                ballsToRemove.add(ball);
            }
        }
    }

    @Override
    public void render(Batch batch) {
        for (Ball ball : balls) {
            ball.render(batch);
        }
    }
}

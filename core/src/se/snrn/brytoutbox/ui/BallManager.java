package se.snrn.brytoutbox.ui;


import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.brytoutbox.Ball;
import se.snrn.brytoutbox.Renderable;
import se.snrn.brytoutbox.Updateable;

import java.util.ArrayList;

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

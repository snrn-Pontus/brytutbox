package se.snrn.brytoutbox.effects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import se.snrn.brytoutbox.Renderable;
import se.snrn.brytoutbox.Updateable;
import se.snrn.brytoutbox.ball.Ball;

public class BallTrail implements Updateable, Renderable {
    private ParticleEffect p;
    private Ball ball;

    public BallTrail(Ball ball) {
        this.ball = ball;
        p = new ParticleEffect();
        p.load(Gdx.files.internal("effects/trail2.particle"), Gdx.files.internal("gfx")); //files.internal loads from the "assets" folder
        p.scaleEffect(0.03125f);

    }

    @Override
    public void update(float delta) {
        for (int i = 0; i < p.getEmitters().size; i++) { //get the list of emitters - things that emit particles
            p.getEmitters().get(i).getAngle().setLow(ball.body.getLinearVelocity().angle()-180); //low is the minimum rotation
            p.getEmitters().get(i).getAngle().setHigh(ball.body.getLinearVelocity().angle()-180); //high is the max rotation
        }
        p.setPosition(ball.body.getPosition().x, ball.body.getPosition().y);
        p.update(delta);
        if (p.isComplete()) {
            p.reset();
        }
    }

    @Override
    public void render(Batch batch) {
        p.getEmitters().get(0).durationTimer = 1;
        p.draw(batch);
    }
}

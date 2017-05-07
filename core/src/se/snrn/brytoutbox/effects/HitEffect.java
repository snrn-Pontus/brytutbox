package se.snrn.brytoutbox.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

import static se.snrn.brytoutbox.BrytUtBox.PPM;


public class HitEffect implements Effect {
    private float x;
    private float y;
    private ParticleEffect p;
    private float angle;

    public HitEffect(float x, float y, float angle) {
        this.x = x * PPM;
        this.y = y * PPM;
        this.angle = angle;
        p = new ParticleEffect();
        p.load(Gdx.files.internal("effects/hit.particle"), Gdx.files.internal("effects"));
        p.start();
    }

    @Override
    public void update(float delta) {
        for (int i = 0; i < p.getEmitters().size; i++) { //get the list of emitters - things that emit particles
            p.getEmitters().get(i).getAngle().setHighMax(angle + 45); //high is the max rotation
            p.getEmitters().get(i).getAngle().setHighMin(angle - 45);
        }
        p.setPosition(x, y);
        p.update(delta);
    }

    @Override
    public void render(Batch batch) {
        p.draw(batch);
    }

    @Override
    public boolean isFinished() {
        return p.isComplete();
    }
}

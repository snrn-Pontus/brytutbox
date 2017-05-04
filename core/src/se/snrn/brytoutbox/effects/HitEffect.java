package se.snrn.brytoutbox.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

import static se.snrn.brytoutbox.GameBoard.PPM;


public class HitEffect implements Effect {
    private float x;
    private float y;
    private ParticleEffect p;

    public HitEffect(float x, float y) {
        this.x = x*PPM;
        this.y = y*PPM;
        p = new ParticleEffect();
        p.load(Gdx.files.internal("effects/hit.particle"), Gdx.files.internal("effects"));
        p.start();

    }

    @Override
    public void update(float delta) {
        p.setPosition(x,y);
        System.out.println("hit effect");
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

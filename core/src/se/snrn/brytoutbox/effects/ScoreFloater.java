package se.snrn.brytoutbox.effects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

import static se.snrn.brytoutbox.GameBoard.PPM;

public class ScoreFloater implements Effect {

    private float x;
    private float y;
    private int score;
    private boolean finished;
    private Sprite one;

    public ScoreFloater(float x, float y, int score) {
        this.x = x;
        this.y = y;
        this.score = score;
        one = new Sprite(new Texture(Gdx.files.internal("gfx/num_1.png")));
        one.setSize(1, 1);
    }

    @Override
    public void update(float delta) {
        y += 0.1f;
        one.setPosition(x, y);
    }

    @Override
    public void render(Batch batch) {
        one.draw(batch);
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}

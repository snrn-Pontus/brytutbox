package se.snrn.brytoutbox.effects;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import static se.snrn.brytoutbox.BrytUtBox.PPM;


public class ScoreFloater implements Effect {

    private float x;
    private float y;
    private int score;
    private boolean finished;

    private BitmapFont bitmapFont;

    public ScoreFloater(float x, float y, int score) {
        this.x = x * PPM;
        this.y = y * PPM;
        this.score = score;
        bitmapFont = new BitmapFont();

    }

    @Override
    public void update(float delta) {
        y += 1f;

        if (y > 480) {
            finished = true;
        }
    }

    @Override
    public void render(Batch batch) {
        bitmapFont.draw(batch, score + "", x, y);
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}

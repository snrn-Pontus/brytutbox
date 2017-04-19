package se.snrn.brytoutbox.effects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import se.snrn.brytoutbox.Renderable;
import se.snrn.brytoutbox.Updateable;

public class ScoreFloater implements Updateable, Renderable{

    private float x;
    private float y;
    private int score;
    BitmapFont bitmapFont;

    public ScoreFloater(float x, float y, int score) {
        bitmapFont = new BitmapFont(Gdx.files.internal("font.fnt"));
        this.x = x;
        this.y = y;
        this.score = score;

    }

    @Override
    public void update(float delta) {
        y+= 0.1f;
    }

    @Override
    public void render(Batch batch) {
        bitmapFont.draw(batch, ""+score, x, y);
    }
}

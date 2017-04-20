package se.snrn.brytoutbox.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import se.snrn.brytoutbox.Renderable;
import se.snrn.brytoutbox.Score;
import se.snrn.brytoutbox.Updateable;

public class ScoreUi implements Updateable, Renderable{

    private Score score;
    private int x;
    private int y;
    private BitmapFont bitmapFont;

    public ScoreUi(Score score, int x, int y) {
        this.score = score;
        this.x = x;
        this.y = y;
        bitmapFont = new BitmapFont();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {
            bitmapFont.draw(batch, score.getScore()+"",x,y);
    }
}

package se.snrn.brytoutbox.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import se.snrn.brytoutbox.Renderable;
import se.snrn.brytoutbox.ScoreState;
import se.snrn.brytoutbox.Updateable;

public class ScoreUi implements Updateable, Renderable{

    private ScoreState scoreState;
    private int x;
    private int y;
    private BitmapFont bitmapFont;

    public ScoreUi(ScoreState scoreState, int x, int y) {
        this.scoreState = scoreState;
        this.x = x;
        this.y = y;
        bitmapFont = new BitmapFont();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {
            bitmapFont.draw(batch, scoreState.getScore()+"",x,y);
    }
}

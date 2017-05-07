package se.snrn.brytoutbox.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import se.snrn.brytoutbox.GameState;
import se.snrn.brytoutbox.Renderable;
import se.snrn.brytoutbox.Score;
import se.snrn.brytoutbox.Updateable;

public class BricksLeftUi implements Updateable, Renderable{

    private GameState gameState;
    private int x;
    private int y;
    private BitmapFont bitmapFont;

    public BricksLeftUi(GameState gameState, int x, int y) {
        this.gameState = gameState;
        this.x = x;
        this.y = y;
        bitmapFont = new BitmapFont();
    }


    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {
            bitmapFont.draw(batch, "Bricks Left: "+gameState.getBricksLeft(),x,y);
    }
}

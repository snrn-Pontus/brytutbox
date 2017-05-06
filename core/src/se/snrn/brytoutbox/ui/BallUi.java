package se.snrn.brytoutbox.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import se.snrn.brytoutbox.GameState;
import se.snrn.brytoutbox.Renderable;
import se.snrn.brytoutbox.Updateable;
import se.snrn.brytoutbox.ball.BallManager;

public class BallUi implements Updateable, Renderable{

    private GameState gameState;
    private int x;
    private int y;
    private Sprite ballSprite;

    public BallUi(GameState gameState, int x, int y) {
        this.gameState = gameState;

        this.x = x;
        this.y = y;
        ballSprite = new Sprite(new Texture(Gdx.files.internal("gfx/ball.png")));

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {

        for (int i = 0; i < gameState.getBallsLeft(); i++) {
            ballSprite.setPosition(x+(32*i), y);
            ballSprite.draw(batch);
        }
    }
}

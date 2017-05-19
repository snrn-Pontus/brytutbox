package se.snrn.brytoutbox.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import se.snrn.brytoutbox.GameState;
import se.snrn.brytoutbox.Renderable;
import se.snrn.brytoutbox.ScoreState;
import se.snrn.brytoutbox.Updateable;

public class Ui implements Updateable, Renderable{
    private ScoreState scoreState;
    private ScoreUi scoreUi;
    private Sprite uiBackground;
    private BallUi ballUi;
    private BricksLeftUi bricksLeftUi;

    public Ui(GameState gameState) {
        uiBackground = new Sprite(new Texture(Gdx.files.internal("gfx/ui/background.png")));
        uiBackground.setSize(Gdx.graphics.getWidth(), 32);
        uiBackground.setPosition(0, Gdx.graphics.getHeight()-32);
        this.scoreState = gameState.getScoreState();

        scoreUi = new ScoreUi(scoreState, 16, Gdx.graphics.getHeight()-16);
        ballUi = new BallUi(gameState,64, Gdx.graphics.getHeight()-32 );
        bricksLeftUi = new BricksLeftUi(gameState, 128, Gdx.graphics.getHeight()-16);
    }


    @Override
    public void update(float delta) {
        scoreUi.update(delta);

    }

    @Override
    public void render(Batch batch) {
        uiBackground.draw(batch);
        scoreUi.render(batch);
        ballUi.render(batch);
        bricksLeftUi.render(batch);
    }
}

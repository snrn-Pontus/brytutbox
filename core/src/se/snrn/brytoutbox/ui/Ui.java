package se.snrn.brytoutbox.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import se.snrn.brytoutbox.ball.BallManager;
import se.snrn.brytoutbox.Renderable;
import se.snrn.brytoutbox.Score;
import se.snrn.brytoutbox.Updateable;

public class Ui implements Updateable, Renderable{
    private Score score;
    private BallManager ballManager;
    private ScoreUi scoreUi;
    private Sprite uiBackground;
    private BallUi ballUi;

    public Ui(Score score, BallManager ballManager) {
        uiBackground = new Sprite(new Texture(Gdx.files.internal("gfx/ui/background.png")));
        uiBackground.setSize(Gdx.graphics.getWidth(), 32);
        uiBackground.setPosition(0, Gdx.graphics.getHeight()-32);
        this.score = score;
        this.ballManager = ballManager;
        scoreUi = new ScoreUi(score, 16, Gdx.graphics.getHeight()-16);
        ballUi = new BallUi(ballManager,64, Gdx.graphics.getHeight()-32 );
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
    }
}

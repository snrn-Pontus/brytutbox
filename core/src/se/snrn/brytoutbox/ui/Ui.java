package se.snrn.brytoutbox.ui;


import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.brytoutbox.Renderable;
import se.snrn.brytoutbox.Score;
import se.snrn.brytoutbox.Updateable;

public class Ui implements Updateable, Renderable{
    private Score score;
    private BallManager ballManager;
    private ScoreUi scoreUi;

    public Ui(Score score, BallManager ballManager) {
        this.score = score;
        this.ballManager = ballManager;
        scoreUi = new ScoreUi(score, 10,10);
    }

    @Override
    public void update(float delta) {
        scoreUi.update(delta);
    }

    @Override
    public void render(Batch batch) {
        scoreUi.render(batch);
    }
}

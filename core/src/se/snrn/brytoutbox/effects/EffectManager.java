package se.snrn.brytoutbox.effects;


import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.brytoutbox.Renderable;
import se.snrn.brytoutbox.Updateable;

import java.util.ArrayList;

public class EffectManager implements Updateable, Renderable{

    ArrayList<ScoreFloater> scoreFloaters;

    public EffectManager() {
        scoreFloaters = new ArrayList<ScoreFloater>();

    }

    public void addScoreFloater(ScoreFloater scoreFloater){
        scoreFloaters.add(scoreFloater);
    }

    @Override
    public void update(float delta) {
        for (ScoreFloater scoreFloater : scoreFloaters) {
            scoreFloater.update(delta);
        }
    }

    @Override
    public void render(Batch batch) {
        for (ScoreFloater scoreFloater : scoreFloaters) {
            scoreFloater.render(batch);
        }
    }
}

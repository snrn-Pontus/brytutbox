package se.snrn.brytoutbox.effects;


import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.brytoutbox.Renderable;
import se.snrn.brytoutbox.Updateable;

import java.util.ArrayList;

public class EffectManager implements Updateable, Renderable{

    private ArrayList<Effect> effects;
    private ArrayList<Effect> effectsToRemove;
    private ArrayList<Effect> effectsToAdd;

    public EffectManager() {
        effects = new ArrayList<>();
        effectsToRemove = new ArrayList<>();
        effectsToAdd = new ArrayList<>();

    }

    public void addEffect(Effect effect){
        effectsToAdd.add(effect);
    }

    @Override
    public void update(float delta) {
        effects.addAll(effectsToAdd);
        effectsToAdd.clear();
        effects.removeAll(effectsToRemove);
        effectsToRemove.clear();
        for (Effect effect : effects) {
            effect.update(delta);
            if(effect.isFinished()){
                effectsToRemove.add(effect);
            }
        }
    }

    @Override
    public void render(Batch batch) {
        for (Effect effect : effects) {
            effect.render(batch);
        }
    }
}

package se.snrn.brytoutbox.powerups;


import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.brytoutbox.Renderable;
import se.snrn.brytoutbox.Updateable;

import java.util.ArrayList;

public class PowerUpManager implements Updateable, Renderable {
    private ArrayList<PowerUp> powerUps;
    private ArrayList<PowerUp> powerUpsToRemove;
    private ArrayList<PowerUp> powerUpsToAdd;

    public PowerUpManager() {
        powerUps = new ArrayList<>();
        powerUpsToAdd = new ArrayList<>();
        powerUpsToRemove = new ArrayList<>();
    }

    @Override
    public void update(float delta) {
        powerUps.addAll(powerUpsToAdd);
        powerUpsToAdd.clear();
        powerUps.removeAll(powerUpsToRemove);
        powerUpsToRemove.clear();
        for (PowerUp powerUp : powerUps
                ) {
            if(powerUp.isQueuedToStart()){
                powerUp.getEffect().start();
                powerUp.setQueuedToStart(false);
            }
            powerUp.update(delta);
        }
    }

    @Override
    public void render(Batch batch) {
        for (PowerUp powerUp : powerUps
                ) {
            powerUp.render(batch);
        }
    }


    public void addPowerUp(PowerUp powerUp) {
        powerUpsToAdd.add(powerUp);
    }

    public void removePowerUp(PowerUp powerUp) {
        powerUpsToRemove.add(powerUp);
    }

    public void addRandomPowerUp(float x, float y) {
        addPowerUp((new PowerUp(new SplitBall(), x, y)));
    }
}

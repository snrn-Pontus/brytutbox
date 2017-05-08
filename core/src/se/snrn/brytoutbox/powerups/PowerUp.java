package se.snrn.brytoutbox.powerups;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import se.snrn.brytoutbox.Renderable;
import se.snrn.brytoutbox.ResourceManager;
import se.snrn.brytoutbox.Updateable;
import se.snrn.brytoutbox.paddle.Paddle;
import se.snrn.brytoutbox.physics.Box2DFactory;
import se.snrn.brytoutbox.physics.Collidable;
import se.snrn.brytoutbox.physics.Types;

public class PowerUp implements Updateable, Renderable, Collidable {

    private PowerUpEffect effect;
    private Body body;

    private float x;
    private float y;

    private Sprite sprite;
    private Sprite background;
    private boolean queuedToStart;

    public PowerUp(PowerUpEffect effect, float x, float y) {
        this.effect = effect;
        this.x = x;
        this.y = y;
        body = Box2DFactory.createPowerUpBody(x, y, 32, 32, this);
        sprite = ResourceManager.getEffect(effect.getType());
        background = ResourceManager.getPowerUpBackground();
        sprite.setSize(1f, 1f);
        background.setSize(1f, 1f);
        body.setLinearVelocity(0, -5);

    }


    @Override
    public void update(float delta) {
        y--;
        background.setPosition(body.getPosition().x - 0.5f, body.getPosition().y - 0.5f);
        sprite.setPosition(body.getPosition().x - 0.5f, body.getPosition().y - 0.5f);

    }

    @Override
    public void render(Batch batch) {
        background.draw(batch);
        sprite.draw(batch);
    }

    public PowerUpEffect getEffect() {
        return effect;
    }

    @Override
    public void hit(Collidable collidable) {
        if (collidable instanceof Paddle) {
            queuedToStart = true;
        }
    }

    @Override
    public Types getType() {
        return Types.POWER_UP;
    }

    public boolean isQueuedToStart() {
        return queuedToStart;
    }

    public void setQueuedToStart(boolean queuedToStart) {
        this.queuedToStart = queuedToStart;
    }
}

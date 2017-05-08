package se.snrn.brytoutbox.bricks;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Pool;
import se.snrn.brytoutbox.*;
import se.snrn.brytoutbox.ball.Ball;
import se.snrn.brytoutbox.effects.HitEffect;
import se.snrn.brytoutbox.effects.ScoreFloater;
import se.snrn.brytoutbox.physics.Box2DFactory;
import se.snrn.brytoutbox.physics.Collidable;
import se.snrn.brytoutbox.physics.Types;
import se.snrn.brytoutbox.powerups.PowerUpEffect;

import static se.snrn.brytoutbox.BrytUtBox.PPM;
import static se.snrn.brytoutbox.physics.Types.BRICK;
import static se.snrn.brytoutbox.physics.Types.DEAD_BRICK;

public class Brick implements Updateable, Renderable, Debuggable, Pool.Poolable, Collidable {

    public static int BRICK_WIDTH = 64;
    public static int BRICK_HEIGHT = 32;
    private Sprite sprite;
    private float x;
    private float y;

    private int strength;
    Body body;
    private Types type;
    private boolean destroyed;
    private PowerUpEffect powerUpEffect;

    public Brick() {
        x = 0;
        y = 0;
        type = BRICK;
    }

    public void init(float x, float y, int strength) {
        this.strength = strength;
        this.x = x;
        this.y = y;
        sprite = (new Sprite(new Texture(Gdx.files.internal("gfx/bricks/white_brick.png"))));


        body = Box2DFactory.createRectangleBody(x, y, BRICK_WIDTH, BRICK_HEIGHT, this);
        body.setTransform((x + BRICK_WIDTH / 2) / PPM, (y + BRICK_HEIGHT / 2) / PPM, 0);
        sprite.setSize(BRICK_WIDTH / PPM, BRICK_HEIGHT / PPM);
        sprite.setPosition(body.getPosition().x - 1, body.getPosition().y - 0.5f);

    }

    @Override
    public void reset() {
        x = 0;
        y = 0;
        strength = 0;
        sprite = null;

    }

    @Override
    public void update(float delta) {
        if (strength <= 0) {
            destroyed = true;
            type = DEAD_BRICK;
//            body.setType(BodyDef.BodyType.DynamicBody);
//            body.applyLinearImpulse(0, -1, body.getWorldCenter().x, body.getWorldCenter().y, true);
//            body.applyAngularImpulse(1,true);

        }
    }

    @Override
    public void render(Batch batch) {
        sprite.draw(batch, strength * 0.20f);
    }

    @Override
    public void drawDebug(ShapeRenderer shapeRenderer) {
        //shapeRenderer.rect(x, y, 64, 32);
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getStrength() {
        return strength;
    }

    @Override
    public void hit(Collidable collidable) {
        if (collidable instanceof Ball) {
            float angle = -90;


            if (strength != 0) {
                strength--;
            }

            BrytUtBox.gameState.getScore().increaseMultiplier(1);
            BrytUtBox.gameState.getScore().addScore(100);


            angle = ((Ball) collidable).body.getLinearVelocity().angle() - 180;

            GameBoard.effectManager.addEffect(new HitEffect(body.getPosition().x, body.getPosition().y, angle));
            GameBoard.effectManager.addEffect(new ScoreFloater(body.getPosition().x, body.getPosition().y, 100 * BrytUtBox.gameState.getScore().getMultiplier()));
        }
    }

    @Override
    public Types getType() {
        return type;
    }

    public PowerUpEffect getPowerUpEffect() {
        return powerUpEffect;
    }

    public void setPowerUpEffect(PowerUpEffect powerUpEffect) {
        this.powerUpEffect = powerUpEffect;
    }
}

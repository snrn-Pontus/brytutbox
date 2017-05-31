package se.snrn.brytoutbox.bricks;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import se.snrn.brytoutbox.BrytUtBox;
import se.snrn.brytoutbox.GameBoard;
import se.snrn.brytoutbox.Renderable;
import se.snrn.brytoutbox.Updateable;
import se.snrn.brytoutbox.ball.Ball;
import se.snrn.brytoutbox.effects.HitEffect;
import se.snrn.brytoutbox.effects.ScoreFloater;
import se.snrn.brytoutbox.physics.Box2DFactory;
import se.snrn.brytoutbox.physics.Collidable;
import se.snrn.brytoutbox.physics.Types;

import static se.snrn.brytoutbox.BrytUtBox.*;
import static se.snrn.brytoutbox.physics.Types.BRICK;
import static se.snrn.brytoutbox.physics.Types.DEAD_BRICK;

public class Brick implements Updateable, Renderable, Collidable {


    private Sprite sprite;
    private float x;
    private float y;

    private int strength;
    Body body;
    private Types type;
    private boolean destroyed;

    public Brick(int x, int y, int strength) {
        type = BRICK;
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
    public void update(float delta) {
        if (strength <= 0) {
            destroyed = true;
            type = DEAD_BRICK;
        }
    }

    @Override
    public void render(Batch batch) {
        sprite.draw(batch, strength * 0.20f);
    }


    public boolean isDestroyed() {
        return destroyed;
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }


    @Override
    public void hit(Collidable collidable) {
        if (collidable instanceof Ball) {
            float angle;

            if (strength != 0) {
                strength--;
            }

            BrytUtBox.gameState.getScoreState().increaseMultiplier(1);
            BrytUtBox.gameState.getScoreState().addScore(100);

            angle = ((Ball) collidable).body.getLinearVelocity().angle() - 180;

            GameBoard.effectManager.addEffect(new HitEffect(body.getPosition().x, body.getPosition().y, angle));
            GameBoard.effectManager.addEffect(new ScoreFloater(body.getPosition().x, body.getPosition().y, 100 * BrytUtBox.gameState.getScoreState().getMultiplier()));
        }
    }

    @Override
    public Types getType() {
        return type;
    }
}

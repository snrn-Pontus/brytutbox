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
import se.snrn.brytoutbox.physics.Wall;

import java.util.ArrayList;

import static se.snrn.brytoutbox.GameBoard.PPM;
import static se.snrn.brytoutbox.physics.Types.BRICK;

public class Brick implements Updateable, Renderable, Debuggable, Pool.Poolable, Collidable {

    public static int BRICK_WIDTH = 64;
    public static int BRICK_HEIGHT = 32;
    private BrickGrid brickGrid;

    private Sprite sprite;
    private float x;
    private float y;

    private int strength;
    private ArrayList<Sprite> sprites;
    Body body;
    private Types type;
    private boolean destroyed;

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public void init(float x, float y, int strength) {
        this.strength = strength;
        this.x = x;
        this.y = y;
        sprite = sprites.get(strength);

        body = Box2DFactory.createRectangleBody(x, y, 64, 32, this);

        sprite.setSize(64 / PPM, 32 / PPM);
    }

    public Brick() {
        sprites = new ArrayList<>();

        x = 0;
        y = 0;
        for (int i = 0; i < 6; i++) {
            sprites.add(new Sprite(new Texture(Gdx.files.internal("gfx/bricks/brick_" + i + ".png"))));

        }

        type = BRICK;
        brickGrid = GameBoard.brickGrid;

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
        }

        body.setTransform((x + 32) / PPM, (y + 16) / PPM, 0);

        sprite.setPosition(body.getPosition().x - 1, body.getPosition().y - 0.5f);

    }

    @Override
    public void render(Batch batch) {
        sprite.draw(batch);
    }

    @Override
    public void drawDebug(ShapeRenderer shapeRenderer) {
        //shapeRenderer.rect(x, y, 64, 32);
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
        float angle = -90;
        if (strength != 0) {
            strength--;
            GameBoard.score.increaseMultiplier(1);
            GameBoard.score.addScore(100);
            if(collidable instanceof Ball){
                angle = ((Ball) collidable).body.getLinearVelocity().angle()-180;
            }
            GameBoard.effectManager.addEffect(new HitEffect(body.getPosition().x, body.getPosition().y,angle));
            GameBoard.effectManager.addEffect(new ScoreFloater(body.getPosition().x, body.getPosition().y, 100 * GameBoard.score.getMultiplier()));
            sprite = sprites.get(strength);
            sprite.setSize(64 / PPM, 32 / PPM);

        }
    }

    @Override
    public Types getType() {
        return type;
    }
}

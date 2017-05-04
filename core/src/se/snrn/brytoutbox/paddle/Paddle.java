package se.snrn.brytoutbox.paddle;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import se.snrn.brytoutbox.*;
import se.snrn.brytoutbox.ball.Ball;
import se.snrn.brytoutbox.physics.Box2DFactory;
import se.snrn.brytoutbox.physics.Collidable;
import se.snrn.brytoutbox.physics.Types;

import static se.snrn.brytoutbox.GameBoard.PPM;
import static se.snrn.brytoutbox.GameBoard.score;
import static se.snrn.brytoutbox.physics.Types.PADDLE;

public class Paddle implements Updateable, Renderable, Debuggable, Collidable {

    private Sprite sprite;
    private int x;
    private int y;

    private boolean movingLeft;
    private boolean movingRight;
    public Body body;
    private Types type;
    private Ball stuckBall;


    public Paddle() {
        sprite = new Sprite(new Texture(Gdx.files.internal("gfx/paddle.png")));
        x = 50;
        y = 8;

        type = PADDLE;


        body = Box2DFactory.createRectangleBody(x, y, 96, 16, this);

        sprite.setSize(3, 0.5f);
        stuckBall = null;

    }


    @Override
    public void update(float delta) {
        if (!movingLeft) {
            body.setLinearVelocity(0, 0);
        }
        if (!movingRight) {
            body.setLinearVelocity(0, 0);
        }

        if (movingLeft && body.getPosition().x > 0 + 1.5f) {
            body.setLinearVelocity(-20, 0);
        }
        if (movingRight && body.getPosition().x < Gdx.graphics.getWidth() / PPM - 1.5f) {
            body.setLinearVelocity(+20, 0);
        }
        sprite.setPosition(body.getPosition().x - 1.5f, body.getPosition().y - 0.25f);

    }

    @Override
    public void render(Batch batch) {
        sprite.draw(batch);
    }

    @Override
    public void drawDebug(ShapeRenderer shapeRenderer) {
        //shapeRenderer.rect(x, y, 64, 32);
    }


    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    @Override
    public void hit(Collidable collidable) {
        score.resetMultiplier();
    }

    @Override
    public Types getType() {
        return type;
    }

    public void release() {
        if (stuckBall != null) {
            stuckBall.release();
            stuckBall = null;
        }
    }

    public void setStuckBall(Ball stuckBall) {
        this.stuckBall = stuckBall;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public boolean isMovingRight() {
        return movingRight;
    }
}

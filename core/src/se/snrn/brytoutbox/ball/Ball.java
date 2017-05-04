package se.snrn.brytoutbox.ball;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import se.snrn.brytoutbox.*;
import se.snrn.brytoutbox.effects.BallTrail;
import se.snrn.brytoutbox.paddle.Paddle;
import se.snrn.brytoutbox.physics.Box2DFactory;
import se.snrn.brytoutbox.physics.Collidable;
import se.snrn.brytoutbox.physics.Types;

import static se.snrn.brytoutbox.GameBoard.PPM;
import static se.snrn.brytoutbox.physics.Types.BALL;

public class Ball implements Updateable, Renderable, Debuggable, Collidable {

    private Sprite sprite;
    private int x;
    private int y;

    public Body body;
    private Paddle paddle;
    private boolean lost;
    private boolean stuck;

    private static int BALL_SIZE = 32;
    private Types type;
    private float minSpeed = 10;
    private float maxSpeed = 10;
    private BallTrail ballTrail;
    private Vector2 vel;


    public Ball() {
        sprite = new Sprite(new Texture(Gdx.files.internal("gfx/ball.png")));
        x = 50;
        y = 100;

        paddle = GameBoard.paddle;
        stuck = false;

        type = BALL;

        ballTrail = new BallTrail(this);

        body = Box2DFactory.createCircleBody(x, y, 16, this);

        sprite.setSize(BALL_SIZE / PPM, BALL_SIZE / PPM);
        sprite.setOriginCenter();

    }


    public boolean isStuck() {
        return stuck;
    }

    public void setStuck(boolean stuck) {
        this.stuck = stuck;
    }

    public void release() {
        stuck = false;
        if (paddle.isMovingLeft()) {
            body.setLinearVelocity(-maxSpeed / 2, maxSpeed / 2);
        } else if (paddle.isMovingRight()) {
            body.setLinearVelocity(maxSpeed / 2, maxSpeed / 2);
        } else {
            body.setLinearVelocity(maxSpeed / 2, maxSpeed / 2);
        }
    }


    @Override
    public void update(float delta) {


        ballTrail.update(delta);

        if (!stuck) {
            body.setLinearVelocity(body.getLinearVelocity().clamp(minSpeed, maxSpeed));
        }

        if (body.getPosition().y < 0) {
            System.out.println("Lost");
            lost = true;
        }

        if (stuck) {
            body.setTransform(paddle.body.getPosition().x, paddle.body.getPosition().y + BALL_SIZE / PPM, 0);
        }
        sprite.setPosition(body.getPosition().x - 0.5f, body.getPosition().y - 0.5f);
        sprite.setRotation(body.getTransform().getRotation());
    }

    @Override
    public void render(Batch batch) {
        ballTrail.render(batch);
        sprite.draw(batch);
    }

    @Override
    public void drawDebug(ShapeRenderer shapeRenderer) {
//        shapeRenderer.rect(x, y, 32, 32);
//        shapeRenderer.line(centerX, centerY, centerX + xVel * 100, centerY + yVel * 100);
//        shapeRenderer.circle(circle.x, circle.y, circle.radius);
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    @Override
    public void hit(Collidable collidable) {

    }

    @Override
    public Types getType() {
        return type;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }

    public boolean isLost() {
        return lost;
    }
}

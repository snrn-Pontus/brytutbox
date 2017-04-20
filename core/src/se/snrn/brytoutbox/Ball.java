package se.snrn.brytoutbox;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.*;

import static se.snrn.brytoutbox.GameBoard.PPM;
import static se.snrn.brytoutbox.Types.BALL;

public class Ball implements Updateable, Renderable, Debuggable, Collidable {

    private Sprite sprite;
    private int x;
    private int y;
    private int centerX;
    private int centerY;

    Body body;
    Paddle paddle;
    private boolean lost;
    private boolean stuck;

    public static int BALL_SIZE = 32;
    private Types type;

    public Ball() {
        sprite = new Sprite(new Texture(Gdx.files.internal("gfx/ball.png")));
        x = 50;
        y = 100;

        paddle = GameBoard.paddle;
        stuck = true;

        type = BALL;

        centerX = x + 16;
        centerY = y + 16;

        body = Box2DFactory.createCircleBody(x, y, 16, this);

        sprite.setSize(BALL_SIZE / PPM, BALL_SIZE / PPM);

    }



    public boolean isStuck() {
        return stuck;
    }

    public void setStuck(boolean stuck) {
        this.stuck = stuck;
    }

    public void release() {
        stuck = false;
        body.applyLinearImpulse(0, 1, 0, 0, true);

    }


    @Override
    public void update(float delta) {
        if (stuck) {
            body.setTransform(paddle.body.getPosition().x, paddle.body.getPosition().y + BALL_SIZE / PPM, 0);
        }
        sprite.setPosition(body.getPosition().x - 0.5f, body.getPosition().y - 0.5f);
    }

    @Override
    public void render(Batch batch) {
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

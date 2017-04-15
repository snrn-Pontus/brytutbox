package se.snrn.brytoutbox;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static se.snrn.brytoutbox.GameBoard.PPM;
import static se.snrn.brytoutbox.GameBoard.world;
import static se.snrn.brytoutbox.Types.BALL;
import static se.snrn.brytoutbox.Types.BRICK;
import static se.snrn.brytoutbox.Types.PADDLE;

public class Paddle implements Updateable, Renderable, Debuggable, Collidable {

    private Sprite sprite;
    private int x;
    private int y;
    private int xVel;

    private boolean movingLeft;
    private boolean movingRight;
    Body body;
    FixtureDef fixtureDef;
    private Types type;


    public Paddle() {
        sprite = new Sprite(new Texture(Gdx.files.internal("gfx/brick.png")));
        x = 50;
        y = 16;
        xVel = 5;

        type = PADDLE;


        body = Box2DFactory.createRectangleBody(x, y, 64, 32, this);

        sprite.setSize(64 / PPM, 32 / PPM);


    }



    @Override
    public void update(float delta) {
        if (!movingLeft) {
            body.setLinearVelocity(0, 0);
        }
        if (!movingRight) {
            body.setLinearVelocity(0, 0);
        }

        if (movingLeft) {
            body.setLinearVelocity(-20, 0);
        }
        if (movingRight) {
            body.setLinearVelocity(+20, 0);
        }
        sprite.setPosition(body.getPosition().x-1, body.getPosition().y-0.5f);

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

    }

    @Override
    public Types getType() {
        return type;
    }
}

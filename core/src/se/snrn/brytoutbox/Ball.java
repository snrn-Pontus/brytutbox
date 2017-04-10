package se.snrn.brytoutbox;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.*;

import static se.snrn.brytoutbox.Types.BALL;

public class Ball implements Updateable, Renderable, Debuggable, Collidable {

    private Sprite sprite;
    private int x;
    private int y;
    private int centerX;
    private int centerY;

    Body body;
    FixtureDef fixtureDef;
    Paddle paddle;

    public boolean isStuck() {
        return stuck;
    }

    public void setStuck(boolean stuck) {
        this.stuck = stuck;
    }

    public void release(){
        stuck = false;
        body.applyLinearImpulse(0, 500, 0,0,true);

    }

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

        BodyDef bodyDef = new BodyDef();
// We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        bodyDef.type = BodyDef.BodyType.DynamicBody;
// Set our body's starting position in the world
        bodyDef.position.set(x, y);

// Create our body in the world using our body definition
        body = GameBoard.world.createBody(bodyDef);
        body.applyLinearImpulse(0, 1000, 0,0,true);
        body.setUserData(BALL);

// Create a circle shape and set its radius to 6
        CircleShape circle = new CircleShape();
        circle.setRadius(16);

// Create a fixture definition to apply our shape to
        fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 1f; // Make it bounce a little bit

// Create our fixture and attach it to the body
        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);

// Remember to dispose of any shapes after you're done with them!
// BodyDef and FixtureDef don't need disposing, but shapes do.
        circle.dispose();


    }




    @Override
    public void update(float delta) {
        if(stuck){
            body.setTransform(paddle.body.getPosition().x, paddle.body.getPosition().y+BALL_SIZE, 0);
        }
        sprite.setPosition(body.getPosition().x-BALL_SIZE/2, body.getPosition().y-BALL_SIZE/2);
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
}

package se.snrn.brytoutbox;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static se.snrn.brytoutbox.GameBoard.world;
import static se.snrn.brytoutbox.Types.BALL;
import static se.snrn.brytoutbox.Types.BRICK;
import static se.snrn.brytoutbox.Types.PADDLE;

public class Paddle implements Updateable, Renderable, Debuggable, Collidable {

    private Sprite sprite;
    private int x;
    private int y;
    private int xVel;

    private Rectangle rectangle;
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

        rectangle = new Rectangle(x, y, 64, 32);

        BodyDef bodyDef = new BodyDef();
// We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        bodyDef.type = BodyDef.BodyType.KinematicBody;
// Set our body's starting position in the world
        bodyDef.position.set(x, y);

// Create our body in the world using our body definition
        body = GameBoard.world.createBody(bodyDef);
        body.setUserData(BALL);

// Create a circle shape and set its radius to 6
        PolygonShape circle = new PolygonShape();
        circle.setAsBox(rectangle.width / 2, rectangle.height / 2);

// Create a fixture definition to apply our shape to
        fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 1f; // Make it bounce a little bit

// Create our fixture and attach it to the body
        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);

// Remember to dispose of any shapes after you're done with them!
// BodyDef and FixtureDef don't need disposing, but shapes do.
        circle.dispose();

    }




    public int getCenterX() {
        return (int) (rectangle.getX() + rectangle.getWidth() / 2);
    }

    public int getCenterY() {
        return (int) (rectangle.getY() + rectangle.getHeight() / 2);
    }

    @Override
    public void update(float delta) {
        if (!movingLeft) {
            body.setLinearVelocity( 0, 0);
        }
        if (!movingRight) {
            body.setLinearVelocity(0, 0);
        }

        if (movingLeft) {
            body.setLinearVelocity(-200, 0);
        }
        if (movingRight) {
            body.setLinearVelocity(+200, 0);
        }
        rectangle.setPosition(body.getPosition().x, body.getPosition().y);
        sprite.setPosition(body.getPosition().x-rectangle.getWidth()/2, body.getPosition().y-rectangle.getHeight()/2);

    }

    @Override
    public void render(Batch batch) {
        sprite.draw(batch);
    }

    @Override
    public void drawDebug(ShapeRenderer shapeRenderer) {
        //shapeRenderer.rect(x, y, 64, 32);
    }

    public Rectangle getRectangle() {
        return rectangle;
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

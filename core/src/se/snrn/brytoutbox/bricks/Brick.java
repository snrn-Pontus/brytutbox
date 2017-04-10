package se.snrn.brytoutbox.bricks;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Pool;
import se.snrn.brytoutbox.*;

import java.util.ArrayList;

import static se.snrn.brytoutbox.GameBoard.PPM;
import static se.snrn.brytoutbox.Types.BALL;
import static se.snrn.brytoutbox.Types.BRICK;

public class Brick implements Updateable, Renderable, Debuggable, Pool.Poolable, Collidable {

    public static int BRICK_WIDTH = 64;
    public static int BRICK_HEIGHT = 32;
    private BrickGrid brickGrid;

    private Sprite sprite;
    private int x;
    private int y;

    private Rectangle rectangle;
    private int strength;
    private ArrayList<Sprite> sprites;
    Body body;
    FixtureDef fixtureDef;
    private Types type;
    private boolean destroyed;

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public void init(int x, int y, int strength) {
        this.strength = strength;
        this.x = x;
        this.y = y;
        rectangle = new Rectangle(x - 32, y - 16, 64, 32);
        sprite = sprites.get(strength);

        BodyDef bodyDef = new BodyDef();
// We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        bodyDef.type = BodyDef.BodyType.StaticBody;
// Set our body's starting position in the world
        bodyDef.position.set(x, y);

// Create our body in the world using our body definition
        body = GameBoard.world.createBody(bodyDef);

// Create a circle shape and set its radius to 6
        PolygonShape circle = new PolygonShape();
        circle.setAsBox(rectangle.width / 2, rectangle.height / 2);

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

    public Brick() {
        sprites = new ArrayList<Sprite>();

        x = 0;
        y = 0;
        for (int i = 0; i < 6; i++) {
            sprites.add(new Sprite(new Texture(Gdx.files.internal("gfx/brick_" + i + ".png"))));

        }
        rectangle = new Rectangle(x, y, 64, 32);

        type = BRICK;
        brickGrid = GameBoard.brickGrid;

    }

    @Override
    public void reset() {
        x = 0;
        y = 0;
        strength = 0;
        rectangle = null;
        sprite = null;

    }

    @Override
    public void update(float delta) {

        if(strength <= 0) {
            destroyed = true;
        }

        rectangle.setPosition(x, y);
        body.setTransform(x+32,y+16,0);
        sprite.setPosition(body.getPosition().x - rectangle.getWidth() / 2, body.getPosition().y - rectangle.getHeight() / 2);

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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public int getCenterX() {
        return (int) (rectangle.getX() + rectangle.getWidth() / 2);
    }

    public int getCenterY() {
        return (int) (rectangle.getY() + rectangle.getHeight() / 2);
    }

    public int getStrength() {
        return strength;
    }

    @Override
    public void hit(Collidable collidable) {
        if (strength != 0) {
            strength--;
            System.out.println("hit");
            sprite = sprites.get(strength);
        }
    }

    @Override
    public Types getType() {
        return type;
    }
}

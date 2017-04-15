package se.snrn.brytoutbox;


import com.badlogic.gdx.physics.box2d.*;

public class Box2DFactory {

    public Box2DFactory(World world) {

    }

    public static Body createCircleBody(float x, float y, float radius, Object object) {
        x /= GameBoard.PPM;
        y /= GameBoard.PPM;
        radius /= GameBoard.PPM;
        BodyDef bodyDef = new BodyDef();
// We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        bodyDef.type = BodyDef.BodyType.DynamicBody;
// Set our body's starting position in the world
        bodyDef.position.set(x, y);

// Create our body in the world using our body definition
        Body body = GameBoard.world.createBody(bodyDef);
        body.setBullet(true);

// Create a circle shape and set its radius to 6
        CircleShape circle = new CircleShape();
        circle.setRadius(radius);

// Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 1f; // Make it bounce a little bit

// Create our fixture and attach it to the body
        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(object);

// Remember to dispose of any shapes after you're done with them!
// BodyDef and FixtureDef don't need disposing, but shapes do.
        circle.dispose();
        return body;

    }
    public static Body createRectangleBody(float x, float y, float width, float height, Object object){
        x /= GameBoard.PPM;
        y /= GameBoard.PPM;
        width /= GameBoard.PPM;
        height /= GameBoard.PPM;

        BodyDef bodyDef = new BodyDef();
// We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        bodyDef.type = BodyDef.BodyType.KinematicBody;
// Set our body's starting position in the world
        bodyDef.position.set(x, y);

// Create our body in the world using our body definition
        Body body = GameBoard.world.createBody(bodyDef);

// Create a circle shape and set its radius to 6
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(width / 2, height / 2);

// Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 1f; // Make it bounce a little bit

// Create our fixture and attach it to the body
        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(object);

// Remember to dispose of any shapes after you're done with them!
// BodyDef and FixtureDef don't need disposing, but shapes do.
        rectangle.dispose();
        return body;
    }



}

package se.snrn.brytoutbox.physics;


import com.badlogic.gdx.physics.box2d.*;
import se.snrn.brytoutbox.GameBoard;

public class Box2DFactory {


    public static Body createCircleBody(float x, float y, float radius, Object object) {
        x /= GameBoard.PPM;
        y /= GameBoard.PPM;
        radius /= GameBoard.PPM;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        Body body = GameBoard.world.createBody(bodyDef);
        body.setBullet(true);


        CircleShape circle = new CircleShape();
        circle.setRadius(radius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 1f;


        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(object);

        circle.dispose();
        return body;

    }
    public static Body createRectangleBody(float x, float y, float width, float height, Object object){
        x /= GameBoard.PPM;
        y /= GameBoard.PPM;
        width /= GameBoard.PPM;
        height /= GameBoard.PPM;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(x, y);

        Body body = GameBoard.world.createBody(bodyDef);

        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(width / 2, height / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 1f;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(object);
        rectangle.dispose();
        return body;
    }



}

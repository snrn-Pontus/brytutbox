package se.snrn.brytoutbox;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import static se.snrn.brytoutbox.Types.WALL;

public class Wall implements Collidable{

    private Types type;

    public Wall(int x, int y, int width, int height) {

        type = WALL;

        Rectangle rectangle = new Rectangle(x, y, width, height);

        BodyDef groundBodyDef = new BodyDef();
// Set its world position
        groundBodyDef.position.set(rectangle.x + rectangle.getWidth(), rectangle.y + rectangle.height);

// Create a body from the defintion and add it to the world
        Body groundBody = GameBoard.world.createBody(groundBodyDef);

// Create a polygon shape
        PolygonShape groundBox = new PolygonShape();
// Set the polygon shape as a box which is twice the size of our view port and 20 high
// (setAsBox takes half-width and half-height as arguments)
        groundBox.setAsBox(rectangle.width / 2, rectangle.height / 2);
// Create a fixture from our polygon shape and add it to our ground body
        groundBody.createFixture(groundBox, 0.0f);
// Clean up after ourselves
        groundBox.dispose();
    }

    @Override
    public void hit(Collidable collidable) {

    }

    @Override
    public Types getType() {
        return type;
    }
}

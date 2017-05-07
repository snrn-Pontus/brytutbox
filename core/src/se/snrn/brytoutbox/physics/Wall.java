package se.snrn.brytoutbox.physics;


import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import se.snrn.brytoutbox.GameBoard;

import static se.snrn.brytoutbox.physics.Types.WALL;

public class Wall implements Collidable{

    private Types type;

    public Wall(float x, float y, float width, float height) {

        type = WALL;


        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(x, y);

        Body groundBody = GameBoard.world.createBody(groundBodyDef);

        PolygonShape groundBox = new PolygonShape();

        groundBox.setAsBox(width / 2, height / 2);
        groundBody.createFixture(groundBox, 0.0f);
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

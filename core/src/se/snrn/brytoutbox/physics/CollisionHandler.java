package se.snrn.brytoutbox.physics;

import com.badlogic.gdx.physics.box2d.*;
import se.snrn.brytoutbox.Updateable;
import se.snrn.brytoutbox.physics.Collidable;


public class CollisionHandler implements Updateable {


    public CollisionHandler(World world) {


        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {

                if (contact.getFixtureA().getUserData() instanceof Collidable && contact.getFixtureB().getUserData() instanceof Collidable) {
                    ((Collidable) contact.getFixtureA().getUserData()).hit((Collidable) contact.getFixtureB().getUserData());
                    ((Collidable) contact.getFixtureB().getUserData()).hit((Collidable) contact.getFixtureB().getUserData());
                }



            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }

    @Override
    public void update(float delta) {


    }


}

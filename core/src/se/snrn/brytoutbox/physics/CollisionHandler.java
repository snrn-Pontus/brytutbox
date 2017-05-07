package se.snrn.brytoutbox.physics;

import com.badlogic.gdx.physics.box2d.*;


public class CollisionHandler {


    public CollisionHandler(World world) {


        world.setContactFilter(new ContactFilter() {
            @Override
            public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
                if(fixtureA.getUserData() instanceof Collidable && fixtureB.getUserData() instanceof Collidable){
                    if(((Collidable) fixtureA.getUserData()).getType() == Types.DEAD_BRICK ||
                            ((Collidable) fixtureB.getUserData()).getType() == Types.DEAD_BRICK) {
                        return false;
                    }
                }
                return true;
            }
        });

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
}

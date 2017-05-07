package se.snrn.brytoutbox.physics;


import se.snrn.brytoutbox.ball.Ball;

import static se.snrn.brytoutbox.physics.Types.WALL;

public class Wall implements Collidable{

    private Types type;

    public Wall(float x, float y, float width, float height) {

        type = WALL;

        Box2DFactory.createRectangleBody(x,y,width,height,this);

    }

    @Override
    public void hit(Collidable collidable) {
        if(collidable instanceof Ball){
            Ball ball = (Ball)collidable;
            if(ball.body.getLinearVelocity().y == 0){
                ball.body.setLinearVelocity(ball.getMaxSpeed()/2,ball.getMaxSpeed()/2);
            }
            if(ball.body.getLinearVelocity().x == 0){
                ball.body.setLinearVelocity(ball.getMaxSpeed()/2,ball.getMaxSpeed()/2);
            }
        }
    }

    @Override
    public Types getType() {
        return type;
    }
}

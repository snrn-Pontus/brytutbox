package se.snrn.brytoutbox.physics;


public interface Collidable {

    void hit (Collidable collidable);
    Types getType();
}

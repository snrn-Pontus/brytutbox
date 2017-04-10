package se.snrn.brytoutbox;


public interface Collidable {

    void hit (Collidable collidable);
    Types getType();
}

package se.snrn.brytoutbox.powerups;



public interface PowerUp {

    void start();
    void stop();
    void tick(float delta);
}

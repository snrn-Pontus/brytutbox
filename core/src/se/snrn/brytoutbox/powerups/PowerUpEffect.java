package se.snrn.brytoutbox.powerups;


public interface PowerUpEffect {

    void start();
    void stop();
    void tick(float delta);
    boolean isFinished();

    PowerUpType getType();

    void setFinished(boolean finished);
}

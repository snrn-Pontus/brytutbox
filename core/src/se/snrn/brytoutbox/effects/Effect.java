package se.snrn.brytoutbox.effects;


import se.snrn.brytoutbox.Renderable;
import se.snrn.brytoutbox.Updateable;

public interface Effect extends Updateable, Renderable{
    boolean isFinished();
}

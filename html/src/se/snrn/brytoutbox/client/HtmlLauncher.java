package se.snrn.brytoutbox.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import se.snrn.brytoutbox.BrytUtBox;

public class HtmlLauncher extends GwtApplication {

    @Override
    public GwtApplicationConfiguration getConfig() {
        return new GwtApplicationConfiguration(540, 960);

    }

    @Override
    public ApplicationListener createApplicationListener() {
        return new BrytUtBox();
    }
}
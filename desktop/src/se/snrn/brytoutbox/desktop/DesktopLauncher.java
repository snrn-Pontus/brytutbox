package se.snrn.brytoutbox.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import se.snrn.brytoutbox.BrytUtBox;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.height = 960;
        config.width = 540;


        new LwjglApplication(new BrytUtBox(), config);
    }
}

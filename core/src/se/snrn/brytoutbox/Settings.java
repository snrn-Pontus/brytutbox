package se.snrn.brytoutbox;


import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class Settings {
    public static int chanceToDropPowerUp;
    public static int lives;


    public Settings() {
        JsonReader jsonReader = new JsonReader();
        JsonValue settingsValues = jsonReader.parse(new FileHandle("settings/settings.json"));

        chanceToDropPowerUp = settingsValues.getInt("chanceToDropPowerUp");
        lives = settingsValues.getInt("lives");
        System.out.println(chanceToDropPowerUp);
        System.out.println(lives);
    }
}

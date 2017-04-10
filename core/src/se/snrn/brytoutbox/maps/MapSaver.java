package se.snrn.brytoutbox.maps;


import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

import java.io.FileWriter;
import java.io.IOException;

public class MapSaver {

    public static void saveMap(int[][] mapArray, String name){
        try {
            JsonWriter jsonWriter = new JsonWriter(new FileWriter("maps/"+name+".json"));
            Json json = new Json();
            json.setWriter(jsonWriter);
            json.writeValue(MapLoader.getRandomGrid());
            jsonWriter.flush();
            jsonWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

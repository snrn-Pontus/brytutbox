package se.snrn.brytoutbox.maps;


import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class MapLoader {

    private static int emptyRows = 10;

    public static int[][] getRandomGrid() {
        int[][] brickMap = new int[8][29];

        for (int x = 0; x < brickMap.length; x++) {
            for (int y = 0; y < brickMap[x].length; y++) {
                if (y > emptyRows) {
                    brickMap[x][y] = (int) (Math.random() * 5);
                } else {
                    brickMap[x][y] = 0;
                }

            }
        }

        return brickMap;
    }


    public static int[][] getLevel(int level) {
        int[][] brickMap = new int[10][15];
        JsonReader jsonReader = new JsonReader();
        JsonValue jsonValue = jsonReader.parse(new FileHandle("maps/map" + level + ".json"));


        for (int i = 0; i < jsonValue.size; i++) {
            for (int j = 0; j < jsonValue.get(i).size; j++) {
                brickMap[i]= jsonValue.get(i).asIntArray();

            }
        }



        return brickMap;
    }

}

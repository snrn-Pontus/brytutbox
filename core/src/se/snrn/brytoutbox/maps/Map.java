package se.snrn.brytoutbox.maps;


import java.util.ArrayList;


public class Map {

    private long id;

    private String mapName;

    private String author;

    private long time;

    private String mapValue;

    private ArrayList<Score> scores;
    private int[][] intArray;

    public Map() {
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMapValue() {
        return mapValue;
    }

    public void setMapValue(String mapValue) {
        this.mapValue = mapValue;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIntArray(int[][] intArray) {
        this.intArray = intArray;
    }

    public int[][] getIntArray() {
        return intArray;
    }
}

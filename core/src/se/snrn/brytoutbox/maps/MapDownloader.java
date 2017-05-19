package se.snrn.brytoutbox.maps;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;
import java.util.HashMap;

public class MapDownloader {


    private ArrayList<Map> maps;

    public MapDownloader() {
        maps = new ArrayList<>();
        sendRequest(createRequest("62"));
        sendRequest(createRequest("63"));



    }

    public Net.HttpRequest createRequest(String id){
        Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.GET);
        httpRequest.setUrl("http://localhost:8080/map");

        HashMap<String, String> parameters = new HashMap<>();

        parameters.put("map_id", id);


        String convertedParamaters = HttpParametersUtils.convertHttpParameters(parameters);

        httpRequest.setContent(convertedParamaters);
        httpRequest.setHeader("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTQ5NTUyNjU5OX0.UhfryKMDgEvLr5l1idBkT5u6yIAcHsieSvGp7kkwSrPWFvwc5CbjU3eGnfohYVpU6Xi007ngbvuoFvJI_A1UUg");

        return httpRequest;
    }

    public void sendRequest(Net.HttpRequest request){
        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                parseMapResponse(httpResponse);
            }

            public void failed(Throwable t) {
                String status = "failed";
                t.printStackTrace();
                System.out.println(t.getMessage());
            }

            @Override
            public void cancelled() {

            }

        });
    }

    private void parseMapResponse(Net.HttpResponse httpResponse) {
        String status = httpResponse.getResultAsString();
        System.out.println(status);
        Json json = new Json();


        Map map = json.fromJson(Map.class, status);
        System.out.println(map);


        String string = map.getMapValue();
        string = string.replace(",", "");
        string = string.replace("][", ",");
        string = string.replace("[[", "");
        string = string.replace("]]", "");
        System.out.println(string);


        String[] colums = string.split(",");
        String[][] array = new String[8][19];

        for (int i = 0; i < colums.length; i++) {
            String thing = colums[i];
            array[i] = thing.split("");
        }

        int[][] intArray = new int[8][19];
        for (int i = 0; i < array.length; i++) {
            for (int i1 = 0; i1 < array[0].length; i1++) {

                intArray[i][i1] = Integer.valueOf(array[i][i1]);
            }
        }
        map.setIntArray(intArray);
        maps.add(map);
    }


    public ArrayList<Map> getMaps() {
        return maps;
    }

}

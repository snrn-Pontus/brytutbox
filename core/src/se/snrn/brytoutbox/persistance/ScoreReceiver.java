//package se.snrn.brytoutbox.persistance;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Net;
//import com.badlogic.gdx.net.HttpParametersUtils;
//import com.badlogic.gdx.utils.Json;
//import se.snrn.brytoutbox.Score;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class ScoreReceiver {
//    public Score result;
//
//    public ScoreReceiver() {
//
//    }
//
//    public Score getScore() {
//        Net.HttpRequest httpGet = new Net.HttpRequest(Net.HttpMethods.GET);
//        httpGet.setHeader("Accept", "application/json");
//
//        httpGet.setUrl("http://localhost:8080/get");
//
//        Map<String, String> parameters = new HashMap<String, String>();
//        parameters.put("id", "1");
//        httpGet.setContent(HttpParametersUtils.convertHttpParameters(parameters));
//
//        Gdx.net.sendHttpRequest(httpGet, new Net.HttpResponseListener() {
//
//
//            public void handleHttpResponse(Net.HttpResponse httpResponse) {
//                String status = httpResponse.getResultAsString();
//                System.out.println(status);
//                Json json = new Json();
//                Score score = json.fromJson(Score.class, status);
//                System.out.println(score);
//            }
//
//            public void failed(Throwable t) {
//                String status = "failed";
//                System.out.println(status);
//            }
//
//            @Override
//            public void cancelled() {
//                String status = "canceled";
//                System.out.println(status);
//            }
//        });
//
//
//
//        return result;
//    }
//
//}

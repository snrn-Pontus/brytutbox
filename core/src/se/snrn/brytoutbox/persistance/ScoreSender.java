//package se.snrn.brytoutbox.persistance;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Net;
//import com.badlogic.gdx.utils.Json;
//import com.badlogic.gdx.utils.JsonWriter;
//import se.snrn.brytoutbox.ScoreState;
//
//public class ScoreSender {
//
//    public ScoreSender() {
//
//    }
//
//    public void sendScore(ScoreState score) {
//
//
//        Json json = new Json();
//
//        score.addScore(100);
//
//        Net.HttpRequest httpPost = new Net.HttpRequest(Net.HttpMethods.POST);
//        httpPost.setHeader("Content-Type", "application/json");
//
//        httpPost.setUrl("http://localhost:8080/put");
//
//        json.setOutputType(JsonWriter.OutputType.json);
//        httpPost.setContent(json.toJson(score));
//
//        Gdx.net.sendHttpRequest (httpPost, new Net.HttpResponseListener() {
//            public void handleHttpResponse(Net.HttpResponse httpResponse) {
//                String status = httpResponse.getResultAsString();
//                System.out.println(status);
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
//    }
//}
